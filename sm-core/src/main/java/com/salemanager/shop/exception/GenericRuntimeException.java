package com.salemanager.shop.exception;

import lombok.Getter;

@Getter
public class GenericRuntimeException extends RuntimeException{

    private String errorCode;
    private String message;

    public GenericRuntimeException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public GenericRuntimeException(String message) {
        this.message = message;
    }

    public GenericRuntimeException(Throwable exception) {
        super(exception);
        this.errorCode = null;
        this.message = null;
    }

    public GenericRuntimeException(String message, Throwable exception) {
        super(exception);
        this.errorCode = null;
        this.message = message;
    }

    public GenericRuntimeException(String errorCode, String message, Throwable exception) {
        super(exception);
        this.errorCode = errorCode;
        this.message = message;
    }
}
