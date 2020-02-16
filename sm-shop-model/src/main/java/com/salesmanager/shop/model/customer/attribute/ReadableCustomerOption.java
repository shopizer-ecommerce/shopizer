package com.salesmanager.shop.model.customer.attribute;

import java.io.Serializable;

public class ReadableCustomerOption extends CustomerOptionEntity
		implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerOptionDescription description;
	public void setDescription(CustomerOptionDescription description) {
		this.description = description;
	}
	public CustomerOptionDescription getDescription() {
		return description;
	}



}
