package com.salesmanager.shop.store.api.exception;

public class OperationNotAllowedException extends ServiceRuntimeException {
  
  private final static String ERROR_CODE = "304";

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public OperationNotAllowedException(String message) {
    super(ERROR_CODE, message);
  }
  

}
