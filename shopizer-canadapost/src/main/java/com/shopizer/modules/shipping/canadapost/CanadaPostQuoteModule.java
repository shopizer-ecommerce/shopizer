package com.shopizer.modules.shipping.canadapost;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.JAXBContext;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.canadapost.cpcdp.rating.generated.messages.Messages;
import ca.canadapost.cpcdp.rating.generated.rating.MailingScenario;
import ca.canadapost.cpcdp.rating.generated.rating.MailingScenario.Destination;
import ca.canadapost.cpcdp.rating.generated.rating.MailingScenario.Destination.Domestic;
import ca.canadapost.cpcdp.rating.generated.rating.MailingScenario.Destination.International;
import ca.canadapost.cpcdp.rating.generated.rating.MailingScenario.Destination.UnitedStates;
import ca.canadapost.cpcdp.rating.generated.rating.MailingScenario.Services;
import ca.canadapost.cpcdp.rating.generated.rating.PriceQuotes;

import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.shipping.PackageDetails;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingOption;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.system.CustomIntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.model.system.ModuleConfig;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuoteModule;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Shipping Module for Canada Post GetRates service
 * https://www.canadapost.ca/cpo/mc/business/productsservices/developers/services/rating/default.jsf
 * @author carlsamson
 *
 */
