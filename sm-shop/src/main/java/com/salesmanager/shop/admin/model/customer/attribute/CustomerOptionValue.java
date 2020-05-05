package com.salesmanager.shop.admin.model.customer.attribute;

import java.io.Serializable;

import com.salesmanager.shop.model.entity.ShopEntity;



public class CustomerOptionValue extends ShopEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
