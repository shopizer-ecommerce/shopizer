package com.salesmanager.core.modules.integration.shipping.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.PackageDetails;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.modules.integration.IntegrationException;

/**
 * Invoked before or after quote processing
 * @author carlsamson
 *
 */
public interface ShippingQuotePrePostProcessModule {
	
	
	public String getModuleCode();
	

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
			List<IntegrationModule> allModules, 
			Locale locale) throws IntegrationException;

}
