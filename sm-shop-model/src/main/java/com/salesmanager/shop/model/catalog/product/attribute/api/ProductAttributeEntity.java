package com.salesmanager.shop.model.catalog.product.attribute.api;

import java.io.Serializable;

import com.salesmanager.shop.model.catalog.product.attribute.ProductAttribute;

public class ProductAttributeEntity extends ProductAttribute implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int sortOrder;
	private boolean attributeDefault=false;
	private boolean attributeDisplayOnly = false;


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

	public boolean isAttributeDisplayOnly() {
		return attributeDisplayOnly;
	}
	public void setAttributeDisplayOnly(boolean attributeDisplayOnly) {
		this.attributeDisplayOnly = attributeDisplayOnly;
	}

}
