package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReadableProductList implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalCount;
	private List<ReadableProduct> products = new ArrayList<ReadableProduct>();
	public void setProducts(List<ReadableProduct> products) {
		this.products = products;
	}
	public List<ReadableProduct> getProducts() {
		return products;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalCount() {
		return totalCount;
	}

}
