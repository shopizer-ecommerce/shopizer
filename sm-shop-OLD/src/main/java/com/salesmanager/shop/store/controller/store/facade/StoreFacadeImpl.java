package com.salesmanager.shop.store.controller.store.facade;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.drools.core.util.StringUtils;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.model.shop.ReadableMerchantStore;
import com.salesmanager.shop.populator.store.ReadableMerchantStorePopulator;

@Service("storeFacade")
public class StoreFacadeImpl implements StoreFacade {
	
	@Inject
	private MerchantStoreService merchantStoreService;
	
	@Inject
	private LanguageService languageService;

	@Override
	public MerchantStore getByCode(HttpServletRequest request) throws Exception {
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

	@Override
	public ReadableMerchantStore getByCode(String code) throws Exception {
		MerchantStore store = get(code);
		ReadableMerchantStorePopulator populator = new ReadableMerchantStorePopulator();
		
		ReadableMerchantStore readable = new ReadableMerchantStore();
		/**
		 * Language is not important for this conversion
		 * using default language
		 */
		readable = populator.populate(store, readable, store, languageService.defaultLanguage());
		return readable;
	}

}
