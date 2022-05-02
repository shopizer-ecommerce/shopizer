package com.salesmanager.shop.model.catalog.product.product.instance;

import com.salesmanager.shop.model.catalog.product.Product;

public class ProductInstance extends Product {

	private static final long serialVersionUID = 1L;
	private String store;
	private Long productId;
	private String sku;
	
	private boolean defaultSelection;
	
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public boolean isDefaultSelection() {
		return defaultSelection;
	}
	public void setDefaultSelection(boolean defaultSelection) {
		this.defaultSelection = defaultSelection;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	

}
