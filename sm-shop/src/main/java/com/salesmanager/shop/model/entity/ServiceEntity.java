package com.salesmanager.shop.model.entity;

public abstract class ServiceEntity {
	
	private int status = 0;
	private String message = null;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
