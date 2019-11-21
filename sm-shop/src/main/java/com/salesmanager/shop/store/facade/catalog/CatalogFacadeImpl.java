package com.salesmanager.shop.store.facade.catalog;

import org.springframework.stereotype.Service;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.model.catalog.catalog.PersistableCatalog;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalog;
import com.salesmanager.shop.store.controller.catalog.facade.CatalogFacade;

@Service("catalogFacade")
public class CatalogFacadeImpl implements CatalogFacade {

	@Override
	public ReadableCatalog saveCatalog(PersistableCatalog catalog, MerchantStore store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCatalog(Long catalogId, MerchantStore store) {
		// TODO Auto-generated method stub

	}

	@Override
	public ReadableCatalog getCatalog(Long catalogId, MerchantStore store) {
		// TODO Auto-generated method stub
		return null;
	}

}
