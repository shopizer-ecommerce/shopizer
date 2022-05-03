package com.salesmanager.shop.store.controller.product.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.product.instance.PersistableProductInstance;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.model.entity.ReadableEntityList;

public interface ProductInstanceFacade {
	
	ReadableProductInstance get(Long instanceId, Long productId, MerchantStore store, Language language);
	boolean exists(String sku, MerchantStore store, Long productId, Language language);
	Long create(PersistableProductInstance productInstance, Long productId, MerchantStore store, Language language);
	void update(Long instanceId, PersistableProductInstance instance, Long productId, MerchantStore store, Language language);
	void delete(Long productInstance, Long productId, MerchantStore store);
	ReadableEntityList<ReadableProductInstance> list(Long productId, MerchantStore store, Language language, int page, int count);
	

}
