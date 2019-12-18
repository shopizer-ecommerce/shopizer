package com.salesmanager.core.business.modules.integration.shipping.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.PackageDetails;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.modules.constants.Constants;
import com.salesmanager.core.modules.integration.IntegrationException;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuotePrePostProcessModule;

/**
 * Uses google api to get lng, lat and distance in km for a given delivery address
 * The route will be displayed on a map to the end user and available
 * from the admin section
 * 
 * The module can be configured to use miles by changing distance.inMeters
 * 
 * To use this pre-processor you will need a google api-key
 * 
 * Access google developers console
 * https://console.developers.google.com/project
 * 
 * Geocoding
 * Distance Matrix
 * Directions
 * 
 * Create new key for server application
 * Copy API key
 * https://console.developers.google.com
 * 
 * https://developers.google.com/maps/documentation/webservices/client-library
 * https://github.com/googlemaps/google-maps-services-java/tree/master/src/test/java/com/google/maps
 * 
 * @author carlsamson
 *
 */
@Component("shippingDistancePreProcessor")
public class ShippingDistancePreProcessorImpl implements ShippingQuotePrePostProcessModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingDistancePreProcessorImpl.class);
	
	private final static String BLANK = " ";
	
	private final static String MODULE_CODE = "shippingDistanceModule";

	@Value("${config.shippingDistancePreProcessor.apiKey}")
	private String apiKey;

	@Value("#{'${config.shippingDistancePreProcessor.acceptedZones}'.split(',')}") 
	private List<String> allowedZonesCodes = null;



	public List<String> getAllowedZonesCodes() {
		return allowedZonesCodes;
	}





	public void setAllowedZonesCodes(List<String> allowedZonesCodes) {
		this.allowedZonesCodes = allowedZonesCodes;
	}





	public void prePostProcessShippingQuotes(ShippingQuote quote,
			List<PackageDetails> packages, BigDecimal orderTotal,
			Delivery delivery, ShippingOrigin origin, MerchantStore store,
			IntegrationConfiguration globalShippingConfiguration,
			IntegrationModule currentModule,
			ShippingConfiguration shippingConfiguration,
			List<IntegrationModule> allModules, Locale locale)
			throws IntegrationException {
		
		
		/** which destinations are supported by this module **/
		
		if(delivery.getZone()==null) {
			return;
		}
		
		boolean zoneAllowed = false;
		if(allowedZonesCodes!=null) {
			for(String zoneCode : allowedZonesCodes) {
				if(zoneCode.equals(delivery.getZone().getCode())) {
					zoneAllowed = true;
					break;
				}
			}
		}
		
		if(!zoneAllowed) {
			return;
		}
		
		if(StringUtils.isBlank(delivery.getPostalCode())) {
			return;
		}
		
		Validate.notNull(apiKey, "Requires the configuration of google apiKey");
		
		GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
		
		//build origin address
		StringBuilder originAddress = new StringBuilder();
		
		originAddress.append(origin.getAddress()).append(BLANK)
		.append(origin.getCity()).append(BLANK)
		.append(origin.getPostalCode()).append(BLANK);
		
		if(!StringUtils.isBlank(origin.getState())) {
			originAddress.append(origin.getState()).append(" ");
		}
		if(origin.getZone()!=null) {
			originAddress.append(origin.getZone().getCode()).append(" ");
		}
		originAddress.append(origin.getCountry().getIsoCode());

		
		//build destination address
		StringBuilder destinationAddress = new StringBuilder();
		
		destinationAddress.append(delivery.getAddress()).append(BLANK);
		if(!StringUtils.isBlank(delivery.getCity())) {
			destinationAddress.append(delivery.getCity()).append(BLANK);
		}
		destinationAddress.append(delivery.getPostalCode()).append(BLANK);
		
		if(!StringUtils.isBlank(delivery.getState())) {
			destinationAddress.append(delivery.getState()).append(" ");
		}
		if(delivery.getZone()!=null) {
			destinationAddress.append(delivery.getZone().getCode()).append(" ");
		}
		destinationAddress.append(delivery.getCountry().getIsoCode());
		
		
		try {
			GeocodingResult[] originAdressResult =  GeocodingApi.geocode(context,
					originAddress.toString()).await();

			GeocodingResult[] destinationAdressResult =  GeocodingApi.geocode(context,
					destinationAddress.toString()).await();

			if(originAdressResult.length>0 && destinationAdressResult.length>0) {
				LatLng originLatLng = originAdressResult[0].geometry.location;
				LatLng destinationLatLng = destinationAdressResult[0].geometry.location;
				
				delivery.setLatitude(String.valueOf(destinationLatLng.lat));
				delivery.setLongitude(String.valueOf(destinationLatLng.lng));
				
				//keep latlng for further usage in order to display the map
	
				
				DistanceMatrix  distanceRequest = DistanceMatrixApi.newRequest(context)
		 		.origins(new LatLng(originLatLng.lat, originLatLng.lng))
		 		
		 		.destinations(new LatLng(destinationLatLng.lat, destinationLatLng.lng))
		 				.awaitIgnoreError();
				
				
				if(distanceRequest!=null) {
					DistanceMatrixRow distanceMax = distanceRequest.rows[0];
					Distance distance = distanceMax.elements[0].distance;
					quote.getQuoteInformations().put(Constants.DISTANCE_KEY, 0.001 * distance.inMeters);
				} else {
				  LOGGER.error("Expected distance inner google api to return DistanceMatrix, it returned null. API key might not be working for this request");
				}

			}
		
		} catch (Exception e) {
			LOGGER.error("Exception while calculating the shipping distance",e);
		}

	}




	
	public String getModuleCode() {
		return MODULE_CODE;
	}














}
