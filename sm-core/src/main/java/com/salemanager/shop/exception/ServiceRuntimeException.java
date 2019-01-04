package com.salemanager.shop.exception;

import lombok.Getter;

public class ServiceRuntimeException extends GenericRuntimeException {

    public ServiceRuntimeException(String errorCode, String message) {
        super(errorCode, message);
    }

    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(Throwable exception) {
        super(exception);
    }

    public ServiceRuntimeException(String message, Throwable exception) {
        super(message, exception);
    }

    public ServiceRuntimeException(String errorCode, String message, Throwable exception) {
        super(errorCode, message, exception);
    }

}
