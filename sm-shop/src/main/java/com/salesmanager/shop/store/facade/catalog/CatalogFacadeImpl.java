package com.salesmanager.shop.store.facade.catalog;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.catalog.CatalogEntryService;
import com.salesmanager.core.business.services.catalog.catalog.CatalogService;
import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.catalog.catalog.CatalogCategoryEntry;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.PersistableCatalogCategoryEntryMapper;
import com.salesmanager.shop.mapper.catalog.PersistableCatalogMapper;
import com.salesmanager.shop.mapper.catalog.ReadableCatalogCategoryEntryMapper;
import com.salesmanager.shop.mapper.catalog.ReadableCatalogMapper;
import com.salesmanager.shop.model.catalog.catalog.PersistableCatalog;
import com.salesmanager.shop.model.catalog.catalog.PersistableCatalogCategoryEntry;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalog;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalogCategoryEntry;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalogCategoryEntryList;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalogList;
import com.salesmanager.shop.store.api.exception.OperationNotAllowedException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.catalog.facade.CatalogFacade;

@Service("catalogFacade")
public class CatalogFacadeImpl implements CatalogFacade {
	
	
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private CatalogEntryService catalogEntryService;
	
	@Autowired
	private PersistableCatalogMapper persistableCatalogMapper;
	
	@Autowired
	private ReadableCatalogMapper readableCatalogMapper;
	
	@Autowired
	private PersistableCatalogCategoryEntryMapper persistableCatalogEntryMapper;
	
	@Autowired
	private ReadableCatalogCategoryEntryMapper readableCatalogEntryMapper;


	@Override
	public ReadableCatalog saveCatalog(PersistableCatalog catalog, MerchantStore store, Language language) {
		Validate.notNull(catalog,"Catalog cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(language,"Language cannot be null");
		Catalog c = persistableCatalogMapper.convert(catalog, store, language);
		

		try {
			
			boolean existByCode = uniqueCatalog(catalog.getCode(), store);
			
			if(existByCode) {
				throw new OperationNotAllowedException("Catalog [" + catalog.getCode() +"] already exists");
			}
			
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
		
		Catalog c = catalogService.getById(id, store);
		
		if(c == null) {
			throw new ResourceNotFoundException("Catalog with id [" + id + "] not found");
		}

		
		return readableCatalogMapper.convert(c, store, language);
	}

	@Override
	public Catalog getCatalog(String code, MerchantStore store) {
		Validate.notNull(code,"Catalog code cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");

		return catalogService.getByCode(code, store);
	}

	@Override
	public ReadableCatalogList listCatalogs(Optional<String> code, MerchantStore store, Language language, int page, int count) {
		Validate.notNull(store,"MerchantStore cannot be null");
		
		String catalogCode = null;
		if(code.isPresent()) {
			catalogCode = code.get();
		}
		
		ReadableCatalogList catalogList = new ReadableCatalogList();
		
		try {
			Page<Catalog> catalogs = catalogService.getCatalogs(store, language, catalogCode, page, count);
		
			if(catalogs.getSize() == 0) {
				return catalogList;
			}
			
			List<ReadableCatalog> readableList = catalogs.getContent().stream()
					.map(cat -> readableCatalogMapper.convert(cat, store, language))
					.collect(Collectors.toList());
			
			catalogList.setCatalogs(readableList);
			catalogList.setTotalPages(catalogs.getTotalPages());
			catalogList.setNumber(catalogs.getNumber());
			catalogList.setRecordsTotal(catalogs.getTotalElements());
		
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot get catalogs for merchant [" + store.getCode() + "]");
		}

		return catalogList;
	}

	@Override
	public ReadableCatalogCategoryEntryList listCatalogEntry(Optional<String> product, Long id, MerchantStore store, Language language, int page, int count) {
		Validate.notNull(store,"MerchantStore cannot be null");
		String productCode = null;
		if(product.isPresent()) {
			productCode = product.get();
		}
		
		Catalog catalog = catalogService.getById(id, store);
		
		if(catalog == null) {
			throw new ResourceNotFoundException("Catalog with id [" + id + "] not found for store ["+ store.getCode() +"]");
		}
		
		ReadableCatalogCategoryEntryList catalogList = new ReadableCatalogCategoryEntryList();
		
		try {
			Page<CatalogCategoryEntry> entry = catalogEntryService.list(catalog, store, language, productCode, page, count);
		
			if(entry.getSize() == 0) {
				return catalogList;
			}
			
			List<ReadableCatalogCategoryEntry> readableList = entry.getContent().stream()
					.map(cat -> readableCatalogEntryMapper.convert(cat, store, language))
					.collect(Collectors.toList());
			
			catalogList.setCatalogEntry(readableList);
			catalogList.setTotalPages(entry.getTotalPages());
			catalogList.setRecordsTotal(entry.getTotalElements());
			catalogList.setNumber(entry.getNumber());
		
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot get catalog entry for catalog [" + id + "] andr merchant [" + store.getCode() + "]");
		}

		return catalogList;
	}

	@Override
	public ReadableCatalogCategoryEntry getCatalogEntry(Long id, MerchantStore store, Language language) {
		CatalogCategoryEntry entry = catalogEntryService.getById(id);
		if(entry == null) {
			throw new ResourceNotFoundException("catalog entry [" + id + "] not found");
		}
		
		if(entry.getCatalog().getMerchantStore().getId().intValue() != store.getId().intValue()) {
			throw new ResourceNotFoundException("catalog entry [" + id + "] not found");
		}
		
		ReadableCatalogCategoryEntry readable = readableCatalogEntryMapper.convert(entry, store, language);
		return readable;
	}

	@Override
	public ReadableCatalogCategoryEntry addCatalogEntry(PersistableCatalogCategoryEntry entry, MerchantStore store, Language language) {
		
		Validate.notNull(entry,"PersistableCatalogEntry cannot be null");
		Validate.notNull(entry.getCatalog(),"CatalogEntry.catalog cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		
		Catalog catalog = catalogService.getByCode(entry.getCatalog(), store);
		
		if(catalog == null) {
			throw new ResourceNotFoundException("catalog [" + entry.getCatalog() + "] not found");
		}
		
		CatalogCategoryEntry catalogEntryModel = persistableCatalogEntryMapper.convert(entry, store, language);
		
		try {
			catalogEntryService.add(catalogEntryModel, catalog);
			return readableCatalogEntryMapper.convert(catalogEntryModel, store, language);
			
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while creating catalogEntry",e);
		}

	}

	@Override
	public void removeCatalogEntry(Long catalogId, Long catalogEntryId, MerchantStore store, Language language) {
		CatalogCategoryEntry entry = catalogEntryService.getById(catalogEntryId);
		if(entry == null) {
			throw new ResourceNotFoundException("catalog entry [" + catalogEntryId + "] not found");
		}
		
		if(entry.getCatalog().getId().longValue() != catalogId.longValue()) {
			throw new ResourceNotFoundException("catalog entry [" + catalogEntryId + "] not found");
		}
		
		if(entry.getCatalog().getMerchantStore().getId().intValue() != store.getId().intValue()) {
			throw new ResourceNotFoundException("catalog entry [" + catalogEntryId + "] not found");
		}
		
		try {
			catalogEntryService.delete(entry);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while deleting catalogEntry",e);
		}
		
	}

	@Override
	public boolean uniqueCatalog(String code, MerchantStore store) {
		return catalogService.existByCode(code, store);
	}

}
