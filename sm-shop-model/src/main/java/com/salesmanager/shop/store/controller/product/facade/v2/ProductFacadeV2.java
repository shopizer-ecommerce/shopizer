package com.salesmanager.shop.store.controller.product.facade.v2;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.product.instance.PersistableProductInstance;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.model.entity.ReadableEntityList;

public interface ProductFacadeV2 {
	
	Long saveOrUpdate(PersistableProductInstance productInstance, MerchantStore store, Language language);
	
	ReadableProductInstance get(Long id, MerchantStore store, Language language);
	
	ReadableEntityList<ReadableProductInstance> listByProduct(Long productId, MerchantStore store, Language language);
	
	void update(Long id, PersistableProductInstance instance, MerchantStore store, Language language);
	
	void delete(Long id, MerchantStore store);

}
