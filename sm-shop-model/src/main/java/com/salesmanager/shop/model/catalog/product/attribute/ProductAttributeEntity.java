package com.salesmanager.shop.model.catalog.product.attribute;

import java.io.Serializable;

public class ProductAttributeEntity extends ProductAttribute implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String code;

	private int sortOrder;
	private boolean attributeDefault=false;


	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setAttributeDefault(boolean attributeDefault) {
		this.attributeDefault = attributeDefault;
	}
	public boolean isAttributeDefault() {
		return attributeDefault;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
