package com.salesmanager.shop.mapper.catalog;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;

@Component
public class ReadableProductTypeMapper implements Mapper<ProductType, ReadableProductType> {

	@Override
	public ReadableProductType convert(ProductType source, MerchantStore store, Language language) {
		ReadableProductType type = new ReadableProductType();
		return this.convert(source, type, store, language);
	}

	@Override
	public ReadableProductType convert(ProductType source, ReadableProductType destination, MerchantStore store,
			Language language) {
		Validate.notNull(source, "ProductType cannot be null");
		Validate.notNull(destination, "ReadableProductType cannot be null");
		destination.setId(source.getId());
		destination.setCode(source.getCode());
		destination.setName(source.getCode());
		destination.setAllowAddToCart(source.isAllowAddToCart());
		return destination;
	}

}
