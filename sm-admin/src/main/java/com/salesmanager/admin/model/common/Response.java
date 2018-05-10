package com.salesmanager.admin.model.common;

import java.io.Serializable;

public abstract class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status;
	private String message;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
