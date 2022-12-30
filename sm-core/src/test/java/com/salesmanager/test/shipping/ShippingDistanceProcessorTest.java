package com.salesmanager.test.shipping;

import javax.inject.Inject;

import org.junit.Ignore;

import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuotePrePostProcessModule;

@Ignore
public class ShippingDistanceProcessorTest {
	
	@Inject
	ShippingQuotePrePostProcessModule shippingDecisionTablePreProcessor;

	@Ignore
	public void testDistance() throws Exception {
		
		ShippingQuote shippingQuote = new ShippingQuote();
		
		Delivery delivery = new Delivery();
		delivery.setAddress("2055 Peel Street");
		delivery.setCity("Montreal");
		delivery.setPostalCode("H3A 1V4");
		
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
		
		//shippingDecisionTablePreProcessor.prePostProcessShippingQuotes(shippingQuote, null, null, delivery, origin, null, null, null, null, null, Locale.CANADA);
		
		System.out.println(delivery.getLatitude());
		System.out.println(delivery.getLongitude());
		//System.out.println(shippingQuote.getQuoteInformations().get(Constants.DISTANCE_KEY));

	}
}
