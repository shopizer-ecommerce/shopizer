package com.salesmanager.shop.model.catalog.category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReadableCategory extends CategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CategoryDescription description;//one category based on language
	//private ReadableCategory parent;
	private int productCount;
	private List<ReadableCategory> children = new ArrayList<ReadableCategory>();
	
	
	public void setDescription(CategoryDescription description) {
		this.description = description;
	}
	public CategoryDescription getDescription() {
		return description;
	}

	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public List<ReadableCategory> getChildren() {
		return children;
	}
	public void setChildren(List<ReadableCategory> children) {
		this.children = children;
	}

}
