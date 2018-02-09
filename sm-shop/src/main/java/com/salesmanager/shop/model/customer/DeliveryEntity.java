package com.salesmanager.shop.model.customer;

import java.io.Serializable;

import javax.persistence.Transient;


public class DeliveryEntity extends Address implements Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private String countryName;
	
	@Transient
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

    
}
