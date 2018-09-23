package com.salesmanager.shop.model.catalog.product.attribute;

import java.io.Serializable;

import com.salesmanager.shop.model.Entity;


public class ProductOptionValue extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private boolean defaultValue;
	private int sortOrder;
	private String description;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
