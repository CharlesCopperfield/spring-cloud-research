package com.charles.spring.cloud.framework.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    private String time;

    public static <T> ReturnMessage<T> success(T data) {
        return success(null, "成功", data, 200);
    }

    public static <T> ReturnMessage<T> success(String traceId, T data) {
        return success(traceId, "成功", data, 200);
    }

    public static <T> ReturnMessage<T> success(String traceId, String message, T data, Integer code) {
        return (ReturnMessage<T>) ReturnMessage.builder().traceId(traceId).succ(true).message(message)
                .data(data).code(code).time(DateUtils.getNow()).build();
    }

    public static <T> ReturnMessage<T> fail(String message, T data, Integer code, Throwable e) {
        return (ReturnMessage<T>) ReturnMessage.builder().succ(false).message(message)
                .data(data).code(code).time(DateUtils.getNow())
                .exception(e == null ? null : ExceptionUtils.getStackTrace(e)).build();
    }


    public static <T> ReturnMessage<T> fail(String message, Throwable e) {
        return fail(message, null, 500, e);
    }

    public static <T> ReturnMessage<T> fail(Throwable e) {
        return fail("失败", null, 500, e);
    }

    public static <T> ReturnMessage<T> fail(String message) {
        return fail(message, null, 500, null);
    }


}
