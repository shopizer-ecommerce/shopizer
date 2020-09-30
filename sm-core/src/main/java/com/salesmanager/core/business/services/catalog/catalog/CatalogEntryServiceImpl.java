package com.salesmanager.core.business.services.catalog.catalog;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.catalog.CatalogEntryRepository;
import com.salesmanager.core.business.repositories.catalog.catalog.PageableCatalogEntryRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.catalog.catalog.CatalogCategoryEntry;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

@Service("catalogEntryService")
public class CatalogEntryServiceImpl extends SalesManagerEntityServiceImpl<Long, CatalogCategoryEntry> 
implements CatalogEntryService {
	
	@Autowired
	private PageableCatalogEntryRepository pageableCatalogEntryRepository;

	private CatalogEntryRepository catalogEntryRepository;
	
	@Inject
	public CatalogEntryServiceImpl(CatalogEntryRepository repository) {
		super(repository);
		this.catalogEntryRepository = repository;
	}

	@Override
	public void add(CatalogCategoryEntry entry, Catalog catalog) throws ServiceException {
		entry.setCatalog(catalog);
		catalogEntryRepository.save(entry);
	}


	@Override
	public Page<CatalogCategoryEntry> list(Catalog catalog, MerchantStore store, Language language, String name, int page,
			int count) throws ServiceException {
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableCatalogEntryRepository.listByCatalog(catalog.getId(), store.getId(), language.getId(), name, pageRequest);

	}

	@Override
	public void remove(CatalogCategoryEntry catalogEntry) throws ServiceException {
		catalogEntryRepository.delete(catalogEntry);
		
	}


}
