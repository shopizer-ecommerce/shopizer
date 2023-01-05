package com.salesmanager.shop.store.controller.product.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.product.variant.PersistableProductVariant;
import com.salesmanager.shop.model.catalog.product.product.variant.ReadableProductVariant;
import com.salesmanager.shop.model.entity.ReadableEntityList;

public interface ProductVariantFacade {
	
	ReadableProductVariant get(Long instanceId, Long productId, MerchantStore store, Language language);
	boolean exists(String sku, MerchantStore store, Long productId, Language language);
	Long create(PersistableProductVariant productVariant, Long productId, MerchantStore store, Language language);
	void update(Long instanceId, PersistableProductVariant instance, Long productId, MerchantStore store, Language language);
	void delete(Long productVariant, Long productId, MerchantStore store);
	ReadableEntityList<ReadableProductVariant> list(Long productId, MerchantStore store, Language language, int page, int count);
	

}
