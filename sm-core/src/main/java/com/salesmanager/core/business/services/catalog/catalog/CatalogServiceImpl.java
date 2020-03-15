package com.salesmanager.core.business.services.catalog.catalog;

import javax.inject.Inject;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.catalog.CatalogRepository;
import com.salesmanager.core.business.repositories.catalog.catalog.PageableCatalogRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

@Service("catalogService")
public class CatalogServiceImpl 
extends SalesManagerEntityServiceImpl<Long, Catalog> 
implements CatalogService {
	
	
	private CatalogRepository catalogRepository;
	
	@Autowired
	private PageableCatalogRepository pageableCatalogRepository;

	@Inject
	public CatalogServiceImpl(CatalogRepository repository) {
		super(repository);
		this.catalogRepository = repository;
	}

	@Override
	public Catalog saveOrUddate(Catalog catalog, MerchantStore store) throws ServiceException {
		catalogRepository.save(catalog);
		return catalog;
	}

	@Override
	public Page<Catalog> getCatalogs(MerchantStore store, Language language, String name, int page, int count)
			throws ServiceException {
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableCatalogRepository.listByStore(store.getId(), name, pageRequest);
	}

	@Override
	public void delete(Catalog catalog) throws ServiceException {
		Validate.notNull(catalog,"Catalog must not be null");
		catalogRepository.delete(catalog);
	}

	@Override
	public Catalog getById(Long catalogId, MerchantStore store) {
		return catalogRepository.findById(catalogId, store.getId());
	}

	@Override
	public Catalog getByCode(String code, MerchantStore store) {
		return catalogRepository.findByCode(code, store.getId());
	}

	@Override
	public boolean existByCode(String code) {
		return catalogRepository.existsByCode(code);
	}
	
	

}
