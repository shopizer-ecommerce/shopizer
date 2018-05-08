package com.salesmanager.admin.controller.exception;

public class AdminException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String field;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public AdminException(String message) {
		super(message);
	}
	
	public AdminException(Exception ex) {
		super(ex);
	}
	
	public AdminException(Exception ex, String code) {
		super(ex);
		this.code = code;
	}
	
	public AdminException(Exception ex, String code, String field) {
		super(ex);
		this.code = code;
		this.field = field;
	}
	

}
