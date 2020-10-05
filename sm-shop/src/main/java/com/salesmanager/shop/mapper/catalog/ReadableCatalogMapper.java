package com.salesmanager.shop.mapper.catalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.catalog.catalog.CatalogCategoryEntry;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalog;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalogCategoryEntry;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.store.ReadableMerchantStore;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.DateUtil;

@Component
public class ReadableCatalogMapper implements Mapper<Catalog, ReadableCatalog> {
	
	@Autowired
	private StoreFacade storeFacade;
	

	@Autowired
	private ReadableCategoryMapper readableCategoryMapper;

	@Override
	public ReadableCatalog convert(Catalog source, MerchantStore store, Language language) {
		ReadableCatalog destination = new ReadableCatalog();
		return convert(source, destination, store, language);
	}

	@Override
	public ReadableCatalog convert(Catalog source, ReadableCatalog destination, MerchantStore store,
			Language language) {
		if(destination == null) {
			destination = new ReadableCatalog();
		}
		
		if(source.getId()!=null && source.getId().longValue() >0) {
			destination.setId(source.getId());
		}
		
		destination.setCode(source.getCode());
		destination.setDefaultCatalog(source.isDefaultCatalog());
		destination.setVisible(source.isVisible());
		
		if(source.getMerchantStore() != null) {
			ReadableMerchantStore st = storeFacade.getByCode(source.getMerchantStore().getCode(), language);
			destination.setStore(st);
		}
		
		destination.setDefaultCatalog(source.isDefaultCatalog());
		
		if(source.getAuditSection()!=null) {
			destination.setCreationDate(DateUtil.formatDate(source.getAuditSection().getDateCreated()));
		}
		
		if(!CollectionUtils.isEmpty(source.getEntry())) {
			
			//hierarchy temp object
			Map<Long, ReadableCategory> hierarchy = new HashMap<Long, ReadableCategory>();
			Map<Long, ReadableCategory> processed = new HashMap<Long, ReadableCategory>();
			
			source.getEntry().stream().forEach(entry -> {
				processCategory(entry.getCategory(), store, language, hierarchy, processed);
			});
			
			destination.setCategory(hierarchy.values().stream().collect(Collectors.toList()));
		}
		
		return destination;
		
	}
	
	/**
	 * B
	 * 	1
	 * 	  D
	 * 	2
	 * C
	 * 	1
	 * 	4
	 * A
	 * @param parent
	 * @param c
	 * @param store
	 * @param language
	 * @param hierarchy
	 */
	
	private void processCategory(Category c, MerchantStore store, Language language, Map<Long, ReadableCategory> hierarchy, Map<Long, ReadableCategory> processed ) {
		
		//build category hierarchy
		
		ReadableCategory rc = null;
		ReadableCategory rp = null;
		
		if(! CollectionUtils.isEmpty(c.getCategories())) {
			c.getCategories().stream().forEach(element -> {
				this.processCategory(element, store, language, hierarchy, processed);
			});
		}

		if(c.getParent() != null) {
			rp = hierarchy.get(c.getParent().getId());
			if(rp == null) {
				rp = this.toReadableCategory(c.getParent(), store, language, processed);
				hierarchy.put(c.getParent().getId(), rp);
			}
		}

		rc =  this.toReadableCategory(c, store, language, processed);
		if(rp != null) {
			rp.getChildren().add(rc);
		} else {
			hierarchy.put(c.getId(), rc);
		}

	}
	
	private ReadableCategory toReadableCategory (Category c, MerchantStore store, Language lang, Map<Long, ReadableCategory> processed) {
		if(processed.get(c.getId()) != null) {
			return processed.get(c.getId());
		}
		ReadableCategory readable =  readableCategoryMapper.convert(c, store, lang);
		processed.put(readable.getId(), readable);
		return readable;
	}

}
