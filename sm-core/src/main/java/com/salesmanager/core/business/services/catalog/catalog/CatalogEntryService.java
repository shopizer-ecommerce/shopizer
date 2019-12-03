package com.salesmanager.core.business.services.catalog.catalog;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.catalog.catalog.CatalogEntry;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface CatalogEntryService extends SalesManagerEntityService<Long, CatalogEntry> {
	
	
	void add (CatalogEntry entry, Catalog catalog) throws ServiceException;
	
	void remove (CatalogEntry catalogEntry) throws ServiceException;
	
	Page<CatalogEntry> list(Catalog catalog, MerchantStore store, Language language, String name, int page, int count) throws ServiceException;

}
