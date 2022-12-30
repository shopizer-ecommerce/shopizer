package com.salesmanager.shop.store.controller.shipping.facade;

import java.util.List;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shipping.PackageDetails;
import com.salesmanager.shop.model.references.PersistableAddress;
import com.salesmanager.shop.model.references.ReadableAddress;
import com.salesmanager.shop.model.references.ReadableCountry;
import com.salesmanager.shop.model.shipping.ExpeditionConfiguration;

public interface ShippingFacade {
	
	ExpeditionConfiguration getExpeditionConfiguration(MerchantStore store, Language language);
	void saveExpeditionConfiguration(ExpeditionConfiguration expedition, MerchantStore store);
	
	
	ReadableAddress getShippingOrigin(MerchantStore store);
	void saveShippingOrigin(PersistableAddress address, MerchantStore store);
	

	void createPackage(PackageDetails packaging, MerchantStore store);
	
	PackageDetails getPackage(String code, MerchantStore store);
	
	/**
	 * List of configured ShippingCountry for a given store
	 * @param store
	 * @param lanuage
	 * @return
	 */
	List<ReadableCountry> shipToCountry(MerchantStore store, Language lanuage);
	
	List<PackageDetails> listPackages(MerchantStore store);
	
	void updatePackage(String code, PackageDetails packaging, MerchantStore store);
	
	void deletePackage(String code, MerchantStore store);
	
	


}
