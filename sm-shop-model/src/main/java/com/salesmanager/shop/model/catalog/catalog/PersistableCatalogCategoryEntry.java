package com.salesmanager.shop.model.catalog.catalog;

public class PersistableCatalogCategoryEntry extends CatalogEntryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productCode;
	private String categoryCode;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

}
