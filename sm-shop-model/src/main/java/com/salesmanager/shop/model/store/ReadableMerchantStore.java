package com.salesmanager.shop.model.store;

import java.io.Serializable;
import java.util.List;

import com.salesmanager.core.model.reference.language.Language;
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
	private ReadableMerchantStore parent;
<<<<<<< HEAD:sm-shop-model/src/main/java/com/salesmanager/shop/model/store/ReadableMerchantStore.java
	private List<Language> supportedLanguages;
=======
>>>>>>> origin/sb2.2:sm-shop-model/src/main/java/com/salesmanager/shop/model/shop/ReadableMerchantStore.java



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

  public ReadableMerchantStore getParent() {
    return parent;
  }

  public void setParent(ReadableMerchantStore parent) {
    this.parent = parent;
  }

<<<<<<< HEAD:sm-shop-model/src/main/java/com/salesmanager/shop/model/store/ReadableMerchantStore.java
public List<Language> getSupportedLanguages() {
	return supportedLanguages;
}

public void setSupportedLanguages(List<Language> supportedLanguages) {
	this.supportedLanguages = supportedLanguages;
}

=======
>>>>>>> origin/sb2.2:sm-shop-model/src/main/java/com/salesmanager/shop/model/shop/ReadableMerchantStore.java


}
