package com.salesmanager.shop.model.catalog.product.product.instance;

import javax.validation.constraints.NotNull;

public class PersistableProductInstance extends ProductInstance {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long variant;
	
	private Long variantValue;

	public Long getVariant() {
		return variant;
	}

	public void setVariant(Long variant) {
		this.variant = variant;
	}

	public Long getVariantValue() {
		return variantValue;
	}

	public void setVariantValue(Long variantValue) {
		this.variantValue = variantValue;
	}
	

}
