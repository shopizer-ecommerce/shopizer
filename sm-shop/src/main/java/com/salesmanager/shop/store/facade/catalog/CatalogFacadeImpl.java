package com.salesmanager.shop.store.facade.catalog;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.catalog.CatalogService;
import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.PersistableCatalogMapper;
import com.salesmanager.shop.mapper.catalog.ReadableCatalogMapper;
import com.salesmanager.shop.model.catalog.catalog.PersistableCatalog;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalog;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.catalog.facade.CatalogFacade;

@Service("catalogFacade")
public class CatalogFacadeImpl implements CatalogFacade {
	
	
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private PersistableCatalogMapper persistableCatalogMapper;
	
	@Autowired
	private ReadableCatalogMapper readableCatalogMapper;


	@Override
	public ReadableCatalog saveCatalog(PersistableCatalog catalog, MerchantStore store, Language language) {
		Validate.notNull(catalog,"Catalog cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(language,"Language cannot be null");
		Catalog c = persistableCatalogMapper.convert(catalog, store, language);
		

		try {
			catalogService.saveOrUddate(c, store);
			
			c = catalogService.getByCode(c.getCode(), store);
			
			ReadableCatalog readable = readableCatalogMapper.convert(c, store, language);
			
			
			return readable;
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while saving catalog",e);
		}

	}

	@Override
	public void deleteCatalog(Long catalogId, MerchantStore store, Language language) {
		Validate.notNull(catalogId,"Catalog id cannot be null");
		Validate.isTrue(catalogId > 0, "Catalog id cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		
		Catalog c = catalogService.getById(catalogId);
		
		if(c == null) {
			throw new ResourceNotFoundException("Catalog with id [" + catalogId + "] not found");
		}
		
		if(c.getMerchantStore() != null && !c.getMerchantStore().getCode().equals(store.getCode())) {
			throw new ResourceNotFoundException("Catalog with id [" + catalogId + "] not found for merchant [" + store.getCode()+ "]");
		}
		
		try {
			catalogService.delete(c);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while deleting catalog id [" + catalogId + "]" ,e);
		}

	}

	@Override
	public ReadableCatalog getCatalog(String code, MerchantStore store, Language language) {
		Validate.notNull(code,"Catalog code cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(language,"Language cannot be null");
		
		Catalog c = catalogService.getByCode(code, store);
		
		if(c == null) {
			throw new ResourceNotFoundException("Catalog with code [" + code + "] not found");
		}
		

		
		return readableCatalogMapper.convert(c, store, language);

	}

	@Override
	public void updateCatalog(Long catalogId, PersistableCatalog catalog, MerchantStore store, Language language) {
		Validate.notNull(catalogId,"Catalog id cannot be null");
		Validate.isTrue(catalogId > 0, "Catalog id cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(language,"Language cannot be null");
		
		Catalog c = catalogService.getById(catalogId);
		
		if(c == null) {
			throw new ResourceNotFoundException("Catalog with id [" + catalogId + "] not found");
		}
		
		if(c.getMerchantStore() != null && !c.getMerchantStore().getCode().equals(store.getCode())) {
			throw new ResourceNotFoundException("Catalog with id [" + catalogId + "] not found for merchant [" + store.getCode()+ "]");
		}
		
		c.setDefaultCatalog(catalog.isDefaultCatalog());
		c.setVisible(catalog.isVisible());
		
		try {
			catalogService.saveOrUddate(c, store);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while saving catalog",e);
		}
	}

	@Override
	public ReadableCatalog getCatalog(Long id, MerchantStore store, Language language) {
		Validate.notNull(id,"Catalog id cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(language,"Language cannot be null");
		
		Catalog c = catalogService.getById(id, store);
		
		if(c == null) {
			throw new ResourceNotFoundException("Catalog with id [" + id + "] not found");
		}
		

		
		return readableCatalogMapper.convert(c, store, language);
	}

}
