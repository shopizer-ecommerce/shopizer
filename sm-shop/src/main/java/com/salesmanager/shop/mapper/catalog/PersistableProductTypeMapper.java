package com.salesmanager.shop.mapper.catalog;

import org.apache.commons.lang3.Validate;
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
		ProductType type = new ProductType();
		return this.convert(source, type, store, language);
	}

	@Override
	public ProductType convert(PersistableProductType source, ProductType destination, MerchantStore store,
			Language language) {
		Validate.notNull(destination, "ReadableProductType cannot be null");
		destination.setId(source.getId());
		destination.setCode(source.getCode());
		destination.setAllowAddToCart(source.isAllowAddToCart());
		return destination;
	}

}
