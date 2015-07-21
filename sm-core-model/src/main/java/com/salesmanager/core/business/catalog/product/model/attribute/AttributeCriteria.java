package com.salesmanager.core.business.catalog.product.model.attribute;

import java.io.Serializable;

public class AttributeCriteria implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String attributeCode;
	private String attributeValue;
	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}
	public String getAttributeCode() {
		return attributeCode;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	public String getAttributeValue() {
		return attributeValue;
	}

}
