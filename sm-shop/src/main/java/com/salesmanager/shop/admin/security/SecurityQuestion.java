package com.salesmanager.shop.admin.security;

import java.io.Serializable;

public class SecurityQuestion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String label;
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}

}
