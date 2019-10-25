package com.salesmanager.shop.store.controller.items.facade;

import java.util.List;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;

public interface ProductItemsFacade {
	
	/**
	 * List items attached to a Manufacturer
	 * @param store
	 * @param language
	 * @return
	 */
	ReadableProductList listItemsByManufacturer(MerchantStore store, Language language, Long manufacturerId, int startCount, int maxCount) throws Exception;

	/**
	 * List product items by id
	 * @param store
	 * @param language
	 * @param ids
	 * @param startCount
	 * @param maxCount
	 * @return
	 * @throws Exception
	 */
	ReadableProductList listItemsByIds(MerchantStore store, Language language, List<Long> ids, int startCount, int maxCount) throws Exception;

	
	/**
	 * List products created in a group, for instance FEATURED group
	 * @param group
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProductList listItemsByGroup(String group, MerchantStore store, Language language) throws Exception;

	/**
	 * Add product to a group
	 * @param product
	 * @param group
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProductList addItemToGroup(Product product, String group, MerchantStore store, Language language) throws Exception;
	
	/**
	 * Removes a product from a group
	 * @param product
	 * @param group
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProductList removeItemFromGroup(Product product, String group, MerchantStore store, Language language) throws Exception;
	
	

}
