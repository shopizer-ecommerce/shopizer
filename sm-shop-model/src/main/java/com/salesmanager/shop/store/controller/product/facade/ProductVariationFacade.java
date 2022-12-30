package com.salesmanager.shop.store.controller.product.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.variation.PersistableProductVariation;
import com.salesmanager.shop.model.catalog.product.variation.ReadableProductVariation;
import com.salesmanager.shop.model.entity.ReadableEntityList;

public interface ProductVariationFacade {
	
	
	ReadableProductVariation get(Long variationId, MerchantStore store, Language language);
	boolean exists(String code, MerchantStore store);
	Long create(PersistableProductVariation optionSet, MerchantStore store, Language language);
	void update(Long variationId, PersistableProductVariation variation, MerchantStore store, Language language);
	void delete(Long variation, MerchantStore store);
	ReadableEntityList<ReadableProductVariation> list(MerchantStore store, Language language, int page, int count);
	
	
	

}
