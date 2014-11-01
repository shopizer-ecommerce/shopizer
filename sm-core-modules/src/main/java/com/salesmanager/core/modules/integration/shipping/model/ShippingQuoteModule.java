package com.salesmanager.core.modules.integration.shipping.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.shipping.model.PackageDetails;
import com.salesmanager.core.business.shipping.model.ShippingConfiguration;
import com.salesmanager.core.business.shipping.model.ShippingOption;
import com.salesmanager.core.business.system.model.CustomIntegrationConfiguration;
import com.salesmanager.core.business.system.model.IntegrationConfiguration;
import com.salesmanager.core.business.system.model.IntegrationModule;
import com.salesmanager.core.modules.integration.IntegrationException;

public interface ShippingQuoteModule {
	
	public void validateModuleConfiguration(IntegrationConfiguration integrationConfiguration, MerchantStore store) throws IntegrationException;
	public CustomIntegrationConfiguration getCustomModuleConfiguration(MerchantStore store) throws IntegrationException;
	
	public List<ShippingOption> getShippingQuotes(List<PackageDetails> packages, BigDecimal orderTotal, Delivery delivery, MerchantStore store, IntegrationConfiguration configuration, IntegrationModule module, ShippingConfiguration shippingConfiguration, Locale locale) throws IntegrationException;

}
