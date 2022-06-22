package com.salesmanager.shop.model.catalog.product.attribute;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionValue;

public class ReadableProductOption extends ProductPropertyOption {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String lang;
	private boolean variant;
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

	public boolean isVariant() {
		return variant;
	}

	public void setVariant(boolean variant) {
		this.variant = variant;
	}



}
