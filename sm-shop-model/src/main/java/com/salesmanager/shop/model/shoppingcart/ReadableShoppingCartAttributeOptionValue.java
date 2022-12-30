package com.salesmanager.shop.model.shoppingcart;

import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValue;

public class ReadableShoppingCartAttributeOptionValue extends ReadableProductOptionValue {

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
