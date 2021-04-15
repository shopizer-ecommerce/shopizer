package com.salesmanager.shop.mapper.catalog;

import org.jsoup.helper.Validate;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.product.definition.ReadableProductDefinition;

@Component
public class ReadableProductDefinitionMapper implements Mapper<Product, ReadableProductDefinition> {

	@Override
	public ReadableProductDefinition convert(Product source, MerchantStore store, Language language) {
		ReadableProductDefinition target = new ReadableProductDefinition();
		return this.merge(source, target, store, language);
	}

	@Override
	public ReadableProductDefinition merge(Product source, ReadableProductDefinition destination, MerchantStore store,
			Language language) {
		Validate.notNull(source, "Product cannot be null");
		Validate.notNull(destination, "Product destination cannot be null");
		
		if(language == null) {
			
		}
		
		
		return destination;
	}

}
