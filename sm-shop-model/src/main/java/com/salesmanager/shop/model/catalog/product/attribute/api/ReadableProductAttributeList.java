package com.salesmanager.shop.model.catalog.product.attribute.api;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.entity.ReadableList;

public class ReadableProductAttributeList extends ReadableList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ReadableProductAttributeEntity> attributes = new ArrayList<ReadableProductAttributeEntity>();

	public List<ReadableProductAttributeEntity> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ReadableProductAttributeEntity> attributes) {
		this.attributes = attributes;
	}

}
