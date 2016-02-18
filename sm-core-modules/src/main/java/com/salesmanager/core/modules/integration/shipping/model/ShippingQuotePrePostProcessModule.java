package com.salesmanager.core.modules.integration.shipping.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.shipping.model.PackageDetails;
import com.salesmanager.core.business.shipping.model.ShippingConfiguration;
import com.salesmanager.core.business.shipping.model.ShippingOrigin;
import com.salesmanager.core.business.shipping.model.ShippingQuote;
import com.salesmanager.core.business.system.model.IntegrationConfiguration;
import com.salesmanager.core.business.system.model.IntegrationModule;
import com.salesmanager.core.modules.integration.IntegrationException;

public interface ShippingQuotePrePostProcessModule {

	String getModuleCode();
	
	 public void prePostProcessShippingQuotes(
	            ShippingQuote quote, 
	            List<PackageDetails> packages, 
	            BigDecimal orderTotal, 
	            Delivery delivery, 
	            ShippingOrigin origin, 
	            MerchantStore store, 
	            IntegrationConfiguration globalShippingConfiguration, 
	            IntegrationModule currentModule, 
	            ShippingConfiguration shippingConfiguration, 
	            List<IntegrationModule> allModules, Locale locale) throws IntegrationException;
}
