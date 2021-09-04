package com.salesmanager.shop.model.customer;


import com.salesmanager.shop.model.customer.address.Address;

public class BillingEntity extends Address {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;

	private String countryName;

	private String provinceName;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
