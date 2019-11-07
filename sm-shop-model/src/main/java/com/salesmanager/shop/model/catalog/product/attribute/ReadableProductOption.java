package com.salesmanager.shop.model.catalog.product.attribute;

import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionValueEntity;

public class ReadableProductOption extends ProductOption {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String lang;
	private List<ReadableProductOptionValueEntity> optionValues = new ArrayList<ReadableProductOptionValueEntity>();


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

	public List<ReadableProductOptionValueEntity> getOptionValues() {
		return optionValues;
	}

	public void setOptionValues(List<ReadableProductOptionValueEntity> optionValues) {
		this.optionValues = optionValues;
	}



}
