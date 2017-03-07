package com.salesmanager.shop.model.order;

import java.io.Serializable;

import com.salesmanager.shop.model.Entity;

public class ReadableOrderProductAttribute extends Entity implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String attributeName;
	private String attributePrice;
	private String attributeValue;
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributePrice() {
		return attributePrice;
	}
	public void setAttributePrice(String attributePrice) {
		this.attributePrice = attributePrice;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

}
