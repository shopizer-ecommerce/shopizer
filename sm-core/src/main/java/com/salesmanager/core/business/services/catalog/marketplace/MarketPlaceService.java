package com.salesmanager.core.business.services.catalog.marketplace;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.marketplace.MarketPlace;
import com.salesmanager.core.model.merchant.MerchantStore;

public interface MarketPlaceService extends SalesManagerEntityService<Long, MarketPlace> {
	
	/**
	 * Creates a MarketPlace
	 * @param store
	 * @param code
	 * @return MarketPlace
	 * @throws ServiceException
	 */
	MarketPlace create(MerchantStore store, String code) throws ServiceException;
	
	/**
	 * Fetch a specific marketplace
	 * @param store
	 * @param code
	 * @return MarketPlace
	 * @throws ServiceException
	 */
	MarketPlace getByCode(MerchantStore store, String code) throws ServiceException;
	
	void delete(MarketPlace marketPlace) throws ServiceException;

}
