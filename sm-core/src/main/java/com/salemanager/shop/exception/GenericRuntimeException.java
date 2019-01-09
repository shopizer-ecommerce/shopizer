package com.salemanager.shop.exception;

import lombok.Getter;

@Getter
public class GenericRuntimeException extends RuntimeException{

    private String errorCode;
    private String errorMessage;

    public GenericRuntimeException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public GenericRuntimeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public GenericRuntimeException(Throwable exception) {
        super(exception);
        this.errorCode = null;
        this.errorMessage = null;
    }

    public GenericRuntimeException(String errorMessage, Throwable exception) {
        super(exception);
        this.errorCode = null;
        this.errorMessage = errorMessage;
    }

    public GenericRuntimeException(String errorCode, String errorMessage, Throwable exception) {
        super(exception);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
