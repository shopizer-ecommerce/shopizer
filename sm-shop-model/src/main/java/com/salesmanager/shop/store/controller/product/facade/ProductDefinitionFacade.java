package com.salesmanager.shop.store.controller.product.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.product.definition.PersistableProductDefinition;
import com.salesmanager.shop.model.catalog.product.product.definition.ReadableProductDefinition;

public interface ProductDefinitionFacade {

	/**
	 * 
	 * @param store
	 * @param product
	 * @param language
	 * @return
	 */
	Long saveProductDefinition(MerchantStore store, PersistableProductDefinition product, Language language);

	/**
	 * 
	 * @param productId
	 * @param product
	 * @param merchant
	 * @param language
	 */
	void update(Long productId, PersistableProductDefinition product, MerchantStore merchant, Language language);

	/**
	 * 
	 * @param store
	 * @param id
	 * @param language
	 * @return
	 */
	ReadableProductDefinition getProduct(MerchantStore store, Long id, Language language);

	/**
	 * 
	 * @param store
	 * @param uniqueCode
	 * @param language
	 * @return
	 */
	ReadableProductDefinition getProductBySku(MerchantStore store, String uniqueCode, Language language);

}
