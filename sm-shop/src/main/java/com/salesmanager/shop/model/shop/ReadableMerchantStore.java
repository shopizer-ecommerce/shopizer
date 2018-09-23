package com.salesmanager.shop.model.shop;

import java.io.Serializable;
import java.util.List;

import com.salesmanager.core.constants.MeasureUnit;
import com.salesmanager.shop.model.content.ReadableImage;
import com.salesmanager.shop.model.references.ReadableAddress;

public class ReadableMerchantStore extends MerchantStoreEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> supportedLanguages;
	private String currentUserLanguage;
	private ReadableAddress address;
	private ReadableImage logo;
	private MeasureUnit dimension;
	private MeasureUnit weight;;
	


	public List<String> getSupportedLanguages() {
		return supportedLanguages;
	}

	public void setSupportedLanguages(List<String> supportedLanguages) {
		this.supportedLanguages = supportedLanguages;
	}

	public String getCurrentUserLanguage() {
		return currentUserLanguage;
	}

	public void setCurrentUserLanguage(String currentUserLanguage) {
		this.currentUserLanguage = currentUserLanguage;
	}

	public ReadableAddress getAddress() {
		return address;
	}

	public void setAddress(ReadableAddress address) {
		this.address = address;
	}

	public ReadableImage getLogo() {
		return logo;
	}

	public void setLogo(ReadableImage logo) {
		this.logo = logo;
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



}
