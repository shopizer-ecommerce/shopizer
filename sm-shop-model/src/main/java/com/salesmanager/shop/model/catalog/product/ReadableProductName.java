package com.salesmanager.shop.model.catalog.product;

import com.salesmanager.shop.model.catalog.product.product.ProductEntity;

public class ReadableProductName extends ProductEntity {

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
