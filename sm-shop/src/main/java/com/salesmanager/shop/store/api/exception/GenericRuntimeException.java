package com.salesmanager.shop.store.api.exception;


public class GenericRuntimeException extends RuntimeException {

  /**
  * 
  */
  private static final long serialVersionUID = 1L;
  private String errorCode;
  private String errorMessage;

  public GenericRuntimeException(String errorCode, String errorMessage) {
    this.setErrorCode(errorCode);
    this.setErrorMessage(errorMessage);
  }

  public GenericRuntimeException(String errorMessage) {
    this.setErrorMessage(errorMessage);
  }

  public GenericRuntimeException(Throwable exception) {
    super(exception);
    this.setErrorCode(null);
    this.setErrorMessage(null);
  }

  public GenericRuntimeException(String errorMessage, Throwable exception) {
    super(exception);
    this.setErrorCode(null);
    this.setErrorMessage(errorMessage);
  }

  public GenericRuntimeException(String errorCode, String errorMessage, Throwable exception) {
    super(exception);
    this.setErrorCode(errorCode);
    this.setErrorMessage(errorMessage);
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
