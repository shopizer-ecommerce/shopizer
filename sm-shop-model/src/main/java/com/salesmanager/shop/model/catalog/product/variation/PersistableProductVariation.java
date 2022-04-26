package com.salesmanager.shop.model.catalog.product.variation;

/**
 * A Variant 
 * @author carlsamson
 *
 */
public class PersistableProductVariation extends ProductVariationEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long option = null;
	private Long optionValue = null;
	public Long getOption() {
		return option;
	}
	public void setOption(Long option) {
		this.option = option;
	}
	public Long getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(Long optionValue) {
		this.optionValue = optionValue;
	}
	


}
