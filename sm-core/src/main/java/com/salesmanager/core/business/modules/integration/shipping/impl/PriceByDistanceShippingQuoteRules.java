package com.salesmanager.core.business.modules.integration.shipping.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.PackageDetails;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingOption;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.system.CustomIntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.modules.constants.Constants;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuoteModule;


/**
 * Requires to set pre-processor distance calculator
 * pre-processor calculates the distance (in kilometers [can be changed to miles]) based on delivery address
 * when that module is invoked during process it will calculate the price
 * DISTANCE * PRICE/KM
 * @author carlsamson
 *
 */
public class PriceByDistanceShippingQuoteRules implements ShippingQuoteModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PriceByDistanceShippingQuoteRules.class);

	public final static String MODULE_CODE = "priceByDistance";

	@Override
	public void validateModuleConfiguration(
			IntegrationConfiguration integrationConfiguration,
			MerchantStore store) throws IntegrationException {
		// Not used

	}

	@Override
	public CustomIntegrationConfiguration getCustomModuleConfiguration(
			MerchantStore store) throws IntegrationException {
		// Not used
		return null;
	}

	@Override
	public List<ShippingOption> getShippingQuotes(ShippingQuote quote,
			List<PackageDetails> packages, BigDecimal orderTotal,
			Delivery delivery, ShippingOrigin origin, MerchantStore store,
			IntegrationConfiguration configuration, IntegrationModule module,
			ShippingConfiguration shippingConfiguration, Locale locale)
			throws IntegrationException {

		
		
		Validate.notNull(delivery, "Delivery cannot be null");
		Validate.notNull(delivery.getCountry(), "Delivery.country cannot be null");
		Validate.notNull(packages, "packages cannot be null");
		Validate.notEmpty(packages, "packages cannot be empty");
		
		//requires the postal code
		if(StringUtils.isBlank(delivery.getPostalCode())) {
			return null;
		}

		Double distance = null;

		//look if distance has been calculated
		if (Objects.nonNull(quote) && Objects.nonNull(quote.getQuoteInformations())) {
			if (quote.getQuoteInformations().containsKey(Constants.DISTANCE_KEY)) {
				distance = (Double) quote.getQuoteInformations().get(Constants.DISTANCE_KEY);
			}
		}
		
		if(distance==null) {
			return null;
		}
		
		//maximum distance TODO configure from admin
		if(distance > 150D) {
			return null;
		}
		
		List<ShippingOption> options = quote.getShippingOptions();
		
		if(options == null) {
			options = new ArrayList<>();
			quote.setShippingOptions(options);
		}
		
		BigDecimal price = null;
		
		if(distance<=20) {
			price = new BigDecimal(2);//TODO from the admin
		} else {
			price = new BigDecimal(3);//TODO from the admin
		}
		BigDecimal total = new BigDecimal(distance).multiply(price);
		
		if(distance < 1) { //minimum 1 unit
			distance = 1D;
		}


		ShippingOption shippingOption = new ShippingOption();
			
			
		shippingOption.setOptionPrice(total);
		shippingOption.setShippingModuleCode(MODULE_CODE);
		shippingOption.setOptionCode(MODULE_CODE);
		shippingOption.setOptionId(MODULE_CODE);

		options.add(shippingOption);

		
		return options;
		
		
	}

}