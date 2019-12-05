package com.charles.spring.cloud.framework.exception;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 11:35 AM
 */
public class SpringStartException extends RuntimeException {

    public SpringStartException() {
    }

    public SpringStartException(String message) {
        super(message);
    }

    public SpringStartException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpringStartException(Throwable cause) {
        super(cause);
    }

    public SpringStartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
