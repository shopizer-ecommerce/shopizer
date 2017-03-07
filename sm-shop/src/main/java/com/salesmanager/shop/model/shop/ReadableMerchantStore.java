package com.salesmanager.shop.model.shop;

import java.io.Serializable;
import java.util.List;

public class ReadableMerchantStore extends MerchantStoreEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> supportedLanguages;
	private String currentUserLanguage;

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

}
