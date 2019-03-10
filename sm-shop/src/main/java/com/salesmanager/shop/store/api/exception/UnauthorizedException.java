package com.salesmanager.shop.store.api.exception;

public class UnauthorizedException extends GenericRuntimeException {

  private final static String ERROR_CODE = "401";

  private static final long serialVersionUID = 1L;

  public UnauthorizedException() {
    super("Not authorized");
  }

  public UnauthorizedException(String errorCode, String message) {
    super(errorCode, message);
  }

  public UnauthorizedException(String message) {
    super(ERROR_CODE, message);
  }
}
