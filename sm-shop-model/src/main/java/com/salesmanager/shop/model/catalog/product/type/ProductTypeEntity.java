package com.salesmanager.shop.model.catalog.product.type;

import java.io.Serializable;

import com.salesmanager.shop.model.entity.Entity;

public class ProductTypeEntity extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String name;
	boolean allowAddToCart;

	public boolean isAllowAddToCart() {
		return allowAddToCart;
	}

	public void setAllowAddToCart(boolean allowAddToCart) {
		this.allowAddToCart = allowAddToCart;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
