package com.salesmanager.shop.model.system;

import java.io.Serializable;

public class IntegrationModuleEntity implements Serializable {
	
	private String code;
	private boolean active;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
