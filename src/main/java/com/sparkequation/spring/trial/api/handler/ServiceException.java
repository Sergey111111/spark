package com.sparkequation.spring.trial.api.handler;

public class ServiceException extends RuntimeException {

    private final Integer code;

    public ServiceException(String message,Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
