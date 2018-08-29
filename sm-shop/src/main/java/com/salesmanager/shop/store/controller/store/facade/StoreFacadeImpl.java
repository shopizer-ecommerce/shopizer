package com.salesmanager.shop.store.controller.store.facade;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.drools.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.shop.ReadableMerchantStore;
import com.salesmanager.shop.populator.store.ReadableMerchantStorePopulator;
import com.salesmanager.shop.utils.ImageFilePath;

@Service("storeFacade")
public class StoreFacadeImpl implements StoreFacade {
	
	@Inject
	private MerchantStoreService merchantStoreService;
	
	@Inject
	private LanguageService languageService;
	
	@Inject
	private CountryService countryService;
	
	@Inject
	private ZoneService zoneService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

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
	public ReadableMerchantStore getByCode(String code, Language language) throws Exception {
		
		MerchantStore store = get(code);
		ReadableMerchantStorePopulator populator = new ReadableMerchantStorePopulator();
		
		ReadableMerchantStore readable = new ReadableMerchantStore();
		
		populator.setCountryService(countryService);
		populator.setZoneService(zoneService);
		populator.setFilePath(imageUtils);
		
		/**
		 * Language is not important for this conversion
		 * using default language
		 */
		readable = populator.populate(store, readable, store, language);
		return readable;
	}

}
