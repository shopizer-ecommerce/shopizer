package com.salesmanager.web.entity.catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.salesmanager.web.entity.catalog.product.ReadableProduct;

public class ProductList implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int productCount;
	private List<ReadableProduct> products = new ArrayList<ReadableProduct>();
	public void setProducts(List<ReadableProduct> products) {
		this.products = products;
	}
	public List<ReadableProduct> getProducts() {
		return products;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}


}
