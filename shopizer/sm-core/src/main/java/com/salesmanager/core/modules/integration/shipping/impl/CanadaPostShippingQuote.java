package com.salesmanager.core.modules.integration.shipping.impl;

import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.shipping.model.PackageDetails;
import com.salesmanager.core.business.shipping.model.ShippingConfiguration;
import com.salesmanager.core.business.shipping.model.ShippingOption;
import com.salesmanager.core.business.system.model.CustomIntegrationConfiguration;
import com.salesmanager.core.business.system.model.IntegrationConfiguration;
import com.salesmanager.core.business.system.model.IntegrationModule;
import com.salesmanager.core.business.system.model.MerchantLog;
import com.salesmanager.core.business.system.model.ModuleConfig;
import com.salesmanager.core.business.system.service.MerchantLogService;
import com.salesmanager.core.constants.MeasureUnit;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuoteModule;
import com.salesmanager.core.utils.DataUtils;
import com.salesmanager.core.utils.ProductPriceUtils;

/**
 * Integrates with Canada Post sell online API
 * @author casams1
 *
 */
public class CanadaPostShippingQuote implements ShippingQuoteModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CanadaPostShippingQuote.class);

	
	private final static String SHIPPING_TURN_AROUND_TIME = "24";


	@Autowired
	private ProductPriceUtils productPriceUtils;
	
	@Autowired
	private MerchantLogService merchantLogService;

	@Override
	public void validateModuleConfiguration(
			IntegrationConfiguration integrationConfiguration,
			MerchantStore store) throws IntegrationException {
		
		
		
		
		List<String> errorFields = null;
		
		//validate integrationKeys['account']
		Map<String,String> keys = integrationConfiguration.getIntegrationKeys();
		if(keys==null || StringUtils.isBlank(keys.get("account"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("identifier");
		}

		//validate at least one integrationOptions['packages']
		Map<String,List<String>> options = integrationConfiguration.getIntegrationOptions();
		if(options==null) {
			errorFields = new ArrayList<String>();
			errorFields.add("identifier");
		}
		
		List<String> packages = options.get("packages");
		if(packages==null || packages.size()==0) {
			if(errorFields==null) {
				errorFields = new ArrayList<String>();
			}
			errorFields.add("packages");
		}
		
		if(errorFields!=null) {
			IntegrationException ex = new IntegrationException(IntegrationException.ERROR_VALIDATION_SAVE);
			ex.setErrorFields(errorFields);
			throw ex;
			
		}

	}
	
	@Override
	public List<ShippingOption> getShippingQuotes(List<PackageDetails> packages, BigDecimal orderTotal, Delivery delivery, MerchantStore store, IntegrationConfiguration configuration, IntegrationModule module, ShippingConfiguration shippingConfiguration, Locale locale) throws IntegrationException {
		BigDecimal total = orderTotal;


		Validate.notNull(packages, "Packages are null");
		Validate.notNull(delivery.getPostalCode(), "Delivery postal code is null");
		
		List<ShippingOption> options = null;

		// only applies to Canada and US
		Country country = delivery.getCountry();
		

		
		
		if(!(country.getIsoCode().equals("US") || country.getIsoCode().equals("CA"))) {
			throw new IntegrationException("Canadapost Not configured for shipping in country " + country.getIsoCode());
		}

		// supports en and fr
		String language = locale.getLanguage();
		if (!language.equals(Locale.FRENCH.getLanguage())
				&& !language.equals(Locale.ENGLISH.getLanguage())) {
			language = Locale.ENGLISH.getLanguage();
		}
		

		// if store is not CAD /** maintained in the currency **/
/*		if (!store.getCurrency().equals(Constants.CURRENCY_CODE_CAD)) {
			total = CurrencyUtil.convertToCurrency(total, store.getCurrency(),
					Constants.CURRENCY_CODE_CAD);
		}*/

		
		PostMethod httppost = null;
		CanadaPostParsedElements canadaPost = null;

		try {
			
			Map<String,String> keys = configuration.getIntegrationKeys();
			if(keys==null || StringUtils.isBlank(keys.get("account"))) {
				throw new IntegrationException("Canadapost missing configuration key account");
			}

			
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
				}
			}
			


			// accept KG and CM

			StringBuilder request = new StringBuilder();

			request.append("<?xml version=\"1.0\" ?>");
			request.append("<eparcel>");
			request.append("<language>").append(language).append("</language>");

			request.append("<ratesAndServicesRequest>");
			request.append("<merchantCPCID>").append(keys.get("account")).append(
					"</merchantCPCID>");
			request.append("<fromPostalCode>").append(
					DataUtils
							.trimPostalCode(store.getStorepostalcode()))
					.append("</fromPostalCode>");
			request.append("<turnAroundTime>").append(SHIPPING_TURN_AROUND_TIME).append(
					"</turnAroundTime>");
			request.append("<itemsPrice>").append(
					productPriceUtils.getFormatedAmountWithCurrency(store,total, locale))
					.append("</itemsPrice>");
			request.append("<lineItems>");


			for(PackageDetails pack : packages) {
				request.append("<item>");
				request.append("<quantity>").append(pack.getShippingQuantity())
						.append("</quantity>");
				request.append("<weight>").append(
						String.valueOf(DataUtils.getWeight(pack
								.getShippingWeight(), store,
								MeasureUnit.KG.name()))).append("</weight>");
				request.append("<length>").append(
						String.valueOf(DataUtils.getMeasure(pack
								.getShippingLength(), store,
								MeasureUnit.CM.name()))).append("</length>");
				request.append("<width>").append(
						String.valueOf(DataUtils.getMeasure(pack
								.getShippingWidth(), store,
								MeasureUnit.CM.name()))).append("</width>");
				request.append("<height>").append(
						String.valueOf(DataUtils.getMeasure(pack
								.getShippingHeight(), store,
								MeasureUnit.CM.name()))).append("</height>");
				request.append("<description>").append(pack.getItemName())
						.append("</description>");
				request.append("<readyToShip/>");//item is properly packed
				request.append("</item>");
			}
			
			request.append("</lineItems>");
			
			request.append("<city>").append(delivery.getCity()).append(
					"</city>");
			if(delivery.getZone()!=null) {
				request.append("<provOrState>").append(delivery.getZone().getCode())
					.append("</provOrState>");
			} else {
				request.append("<provOrState>").append(delivery.getZone())
				.append("</provOrState>");				
			}
			request.append("<country>")
					.append(delivery.getCountry().getIsoCode()).append(
							"</country>");
			request.append("<postalCode>").append(
					DataUtils
					.trimPostalCode(delivery.getPostalCode()))
					.append("</postalCode>");
			request.append("</ratesAndServicesRequest>");
			request.append("</eparcel>");


			/**
			 * <?xml version="1.0" ?> <eparcel>
			 * <!--********************************--> <!-- Prefered language
			 * for the --> <!-- response (FR/EN) (optional) -->
			 * <!--********************************--> <language>en</language>
			 * 
			 * <ratesAndServicesRequest>
			 * <!--**********************************--> <!-- Merchant
			 * Identification assigned --> <!-- by Canada Post --> <!-- --> <!--
			 * Note: Use 'CPC_DEMO_HTML' or ask --> <!-- our Help Desk to change
			 * your --> <!-- profile if you want HTML to be --> <!-- returned to
			 * you --> <!--**********************************--> <merchantCPCID>
			 * CPC_DEMO_XML </merchantCPCID>
			 * 
			 * <!--*********************************--> <!--Origin Postal Code
			 * --> <!--This parameter is optional -->
			 * <!--*********************************-->
			 * <fromPostalCode>m1p1c0</fromPostalCode>
			 * 
			 * <!--**********************************--> <!-- Turn Around Time
			 * (hours) --> <!-- This parameter is optional -->
			 * <!--**********************************--> <turnAroundTime> 24
			 * </turnAroundTime>
			 * 
			 * <!--**********************************--> <!-- Total amount in $
			 * of the items --> <!-- for insurance calculation --> <!-- This
			 * parameter is optional -->
			 * <!--**********************************-->
			 * <itemsPrice>0.00</itemsPrice>
			 * 
			 * <!--**********************************--> <!-- List of items in
			 * the shopping --> <!-- cart --> <!-- Each item is defined by : -->
			 * <!-- - quantity (mandatory) --> <!-- - size (mandatory) --> <!--
			 * - weight (mandatory) --> <!-- - description (mandatory) --> <!--
			 * - ready to ship (optional) -->
			 * <!--**********************************--> <lineItems> <item>
			 * <quantity> 1 </quantity> <weight> 1.491 </weight> <length> 1
			 * </length> <width> 1 </width> <height> 1 </height> <description>
			 * KAO Diskettes </description> </item>
			 * 
			 * <item> <quantity> 1 </quantity> <weight> 1.5 </weight> <length>
			 * 20 </length> <width> 30 </width> <height> 20 </height>
			 * <description> My Ready To Ship Item</description>
			 * <!--**********************************************--> <!-- By
			 * adding the 'readyToShip' tag, Sell Online --> <!-- will not pack
			 * this item in the boxes --> <!-- defined in the merchant profile.
			 * --> <!-- Instead, this item will be shipped in its --> <!--
			 * original box: 1.5 kg and 20x30x20 cm -->
			 * <!--**********************************************-->
			 * <readyToShip/> </item> </lineItems>
			 * 
			 * <!--********************************--> <!-- City where the
			 * parcel will be --> <!-- shipped to -->
			 * <!--********************************--> <city> </city>
			 * 
			 * <!--********************************--> <!-- Province (Canada) or
			 * State (US)--> <!-- where the parcel will be --> <!-- shipped to
			 * --> <!--********************************--> <provOrState>
			 * Wisconsin </provOrState>
			 * 
			 * <!--********************************--> <!-- Country or ISO
			 * Country code --> <!-- where the parcel will be --> <!-- shipped
			 * to --> <!--********************************--> <country> CANADA
			 * </country>
			 * 
			 * <!--********************************--> <!-- Postal Code (or ZIP)
			 * where the --> <!-- parcel will be shipped to -->
			 * <!--********************************--> <postalCode>
			 * H3K1E5</postalCode> </ratesAndServicesRequest> </eparcel>
			 **/
			

			LOGGER.debug("canadapost request " + request.toString());

			HttpClient client = new HttpClient();
			
			StringBuilder u = new StringBuilder().append(protocol).append("://").append(host).append(":").append(port);
			if(!StringUtils.isBlank(url)) {
				u.append(url);
			}
			
			LOGGER.debug("Canadapost URL " + u.toString());

			httppost = new PostMethod(u.toString());
			RequestEntity entity = new StringRequestEntity(request.toString(),
					"text/plain", "UTF-8");
			httppost.setRequestEntity(entity);

			int result = client.executeMethod(httppost);

			if (result != 200) {
				LOGGER.error("Communication Error with canadapost " + protocol
						+ "://" + host + ":" + port + url);
				throw new Exception("Communication Error with canadapost "
						+ protocol + "://" + host + ":" + port + url);
			}
			String stringresult = httppost.getResponseBodyAsString();
			LOGGER.debug("canadapost response " + stringresult);
			
			
/*			<eparcel>
			<error>
			<statusCode>-3001</statusCode>
			<statusMessage>Destination Postal Code/State Name/ Country is illegal. </statusMessage>
			<requestID>2773909</requestID>
			</error>
			</eparcel>*/

			canadaPost = new CanadaPostParsedElements();
			Digester digester = new Digester();
			digester.push(canadaPost);
			
			digester.addCallMethod(
					"eparcel/error/statusCode",
					"setStatusCode", 0);
			
			digester.addCallMethod(
					"eparcel/error/statusMessage",
					"setStatusMessage", 0);

			digester.addCallMethod(
					"eparcel/ratesAndServicesResponse/statusCode",
					"setStatusCode", 0);
			digester.addCallMethod(
					"eparcel/ratesAndServicesResponse/statusMessage",
					"setStatusMessage", 0);
			digester.addObjectCreate(
					"eparcel/ratesAndServicesResponse/product",
					ShippingOption.class);
			digester.addSetProperties(
					"eparcel/ratesAndServicesResponse/product", "sequence",
					"optionId");
			digester.addCallMethod(
					"eparcel/ratesAndServicesResponse/product/shippingDate",
					"setOptionShippingDate", 0);
			digester.addCallMethod(
					"eparcel/ratesAndServicesResponse/product/deliveryDate",
					"setOptionDeliveryDate", 0);
			digester.addCallMethod(
					"eparcel/ratesAndServicesResponse/product/name",
					"setOptionName", 0);
			digester.addCallMethod(
					"eparcel/ratesAndServicesResponse/product/rate",
					"setOptionPriceText", 0);
			
			digester.addSetNext("eparcel/ratesAndServicesResponse/product",
					"addOption");

			/**
			 * response
			 * 
			 * <?xml version="1.0" ?> <!DOCTYPE eparcel (View Source for full
			 * doctype...)> - <eparcel> - <ratesAndServicesResponse>
			 * <statusCode>1</statusCode> <statusMessage>OK</statusMessage>
			 * <requestID>1769506</requestID> <handling>0.0</handling>
			 * <language>0</language> - <product id="1040" sequence="1">
			 * <name>Priority Courier</name> <rate>38.44</rate>
			 * <shippingDate>2008-12-22</shippingDate>
			 * <deliveryDate>2008-12-23</deliveryDate>
			 * <deliveryDayOfWeek>3</deliveryDayOfWeek>
			 * <nextDayAM>true</nextDayAM> <packingID>P_0</packingID> </product>
			 * - <product id="1020" sequence="2"> <name>Expedited</name>
			 * <rate>16.08</rate> <shippingDate>2008-12-22</shippingDate>
			 * <deliveryDate>2008-12-23</deliveryDate>
			 * <deliveryDayOfWeek>3</deliveryDayOfWeek>
			 * <nextDayAM>false</nextDayAM> <packingID>P_0</packingID>
			 * </product> - <product id="1010" sequence="3">
			 * <name>Regular</name> <rate>16.08</rate>
			 * <shippingDate>2008-12-22</shippingDate>
			 * <deliveryDate>2008-12-29</deliveryDate>
			 * <deliveryDayOfWeek>2</deliveryDayOfWeek>
			 * <nextDayAM>false</nextDayAM> <packingID>P_0</packingID>
			 * </product> - <packing> <packingID>P_0</packingID> - <box>
			 * <name>Small Box</name> <weight>1.691</weight>
			 * <expediterWeight>1.691</expediterWeight> <length>25.0</length>
			 * <width>17.0</width> <height>16.0</height> - <packedItem>
			 * <quantity>1</quantity> <description>KAO Diskettes</description>
			 * </packedItem> </box> - <box> <name>My Ready To Ship Item</name>
			 * <weight>2.0</weight> <expediterWeight>1.5</expediterWeight>
			 * <length>30.0</length> <width>20.0</width> <height>20.0</height> -
			 * <packedItem> <quantity>1</quantity> <description>My Ready To Ship
			 * Item</description> </packedItem> </box> </packing> -
			 * <shippingOptions> <insurance>No</insurance>
			 * <deliveryConfirmation>Yes</deliveryConfirmation>
			 * <signature>No</signature> </shippingOptions> <comment />
			 * </ratesAndServicesResponse> </eparcel> - <!-- END_OF_EPARCEL -->
			 **/
			
			Reader reader = new StringReader(stringresult);
			
			digester.parse(reader);

		
			if (canadaPost == null || canadaPost.getStatusCode() == null) {
				LOGGER.error("Nothing received from CanadaPost");
				return null;
			}
			
			System.out.println(canadaPost.getStatusCode());
	
			if (canadaPost.getStatusCode().equals("-6")
					|| canadaPost.getStatusCode().equals("-7")) {
				merchantLogService.save(
						new MerchantLog(store,
						"Can't process CanadaPost statusCode="
								+ canadaPost.getStatusCode() + " message= "
								+ canadaPost.getStatusMessage()));
			}
			
			if (canadaPost.getStatusCode().equals("-5000")) {
				merchantLogService.save(
						new MerchantLog(store,("An error occured with canadapost request (code-> "
						+ canadaPost.getStatusCode() + " message-> "
						+ canadaPost.getStatusMessage() )));
				throw new IntegrationException("Error with post canada service " + canadaPost.getStatusMessage());
			}
	
			if (!canadaPost.getStatusCode().equals("1")) {
				merchantLogService.save(
						new MerchantLog(store,("An error occured with canadapost request (code-> "
						+ canadaPost.getStatusCode() + " message-> "
						+ canadaPost.getStatusMessage() )));
				throw new IntegrationException("Error with post canada service " + canadaPost.getStatusMessage());
			}

		//String carrier = getShippingMethodDescription(locale);
		// cost is in CAD, need to do conversion

/*		boolean requiresCurrencyConversion = false;
		String storeCurrency = store.getCurrency();
		if (!storeCurrency.equals(Constants.CURRENCY_CODE_CAD)) {
			requiresCurrencyConversion = true;
		}*/
		
		
		



/*			options = canadaPost.getOptions();
			for(ShippingOption option : options) {
					StringBuilder description = new StringBuilder();
					description.append(option.getOptionName());
					if (shippingConfiguration.getShippingDescription()==ShippingDescription.LONG_DESCRIPTION) {
						description.append(" (").append(option.getOptionDeliveryDate())
								.append(")");
					}
					option.setDescription(description.toString());
						if (requiresCurrencyConversion) {
						option.setOptionPrice(CurrencyUtil.convertToCurrency(option
								.getOptionPrice(), Constants.CURRENCY_CODE_CAD,
								store.getCurrency()));
					}
					// System.out.println(option.getOptionPrice().toString());

			}*/

		
		
		} catch (Exception e) {
			LOGGER.error("Canadapost getShippingQuote", e);
			throw new IntegrationException(e);
		} finally {
				if (httppost != null) {
					httppost.releaseConnection();
				}
		}

		

		return options;
	}



	@Override
	public CustomIntegrationConfiguration getCustomModuleConfiguration(
			MerchantStore store) throws IntegrationException {
		//nothing to do
		return null;
	}
	





}

class CanadaPostParsedElements {

	private String statusCode;
	private String statusMessage;
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

}


