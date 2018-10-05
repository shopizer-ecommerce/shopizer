package com.salesmanager.shop.model.catalog.product.attribute;

import java.io.Serializable;

public class ReadableProductVariantValue implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long value;
	


	public Long getValue() {
		return value;
	}



	public void setValue(Long value) {
		this.value = value;
	}



}
