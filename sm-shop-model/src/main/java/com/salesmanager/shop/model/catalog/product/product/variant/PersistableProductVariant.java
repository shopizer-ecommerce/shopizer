package com.salesmanager.shop.model.catalog.product.product.variant;

public class PersistableProductVariant extends ProductVariant {

	private static final long serialVersionUID = 1L;
	
	private Long variation;
	private Long variationValue;

	public Long getVariation() {
		return variation;
	}

	public void setVariation(Long variation) {
		this.variation = variation;
	}

	public Long getVariationValue() {
		return variationValue;
	}

	public void setVariationValue(Long variationValue) {
		this.variationValue = variationValue;
	}
	

}
