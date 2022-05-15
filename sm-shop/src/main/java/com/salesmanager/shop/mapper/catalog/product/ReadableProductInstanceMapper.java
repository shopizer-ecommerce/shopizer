package com.salesmanager.shop.mapper.catalog.product;


import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.mapper.catalog.ReadableProductVariationMapper;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.utils.DateUtil;

@Component
public class ReadableProductInstanceMapper implements Mapper<ProductInstance, ReadableProductInstance> {

	
	@Autowired
	private ReadableProductVariationMapper readableProductVariationMapper;

	@Override
	public ReadableProductInstance convert(ProductInstance source, MerchantStore store, Language language) {
		ReadableProductInstance readableProductInstance = new ReadableProductInstance();
		return this.merge(source, readableProductInstance, store, language);
	}

	@Override
	public ReadableProductInstance merge(ProductInstance source, ReadableProductInstance destination,
			MerchantStore store, Language language) {
		
		Validate.notNull(source, "Product instance cannot be null");
		Validate.notNull(source.getProduct(), "Product cannot be null");
		
		if(destination == null) {
			destination = new ReadableProductInstance();
		}
		
		destination.setSortOrder(source.getSortOrder() != null ? source.getSortOrder().intValue():0);
		destination.setAvailable(source.isAvailable());
		destination.setDateAvailable(DateUtil.formatDate(source.getDateAvailable()));
		destination.setId(source.getId());
		destination.setDefaultSelection(source.isDefaultSelection());
		destination.setProductId(source.getProduct().getId());
		destination.setSku(source.getSku());
		destination.setSortOrder(source.getSortOrder());
		destination.setCode(source.getCode());
		//destination.setStore(null);
		destination.setStore(store.getCode());
		destination.setVariant(readableProductVariationMapper.convert(source.getVariant(), store, language));
		destination.setVariantValue(readableProductVariationMapper.convert(source.getVariantValue(), store, language));
		

		return destination;
	}

}
