package com.salemanager.shop.exception;

public class ResourceNotFoundException extends ServiceRuntimeException {


    public ResourceNotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
