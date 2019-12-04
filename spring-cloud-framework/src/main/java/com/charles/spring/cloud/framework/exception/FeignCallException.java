package com.charles.spring.cloud.framework.exception;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 11:35 AM
 */
public class FeignCallException extends RuntimeException {

    public FeignCallException() {
    }

    public FeignCallException(String message) {
        super(message);
    }

    public FeignCallException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeignCallException(Throwable cause) {
        super(cause);
    }

    public FeignCallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
