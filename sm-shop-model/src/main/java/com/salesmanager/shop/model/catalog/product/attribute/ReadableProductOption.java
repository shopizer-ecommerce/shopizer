package com.salesmanager.shop.model.catalog.product.attribute;

import java.util.ArrayList;
import java.util.List;

public class ReadableProductOption extends ProductOption {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String lang;
	private List<ReadableProductOptionValue> optionValues = new ArrayList<ReadableProductOptionValue>();


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public List<ReadableProductOptionValue> getOptionValues() {
		return optionValues;
	}

	public void setOptionValues(List<ReadableProductOptionValue> optionValues) {
		this.optionValues = optionValues;
	}



}
