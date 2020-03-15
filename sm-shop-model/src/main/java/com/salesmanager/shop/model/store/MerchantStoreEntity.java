package com.salesmanager.shop.model.store;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.salesmanager.core.constants.MeasureUnit;

public class MerchantStoreEntity implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	@NotNull
	private String code;
	@NotNull
	private String name;

	private String defaultLanguage;//code
	private String currency;//code
	private String inBusinessSince;
	@NotNull
	private String email;
	private String phone;
	private String template;
	
	private boolean useCache;
	private boolean currencyFormatNational;
	private boolean retailer;
	private MeasureUnit dimension;
	private MeasureUnit weight;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getInBusinessSince() {
		return inBusinessSince;
	}

	public void setInBusinessSince(String inBusinessSince) {
		this.inBusinessSince = inBusinessSince;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public boolean isCurrencyFormatNational() {
		return currencyFormatNational;
	}

	public void setCurrencyFormatNational(boolean currencyFormatNational) {
		this.currencyFormatNational = currencyFormatNational;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isUseCache() {
		return useCache;
	}

	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}

	public MeasureUnit getDimension() {
		return dimension;
	}

	public void setDimension(MeasureUnit dimension) {
		this.dimension = dimension;
	}

	public MeasureUnit getWeight() {
		return weight;
	}

	public void setWeight(MeasureUnit weight) {
		this.weight = weight;
	}

	public boolean isRetailer() {
		return retailer;
	}

	public void setRetailer(boolean retailer) {
		this.retailer = retailer;
	}


}
