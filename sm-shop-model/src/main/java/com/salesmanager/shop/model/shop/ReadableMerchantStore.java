package com.salesmanager.shop.model.shop;

import java.io.Serializable;

import com.salesmanager.shop.model.content.ReadableImage;
import com.salesmanager.shop.model.entity.ReadableAudit;
import com.salesmanager.shop.model.entity.ReadableAuditable;
import com.salesmanager.shop.model.references.ReadableAddress;

public class ReadableMerchantStore extends MerchantStoreEntity implements ReadableAuditable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String currentUserLanguage;
	private ReadableAddress address;
	private ReadableImage logo;
	private ReadableAudit audit;



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

	public void setReadableAudit(ReadableAudit audit) {
		this.audit = audit;
		
	}

	public ReadableAudit getReadableAudit() {
		return this.audit;
	}



}
