package com.salesmanager.core.business.modules.integration.shipping.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.digester.Digester;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.utils.DataUtils;
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


/**
 * Integrates with UPS online API
 * @author casams1
 *
 */
public class UPSShippingQuote implements ShippingQuoteModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UPSShippingQuote.class);


	@Override
	public void validateModuleConfiguration(
			IntegrationConfiguration integrationConfiguration,
			MerchantStore store) throws IntegrationException {
		
		
		List<String> errorFields = null;
		
		//validate integrationKeys['accessKey']
		Map<String,String> keys = integrationConfiguration.getIntegrationKeys();
		if(keys==null || StringUtils.isBlank(keys.get("accessKey"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("accessKey");
		}
		
		if(keys==null || StringUtils.isBlank(keys.get("userId"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("userId");
		}
		
		if(keys==null || StringUtils.isBlank(keys.get("password"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("password");
		}

		//validate at least one integrationOptions['packages']
		Map<String,List<String>> options = integrationConfiguration.getIntegrationOptions();
		if(options==null) {
			errorFields = new ArrayList<String>();
			errorFields.add("packages");
		}
		
		List<String> packages = options.get("packages");
		if(packages==null || packages.size()==0) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("packages");
		}
		
/*		List<String> services = options.get("services");
		if(services==null || services.size()==0) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("services");
		}
		
		if(services!=null && services.size()>3) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("services");
		}*/
		
		if(errorFields!=null) {
			IntegrationException ex = new IntegrationException(IntegrationException.ERROR_VALIDATION_SAVE);
			ex.setErrorFields(errorFields);
			throw ex;
			
		}
		
		

	}

	@Override
	public List<ShippingOption> getShippingQuotes(
			ShippingQuote shippingQuote,
			List<PackageDetails> packages, BigDecimal orderTotal,
			Delivery delivery, ShippingOrigin origin, MerchantStore store,
			IntegrationConfiguration configuration, IntegrationModule module,
			ShippingConfiguration shippingConfiguration, Locale locale)
			throws IntegrationException {
		
		Validate.notNull(configuration, "IntegrationConfiguration must not be null for USPS shipping module");

		
		if(StringUtils.isBlank(delivery.getPostalCode())) {
			return null;
		}
		
		BigDecimal total = orderTotal;

		if (packages == null) {
			return null;
		}
		
		List<ShippingOption> options = null;

		// only applies to Canada and US
		Country country = delivery.getCountry();
		

		
		if(!(country.getIsoCode().equals("US") || country.getIsoCode().equals("CA"))) {
			return null;
			//throw new IntegrationException("UPS Not configured for shipping in country " + country.getIsoCode());
		}

		// supports en and fr
		String language = locale.getLanguage();
		if (!language.equals(Locale.FRENCH.getLanguage())
				&& !language.equals(Locale.ENGLISH.getLanguage())) {
			language = Locale.ENGLISH.getLanguage();
		}
		
		String pack = configuration.getIntegrationOptions().get("packages").get(0);
		Map<String,String> keys = configuration.getIntegrationKeys();
		
		String accessKey = keys.get("accessKey");
		String userId = keys.get("userId");
		String password = keys.get("password");
		
		
		String host = null;
		String protocol = null;
		String port = null;
		String url = null;
		
		StringBuilder xmlbuffer = new StringBuilder();
		HttpPost httppost = null;
		BufferedReader reader = null;

		try {
			String env = configuration.getEnvironment();
			
			Set<String> regions = module.getRegionsSet();
			if(!regions.contains(store.getCountry().getIsoCode())) {
				throw new IntegrationException("Can't use the service for store country code ");
			}
			
			Map<String, ModuleConfig> moduleConfigsMap = module.getModuleConfigs();
			for(String key : moduleConfigsMap.keySet()) {
				
				ModuleConfig moduleConfig = (ModuleConfig)moduleConfigsMap.get(key);
				if(moduleConfig.getEnv().equals(env)) {
					host = moduleConfig.getHost();
					protocol = moduleConfig.getScheme();
					port = moduleConfig.getPort();
					url = moduleConfig.getUri();
				}
			}

			
			StringBuilder xmlreqbuffer = new StringBuilder();
			xmlreqbuffer.append("<?xml version=\"1.0\"?>");
			xmlreqbuffer.append("<AccessRequest>");
			xmlreqbuffer.append("<AccessLicenseNumber>");
			xmlreqbuffer.append(accessKey);
			xmlreqbuffer.append("</AccessLicenseNumber>");
			xmlreqbuffer.append("<UserId>");
			xmlreqbuffer.append(userId);
			xmlreqbuffer.append("</UserId>");
			xmlreqbuffer.append("<Password>");
			xmlreqbuffer.append(password);
			xmlreqbuffer.append("</Password>");
			xmlreqbuffer.append("</AccessRequest>");
			
			String xmlhead = xmlreqbuffer.toString();
			

			String weightCode = store.getWeightunitcode();
			String measureCode = store.getSeizeunitcode();

			if (weightCode.equals("KG")) {
				weightCode = "KGS";
			} else {
				weightCode = "LBS";
			}

			String xml = "<?xml version=\"1.0\"?><RatingServiceSelectionRequest><Request><TransactionReference><CustomerContext>Shopizer</CustomerContext><XpciVersion>1.0001</XpciVersion></TransactionReference><RequestAction>Rate</RequestAction><RequestOption>Shop</RequestOption></Request>";
			StringBuffer xmldatabuffer = new StringBuffer();

			/**
			 * <Shipment>
			 * 
			 * <Shipper> <Address> <City></City>
			 * <StateProvinceCode>QC</StateProvinceCode>
			 * <CountryCode>CA</CountryCode> <PostalCode></PostalCode>
			 * </Address> </Shipper>
			 * 
			 * <ShipTo> <Address> <City>Redwood Shores</City>
			 * <StateProvinceCode>CA</StateProvinceCode>
			 * <CountryCode>US</CountryCode> <PostalCode></PostalCode>
			 * <ResidentialAddressIndicator/> </Address> </ShipTo>
			 * 
			 * <Package> <PackagingType> <Code>21</Code> </PackagingType>
			 * <PackageWeight> <UnitOfMeasurement> <Code>LBS</Code>
			 * </UnitOfMeasurement> <Weight>1.1</Weight> </PackageWeight>
			 * <PackageServiceOptions> <InsuredValue>
			 * <CurrencyCode>CAD</CurrencyCode>
			 * <MonetaryValue>100</MonetaryValue> </InsuredValue>
			 * </PackageServiceOptions> </Package>
			 * 
			 * 
			 * </Shipment>
			 * 
			 * <CustomerClassification> <Code>03</Code>
			 * </CustomerClassification> </RatingServiceSelectionRequest>
			 * **/

			/**Map countriesMap = (Map) RefCache.getAllcountriesmap(LanguageUtil
					.getLanguageNumberCode(locale.getLanguage()));
			Map zonesMap = (Map) RefCache.getAllZonesmap(LanguageUtil
					.getLanguageNumberCode(locale.getLanguage()));

			Country storeCountry = (Country) countriesMap.get(store
					.getCountry());

			Country customerCountry = (Country) countriesMap.get(customer
					.getCustomerCountryId());

			int sZone = -1;
			try {
				sZone = Integer.parseInt(store.getZone());
			} catch (Exception e) {
				// TODO: handle exception
			}

			Zone storeZone = (Zone) zonesMap.get(sZone);
			Zone customerZone = (Zone) zonesMap.get(customer
					.getCustomerZoneId());**/
					
				

			xmldatabuffer.append("<PickupType><Code>03</Code></PickupType>");
			// xmldatabuffer.append("<Description>Daily Pickup</Description>");
			xmldatabuffer.append("<Shipment><Shipper>");
			xmldatabuffer.append("<Address>");
			xmldatabuffer.append("<City>");
			xmldatabuffer.append(store.getStorecity());
			xmldatabuffer.append("</City>");
			// if(!StringUtils.isBlank(store.getStorestateprovince())) {
			if (store.getZone() != null) {
				xmldatabuffer.append("<StateProvinceCode>");
				xmldatabuffer.append(store.getZone().getCode());// zone code
				xmldatabuffer.append("</StateProvinceCode>");
			}
			xmldatabuffer.append("<CountryCode>");
			xmldatabuffer.append(store.getCountry().getIsoCode());
			xmldatabuffer.append("</CountryCode>");
			xmldatabuffer.append("<PostalCode>");
			xmldatabuffer.append(DataUtils
					.trimPostalCode(store.getStorepostalcode()));
			xmldatabuffer.append("</PostalCode></Address></Shipper>");

			// ship to
			xmldatabuffer.append("<ShipTo>");
			xmldatabuffer.append("<Address>");
			xmldatabuffer.append("<City>");
			xmldatabuffer.append(delivery.getCity());
			xmldatabuffer.append("</City>");
			// if(!StringUtils.isBlank(customer.getCustomerState())) {
			if (delivery.getZone() != null) {
				xmldatabuffer.append("<StateProvinceCode>");
				xmldatabuffer.append(delivery.getZone().getCode());// zone code
				xmldatabuffer.append("</StateProvinceCode>");
			}
			xmldatabuffer.append("<CountryCode>");
			xmldatabuffer.append(delivery.getCountry().getIsoCode());
			xmldatabuffer.append("</CountryCode>");
			xmldatabuffer.append("<PostalCode>");
			xmldatabuffer.append(DataUtils
					.trimPostalCode(delivery.getPostalCode()));
			xmldatabuffer.append("</PostalCode></Address></ShipTo>");
			// xmldatabuffer.append("<Service><Code>11</Code></Service>");//TODO service codes (next day ...)


			for(PackageDetails packageDetail : packages){

				xmldatabuffer.append("<Package>");
				xmldatabuffer.append("<PackagingType>");
				xmldatabuffer.append("<Code>");
				xmldatabuffer.append(pack);
				xmldatabuffer.append("</Code>");
				xmldatabuffer.append("</PackagingType>");

				// weight
				xmldatabuffer.append("<PackageWeight>");
				xmldatabuffer.append("<UnitOfMeasurement>");
				xmldatabuffer.append("<Code>");
				xmldatabuffer.append(weightCode);
				xmldatabuffer.append("</Code>");
				xmldatabuffer.append("</UnitOfMeasurement>");
				xmldatabuffer.append("<Weight>");
				xmldatabuffer.append(new BigDecimal(packageDetail.getShippingWeight())
						.setScale(1, BigDecimal.ROUND_HALF_UP));
				xmldatabuffer.append("</Weight>");
				xmldatabuffer.append("</PackageWeight>");

				// dimension
				xmldatabuffer.append("<Dimensions>");
				xmldatabuffer.append("<UnitOfMeasurement>");
				xmldatabuffer.append("<Code>");
				xmldatabuffer.append(measureCode);
				xmldatabuffer.append("</Code>");
				xmldatabuffer.append("</UnitOfMeasurement>");
				xmldatabuffer.append("<Length>");
				xmldatabuffer.append(new BigDecimal(packageDetail.getShippingLength())
						.setScale(2, BigDecimal.ROUND_HALF_UP));
				xmldatabuffer.append("</Length>");
				xmldatabuffer.append("<Width>");
				xmldatabuffer.append(new BigDecimal(packageDetail.getShippingWidth())
						.setScale(2, BigDecimal.ROUND_HALF_UP));
				xmldatabuffer.append("</Width>");
				xmldatabuffer.append("<Height>");
				xmldatabuffer.append(new BigDecimal(packageDetail.getShippingHeight())
						.setScale(2, BigDecimal.ROUND_HALF_UP));
				xmldatabuffer.append("</Height>");
				xmldatabuffer.append("</Dimensions>");
				xmldatabuffer.append("</Package>");

			}

			xmldatabuffer.append("</Shipment>");
			xmldatabuffer.append("</RatingServiceSelectionRequest>");

			xmlbuffer.append(xmlhead).append(xml).append(
					xmldatabuffer.toString());
			


			LOGGER.debug("UPS QUOTE REQUEST " + xmlbuffer.toString());


			try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
			//HttpClient client = new HttpClient();
			httppost = new HttpPost(protocol + "://" + host + ":" + port
					+ url);
			StringEntity entity = new StringEntity(xmlbuffer.toString(),ContentType.APPLICATION_ATOM_XML);
			//RequestEntity entity = new StringRequestEntity(
			//		xmlbuffer.toString(), "text/plain", "UTF-8");
			httppost.setEntity(entity);
            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                    	LOGGER.error("Communication Error with ups quote " + status);
        				throw new ClientProtocolException("UPS quote communication error " + status);
                    }
                }

            };
			String data = httpclient.execute(httppost, responseHandler);

			//int result = response.getStatusLine().getStatusCode();
			//int result = client.executeMethod(httppost);
/*			if (result != 200) {
				LOGGER.error("Communication Error with ups quote " + result + " "
						+ protocol + "://" + host + ":" + port + url);
				throw new Exception("UPS quote communication error " + result);
			}*/

			LOGGER.debug("ups quote response " + data);

			UPSParsedElements parsed = new UPSParsedElements();

			Digester digester = new Digester();
			digester.push(parsed);
			digester.addCallMethod(
					"RatingServiceSelectionResponse/Response/Error",
					"setErrorCode", 0);
			digester.addCallMethod(
					"RatingServiceSelectionResponse/Response/ErrorDescriprion",
					"setError", 0);
			digester
					.addCallMethod(
							"RatingServiceSelectionResponse/Response/ResponseStatusCode",
							"setStatusCode", 0);
			digester
					.addCallMethod(
							"RatingServiceSelectionResponse/Response/ResponseStatusDescription",
							"setStatusMessage", 0);
			digester
					.addCallMethod(
							"RatingServiceSelectionResponse/Response/Error/ErrorDescription",
							"setError", 0);

			digester.addObjectCreate(
					"RatingServiceSelectionResponse/RatedShipment",
					ShippingOption.class);
			// digester.addSetProperties(
			// "RatingServiceSelectionResponse/RatedShipment", "sequence",
			// "optionId" );
			digester
					.addCallMethod(
							"RatingServiceSelectionResponse/RatedShipment/Service/Code",
							"setOptionId", 0);
			digester
					.addCallMethod(
							"RatingServiceSelectionResponse/RatedShipment/TotalCharges/MonetaryValue",
							"setOptionPriceText", 0);
			//digester
			//		.addCallMethod(
			//				"RatingServiceSelectionResponse/RatedShipment/TotalCharges/CurrencyCode",
			//				"setCurrency", 0);
			digester
					.addCallMethod(
							"RatingServiceSelectionResponse/RatedShipment/Service/Code",
							"setOptionCode", 0);
			digester
					.addCallMethod(
							"RatingServiceSelectionResponse/RatedShipment/GuaranteedDaysToDelivery",
							"setEstimatedNumberOfDays", 0);
			digester.addSetNext("RatingServiceSelectionResponse/RatedShipment",
					"addOption");

			// <?xml
			// version="1.0"?><AddressValidationResponse><Response><TransactionReference><CustomerContext>SalesManager
			// Data</CustomerContext><XpciVersion>1.0</XpciVersion></TransactionReference><ResponseStatusCode>0</ResponseStatusCode><ResponseStatusDescription>Failure</ResponseStatusDescription><Error><ErrorSeverity>Hard</ErrorSeverity><ErrorCode>10002</ErrorCode><ErrorDescription>The
			// XML document is well formed but the document is not
			// valid</ErrorDescription><ErrorLocation><ErrorLocationElementName>AddressValidationRequest</ErrorLocationElementName></ErrorLocation></Error></Response></AddressValidationResponse>

			Reader xmlreader = new StringReader(data);

			digester.parse(xmlreader);

			if (!StringUtils.isBlank(parsed.getErrorCode())) {

					LOGGER.error("Can't process UPS statusCode="
							+ parsed.getErrorCode() + " message= "
							+ parsed.getError());
				throw new IntegrationException(parsed.getError());
			}
			if (!StringUtils.isBlank(parsed.getStatusCode())
					&& !parsed.getStatusCode().equals("1")) {

				throw new IntegrationException(parsed.getError());
			}

			if (parsed.getOptions() == null || parsed.getOptions().size() == 0) {

				throw new IntegrationException("No shipping options available for the configuration");
			}

			/*String carrier = getShippingMethodDescription(locale);
			// cost is in CAD, need to do conversion

			boolean requiresCurrencyConversion = false; String storeCurrency
			 = store.getCurrency();
			if(!storeCurrency.equals(Constants.CURRENCY_CODE_CAD)) {
			 requiresCurrencyConversion = true; }

			LabelUtil labelUtil = LabelUtil.getInstance();
			Map serviceMap = com.salesmanager.core.util.ShippingUtil
					.buildServiceMap("upsxml", locale);

			*//** Details on whit RT quote information to display **//*
			MerchantConfiguration rtdetails = config
					.getMerchantConfiguration(ShippingConstants.MODULE_SHIPPING_DISPLAY_REALTIME_QUOTES);
			int displayQuoteDeliveryTime = ShippingConstants.NO_DISPLAY_RT_QUOTE_TIME;
			if (rtdetails != null) {

				if (!StringUtils.isBlank(rtdetails.getConfigurationValue1())) {// display
																				// or
																				// not
																				// quotes
					try {
						displayQuoteDeliveryTime = Integer.parseInt(rtdetails
								.getConfigurationValue1());

					} catch (Exception e) {
						log.error("Display quote is not an integer value ["
								+ rtdetails.getConfigurationValue1() + "]");
					}
				}
			}*/

			List<ShippingOption> shippingOptions = parsed.getOptions();
			if(shippingOptions!=null) {
				Map<String,String> details = module.getDetails();
				for(ShippingOption option : shippingOptions) {
					String name = details.get(option.getOptionCode());
					option.setOptionName(name);
					if(option.getOptionPrice()==null) {
						String priceText = option.getOptionPriceText();
						if(StringUtils.isBlank(priceText)) {
							throw new IntegrationException("Price text is null for option " + name);
						}
						try {
							BigDecimal price = new BigDecimal(priceText);
							option.setOptionPrice(price);
						} catch(Exception e) {
							throw new IntegrationException("Can't convert to numeric price " + priceText);
						}
					}
				}
			}
/*			if (options != null) {

				Map selectedintlservices = (Map) config
						.getConfiguration("service-global-upsxml");

				Iterator i = options.iterator();
				while (i.hasNext()) {
					ShippingOption option = (ShippingOption) i.next();
					// option.setCurrency(store.getCurrency());
					StringBuffer description = new StringBuffer();

					String code = option.getOptionCode();
					option.setOptionCode(code);
					// get description
					String label = (String) serviceMap.get(code);
					if (label == null) {
						log
								.warn("UPSXML cannot find description for service code "
										+ code);
					}

					option.setOptionName(label);

					description.append(option.getOptionName());
					if (displayQuoteDeliveryTime == ShippingConstants.DISPLAY_RT_QUOTE_TIME) {
						if (!StringUtils.isBlank(option
								.getEstimatedNumberOfDays())) {
							description.append(" (").append(
									option.getEstimatedNumberOfDays()).append(
									" ").append(
									labelUtil.getText(locale,
											"label.generic.days.lowercase"))
									.append(")");
						}
					}
					option.setDescription(description.toString());

					// get currency
					if (!option.getCurrency().equals(store.getCurrency())) {
						option.setOptionPrice(CurrencyUtil.convertToCurrency(
								option.getOptionPrice(), option.getCurrency(),
								store.getCurrency()));
					}

					if (!selectedintlservices.containsKey(option
							.getOptionCode())) {
						if (returnColl == null) {
							returnColl = new ArrayList();
						}
						returnColl.add(option);
						// options.remove(option);
					}

				}

				if (options.size() == 0) {
					LogMerchantUtil
							.log(
									store.getMerchantId(),
									" none of the service code returned by UPS ["
											+ selectedintlservices
													.keySet()
													.toArray(
															new String[selectedintlservices
																	.size()])
											+ "] for this shipping is in your selection list");
				}
			}*/



			return shippingOptions;
	}
		} catch (Exception e1) {
			LOGGER.error("UPS quote error",e1);
			throw new IntegrationException(e1);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception ignore) {
				}
			}

			if (httppost != null) {
				httppost.releaseConnection();
			}
		}
}


	@Override
	public CustomIntegrationConfiguration getCustomModuleConfiguration(
			MerchantStore store) throws IntegrationException {
		//nothing to do
		return null;
	}}


class UPSParsedElements  {

	private String statusCode;
	private String statusMessage;
	private String error = "";
	private String errorCode = "";
	private List<ShippingOption> options = new ArrayList<ShippingOption>();

	public void addOption(ShippingOption option) {
		options.add(option);
	}

	public List<ShippingOption> getOptions() {
		return options;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
