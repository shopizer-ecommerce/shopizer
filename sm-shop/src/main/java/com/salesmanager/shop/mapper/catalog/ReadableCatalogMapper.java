package com.salesmanager.shop.mapper.catalog;

import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalog;

@Component
public class ReadableCatalogMapper implements Mapper<Catalog, ReadableCatalog> {

	@Override
	public ReadableCatalog convert(Catalog source, MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadableCatalog convert(Catalog source, ReadableCatalog destination, MerchantStore store,
			Language language) {
		// TODO Auto-generated method stub
		return null;
	}

}
