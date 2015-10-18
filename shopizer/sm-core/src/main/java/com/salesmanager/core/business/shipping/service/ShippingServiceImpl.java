package com.salesmanager.core.business.shipping.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.service.PricingService;
import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.shipping.model.PackageDetails;
import com.salesmanager.core.business.shipping.model.ShippingConfiguration;
import com.salesmanager.core.business.shipping.model.ShippingOption;
import com.salesmanager.core.business.shipping.model.ShippingOptionPriceType;
import com.salesmanager.core.business.shipping.model.ShippingOrigin;
import com.salesmanager.core.business.shipping.model.ShippingPackageType;
import com.salesmanager.core.business.shipping.model.ShippingProduct;
import com.salesmanager.core.business.shipping.model.ShippingQuote;
import com.salesmanager.core.business.shipping.model.ShippingSummary;
import com.salesmanager.core.business.shipping.model.ShippingType;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCartItem;
import com.salesmanager.core.business.system.model.CustomIntegrationConfiguration;
import com.salesmanager.core.business.system.model.IntegrationConfiguration;
import com.salesmanager.core.business.system.model.IntegrationModule;
import com.salesmanager.core.business.system.model.MerchantConfiguration;
import com.salesmanager.core.business.system.model.MerchantLog;
import com.salesmanager.core.business.system.service.MerchantConfigurationService;
import com.salesmanager.core.business.system.service.MerchantLogService;
import com.salesmanager.core.business.system.service.ModuleConfigurationService;
import com.salesmanager.core.constants.ShippingConstants;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.modules.integration.shipping.model.Packaging;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuoteModule;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuotePreProcessModule;
import com.salesmanager.core.modules.utils.Encryption;
import com.salesmanager.core.utils.reference.ConfigurationModulesLoader;

