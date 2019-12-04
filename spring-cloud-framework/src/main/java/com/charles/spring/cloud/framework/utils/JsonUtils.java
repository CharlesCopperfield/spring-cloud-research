package com.charles.spring.cloud.framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

/**
 * @author: st-wgsf-zengs
 * @time: 12/4/2019 1:00 PM
 */
public abstract class JsonUtils {

    public static String getObjToString(Object args) {
        if (null == args) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String argsString = objectMapper.writeValueAsString(args);
            return argsString;
        } catch (JsonProcessingException e) {
        }

        if (args instanceof Object[]) {
            return Arrays.toString((Object[]) args);
        }
        return args.toString();
    }
}
