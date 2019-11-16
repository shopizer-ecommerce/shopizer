package com.salesmanager.core.business.services.catalog.catalog;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.catalog.CatalogEntryRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.catalog.catalog.CatalogEntry;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

@Service("catalogEntryService")
public class CatalogEntryServiceImpl 
extends SalesManagerEntityServiceImpl<Long, CatalogEntry> 
implements CatalogEntryService {

	private CatalogEntryRepository catalogEntryRepository;
	
	@Inject
	public CatalogEntryServiceImpl(CatalogEntryRepository repository) {
		super(repository);
		this.catalogEntryRepository = repository;
	}

	@Override
	public void save(CatalogEntry entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(CatalogEntry entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(CatalogEntry entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(CatalogEntry entity) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public CatalogEntry getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatalogEntry> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public CatalogEntry add(CatalogEntry entry, Catalog catalog) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long entryId, Catalog catalog) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<CatalogEntry> list(Catalog catalog, MerchantStore store, Language language, String name, int page,
			int count) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
