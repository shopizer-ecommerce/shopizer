package com.shopizer.modules.shipping.canadapost.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.shipping.PackageDetails;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingOption;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.model.system.ModuleConfig;
import com.shopizer.modules.shipping.canadapost.CanadaPostQuoteModule;

import junit.framework.Assert;


/**
 * Create a developer account on canada post web site
 * generate your API developer's key
 * https://www.canadapost.ca/cpo/mc/business/productsservices/developers/services/rating/default.jsf
 * @author carlsamson
 *
 */
@ContextConfiguration(locations = {
		"classpath:spring/spring-context-test.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class
})
public class ShippingCanadaPostTestCase {
	
	@Inject
	CanadaPostQuoteModule canadapost;

	 @Inject
	 @Qualifier("canadapost-properties")
	 private Properties userProps;

	@Test
	public void getCanadaPostShippingQuote() throws Exception {
		

    	String username = userProps.getProperty("username");
    	String password = userProps.getProperty("password");
    	String mailedBy = userProps.getProperty("mailBy"); 
		
		ShippingQuote quote = new ShippingQuote();
		PackageDetails pDetail = new PackageDetails();
		pDetail.setShippingHeight(10);
		pDetail.setShippingLength(10);
		pDetail.setShippingWeight(10);
		pDetail.setShippingWidth(10);
		List<PackageDetails> details = new ArrayList<PackageDetails>();
		details.add(pDetail);

		Delivery delivery = new Delivery();
		delivery.setAddress("358 Du Languedoc");
		delivery.setCity("Boucherville");
		delivery.setPostalCode("J4B 8J9");
		
		Country country = new Country();
		country.setIsoCode("CA");
		country.setName("Canada");
		delivery.setCountry(country);
		
		Zone zone = new Zone();
		zone.setCode("QC");
		zone.setName("Quebec");
		
		delivery.setZone(zone);
		
		ShippingOrigin origin = new ShippingOrigin();
		origin.setAddress("7070, avenue Henri-Julien");
		origin.setCity("Montreal");
		origin.setPostalCode("H2S 3S3");
		origin.setZone(zone);
		origin.setCountry(country);
		
		IntegrationModule module = new IntegrationModule();
		ModuleConfig moduleConfigTest = new ModuleConfig();
		moduleConfigTest.setEnv("TEST");
		moduleConfigTest.setScheme("https");
		moduleConfigTest.setHost("ct.soa-gw.canadapost.ca");
		moduleConfigTest.setPort("443");
		moduleConfigTest.setUri("/rs/ship/price");
		
		ModuleConfig moduleConfigProd = new ModuleConfig();
		moduleConfigProd.setEnv("PROD");
		moduleConfigProd.setScheme("https");
		moduleConfigProd.setHost("soa-gw.canadapost.ca");
		moduleConfigProd.setPort("443");
		moduleConfigProd.setUri("/rs/ship/price");
		
		Map<String,ModuleConfig> moduleConfigs = new HashMap<String,ModuleConfig>();
		moduleConfigs.put("TEST", moduleConfigTest);
		moduleConfigs.put("PROD", moduleConfigProd);
		
		module.setModuleConfigs(moduleConfigs);
		
		module.setCode("canadapost");
		module.setModuleConfigs(moduleConfigs);
		
		IntegrationConfiguration configuration = new IntegrationConfiguration();
		configuration.setActive(true);
		configuration.setEnvironment("TEST");
		
		Map<String,String> integrationKeys= new HashMap<String,String>();
		integrationKeys.put("username", username);
		integrationKeys.put("password", password);
		integrationKeys.put("client", mailedBy);
		
		configuration.setIntegrationKeys(integrationKeys);

		Map<String,List<String>> integrationOptions= new HashMap<String,List<String>>();
		
		
		/**
		 *  DOM.RP 	Regular Parcel
			DOM.EP 	Expedited Parcel
			DOM.XP 	Xpresspost
			DOM.XP.CERT 	Xpresspost Certified
			DOM.PC 	Priority
			DOM.DT 	Delivered Tonight
			DOM.LIB 	Library Books
			USA.EP 	Expedited Parcel USA
			USA.PW.ENV 	Priority Worldwide Envelope USA
			USA.PW.PAK 	Priority Worldwide pak USA
			USA.PW.PARCEL 	Priority Worldwide Parcel USA
			USA.SP.AIR 	Small Packet USA Air
			USA.TP 	Tracked Packet – USA
			USA.TP.LVM 	Tracked Packet – USA (LVM)
			(large volume mailers)
			USA.XP 	Xpresspost USA
			INT.XP 	Xpresspost International
			INT.IP.AIR 	International Parcel Air
			INT.IP.SURF 	International Parcel Surface
			INT.PW.ENV 	Priority Worldwide Envelope Int’l
			INT.PW.PAK 	Priority Worldwide pak Int’l
			INT.PW.PARCEL 	Priority Worldwide parcel Int’l
			INT.SP.AIR 	Small Packet International Air
			INT.SP.SURF 	Small Packet International Surface
			INT.TP 	Tracked Packet – International
		 */
		
		List<String> domestic = new ArrayList<String>();
		domestic.add("DOM.RP");
		integrationOptions.put("services-domestic", domestic);
		configuration.setIntegrationOptions(integrationOptions);
		
		ShippingConfiguration shippingConfiguration = new ShippingConfiguration();

		List<ShippingOption> options = canadapost.getShippingQuotes(quote, details, null, delivery, origin, null, configuration, module, shippingConfiguration, Locale.CANADA);
		
		Assert.assertNotNull("Options should not be empty", options);
		
		for(ShippingOption opt : options) {
			System.out.println(opt.getOptionCode() + " " + opt.getOptionPrice().doubleValue());
		}
		
		System.out.println("Done ...");

	}
	

	
}
