package com.salesmanager.core.business.configuration.events.products;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true, exclude = "variant")
public class DeleteProductVariantEvent extends ProductEvent {
	private ProductVariant variant;

	public DeleteProductVariantEvent(Object source, ProductVariant variant, Product product) {
		super(source, product);
		this.variant = variant;
	}
}
