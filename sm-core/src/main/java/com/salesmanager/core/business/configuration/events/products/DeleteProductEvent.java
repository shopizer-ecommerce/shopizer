package com.salesmanager.core.business.configuration.events.products;

import com.salesmanager.core.model.catalog.product.Product;

public class DeleteProductEvent extends ProductEvent {

	private static final long serialVersionUID = 1L;

	public DeleteProductEvent(Object source, Product product) {
		super(source, product);
	}

}
