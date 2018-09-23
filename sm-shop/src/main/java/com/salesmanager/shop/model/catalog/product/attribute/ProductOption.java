package com.salesmanager.shop.model.catalog.product.attribute;

import java.io.Serializable;

import com.salesmanager.shop.model.Entity;


public class ProductOption extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String type;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
