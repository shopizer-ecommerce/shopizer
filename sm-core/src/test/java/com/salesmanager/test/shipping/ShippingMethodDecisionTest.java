package com.salesmanager.test.shipping;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;

import com.salesmanager.core.business.modules.integration.shipping.impl.ShippingDecisionPreProcessorImpl;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.shipping.PackageDetails;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.system.IntegrationModule;



@Ignore
public class ShippingMethodDecisionTest extends com.salesmanager.test.common.AbstractSalesManagerCoreTestCase {
	
	@Inject
	ShippingDecisionPreProcessorImpl shippingMethodDecisionProcess;

	@Test
	@Ignore
	public void validateShippingMethod() throws Exception {
		
		ShippingQuote quote = new ShippingQuote();
		PackageDetails pDetail = new PackageDetails();
		pDetail.setShippingHeight(20);
		pDetail.setShippingLength(10);
		pDetail.setShippingWeight(70);
		pDetail.setShippingWidth(78);
		List<PackageDetails> details = new ArrayList<PackageDetails>();
		details.add(pDetail);

		Delivery delivery = new Delivery();
		delivery.setAddress("358 Du Languedoc");
		delivery.setCity("Boucherville");
		delivery.setPostalCode("J4B 8J9");
		
		Country country = new Country();
		country.setIsoCode("CA");
		country.setName("Canada");
		
		//country.setIsoCode("US");
		//country.setName("United States");
		
		delivery.setCountry(country);
		
		Zone zone = new Zone();
		zone.setCode("QC");
		zone.setName("Quebec");
		
		//zone.setCode("NY");
		//zone.setName("New York");
		
		delivery.setZone(zone);
		
		IntegrationModule currentModule = new IntegrationModule();
		currentModule.setCode("canadapost");
		quote.setCurrentShippingModule(currentModule);
		quote.setShippingModuleCode(currentModule.getCode());
		
		IntegrationModule canadapost = new IntegrationModule();
		canadapost.setCode("canadapost");
		
		IntegrationModule ups = new IntegrationModule();
		ups.setCode("ups");
		
		IntegrationModule inhouse = new IntegrationModule();
		inhouse.setCode("customQuotesRules");
		
		List<IntegrationModule> allModules = new ArrayList<IntegrationModule>();
		allModules.add(canadapost);
		allModules.add(ups);
		allModules.add(inhouse);

		shippingMethodDecisionProcess.prePostProcessShippingQuotes(quote, details, null, delivery, null, null, null, currentModule, null, allModules, Locale.CANADA);
		
		System.out.println("Done : " + quote.getCurrentShippingModule()!=null ? quote.getCurrentShippingModule().getCode() : currentModule.getCode());

	}
}
