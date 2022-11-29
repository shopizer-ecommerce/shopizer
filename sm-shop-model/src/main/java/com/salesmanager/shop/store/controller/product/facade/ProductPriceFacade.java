package com.salesmanager.shop.store.controller.product.facade;

import java.util.List;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableProductPrice;
import com.salesmanager.shop.model.catalog.product.ReadableProductPrice;


/**
 * Product price management api
 * @author carlsamson
 *
 */
public interface ProductPriceFacade {

	/**
	 * Calculate product price based on specific product options
	 * 
	 * @param id
	 * @param priceRequest
	 * @param store
	 * @param language
	 * @return
	 */
	/**
	 * ReadableProductPrice getProductPrice(Long id, ProductPriceRequest
	 * priceRequest, MerchantStore store, Language language);
	 **/

	/**
	 * Creates a product price
	 * @param price
	 * @param productId
	 * @param inventoryId
	 * @param store
	 * @return
	 */
	Long save(PersistableProductPrice price, MerchantStore store);

	/**
	 * Product price deletion
	 * @param priceId
	 * @param productId
	 * @param inventoryId
	 * @param store
	 */
	void delete(Long priceId, String sku, MerchantStore store);
	
	/**
	 * List product prices by product and inventory (product and variants)
	 * @param productId
	 * @param inventoryId
	 * @param store
	 * @return
	 */
	List<ReadableProductPrice> list(String sku, Long inventoryId, MerchantStore store, Language language);
	
	/**
	 * List product prices by product
	 * @param poductId
	 * @param store
	 * @return
	 */
	List<ReadableProductPrice> list(String sku, MerchantStore store, Language language);
	
	/**
	 * Get ProductPrice
	 * @param sku
	 * @param productPriceId
	 * @param store
	 * @param language
	 * @return
	 */
	ReadableProductPrice get(String sku, Long productPriceId, MerchantStore store, Language language);
}
