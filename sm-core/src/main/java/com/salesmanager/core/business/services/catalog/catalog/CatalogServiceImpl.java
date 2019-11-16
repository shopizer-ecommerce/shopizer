package com.salesmanager.core.business.services.catalog.catalog;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.catalog.CatalogRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

@Service("catalogService")
public class CatalogServiceImpl 
extends SalesManagerEntityServiceImpl<Long, Catalog> 
implements CatalogService {
	
	
	private CatalogRepository catalogEntryRepository;

	@Inject
	public CatalogServiceImpl(CatalogRepository repository) {
		super(repository);
		this.catalogEntryRepository = repository;
	}

	@Override
	public Catalog create(MerchantStore store, String code) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Catalog> getCatalogs(MerchantStore store, Language language, String name, int page, int count)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Catalog catalog) throws ServiceException {
		// TODO Auto-generated method stub

	}

}
