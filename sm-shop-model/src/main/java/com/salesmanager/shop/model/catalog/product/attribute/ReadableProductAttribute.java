package com.salesmanager.shop.model.catalog.product.attribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReadableProductAttribute extends ProductAttributeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String lang;
	
	private List<ReadableProductAttributeValue> attributeValues = new ArrayList<ReadableProductAttributeValue>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public List<ReadableProductAttributeValue> getAttributeValues() {
		return attributeValues;
	}
	public void setAttributeValues(List<ReadableProductAttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}

}
