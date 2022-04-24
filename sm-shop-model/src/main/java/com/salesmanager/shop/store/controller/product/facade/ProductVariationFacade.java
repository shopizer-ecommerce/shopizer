package com.salesmanager.shop.store.controller.product.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.variation.PersistableProductVariation;
import com.salesmanager.shop.model.catalog.product.variation.ReadableProductVariation;
import com.salesmanager.shop.model.entity.ReadableEntityList;

public interface ProductVariationFacade {
	
	
	ReadableProductVariation get(Long productId, Long variationId, MerchantStore store, Language language);
	boolean exists(Long productId, String code, MerchantStore store);
	void create(Long productId, PersistableProductVariation optionSet, MerchantStore store, Language language);
	void update(Long productId, Long variationId, PersistableProductVariation variation, MerchantStore store, Language language);
	void delete(Long productId, Long variation, MerchantStore store);
	ReadableEntityList<ReadableProductVariation> list(Long productId, MerchantStore store, Language language, int page, int count);
	
	
	

}
