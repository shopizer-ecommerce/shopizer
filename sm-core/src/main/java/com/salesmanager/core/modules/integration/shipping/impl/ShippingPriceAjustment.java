package com.salesmanager.core.modules.integration.shipping.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.shipping.model.PackageDetails;
import com.salesmanager.core.business.shipping.model.ShippingConfiguration;
import com.salesmanager.core.business.shipping.model.ShippingOption;
import com.salesmanager.core.business.shipping.model.ShippingOrigin;
import com.salesmanager.core.business.shipping.model.ShippingQuote;
import com.salesmanager.core.business.system.model.CustomIntegrationConfiguration;
import com.salesmanager.core.business.system.model.IntegrationConfiguration;
import com.salesmanager.core.business.system.model.IntegrationModule;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuoteModule;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuotePrePostProcessModule;
import com.salesmanager.core.utils.ProductPriceUtils;

public class ShippingPriceAjustment implements ShippingQuoteModule, ShippingQuotePrePostProcessModule {
	

	@Autowired
	private ProductPriceUtils productPriceUtils;



	@Override
	public String getModuleCode() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void prePostProcessShippingQuotes(ShippingQuote quote, List<PackageDetails> packages, BigDecimal orderTotal,
			Delivery delivery, ShippingOrigin origin, MerchantStore store,
			IntegrationConfiguration globalShippingConfiguration, IntegrationModule currentModule,
			ShippingConfiguration shippingConfiguration, List<IntegrationModule> allModules, Locale locale)
			throws IntegrationException {
		// TODO Auto-generated method stub
		
			if(quote == null) {
				return;
			}
			
			try {
			
				if(!StringUtils.isBlank(quote.getShippingModuleCode())) {
					String moduleCode = quote.getShippingModuleCode();
					if("canadapost".equals(moduleCode)) {
						ShippingOption shippingOption = quote.getSelectedShippingOption();
						BigDecimal price = shippingOption.getOptionPrice();
						if(orderTotal.intValue() >= 150) {
							price = new BigDecimal(0);
						} else {
							BigDecimal fraction = BigDecimal.valueOf(0.7);
							price = price.multiply(fraction);
						}
						shippingOption.setOptionPrice(price);
						shippingOption.setOptionPriceText(productPriceUtils.getStoreFormatedAmountWithCurrency(store,price));
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}



	@Override
	public void validateModuleConfiguration(IntegrationConfiguration integrationConfiguration, MerchantStore store)
			throws IntegrationException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public CustomIntegrationConfiguration getCustomModuleConfiguration(MerchantStore store)
			throws IntegrationException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<ShippingOption> getShippingQuotes(ShippingQuote quote, List<PackageDetails> packages,
			BigDecimal orderTotal, Delivery delivery, ShippingOrigin origin, MerchantStore store,
			IntegrationConfiguration configuration, IntegrationModule module,
			ShippingConfiguration shippingConfiguration, Locale locale) throws IntegrationException {
		// TODO Auto-generated method stub
		return null;
	}

}
