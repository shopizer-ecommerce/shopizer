package com.salesmanager.core.business.configuration.events.products;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;

public class SaveProductInstanceEvent extends ProductEvent {
	
	private static final long serialVersionUID = 1L;
	private ProductInstance instance;

	public SaveProductInstanceEvent(Object source, ProductInstance instance, Product product) {
		super(source, product);
		this.instance = instance;
	}

	public ProductInstance getInstance() {
		return instance;
	}


}
