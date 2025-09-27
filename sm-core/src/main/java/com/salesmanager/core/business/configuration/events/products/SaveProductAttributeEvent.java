package com.salesmanager.core.business.configuration.events.products;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;

import lombok.Getter;
import lombok.ToString;
@Getter
@ToString(callSuper = true, exclude = "productAttribute")
public class SaveProductAttributeEvent extends ProductEvent {
	private ProductAttribute productAttribute;

	public SaveProductAttributeEvent(Object source, ProductAttribute productAttribute, Product product) {
		super(source, product);
		this.productAttribute=productAttribute;
	}
}
