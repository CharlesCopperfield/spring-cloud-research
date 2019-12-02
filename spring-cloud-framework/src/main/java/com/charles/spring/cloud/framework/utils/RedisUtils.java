package com.charles.spring.cloud.framework.utils;

import com.charles.spring.cloud.framework.exception.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 10:49 AM
 */
@Configuration
public class RedisUtils {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void setValueForJson(String redisKey, Object object) {
        try {
            String json = objectMapper.writeValueAsString(object);
            stringRedisTemplate.opsForValue().set(redisKey, json, 30, TimeUnit.MINUTES);
        } catch (JsonProcessingException e) {
            throw new JacksonException(e);
        }
    }

    public <T> List<T> getListForJson(String redisKey, Class<T> type) {
        String json = stringRedisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, type);
            List<T> result = (List<T>) objectMapper.readValue(json, javaType);
            return result;
        } catch (IOException e) {
            throw new JacksonException(e);
        }
    }


    public <T> T getObjectForJson(String redisKey, Class<T> type) {
        String json = stringRedisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            T value = objectMapper.readValue(redisKey, type);
            return value;
        } catch (IOException e) {
            throw new JacksonException(e);
        }
    }

}
