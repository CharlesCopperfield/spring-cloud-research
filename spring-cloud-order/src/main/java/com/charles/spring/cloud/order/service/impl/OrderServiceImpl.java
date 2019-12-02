package com.charles.spring.cloud.order.service.impl;

import com.charles.spring.cloud.framework.module.goods.bo.GoodsBO;
import com.charles.spring.cloud.framework.module.goods.bo.OrderGoodsRelBO;
import com.charles.spring.cloud.framework.module.goods.service.OrderGoodsRelService;
import com.charles.spring.cloud.framework.module.order.bo.OrderBO;
import com.charles.spring.cloud.framework.module.user.bo.UserBO;
import com.charles.spring.cloud.framework.utils.RedisUtils;
import com.charles.spring.cloud.order.feign.GoodsFeignService;
import com.charles.spring.cloud.order.feign.OrderGoodsRelFeignService;
import com.charles.spring.cloud.order.mapper.OrderMapper;
import com.charles.spring.cloud.framework.module.order.service.OrderService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 9:32 AM
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private GoodsFeignService goodsFeignService;

    @Autowired
    private OrderGoodsRelFeignService orderGoodsRelFeignService;


    @Override
    public List<OrderBO> queryAll() {
        String redisKey = "com.charles.spring.cloud.order.service.impl.OrderServiceImpl.queryAll";
        if (stringRedisTemplate.hasKey(redisKey)) {
            List<OrderBO> listForJson = redisUtils.getListForJson(redisKey, OrderBO.class);
            return listForJson;
        }
        List<OrderBO> orderBOS = mapperFacade.mapAsList(orderMapper.selectAll(), OrderBO.class);
        redisUtils.setValueForJson(redisKey, orderBOS);
        return orderBOS;
    }

    @Override
    public List<OrderBO> queryAllOrderBOWithGoodsBOs() {
        List<GoodsBO> goodsBOS = goodsFeignService.queryAll();
        List<OrderGoodsRelBO> orderGoodsRelBOS = orderGoodsRelFeignService.queryAll();
        List<OrderBO> orderBOS = queryAll();

        Map<Integer, GoodsBO> goodsId2GoodsBO = goodsBOS.stream()
                .collect(Collectors.toMap(GoodsBO::getId, GoodsBO -> GoodsBO));

        Map<Integer, List<GoodsBO>> orderId2GoodsBOs = new HashMap<>();
        orderGoodsRelBOS.forEach(o -> {
            Integer orderId = o.getOrderId();
            if (!orderId2GoodsBOs.containsKey(orderId)) {
                orderId2GoodsBOs.put(orderId, new ArrayList<>());
            }
            Optional.ofNullable(goodsId2GoodsBO.get(o.getGoodsId()))
                    .ifPresent(g -> {
                        orderId2GoodsBOs.get(orderId).add(g);
                    });
        });

        List<OrderBO> result = new ArrayList<>(orderBOS.size());
        orderBOS.forEach(o -> {
            Optional.ofNullable(orderId2GoodsBOs.get(o.getId()))
                    .ifPresent(g -> {
                        o.setGoods(g);
                        result.add(o);
                    });
        });

        return result;
    }


}
