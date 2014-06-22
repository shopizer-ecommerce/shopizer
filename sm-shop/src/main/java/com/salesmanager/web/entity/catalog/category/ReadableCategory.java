package com.salesmanager.web.entity.catalog.category;

import java.io.Serializable;

public class ReadableCategory extends CategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CategoryDescription description;//one category based on language
	private CategoryEntity parent;
	private int productCount;
	public void setDescription(CategoryDescription description) {
		this.description = description;
	}
	public CategoryDescription getDescription() {
		return description;
	}
	public void setParent(CategoryEntity parent) {
		this.parent = parent;
	}
	public CategoryEntity getParent() {
		return parent;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

}
