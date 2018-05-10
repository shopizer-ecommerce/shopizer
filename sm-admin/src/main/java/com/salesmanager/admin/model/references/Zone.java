package com.salesmanager.admin.model.references;

import com.salesmanager.admin.model.AbstractModel;

public class Zone extends AbstractModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String countryCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
