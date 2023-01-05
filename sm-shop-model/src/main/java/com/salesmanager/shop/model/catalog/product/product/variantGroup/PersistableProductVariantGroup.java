package com.salesmanager.shop.model.catalog.product.product.variantGroup;

import java.util.List;

public class PersistableProductVariantGroup extends ProductVariantGroup {

	private static final long serialVersionUID = 1L;
	
	List<Long> productVariants = null;

	public List<Long> getproductVariants() {
		return productVariants;
	}

	public void setproductVariants(List<Long> productVariants) {
		this.productVariants = productVariants;
	}

}
