package com.salesmanager.core.business.configuration.events.products;

import com.salesmanager.core.model.catalog.product.Product;

public class SaveProductEvent extends ProductEvent {
	
	public SaveProductEvent(Object source, Product product) {
		super(source, product);
	}
}
