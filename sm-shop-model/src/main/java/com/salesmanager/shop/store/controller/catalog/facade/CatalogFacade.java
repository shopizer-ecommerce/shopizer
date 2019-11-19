package com.salesmanager.shop.store.controller.catalog.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.model.catalog.catalog.PersistableCatalog;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalog;

public interface CatalogFacade {
	
	ReadableCatalog saveCatalog(PersistableCatalog catalog, MerchantStore store);
	
	void deleteCatalog(Long catalogId, MerchantStore store);
	
	ReadableCatalog getCatalog(Long catalogId, MerchantStore store);

}
