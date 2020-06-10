package com.salesmanager.shop.store.controller.shipping.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.references.Address;
import com.salesmanager.shop.model.shipping.ExpeditionConfiguration;

public interface ShippingFacade {
	
	ExpeditionConfiguration getExpeditionConfiguration(MerchantStore store, Language language);
	void saveExpeditionConfiguration(ExpeditionConfiguration expedition, MerchantStore store);
	
	
	Address getShippingOrigin(MerchantStore store);
	void saveShippingOrigin(Address address, MerchantStore store);

}
