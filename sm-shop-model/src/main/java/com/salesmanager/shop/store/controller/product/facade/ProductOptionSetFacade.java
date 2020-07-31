package com.salesmanager.shop.store.controller.product.facade;

import java.util.List;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.attribute.optionset.PersistableProductOptionSet;
import com.salesmanager.shop.model.catalog.product.attribute.optionset.ReadableProductOptionSet;

public interface ProductOptionSetFacade {
	
	
	ReadableProductOptionSet get(Long id, MerchantStore store, Language language);
	boolean exists(String code, MerchantStore store);
	List<ReadableProductOptionSet> list(MerchantStore store, Language language);
	void create(PersistableProductOptionSet optionSet, MerchantStore store, Language language);
	void update(Long id, PersistableProductOptionSet optionSet, MerchantStore store, Language language);
	void delete(Long id, MerchantStore store);
	
	
	

}
