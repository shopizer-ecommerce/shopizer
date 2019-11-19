package com.salesmanager.shop.model.catalog.catalog;

import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;

public class ReadableCatalogEntry extends CetalogEntryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creationDate;
	private ReadableProduct product;
	private ReadableCategory category;
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public ReadableProduct getProduct() {
		return product;
	}
	public void setProduct(ReadableProduct product) {
		this.product = product;
	}
	public ReadableCategory getCategory() {
		return category;
	}
	public void setCategory(ReadableCategory category) {
		this.category = category;
	}

}
