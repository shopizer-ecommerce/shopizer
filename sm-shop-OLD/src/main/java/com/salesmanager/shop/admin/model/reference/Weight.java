package com.salesmanager.shop.admin.model.reference;

import java.io.Serializable;

public class Weight implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1006772612089740285L;
	private String code;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public Weight(String code, String name) {
		this.code = code;
		this.name = name;
	}

}