@Service("shippingService")
public class ShippingServiceImpl implements ShippingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingServiceImpl.class);
	
	
	private final static String SUPPORTED_COUNTRIES = "SUPPORTED_CNTR";
	private final static String SHIPPING_MODULES = "SHIPPING";
	
	@Autowired
	private MerchantConfigurationService merchantConfigurationService;
	

	@Autowired
	private PricingService pricingService;
	
	@Autowired
	private ModuleConfigurationService moduleConfigurationService;
	
	@Autowired
	private Packaging packaging;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private Encryption encryption;
	
	@Autowired
	private MerchantLogService merchantLogService;
	
	@Autowired
	private ShippingOriginService shippingOriginService;
	
	@Autowired
	@Resource(name="shippingModules")
	private Map<String,ShippingQuoteModule> shippingModules;
	
	//shipping pre-processors
	@Autowired
	@Resource(name="shippingModulePreProcessors")
	private List<ShippingQuotePreProcessModule> shippingModulePreProcessors;
	
	@Override
	public ShippingConfiguration getShippingConfiguration(MerchantStore store) throws ServiceException {

		MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(ShippingConstants.SHIPPING_CONFIGURATION, store);
		
		ShippingConfiguration shippingConfiguration = null;
		
		if(configuration!=null) {
			String value = configuration.getValue();
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				shippingConfiguration = mapper.readValue(value, ShippingConfiguration.class);
			} catch(Exception e) {
				throw new ServiceException("Cannot parse json string " + value);
			}
		}
		return shippingConfiguration;
		
	}
	
	@Override
	public IntegrationConfiguration getShippingConfiguration(String moduleCode, MerchantStore store) throws ServiceException {

		
		Map<String,IntegrationConfiguration> configuredModules = getShippingModulesConfigured(store);
		if(configuredModules!=null) {
			for(String key : configuredModules.keySet()) {
				if(key.equals(moduleCode)) {
					return configuredModules.get(key);	
				}
			}
		}
		
		return null;
		
	}
	
	@Override
	public CustomIntegrationConfiguration getCustomShippingConfiguration(String moduleCode, MerchantStore store) throws ServiceException {

		
		ShippingQuoteModule quoteModule = (ShippingQuoteModule)shippingModules.get(moduleCode);
		if(quoteModule==null) {
			return null;
		}
		return quoteModule.getCustomModuleConfiguration(store);
		
	}
	
	@Override
	public void saveShippingConfiguration(ShippingConfiguration shippingConfiguration, MerchantStore store) throws ServiceException {
		
		MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(ShippingConstants.SHIPPING_CONFIGURATION, store);

		if(configuration==null) {
			configuration = new MerchantConfiguration();
			configuration.setMerchantStore(store);
			configuration.setKey(ShippingConstants.SHIPPING_CONFIGURATION);
		}
		
		String value = shippingConfiguration.toJSONString();
		configuration.setValue(value);
		merchantConfigurationService.saveOrUpdate(configuration);
		
	}
	
	@Override
	public void saveCustomShippingConfiguration(String moduleCode, CustomIntegrationConfiguration shippingConfiguration, MerchantStore store) throws ServiceException {
		
		
		ShippingQuoteModule quoteModule = (ShippingQuoteModule)shippingModules.get(moduleCode);
		if(quoteModule==null) {
			throw new ServiceException("Shipping module " + moduleCode + " does not exist");
		}
		
		String configurationValue = shippingConfiguration.toJSONString();
		
		
		try {

			MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(moduleCode, store);
	
			if(configuration==null) {

				configuration = new MerchantConfiguration();
				configuration.setKey(moduleCode);
				configuration.setMerchantStore(store);
			}
			configuration.setValue(configurationValue);
			merchantConfigurationService.saveOrUpdate(configuration);
		
		} catch (Exception e) {
			throw new IntegrationException(e);
		}

		
		
	}
	

	@Override
	public List<IntegrationModule> getShippingMethods(MerchantStore store) throws ServiceException {
		
		List<IntegrationModule> modules =  moduleConfigurationService.getIntegrationModules(SHIPPING_MODULES);
		List<IntegrationModule> returnModules = new ArrayList<IntegrationModule>();
		
		for(IntegrationModule module : modules) {
			if(module.getRegionsSet().contains(store.getCountry().getIsoCode())
					|| module.getRegionsSet().contains("*")) {
				
				returnModules.add(module);
			}
		}
		
		return returnModules;
	}
	
	@Override
	public void saveShippingQuoteModuleConfiguration(IntegrationConfiguration configuration, MerchantStore store) throws ServiceException {
		
			//validate entries
			try {
				
				String moduleCode = configuration.getModuleCode();
				ShippingQuoteModule quoteModule = (ShippingQuoteModule)shippingModules.get(moduleCode);
				if(quoteModule==null) {
					throw new ServiceException("Shipping quote module " + moduleCode + " does not exist");
				}
				quoteModule.validateModuleConfiguration(configuration, store);
				
			} catch (IntegrationException ie) {
				throw ie;
			}
			
			try {
				Map<String,IntegrationConfiguration> modules = new HashMap<String,IntegrationConfiguration>();
				MerchantConfiguration merchantConfiguration = merchantConfigurationService.getMerchantConfiguration(SHIPPING_MODULES, store);
				if(merchantConfiguration!=null) {
					if(!StringUtils.isBlank(merchantConfiguration.getValue())) {
						
						String decrypted = encryption.decrypt(merchantConfiguration.getValue());
						modules = ConfigurationModulesLoader.loadIntegrationConfigurations(decrypted);
					}
				} else {
					merchantConfiguration = new MerchantConfiguration();
					merchantConfiguration.setMerchantStore(store);
					merchantConfiguration.setKey(SHIPPING_MODULES);
				}
				modules.put(configuration.getModuleCode(), configuration);
				
				String configs =  ConfigurationModulesLoader.toJSONString(modules);
				
				String encrypted = encryption.encrypt(configs);
				merchantConfiguration.setValue(encrypted);
				merchantConfigurationService.saveOrUpdate(merchantConfiguration);
				
			} catch (Exception e) {
				throw new ServiceException(e);
			}
	}
	
	
	@Override
	public void removeShippingQuoteModuleConfiguration(String moduleCode, MerchantStore store) throws ServiceException {
		
		

		try {
			Map<String,IntegrationConfiguration> modules = new HashMap<String,IntegrationConfiguration>();
			MerchantConfiguration merchantConfiguration = merchantConfigurationService.getMerchantConfiguration(SHIPPING_MODULES, store);
			if(merchantConfiguration!=null) {
				if(!StringUtils.isBlank(merchantConfiguration.getValue())) {
					String decrypted = encryption.decrypt(merchantConfiguration.getValue());
					modules = ConfigurationModulesLoader.loadIntegrationConfigurations(decrypted);
				}
				
				modules.remove(moduleCode);
				String configs =  ConfigurationModulesLoader.toJSONString(modules);
				String encrypted = encryption.encrypt(configs);
				merchantConfiguration.setValue(encrypted);
				merchantConfigurationService.saveOrUpdate(merchantConfiguration);
				
				
			} 
			
			MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(moduleCode, store);
			
			if(configuration!=null) {//custom module

				merchantConfigurationService.delete(configuration);
			}

			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	
	}
	
	@Override
	public void removeCustomShippingQuoteModuleConfiguration(String moduleCode, MerchantStore store) throws ServiceException {
		
		

		try {
			
			removeShippingQuoteModuleConfiguration(moduleCode,store);
			MerchantConfiguration merchantConfiguration = merchantConfigurationService.getMerchantConfiguration(moduleCode, store);
			if(merchantConfiguration!=null) {
				merchantConfigurationService.delete(merchantConfiguration);
			} 
			
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	
	}
	
	@Override
	public Map<String,IntegrationConfiguration> getShippingModulesConfigured(MerchantStore store) throws ServiceException {
		try {
			

			Map<String,IntegrationConfiguration> modules = new HashMap<String,IntegrationConfiguration>();
			MerchantConfiguration merchantConfiguration = merchantConfigurationService.getMerchantConfiguration(SHIPPING_MODULES, store);
			if(merchantConfiguration!=null) {
				if(!StringUtils.isBlank(merchantConfiguration.getValue())) {
					String decrypted = encryption.decrypt(merchantConfiguration.getValue());
					modules = ConfigurationModulesLoader.loadIntegrationConfigurations(decrypted);
					
				}
			}
			return modules;
		
		
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public ShippingSummary getShippingSummary(MerchantStore store, ShippingQuote shippingQuote, ShippingOption selectedShippingOption) throws ServiceException {
		
		ShippingSummary shippingSummary = new ShippingSummary();
		shippingSummary.setFreeShipping(shippingQuote.isFreeShipping());
		shippingSummary.setHandling(shippingQuote.getHandlingFees());
		shippingSummary.setShipping(selectedShippingOption.getOptionPrice());
		shippingSummary.setShippingModule(shippingQuote.getShippingModuleCode());
		shippingSummary.setShippingOption(selectedShippingOption.getDescription());
		
		return shippingSummary;
	}

	@Override
	public ShippingQuote getShippingQuote(MerchantStore store, Delivery delivery, List<ShippingProduct> products, Language language) throws ServiceException  {
		
		
		Validate.notNull(store,"MerchantStore must not be null");
		Validate.notNull(delivery,"Delivery must not be null");
		Validate.notEmpty(products,"products must not be empty");
		Validate.notNull(language,"Language must not be null");
		
		
		ShippingQuote shippingQuote = new ShippingQuote();
		ShippingQuoteModule shippingQuoteModule = null;
		
		try {
		
			//get configuration
			ShippingConfiguration shippingConfiguration = getShippingConfiguration(store);
			ShippingType shippingType = ShippingType.INTERNATIONAL;
			
			/** get shipping origin **/
			ShippingOrigin shippingOrigin = shippingOriginService.getByStore(store);
			if(shippingOrigin == null || !shippingOrigin.isActive()) {
				shippingOrigin = new ShippingOrigin();
				shippingOrigin.setAddress(store.getStoreaddress());
				shippingOrigin.setCity(store.getStorecity());
				shippingOrigin.setCountry(store.getCountry());
				shippingOrigin.setPostalCode(store.getStorepostalcode());
				shippingOrigin.setState(store.getStorestateprovince());
				shippingOrigin.setZone(store.getZone());
			}
			
			
			if(shippingConfiguration==null) {
				shippingConfiguration = new ShippingConfiguration();
			}
			
			if(shippingConfiguration.getShippingType()!=null) {
					shippingType = shippingConfiguration.getShippingType();
			}

			//look if customer country code excluded
			Country shipCountry = delivery.getCountry();
			if(shipCountry==null) {
				throw new ServiceException("Delivery country is null");
			}
			
			//a ship to country is required
			Validate.notNull(shipCountry);
			Validate.notNull(store.getCountry());
			
			if(shippingType.name().equals(ShippingType.NATIONAL.name())){
				//customer country must match store country
				if(!shipCountry.getIsoCode().equals(store.getCountry().getIsoCode())) {
					shippingQuote.setShippingReturnCode(ShippingQuote.NO_SHIPPING_TO_SELECTED_COUNTRY + " " + shipCountry.getIsoCode());
					return shippingQuote;
				}
			} else if(shippingType.name().equals(ShippingType.INTERNATIONAL.name())){
				
				//customer shipping country code must be in accepted list
				List<String> supportedCountries = this.getSupportedCountries(store);
				if(!supportedCountries.contains(shipCountry.getIsoCode())) {
					shippingQuote.setShippingReturnCode(ShippingQuote.NO_SHIPPING_TO_SELECTED_COUNTRY + " " + shipCountry.getIsoCode());
					return shippingQuote;
				}
			}
			
			//must have a shipping module configured
			Map<String, IntegrationConfiguration> modules = this.getShippingModulesConfigured(store);
			if(modules==null){
				shippingQuote.setShippingReturnCode(ShippingQuote.NO_SHIPPING_MODULE_CONFIGURED);
				return shippingQuote;
			}

			
			/** uses this module name **/
			String moduleName = null;
			IntegrationConfiguration configuration = null;
			for(String module : modules.keySet()) {
				moduleName = module;
				configuration = modules.get(module);
				//use the first active module
				if(configuration.isActive()) {
					shippingQuoteModule = this.shippingModules.get(module);
					break;
				}
			}
			
			if(shippingQuoteModule==null){
				shippingQuote.setShippingReturnCode(ShippingQuote.NO_SHIPPING_MODULE_CONFIGURED);
				return shippingQuote;
			}
			
			/** merchant module configs **/
			List<IntegrationModule> shippingMethods = this.getShippingMethods(store);
			IntegrationModule shippingModule = null;
			for(IntegrationModule mod : shippingMethods) {
				if(mod.getCode().equals(moduleName)){
					shippingModule = mod;
					break;
				}
			}
			
			/** general module configs **/
			if(shippingModule==null) {
				shippingQuote.setShippingReturnCode(ShippingQuote.NO_SHIPPING_MODULE_CONFIGURED);
				return shippingQuote;
			}
			
			//calculate order total
			BigDecimal orderTotal = calculateOrderTotal(products,store);
			List<PackageDetails> packages = this.getPackagesDetails(products, store);
			
			//free shipping ?
			if(shippingConfiguration.isFreeShippingEnabled()) {
				BigDecimal freeShippingAmount = shippingConfiguration.getOrderTotalFreeShipping();
				if(freeShippingAmount!=null) {
					if(orderTotal.doubleValue()>freeShippingAmount.doubleValue()) {
						if(shippingConfiguration.getFreeShippingType() == ShippingType.NATIONAL) {
							if(store.getCountry().getIsoCode().equals(shipCountry.getIsoCode())) {
								shippingQuote.setFreeShipping(true);
								shippingQuote.setFreeShippingAmount(freeShippingAmount);
								return shippingQuote;
							}
						} else {//international all
							shippingQuote.setFreeShipping(true);
							shippingQuote.setFreeShippingAmount(freeShippingAmount);
							return shippingQuote;
						}
	
					}
				}
			}
			

			//handling fees
			BigDecimal handlingFees = shippingConfiguration.getHandlingFees();
			if(handlingFees!=null) {
				shippingQuote.setHandlingFees(handlingFees);
			}
			
			//tax basis
			shippingQuote.setApplyTaxOnShipping(shippingConfiguration.isTaxOnShipping());
			

			Locale locale = languageService.toLocale(language);
			
			//invoke pre processors
			if(!CollectionUtils.isEmpty(shippingModulePreProcessors)) {
				for(ShippingQuotePreProcessModule preProcessor : shippingModulePreProcessors) {
					preProcessor.preProcessShippingQuotes(shippingQuote, packages, orderTotal, delivery, shippingOrigin, store, configuration, shippingModule, shippingConfiguration, shippingMethods, locale);
					//TODO switch module if required
					if(shippingQuote.getCurrentShippingModule()!=null && !shippingQuote.getCurrentShippingModule().getCode().equals(shippingModule.getCode())) {
						shippingModule = shippingQuote.getCurrentShippingModule();
						shippingQuoteModule = this.shippingModules.get(shippingModule.getCode());
						configuration = modules.get(shippingModule.getCode());
					}
				}
			}

			//invoke module
			List<ShippingOption> shippingOptions = null;
					
			try {
				shippingOptions = shippingQuoteModule.getShippingQuotes(shippingQuote, packages, orderTotal, delivery, shippingOrigin, store, configuration, shippingModule, shippingConfiguration, locale);
			} catch(Exception e) {
				LOGGER.error("Error while calculating shipping", e);
				merchantLogService.save(
						new MerchantLog(store,
								"Can't process " + shippingModule.getModule()
								+ " -> "
								+ e.getMessage()));
				shippingQuote.setQuoteError(e.getMessage());
				shippingQuote.setShippingReturnCode(ShippingQuote.ERROR);
				return shippingQuote;
			}
			
			if(shippingOptions==null) {
				shippingQuote.setShippingReturnCode(ShippingQuote.NO_SHIPPING_TO_SELECTED_COUNTRY);
			}
			
			
			shippingQuote.setShippingModuleCode(moduleName);	
			
			//filter shipping options
			ShippingOptionPriceType shippingOptionPriceType = shippingConfiguration.getShippingOptionPriceType();
			ShippingOption selectedOption = null;
			
			if(shippingOptions!=null) {
				
				for(ShippingOption option : shippingOptions) {
					if(selectedOption==null) {
						selectedOption = option;
					}
					//set price text
					String priceText = pricingService.getDisplayAmount(option.getOptionPrice(), store);
					option.setOptionPriceText(priceText);
				
					if(StringUtils.isBlank(option.getOptionName())) {
						
						String countryName = delivery.getCountry().getName();
						if(countryName == null) {
							Map<String,Country> deliveryCountries = countryService.getCountriesMap(language);
							Country dCountry = (Country)deliveryCountries.get(delivery.getCountry().getIsoCode());
							if(dCountry!=null) {
								countryName = dCountry.getName();
							} else {
								countryName = delivery.getCountry().getIsoCode();
							}
						}
							option.setOptionName(countryName);		
					}
				
					if(shippingOptionPriceType.name().equals(ShippingOptionPriceType.HIGHEST.name())) {

						if (option.getOptionPrice()
								.longValue() > selectedOption
								.getOptionPrice()
								.longValue()) {
							selectedOption = option;
						}
					}

				
					if(shippingOptionPriceType.name().equals(ShippingOptionPriceType.LEAST.name())) {

						if (option.getOptionPrice()
								.longValue() < selectedOption
								.getOptionPrice()
								.longValue()) {
							selectedOption = option;
						}
					}
					
				
					if(shippingOptionPriceType.name().equals(ShippingOptionPriceType.ALL.name())) {
	
						if (option.getOptionPrice()
								.longValue() < selectedOption
								.getOptionPrice()
								.longValue()) {
							selectedOption = option;
						}
					}

				}
				
				shippingQuote.setSelectedShippingOption(selectedOption);
				
				if(selectedOption!=null && !shippingOptionPriceType.name().equals(ShippingOptionPriceType.ALL.name())) {
					shippingOptions = new ArrayList<ShippingOption>();
					shippingOptions.add(selectedOption);
				}
			
			}
			
			shippingQuote.setShippingOptions(shippingOptions);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		return shippingQuote;
		
	}

	@Override
	public List<String> getSupportedCountries(MerchantStore store) throws ServiceException {
		
		List<String> supportedCountries = new ArrayList<String>();
		MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(SUPPORTED_COUNTRIES, store);
		
		if(configuration!=null) {
			
			String countries = configuration.getValue();
			if(!StringUtils.isBlank(countries)) {

				Object objRegions=JSONValue.parse(countries); 
				JSONArray arrayRegions=(JSONArray)objRegions;
				@SuppressWarnings("rawtypes")
				Iterator i = arrayRegions.iterator();
				while(i.hasNext()) {
					supportedCountries.add((String)i.next());
				}
			}
			
		}
		
		return supportedCountries;
	}
	
	@Override
	public List<Country> getShipToCountryList(MerchantStore store, Language language) throws ServiceException {
		
		
		ShippingConfiguration shippingConfiguration = getShippingConfiguration(store);
		ShippingType shippingType = ShippingType.INTERNATIONAL;
		List<String> supportedCountries = new ArrayList<String>();
		if(shippingConfiguration==null) {
			shippingConfiguration = new ShippingConfiguration();
		}
		
		if(shippingConfiguration.getShippingType()!=null) {
				shippingType = shippingConfiguration.getShippingType();
		}

		
		if(shippingType.name().equals(ShippingType.NATIONAL.name())){
			
			supportedCountries.add(store.getCountry().getIsoCode());
			
		} else {

			MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(SUPPORTED_COUNTRIES, store);
			
			if(configuration!=null) {
				
				String countries = configuration.getValue();
				if(!StringUtils.isBlank(countries)) {

					Object objRegions=JSONValue.parse(countries); 
					JSONArray arrayRegions=(JSONArray)objRegions;
					@SuppressWarnings("rawtypes")
					Iterator i = arrayRegions.iterator();
					while(i.hasNext()) {
						supportedCountries.add((String)i.next());
					}
				}
				
			}

		}
		
		return countryService.getCountries(supportedCountries, language);

	}
	

	@Override
	public void setSupportedCountries(MerchantStore store, List<String> countryCodes) throws ServiceException {
		
		
		//transform a list of string to json entry
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String value  = mapper.writeValueAsString(countryCodes);
			
			MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(SUPPORTED_COUNTRIES, store);
			
			if(configuration==null) {
				configuration = new MerchantConfiguration();
				configuration.
				setKey(SUPPORTED_COUNTRIES);
				configuration.setMerchantStore(store);
			} 
			
			configuration.setValue(value);

			merchantConfigurationService.saveOrUpdate(configuration);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}
	

	private BigDecimal calculateOrderTotal(List<ShippingProduct> products, MerchantStore store) throws Exception {
		
		BigDecimal total = new BigDecimal(0);
		for(ShippingProduct shippingProduct : products) {
			BigDecimal currentPrice = shippingProduct.getFinalPrice().getFinalPrice();
			currentPrice = currentPrice.multiply(new BigDecimal(shippingProduct.getQuantity()));
			total = total.add(currentPrice);
		}
		
		
		return total;
		
		
	}

	@Override
	public List<PackageDetails> getPackagesDetails(
			List<ShippingProduct> products, MerchantStore store)
			throws ServiceException {
		
		List<PackageDetails> packages = null;
		
		ShippingConfiguration shippingConfiguration = this.getShippingConfiguration(store);
		//determine if the system has to use BOX or ITEM
		ShippingPackageType shippingPackageType = ShippingPackageType.ITEM;
		if(shippingConfiguration!=null) {
			shippingPackageType = shippingConfiguration.getShippingPackageType();
		}
		
		if(shippingPackageType.name().equals(ShippingPackageType.BOX.name())){
			packages = packaging.getBoxPackagesDetails(products, store);
		} else {
			packages = packaging.getItemPackagesDetails(products, store);
		}
		
		return packages;
		
	}

	@Override
	public boolean requiresShipping(List<ShoppingCartItem> items,
			MerchantStore store) throws ServiceException {

		boolean requiresShipping = false;
		for(ShoppingCartItem item : items) {
			Product product = item.getProduct();
			if(!product.isProductVirtual() && product.isProductShipeable()) {
				requiresShipping = true;
			}
		}

		return requiresShipping;		
	}
	




}



