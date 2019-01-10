package com.salesmanager.admin.model.references;

import com.salesmanager.admin.model.common.AbstractModel;

public class Zone extends AbstractModel {
	
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
