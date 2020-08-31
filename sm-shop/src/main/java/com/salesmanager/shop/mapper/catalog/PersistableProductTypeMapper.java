package com.salesmanager.shop.mapper.catalog;

import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.type.PersistableProductType;


@Component
public class PersistableProductTypeMapper implements Mapper<PersistableProductType, ProductType> {

	@Override
	public ProductType convert(PersistableProductType source, MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductType convert(PersistableProductType source, ProductType destination, MerchantStore store,
			Language language) {
		// TODO Auto-generated method stub
		return null;
	}

}
