package com.salesmanager.shop.model.catalog.product.type;

public class ReadableProductType extends ProductTypeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductTypeDescription description;

	public ProductTypeDescription getDescription() {
		return description;
	}

	public void setDescription(ProductTypeDescription description) {
		this.description = description;
	}

}
