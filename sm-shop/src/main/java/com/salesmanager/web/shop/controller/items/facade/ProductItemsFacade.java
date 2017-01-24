package com.salesmanager.web.shop.controller.items.facade;

import java.util.List;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.entity.catalog.product.ReadableProductList;

public interface ProductItemsFacade {
	
	/**
	 * List items attached to a Manufacturer
	 * @param store
	 * @param language
	 * @param code
	 * @return
	 */
	ReadableProductList listItemsByManufacturer(MerchantStore store, Language language, Long manufacturerId, int startCount, int maxCount) throws Exception;

	ReadableProductList listItemsByIds(MerchantStore store, Language language, List<Long> ids, int startCount, int maxCount) throws Exception;

	
}