public class CanadaPostQuoteModule implements ShippingQuoteModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CanadaPostQuoteModule.class);
	
	private final static String CANADA_CODE = "CA";
	private final static String US_CODE = "US";
	
	//dev  "https://ct.soa-gw.canadapost.ca/rs/ship/price";
        //prod "https://soa-gw.canadapost.ca/rs/ship/price";

	@Override
	public void validateModuleConfiguration(
			IntegrationConfiguration integrationConfiguration,
			MerchantStore store) throws IntegrationException {

		List<String> errorFields = null;

		Map<String,String> keys = integrationConfiguration.getIntegrationKeys();
		
		if(keys==null) {
			IntegrationException ex = new IntegrationException(IntegrationException.ERROR_VALIDATION_SAVE);
			ex.setErrorFields(errorFields);
			throw ex;
		}
		
		
		if(StringUtils.isBlank(keys.get("username"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("username");
		}

		if(StringUtils.isBlank(keys.get("password"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("password");
		}
		
		if(StringUtils.isBlank(keys.get("account"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("account");
		}
		
		if(StringUtils.isBlank(keys.get("apikey"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("apikey");
		}

		if(errorFields!=null) {
			IntegrationException ex = new IntegrationException(IntegrationException.ERROR_VALIDATION_SAVE);
			ex.setErrorFields(errorFields);
			throw ex;
			
		}


	}

	@Override
	public CustomIntegrationConfiguration getCustomModuleConfiguration(
			MerchantStore store) throws IntegrationException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<ShippingOption> getShippingQuotes(ShippingQuote quote,
			List<PackageDetails> packages, BigDecimal orderTotal,
			Delivery delivery, ShippingOrigin origin, MerchantStore store,
			IntegrationConfiguration configuration, IntegrationModule module,
			ShippingConfiguration shippingConfiguration, Locale locale)
			throws IntegrationException {
		
		
		Validate.notNull(shippingConfiguration, "Module Canadapost is not configured");
		Validate.notNull(packages, "Packages are null");
		Validate.notNull(delivery, "Delivery is null");
		Validate.notNull(origin, "Origin is null");
		Validate.notNull(origin.getPostalCode(), "Origin postal code is null");
		
		if(StringUtils.isBlank(delivery.getPostalCode())) {
			return null;
		}

		// only applies to Canada and US
		Country country = delivery.getCountry();

		if(!(country.getIsoCode().equals("US") || country.getIsoCode().equals("CA"))) {
			throw new IntegrationException("Canadapost Not configured for shipping in country " + country.getIsoCode());
		}
		
		Map<String,String> keys = configuration.getIntegrationKeys();
		Map<String,List<String>> options = configuration.getIntegrationOptions();

		if(keys==null) {
			throw new IntegrationException("Canadapost missing configuration keys");
		}
		
		if(options==null) {
			throw new IntegrationException("Canadapost missing configuration options");
		}

		if(StringUtils.isBlank(keys.get("username"))) {
			throw new IntegrationException("Canadapost missing configuration key username");
		}
		
		if(StringUtils.isBlank(keys.get("password"))) {
			throw new IntegrationException("Canadapost missing configuration key password");
		}
		
		if(StringUtils.isBlank(keys.get("account"))) {
			throw new IntegrationException("Canadapost missing configuration key account");
		}
		
    	String username = keys.get("username");
    	String password = keys.get("password");
    	String client = keys.get("account");
    	
    	List<String> domesticServices = options.get("services-domestic");
    	List<String> intlServices = options.get("services-intl");
		List<String> usaServices = options.get("services-usa");
		
		String host = null;
		String protocol = null;
		String port = null;
		String url = null;
		
		
		//against which environment are we using the service
		String env = configuration.getEnvironment();


		Map<String, ModuleConfig> moduleConfigsMap = module.getModuleConfigs();
		for(String key : moduleConfigsMap.keySet()) {
			
			ModuleConfig moduleConfig = (ModuleConfig)moduleConfigsMap.get(key);
			if(moduleConfig.getEnv().equals(env)) {
				host = moduleConfig.getHost();
				protocol = moduleConfig.getScheme();
				port = moduleConfig.getPort();
				url = moduleConfig.getUri();
				break;
			}
		}
		
		StringBuilder link = new StringBuilder().append(protocol).append("://").append(host).append(":").append(port);
		if(!StringUtils.isBlank(url)) {
			link.append(url);
		}


		Client serviceClient = null;
		
        ClientConfig config = new DefaultClientConfig();
        serviceClient = Client.create(config);
        serviceClient.addFilter(new com.sun.jersey.api.client.filter.HTTPBasicAuthFilter(username, password));
		
  
        MailingScenario mailingScenario = new MailingScenario();
		//mailingScenario.setCustomerNumber(client);
        mailingScenario.setQuoteType("counter");
		
        mailingScenario.setOriginPostalCode(origin.getPostalCode().replaceAll("\\s+","").toUpperCase());
		Destination destination = new Destination();

		if(delivery.getCountry().getIsoCode().equals(CANADA_CODE)) {
		
			if(domesticServices==null || domesticServices.size()==0) {
				throw new IntegrationException("Canadapost selected domestic services are missing");
			}
			
			Domestic domestic = new Domestic();
			domestic.setPostalCode(delivery.getPostalCode().replaceAll("\\s+","").toUpperCase());		
			destination.setDomestic(domestic);
			
			Services service = new Services();
			for(String domesticService : domesticServices) {
				service.getServiceCodes().add(domesticService);
			}
			
			mailingScenario.setServices(service);
		
		} else if(delivery.getCountry().getIsoCode().equals(US_CODE)) {
			
			if(usaServices==null || usaServices.size()==0) {
				throw new IntegrationException("Canadapost selected USA services are missing");
			}
			
			
			UnitedStates unitedStates = new UnitedStates();
			unitedStates.setZipCode(delivery.getPostalCode().trim());
			destination.setUnitedStates(unitedStates);
			
			Services service = new Services();
			for(String usaService : usaServices) {
				service.getServiceCodes().add(usaService);
			}
			
			mailingScenario.setServices(service);
			
			
		} else {
			
			if(intlServices==null || intlServices.size()==0) {
				throw new IntegrationException("Canadapost selected International services are missing");
			}
			
			International international = new International();
			international.setCountryCode(delivery.getCountry().getIsoCode());
			destination.setInternational(international);
			
			Services service = new Services();
			for(String intlService : intlServices) {
				service.getServiceCodes().add(intlService);
			}
			
			mailingScenario.setServices(service);
		}
		
		mailingScenario.setDestination(destination);
		

		Map<String,ShippingOption> allOptions = new HashMap<String,ShippingOption>();
		
		// Execute GetRates Request for each package
        for(PackageDetails packageDetail : packages) {
        	
        	
        	MailingScenario.ParcelCharacteristics parcelCharacteristics = new MailingScenario.ParcelCharacteristics();
        	parcelCharacteristics.setWeight(new BigDecimal(packageDetail.getShippingWeight()));
        	MailingScenario.ParcelCharacteristics.Dimensions dimensions = new MailingScenario.ParcelCharacteristics.Dimensions();
    		
        	dimensions.setHeight(new BigDecimal(packageDetail.getShippingHeight()));
        	dimensions.setWidth(new BigDecimal(packageDetail.getShippingWidth()));
        	dimensions.setLength(new BigDecimal(packageDetail.getShippingLength()));
        	
        	parcelCharacteristics.setDimensions(dimensions);
        	mailingScenario.setParcelCharacteristics(parcelCharacteristics);
   

	        WebResource webResource = serviceClient.resource(link.toString());
	        ClientResponse resp = webResource.accept("application/vnd.cpc.ship.rate-v3+xml").header("Content-Type", "application/vnd.cpc.ship.rate-v3+xml").acceptLanguage("en-CA").post(ClientResponse.class, mailingScenario);
	      
	        
	        
	        InputStream respIS = resp.getEntityInputStream();
	        
	        LOGGER.debug("HTTP Response Status: " + resp.getStatus() + " " + resp.getClientResponseStatus());

	        // Example of using JAXB to parse xml response
	        JAXBContext jc;
	        try {
	        	jc = JAXBContext.newInstance(PriceQuotes.class, Messages.class);
	            Object entity = jc.createUnmarshaller().unmarshal(respIS);
	            // Determine whether response data matches GetRatesInfo schema.
	            if (entity instanceof PriceQuotes) {
	            	PriceQuotes priceQuotes = (PriceQuotes) entity;
	                for (Iterator<PriceQuotes.PriceQuote> iter = priceQuotes.getPriceQuotes().iterator(); iter.hasNext();) { 
	                	PriceQuotes.PriceQuote aPriceQuote = (PriceQuotes.PriceQuote) iter.next();                	
	                	ShippingOption option = allOptions.get(aPriceQuote.getServiceCode());
	                	if(option==null) {
	                		option = new ShippingOption();
		                	option.setOptionCode(aPriceQuote.getServiceCode());
		                	option.setOptionPrice(aPriceQuote.getPriceDetails().getDue());
		                	option.setOptionName(aPriceQuote.getServiceName());
		                	allOptions.put(aPriceQuote.getServiceCode(), option);
	                	} else {
	                		BigDecimal due = option.getOptionPrice().add(aPriceQuote.getPriceDetails().getDue());
	                		option.setOptionPrice(due);
	                	}
	
	                	LOGGER.debug("Service Name: " + aPriceQuote.getServiceName());
	                	LOGGER.debug("Price: $" + aPriceQuote.getPriceDetails().getDue());
		                
	                }
	            } else {
	                // Assume Error Schema
	                Messages messageData = (Messages) entity;
	                StringBuilder messageBuilder = new StringBuilder();
	                for (Iterator<Messages.Message> iter = messageData.getMessage().iterator(); iter.hasNext();) {
	                    Messages.Message aMessage = (Messages.Message) iter.next();
	                    LOGGER.debug("Error Code: " + aMessage.getCode());
	                    LOGGER.debug("Error Msg: " + aMessage.getDescription());
	                    messageBuilder.append(aMessage.getCode()).append(" - ");
	                    messageBuilder.append(aMessage.getDescription()).append(" ");
	                }
	                
	                IntegrationException ex = new IntegrationException(messageBuilder.toString());
	                ex.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
	                throw ex;
	            }
	        } catch (Exception e) {
	            IntegrationException ex = new IntegrationException(e);
	            ex.setErrorCode(IntegrationException.TRANSACTION_EXCEPTION);
	            throw ex;
	        }
	
	        serviceClient.destroy();
		
        }
        
        List<ShippingOption> shippingOptions = new ArrayList<ShippingOption>(allOptions.values());
		return shippingOptions;
	}
	

	

}
