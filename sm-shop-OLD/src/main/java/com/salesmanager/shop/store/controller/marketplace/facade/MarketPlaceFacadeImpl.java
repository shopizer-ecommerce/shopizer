package com.salesmanager.shop.store.controller.marketplace.facade;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.salesmanager.shop.model.marketplace.ReadableMarketPlace;
import com.salesmanager.shop.model.shop.ReadableMerchantStore;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;

@Component
public class MarketPlaceFacadeImpl implements MarketPlaceFacade {
	
	@Inject
	private StoreFacade storeFacade;
	

	@Override
	public ReadableMarketPlace get(String store) throws Exception {
		// TODO Auto-generated method stub
		ReadableMerchantStore readableStore = storeFacade.getByCode(store);
		
		if(readableStore==null) {
			return null;
		}
		
		//TODO add info from Entity
		ReadableMarketPlace marketPlace = new ReadableMarketPlace();
		marketPlace.setStore(readableStore);
		
		return marketPlace;
		
	}

}
