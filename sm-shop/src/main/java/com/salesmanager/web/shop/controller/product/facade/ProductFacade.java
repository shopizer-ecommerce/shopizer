package com.salesmanager.web.shop.controller.product.facade;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.entity.catalog.product.PersistableProduct;
import com.salesmanager.web.entity.catalog.product.ReadableProduct;

public interface ProductFacade {
	
	PersistableProduct saveProduct(MerchantStore store, PersistableProduct product, Language language) throws Exception;
	
	ReadableProduct getProduct(MerchantStore store, Long id, Language language) throws Exception;

}
