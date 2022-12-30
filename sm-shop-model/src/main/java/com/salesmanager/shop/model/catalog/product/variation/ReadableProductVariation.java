package com.salesmanager.shop.model.catalog.product.variation;

import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOption;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValue;

public class ReadableProductVariation extends ProductVariationEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ReadableProductOption option = null;
	ReadableProductOptionValue optionValue = null;
	public ReadableProductOption getOption() {
		return option;
	}
	public void setOption(ReadableProductOption option) {
		this.option = option;
	}
	public ReadableProductOptionValue getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(ReadableProductOptionValue optionValue) {
		this.optionValue = optionValue;
	}

}
