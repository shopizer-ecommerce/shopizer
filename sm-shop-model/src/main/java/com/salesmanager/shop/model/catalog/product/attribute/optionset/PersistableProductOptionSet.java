package com.salesmanager.shop.model.catalog.product.attribute.optionset;

import java.util.List;

public class PersistableProductOptionSet extends ProductOptionSetEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Long> optionValues;
	private List<Long> productTypes;
	private Long option;
	
	public List<Long> getOptionValues() {
		return optionValues;
	}
	public void setOptionValues(List<Long> optionValues) {
		this.optionValues = optionValues;
	}
	public Long getOption() {
		return option;
	}
	public void setOption(Long option) {
		this.option = option;
	}
	public List<Long> getProductTypes() {
		return productTypes;
	}
	public void setProductTypes(List<Long> productTypes) {
		this.productTypes = productTypes;
	}

	
	

}
