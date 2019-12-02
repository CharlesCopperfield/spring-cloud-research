package com.charles.spring.cloud.goods.service.impl;

import com.charles.spring.cloud.framework.module.goods.bo.OrderGoodsRelBO;
import com.charles.spring.cloud.framework.module.goods.service.OrderGoodsRelService;
import com.charles.spring.cloud.framework.module.user.bo.UserBO;
import com.charles.spring.cloud.framework.utils.RedisUtils;
import com.charles.spring.cloud.goods.mapper.OrderGoodsRelMapper;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 9:32 AM
 */
@Service
public class OrderGoodsRelServiceImpl implements OrderGoodsRelService {

    @Autowired
    private OrderGoodsRelMapper orderGoodsRelMapper;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<OrderGoodsRelBO> queryAll() {
        String redisKey = "com.charles.spring.cloud.goods.service.impl.OrderGoodsRelServiceImpl.queryAll";
        if (stringRedisTemplate.hasKey(redisKey)) {
            List<OrderGoodsRelBO> listForJson = redisUtils.getListForJson(redisKey, OrderGoodsRelBO.class);
            return listForJson;
        }
        List<OrderGoodsRelBO> orderGoodsRelBOS = mapperFacade.mapAsList(orderGoodsRelMapper.selectAll(), OrderGoodsRelBO.class);
        redisUtils.setValueForJson(redisKey, orderGoodsRelBOS);
        return orderGoodsRelBOS;
    }
}
