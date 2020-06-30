package com.salesmanager.shop.store.controller.shipping.facade;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.references.PersistableAddress;
import com.salesmanager.shop.model.references.ReadableAddress;
import com.salesmanager.shop.model.shipping.ExpeditionConfiguration;
import com.salesmanager.shop.model.shipping.ShippingConfiguration;

public interface ShippingFacade {
	
	ExpeditionConfiguration getExpeditionConfiguration(MerchantStore store, Language language);
	void saveExpeditionConfiguration(ExpeditionConfiguration expedition, MerchantStore store);
	
	
	ReadableAddress getShippingOrigin(MerchantStore store);
	void saveShippingOrigin(PersistableAddress address, MerchantStore store);
	
	ShippingConfiguration getShippingConfiguration(MerchantStore store);
	void setShippingConfiguration(ShippingConfiguration configuration, MerchantStore store);


}
