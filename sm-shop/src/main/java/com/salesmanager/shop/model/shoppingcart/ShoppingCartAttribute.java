package com.salesmanager.shop.model.shoppingcart;

import java.io.Serializable;

import com.salesmanager.shop.model.entity.ShopEntity;

public class ShoppingCartAttribute extends ShopEntity implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long optionId;
	private long optionValueId;
	private long attributeId;
	private String optionName;
	private String optionValue;
	public long getOptionId() {
		return optionId;
	}
	public void setOptionId(long optionId) {
		this.optionId = optionId;
	}
	public long getOptionValueId() {
		return optionValueId;
	}
	public void setOptionValueId(long optionValueId) {
		this.optionValueId = optionValueId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	public long getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

}
