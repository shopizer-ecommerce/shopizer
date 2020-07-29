package com.salesmanager.shop.mapper.catalog;

import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.attribute.ProductOptionSet;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.attribute.optionset.PersistableProductOptionSet;

@Component
public class PersistableProductOptionSetMapper implements Mapper<PersistableProductOptionSet, ProductOptionSet> {

	@Override
	public ProductOptionSet convert(PersistableProductOptionSet source, MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductOptionSet convert(PersistableProductOptionSet source, ProductOptionSet destination,
			MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

}
