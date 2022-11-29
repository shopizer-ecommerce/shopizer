package com.salesmanager.shop.model.catalog.product.product.variantGroup;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.product.variant.ReadableProductVariant;

public class ReadableProductVariantGroup extends ProductVariantGroup {

	private static final long serialVersionUID = 1L;
	
	List<ReadableImage> images = new ArrayList<ReadableImage>();
	
	private List<ReadableProductVariant> productVariants = new ArrayList<ReadableProductVariant>();
	public List<ReadableProductVariant> getproductVariants() {
		return productVariants;
	}
	public void setproductVariants(List<ReadableProductVariant> productVariants) {
		this.productVariants = productVariants;
	}
	public List<ReadableImage> getImages() {
		return images;
	}
	public void setImages(List<ReadableImage> images) {
		this.images = images;
	}

}
