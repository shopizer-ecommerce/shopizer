package com.salesmanager.shop.store.controller.store.facade;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.model.merchant.MerchantStore;

@Service("storeFacade")
public class StoreFacadeImple implements StoreFacade {
	
	@Inject
	private MerchantStoreService merchantStoreService;

	@Override
	public MerchantStore getByCode(String code) throws Exception {
		return merchantStoreService.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
	}

}
