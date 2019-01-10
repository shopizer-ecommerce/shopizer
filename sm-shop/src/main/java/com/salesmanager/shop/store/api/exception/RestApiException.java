package com.salesmanager.shop.store.api.exception;

import com.salemanager.shop.exception.GenericRuntimeException;

public class RestApiException extends GenericRuntimeException {
    public RestApiException(String errorCode, String message) {
        super(errorCode, message);
    }

    public RestApiException(String message) {
        super(message);
    }

    public RestApiException(Throwable exception) {
        super(exception);
    }

    public RestApiException(String message, Throwable exception) {
        super(message, exception);
    }

    public RestApiException(String errorCode, String message, Throwable exception) {
        super(errorCode, message, exception);
    }
}
