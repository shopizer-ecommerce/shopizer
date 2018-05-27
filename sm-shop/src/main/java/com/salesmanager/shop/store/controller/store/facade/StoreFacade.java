package com.salesmanager.shop.store.controller.store.facade;

import javax.servlet.http.HttpServletRequest;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.shop.ReadableMerchantStore;

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
	MerchantStore getByCode(HttpServletRequest reques) throws Exception;
	
	MerchantStore get(String code) throws Exception;
	
	ReadableMerchantStore getByCode(String code, Language lang) throws Exception;

}
