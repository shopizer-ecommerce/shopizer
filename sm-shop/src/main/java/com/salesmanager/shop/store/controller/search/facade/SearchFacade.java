package com.salesmanager.shop.store.controller.search.facade;

import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * Different services for searching and indexing data
 * @author c.samson
 *
 */
public interface SearchFacade {
	

	
	public void indexAllData(MerchantStore store) throws Exception;

}
