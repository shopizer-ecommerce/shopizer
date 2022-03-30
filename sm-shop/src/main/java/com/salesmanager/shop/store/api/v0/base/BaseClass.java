package com.salesmanager.shop.store.api.v0.base;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.model.merchant.MerchantStore;

public class BaseClass {
	
	@Inject
	private MerchantStoreService merchantStoreService;
	
	/**
	 * @param store
	 * @param response
	 * @param merchantStore
	 * @return
	 * @throws ServiceException
	 * @throws IOException
	 */
	protected MerchantStore getMerchantStore(final String store, HttpServletResponse response, MerchantStore merchantStore)
			throws ServiceException, IOException {
		
		if(merchantStore!=null) {
			if(!merchantStore.getCode().equals(store)) {
				merchantStore = null; //reset for the current request
			}
		}
		
		if(merchantStore== null) {
			merchantStore = merchantStoreService.getByCode(store);
		}
		return merchantStore;
	}
}
