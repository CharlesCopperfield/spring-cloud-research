package com.charles.spring.cloud.framework.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.List;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 9:48 AM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnMessage<T> {

    private Integer code;

    private Boolean succ;

    private T data;

    private String message;

    private String traceId;

    private String exception;

    public static <T> ReturnMessage<T> success(T data) {
        return success("惩罚", data, null);
    }

    public static <T> ReturnMessage<T> success(String message, T data, Integer code) {
        return (ReturnMessage<T>) ReturnMessage.builder().succ(true).message(message)
                .data(data).code(code).build();
    }

    public static <T> ReturnMessage<T> fail(String message, T data, Integer code, Throwable e) {
        return (ReturnMessage<T>) ReturnMessage.builder().succ(false).message(message)
                .data(data).exception(ExceptionUtils.getStackTrace(e)).code(code).build();
    }


    public static <T> ReturnMessage<T> fail(String message, Throwable e) {
        return fail(message, null, null, e);
    }

    public static <T> ReturnMessage<T> fail(Throwable e) {
        return fail("失败", null, null, e);
    }

    public static <T> ReturnMessage<T> fail(String message) {
        return fail(message, null, null, null);
    }


}
