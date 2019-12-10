package com.salesmanager.shop.mapper.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalog;
import com.salesmanager.shop.model.store.ReadableMerchantStore;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.DateUtil;

@Component
public class ReadableCatalogMapper implements Mapper<Catalog, ReadableCatalog> {
	
	  @Autowired
	  private StoreFacade storeFacade;

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
		
		return destination;
		
	}

}
