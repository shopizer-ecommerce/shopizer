package com.salesmanager.shop.store.api.exception;

public class ResourceNotFoundException extends ServiceRuntimeException {

  private final static String ERROR_CODE = "404";
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }

    public ResourceNotFoundException(String message) {
        super(ERROR_CODE, message);
    }
}
