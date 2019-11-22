package com.salesmanager.shop.store.controller.catalog.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.catalog.PersistableCatalog;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalog;

public interface CatalogFacade {
	
	ReadableCatalog saveCatalog(PersistableCatalog catalog, MerchantStore store, Language language);
	
	void updateCatalog(Long catalogId, PersistableCatalog catalog, MerchantStore store, Language language);
	
	void deleteCatalog(Long catalogId, MerchantStore store, Language language);
	
	ReadableCatalog getCatalog(String code, MerchantStore store, Language language);
	
	ReadableCatalog getCatalog(Long id, MerchantStore store, Language language);

}
