package com.salesmanager.shop.model.catalog.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.attribute.ProductAttribute;

public class ProductPriceRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProductAttribute> options = new ArrayList<ProductAttribute>();
	private String sku;//product instance sku

	public List<ProductAttribute> getOptions() {
		return options;
	}

	public void setOptions(List<ProductAttribute> options) {
		this.options = options;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}
