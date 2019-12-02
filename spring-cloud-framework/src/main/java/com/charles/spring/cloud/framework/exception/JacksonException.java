package com.charles.spring.cloud.framework.exception;

/**
 * @author: st-wgsf-zengs
 * @time: 11/10/2019 11:35 AM
 */
public class JacksonException extends RuntimeException {

    public JacksonException() {
    }

    public JacksonException(String message) {
        super(message);
    }

    public JacksonException(String message, Throwable cause) {
        super(message, cause);
    }

    public JacksonException(Throwable cause) {
        super(cause);
    }

    public JacksonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
