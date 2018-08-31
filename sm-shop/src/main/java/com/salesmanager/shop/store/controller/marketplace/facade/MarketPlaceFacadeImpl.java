package com.salesmanager.shop.store.controller.marketplace.facade;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.system.optin.OptinService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.core.model.system.optin.OptinType;
import com.salesmanager.shop.model.marketplace.ReadableMarketPlace;
import com.salesmanager.shop.model.shop.ReadableMerchantStore;
import com.salesmanager.shop.model.system.ReadableOptin;
import com.salesmanager.shop.populator.system.ReadableOptinPopulator;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;

@Component
public class MarketPlaceFacadeImpl implements MarketPlaceFacade {
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private OptinService optinService;
	

	@Override
	public ReadableMarketPlace get(String store, Language lang) throws Exception {
		// TODO Auto-generated method stub
		ReadableMerchantStore readableStore = storeFacade.getByCode(store, lang);
		
		if(readableStore==null) {
			return null;
		}
		
		//TODO add info from Entity
		ReadableMarketPlace marketPlace = new ReadableMarketPlace();
		marketPlace.setStore(readableStore);
		
		return marketPlace;
		
	}


	@Override
	public ReadableOptin findByMerchantAndType(MerchantStore store, OptinType type) throws Exception {
		Optin optin = optinService.getOptinByMerchantAndType(store, type);
		if(optin==null) {
			return null;
		}
		ReadableOptinPopulator populator = new ReadableOptinPopulator();
		ReadableOptin readable = populator.populate(optin, null, store, null);
		return readable;
	}

}
