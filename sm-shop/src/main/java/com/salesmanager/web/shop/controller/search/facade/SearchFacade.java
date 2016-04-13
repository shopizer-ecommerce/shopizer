package com.salesmanager.web.shop.controller.search.facade;

import com.salesmanager.core.business.merchant.model.MerchantStore;

/**
 * Different services for searching and indexing data
 * @author c.samson
 *
 */
public interface SearchFacade {
	

	
	public void indexAllData(MerchantStore store) throws Exception;

}
