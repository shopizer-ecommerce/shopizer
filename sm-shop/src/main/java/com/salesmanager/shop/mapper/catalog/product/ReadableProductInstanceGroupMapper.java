package com.salesmanager.shop.mapper.catalog.product;

import org.jsoup.helper.Validate;

import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.product.instanceGroup.ReadableProductInstanceGroup;

public class ReadableProductInstanceGroupMapper implements Mapper<ProductInstanceGroup, ReadableProductInstanceGroup> {

	@Override
	public ReadableProductInstanceGroup convert(ProductInstanceGroup source, MerchantStore store, Language language) {
		Validate.notNull(source, "ProductInstanceGroup cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		return this.merge(source, new ReadableProductInstanceGroup(), store, language);
	}

	@Override
	public ReadableProductInstanceGroup merge(ProductInstanceGroup source, ReadableProductInstanceGroup destination,
			MerchantStore store, Language language) {
		Validate.notNull(source, "ProductInstanceGroup cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		if(destination == null) {
			destination = new ReadableProductInstanceGroup();
		}
		
		
		//transform product instances
		
		//transform images
		
		
		return null;
	}

}
