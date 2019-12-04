package com.charles.spring.cloud.user.service.impl;

import com.charles.spring.cloud.framework.module.goods.bo.GoodsBO;
import com.charles.spring.cloud.framework.module.goods.bo.OrderGoodsRelBO;
import com.charles.spring.cloud.framework.module.goods.service.OrderGoodsRelService;
import com.charles.spring.cloud.framework.module.order.bo.OrderBO;
import com.charles.spring.cloud.framework.module.user.service.UserService;
import com.charles.spring.cloud.framework.utils.RedisUtils;
import com.charles.spring.cloud.user.feign.OrderFeignService;
import com.charles.spring.cloud.user.mapper.UserMapper;
import com.charles.spring.cloud.framework.module.user.bo.UserBO;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private OrderFeignService orderFeignService;

    @Override
    public List<UserBO> queryAll() {
        String redisKey = "UserServiceImpl.queryAll";
        if (stringRedisTemplate.hasKey(redisKey)) {
            logger.info("从redis中读数据");
            List<UserBO> listForJson = redisUtils.getListForJson(redisKey, UserBO.class);
            return listForJson;
        }
        List<UserBO> userBOS = mapperFacade.mapAsList(userMapper.selectAll(), UserBO.class);
        redisUtils.setValueForJson(redisKey, userBOS);

        logger.info("从数据库读");

        return userBOS;
    }

    @Override
    public List<UserBO> queryAllUserBO() {

        List<OrderBO> orderBOS = orderFeignService.queryAllOrderBOWithGoodsBOs();

        Map<Integer, OrderBO> orderId2OrderBO = orderBOS.stream()
                .collect(Collectors.toMap(OrderBO::getId, OrderBO -> OrderBO));

        Map<Integer, List<OrderBO>> userId2OrderBOs = new HashMap<>();
        orderBOS.forEach(o -> {
            Integer userId = o.getUserId();
            if (!userId2OrderBOs.containsKey(userId)) {
                userId2OrderBOs.put(userId, new ArrayList<>());
            }
            Optional.ofNullable(orderId2OrderBO.get(o.getId()))
                    .ifPresent(g -> {
                        userId2OrderBOs.get(userId).add(g);
                    });
        });

        List<UserBO> userBOS = queryAll();
        List<UserBO> result = new ArrayList<>(userBOS.size());
        userBOS.forEach(userBO -> {
            Optional.ofNullable(userId2OrderBOs.get(userBO.getId()))
                    .ifPresent(o -> {
                        userBO.setOrders(o);
                        result.add(userBO);
                    });
        });
        return result;
    }
}
