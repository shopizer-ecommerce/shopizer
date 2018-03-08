package com.salesmanager.shop.store.controller.store.facade;

import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * Layer between shop controllers, services and API with sm-core
 * @author carlsamson
 *
 */
public interface StoreFacade {
	
	/**
	 * Find MerchantStore model from store code
	 * @param code
	 * @return
	 * @throws Exception
	 */
	MerchantStore getByCode(String code) throws Exception;

}
