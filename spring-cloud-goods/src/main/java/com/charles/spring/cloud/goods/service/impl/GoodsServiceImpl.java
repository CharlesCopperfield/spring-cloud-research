package com.charles.spring.cloud.goods.service.impl;

import com.charles.spring.cloud.framework.module.goods.bo.GoodsBO;
import com.charles.spring.cloud.framework.module.goods.service.GoodsService;
import com.charles.spring.cloud.framework.utils.RedisUtils;
import com.charles.spring.cloud.goods.mapper.GoodsMapper;
import com.charles.spring.cloud.framework.module.user.bo.UserBO;
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
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<GoodsBO> queryAll() {
        String redisKey = "com.charles.spring.cloud.goods.service.impl.GoodsServiceImpl.queryAll";
        if (stringRedisTemplate.hasKey(redisKey)) {
            List<GoodsBO> listForJson = redisUtils.getListForJson(redisKey, GoodsBO.class);
            return listForJson;
        }
        List<GoodsBO> goodsBOS = mapperFacade.mapAsList(goodsMapper.selectAll(), GoodsBO.class);
        redisUtils.setValueForJson(redisKey, goodsBOS);
        return goodsBOS;
    }
}
