package com.salesmanager.shop.mapper.catalog;


import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;

@Component
public class ReadableProductInstanceMapper implements Mapper<ProductInstance, ReadableProductInstance> {

	@Override
	public ReadableProductInstance convert(ProductInstance source, MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadableProductInstance merge(ProductInstance source, ReadableProductInstance destination,
			MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

}
