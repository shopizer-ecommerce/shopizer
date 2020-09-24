package com.salesmanager.shop.model.catalog.catalog;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.category.ReadableCategoryName;
import com.salesmanager.shop.model.catalog.product.ReadableProductName;
import com.salesmanager.shop.model.store.ReadableMerchantStore;

public class ReadableCatalog extends CatalogEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creationDate;
	private ReadableMerchantStore store;
	
	
	//list of category (name, id, code) with
	private List<ReadableCategoryName> category = new ArrayList<ReadableCategoryName>();
	
	//list of products (name, id, code)
	private List<ReadableProductName> products = new ArrayList<ReadableProductName>();
	
	
	
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public ReadableMerchantStore getStore() {
		return store;
	}
	public void setStore(ReadableMerchantStore store) {
		this.store = store;
	}
	public List<ReadableCategoryName> getCategory() {
		return category;
	}
	public void setCategory(List<ReadableCategoryName> category) {
		this.category = category;
	}
	public List<ReadableProductName> getProducts() {
		return products;
	}
	public void setProducts(List<ReadableProductName> products) {
		this.products = products;
	}


}
