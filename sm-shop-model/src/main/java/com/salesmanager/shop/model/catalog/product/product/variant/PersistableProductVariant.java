package com.salesmanager.shop.model.catalog.product.product.variant;

import com.salesmanager.shop.model.catalog.product.product.PersistableProductInventory;

public class PersistableProductVariant extends ProductVariant {

	private static final long serialVersionUID = 1L;
	
	private Long variation;
	private Long variationValue;
	
	private String variationCode;
	private String variationValueCode;
	
	private PersistableProductInventory inventory;

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

	public String getVariationCode() {
		return variationCode;
	}

	public void setVariationCode(String variationCode) {
		this.variationCode = variationCode;
	}

	public String getVariationValueCode() {
		return variationValueCode;
	}

	public void setVariationValueCode(String variationValueCode) {
		this.variationValueCode = variationValueCode;
	}

	public PersistableProductInventory getInventory() {
		return inventory;
	}

	public void setInventory(PersistableProductInventory inventory) {
		this.inventory = inventory;
	}
	
	
	

}
