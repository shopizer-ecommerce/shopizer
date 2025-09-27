package com.salesmanager.core.business.configuration.events.products;

import org.springframework.context.ApplicationEvent;

import com.salesmanager.core.model.catalog.product.Product;

import lombok.Getter;

@Getter
public abstract class ProductEvent extends ApplicationEvent {
	private Product product;
	
	public ProductEvent(Object source, Product product) {
		super(source);
		this.product = product;
	}
}
