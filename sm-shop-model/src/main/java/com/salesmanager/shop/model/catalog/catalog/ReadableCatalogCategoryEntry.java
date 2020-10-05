package com.salesmanager.shop.model.catalog.catalog;

import com.salesmanager.shop.model.catalog.category.ReadableCategory;

public class ReadableCatalogCategoryEntry extends CatalogEntryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creationDate;
	//private ReadableProduct product;
	private ReadableCategory category;
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
/*	public ReadableProduct getProduct() {
		return product;
	}
	public void setProduct(ReadableProduct product) {
		this.product = product;
	}*/
	public ReadableCategory getCategory() {
		return category;
	}
	public void setCategory(ReadableCategory category) {
		this.category = category;
	}

}
