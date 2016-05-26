package com.salesmanager.web.shop.controller.product.facade;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.entity.catalog.product.PersistableProduct;
import com.salesmanager.web.entity.catalog.product.ProductPriceEntity;
import com.salesmanager.web.entity.catalog.product.ReadableProduct;

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
