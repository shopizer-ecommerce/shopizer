package com.salesmanager.shop.store.controller.marketplace.facade;

import com.salesmanager.shop.model.marketplace.ReadableMarketPlace;

/**
 * Builds market places objects for shop and REST api
 * @author c.samson
 *
 */
public interface MarketPlaceFacade {
	
	
	/**
	 * Get a MarketPlace from store code
	 * @param store
	 * @return
	 * @throws Exception
	 */
	ReadableMarketPlace get(String store) throws Exception;

}
