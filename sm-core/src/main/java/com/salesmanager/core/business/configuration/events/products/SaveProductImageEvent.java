package com.salesmanager.core.business.configuration.events.products;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.image.ProductImage;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true, exclude = "productImage")
public class SaveProductImageEvent extends ProductEvent {
	private ProductImage productImage;
	public SaveProductImageEvent(Object source, ProductImage productImage, Product product) {
		super(source, product);
		this.productImage = productImage;
	}
}
