package com.salesmanager.shop.mapper.catalog;

import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.catalog.PersistableCatalog;

@Component
public class PersistableCatalogMapper implements Mapper<PersistableCatalog, Catalog> {

	@Override
	public Catalog convert(PersistableCatalog source, MerchantStore store, Language language) {
		Catalog c = new Catalog();
		return this.convert(source, c, store, language);
	}

	@Override
	public Catalog convert(PersistableCatalog source, Catalog destination, MerchantStore store, Language language) {
		
		
		destination.setCode(source.getCode());
		destination.setDefaultCatalog(source.isDefaultCatalog());
		destination.setId(source.getId());
		destination.setMerchantStore(store);
		destination.setVisible(source.isVisible());
		
		return destination;
	}

}
