package com.salesmanager.core.business.modules.integration;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;


public class IntegrationException extends ServiceException {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int ERROR_VALIDATION_SAVE = 100;
	public static final int TRANSACTION_EXCEPTION = 99;
	
	private List<String> errorFields;
	
	private int errorCode = 0;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public IntegrationException(Exception e) {
		super(e);
	}
	
	public IntegrationException(String message, Exception e) {
		super(message,e);
	}
	
	public IntegrationException(int code, String message) {
		
		super(message);
		this.errorCode = code;
	}
	
	public IntegrationException(int code) {
		
		this.errorCode = code;
	}

	public IntegrationException(String message) {
		super(message);
	}

	public void setErrorFields(List<String> errorFields) {
		this.errorFields = errorFields;
	}

	public List<String> getErrorFields() {
		return errorFields;
	}

}
