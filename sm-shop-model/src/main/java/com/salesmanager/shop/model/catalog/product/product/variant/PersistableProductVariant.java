package com.salesmanager.shop.model.catalog.product.product.variant;

public class PersistableProductVariant extends ProductVariant {

	private static final long serialVersionUID = 1L;
	
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
