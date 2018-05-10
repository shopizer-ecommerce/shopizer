package com.salesmanager.shop.store.controller.store.facade;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.drools.core.util.StringUtils;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.model.merchant.MerchantStore;

@Service("storeFacade")
public class StoreFacadImpl implements StoreFacade {
	
	@Inject
	private MerchantStoreService merchantStoreService;

	@Override
	public MerchantStore getByCode(HttpServletRequest request) throws Exception {
		//return merchantStoreService.getByCode(code);//TODO use db instead of static
		//eturn merchantStoreService.getByCode(request);
		String code = request.getParameter("store");
		if(StringUtils.isEmpty(code)) {
			code = com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;
		} 
		
		return get(code);
	}

	@Override
	public MerchantStore get(String code) throws Exception {
		return merchantStoreService.getByCode(code);
	}

}
