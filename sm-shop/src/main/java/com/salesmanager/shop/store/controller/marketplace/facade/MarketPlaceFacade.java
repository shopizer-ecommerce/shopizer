package com.salesmanager.shop.store.controller.marketplace.facade;

import com.salesmanager.core.model.reference.language.Language;
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
	 * @param lang
	 * @return
	 * @throws Exception
	 */
	ReadableMarketPlace get(String store, Language lang) throws Exception;

}
