package com.salesmanager.shop.model.references;

import com.salesmanager.shop.model.Entity;

public class ZoneEntity extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String countryCode;
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
