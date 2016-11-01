package com.salesmanager.shop.store.controller.product.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.ProductPriceEntity;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;

public interface ProductFacade {
	
	PersistableProduct saveProduct(MerchantStore store, PersistableProduct product, Language language) throws Exception;
	
	/**
	 * Get a Product by id and store
	 * @param store
	 * @param id
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProduct getProduct(MerchantStore store, Long id, Language language) throws Exception;
	
	/**
	 * Get a product by sku and store
	 * @param store
	 * @param sku
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProduct getProduct(MerchantStore store,String sku, Language language) throws Exception;
	
	/**
	 * Sets a new price to an existing product
	 * @param product
	 * @param price
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProduct updateProductPrice(ReadableProduct product, ProductPriceEntity price, Language language) throws Exception;

	/**
	 * Sets a new price to an existing product
	 * @param product
	 * @param quantity
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProduct updateProductQuantity(ReadableProduct product, int quantity, Language language) throws Exception;

}
