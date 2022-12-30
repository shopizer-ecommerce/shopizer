package com.salesmanager.shop.model.catalog.product.type;

import java.util.List;

public class ReadableProductTypeFull extends ReadableProductType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProductTypeDescription> descriptions;

	public List<ProductTypeDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<ProductTypeDescription> descriptions) {
		this.descriptions = descriptions;
	}

}
