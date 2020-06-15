package com.salesmanager.shop.store.controller.shipping.facade;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.jsoup.helper.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.shipping.ShippingOriginService;
import com.salesmanager.core.business.services.shipping.ShippingService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import com.salesmanager.core.model.shipping.ShippingType;
import com.salesmanager.shop.model.references.PersistableAddress;
import com.salesmanager.shop.model.references.ReadableAddress;
import com.salesmanager.shop.model.shipping.ExpeditionConfiguration;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;

@Service("shippingFacade")
public class ShippingFacadeImpl implements ShippingFacade {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingFacadeImpl.class);

	@Autowired
	ShippingOriginService shippingOriginService;
	
	@Autowired
	ShippingService shippingService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	ZoneService zoneService;


	@Override
	public ExpeditionConfiguration getExpeditionConfiguration(MerchantStore store, Language language) {
		ExpeditionConfiguration expeditionConfiguration = new ExpeditionConfiguration();
		try {
			
			ShippingConfiguration config = shippingService.getShippingConfiguration(store);
			if(config!=null) {
				expeditionConfiguration.setIternationalShipping(config.getShipType()!=null && config.getShipType().equals(ShippingType.INTERNATIONAL.name())?true:false);
			}
			
			List<String> countries = shippingService.getSupportedCountries(store);
			
			
			if(!CollectionUtils.isEmpty(countries)) {
				
				List<String> countryCode = countries.stream()
						.sorted(Comparator.comparing(n->n.toString()))
						.collect(Collectors.toList());
				
				expeditionConfiguration.setShipToCountry(countryCode);
			}

		} catch (ServiceException e) {
			LOGGER.error("Error while getting expedition configuration", e);
			throw new ServiceRuntimeException("Error while getting Expedition configuration for store[" + store.getCode() + "]", e);
		}
		return expeditionConfiguration;
	}

	@Override
	public void saveExpeditionConfiguration(ExpeditionConfiguration expedition, MerchantStore store) {
		Validate.notNull(expedition, "ExpeditionConfiguration cannot be null");
		try {
			
			//get original configuration
			ShippingConfiguration config = shippingService.getShippingConfiguration(store);
			if(config==null) {
				config = new ShippingConfiguration();
				config.setShippingType(ShippingType.INTERNATIONAL);
			}
			config.setShippingType(expedition.isIternationalShipping()?ShippingType.INTERNATIONAL:ShippingType.NATIONAL);
			shippingService.saveShippingConfiguration(config, store);
			
			shippingService.setSupportedCountries(store, expedition.getShipToCountry());


		} catch (ServiceException e) {
			LOGGER.error("Error while getting expedition configuration", e);
			throw new ServiceRuntimeException("Error while getting Expedition configuration for store[" + store.getCode() + "]", e);
		}

	}

	@Override
	public ReadableAddress getShippingOrigin(MerchantStore store) {
		
		ShippingOrigin o = shippingOriginService.getByStore(store);
		
		if(o == null) {
			throw new ResourceNotFoundException("Shipping origin does not exists for store [" + store.getCode() + "]");
		}
		
		ReadableAddress address = new ReadableAddress();
		address.setAddress(o.getAddress());
		address.setCity(o.getCity());
		address.setPostalCode(o.getPostalCode());
		address.setCountry(o.getCountry().getIsoCode());
		Zone z = o.getZone();
		if(z != null) {
			address.setStateProvince(z.getCode());
		} else {
			address.setStateProvince(o.getState());
		}

		return address;
	}

	@Override
	public void saveShippingOrigin(PersistableAddress address, MerchantStore store) {
		Validate.notNull(address, "PersistableAddress cannot be null");
		try {
			ShippingOrigin o = shippingOriginService.getByStore(store);
			if(o == null) {
				o = new ShippingOrigin();
			}
			
			o.setAddress(address.getAddress());
			o.setCity(address.getCity());
			o.setCountry(countryService.getByCode(address.getCountry()));
			o.setMerchantStore(store);
			o.setPostalCode(address.getPostalCode());
			
			Zone zone = zoneService.getByCode(address.getStateProvince());
			if(zone == null) {
				o.setState(address.getStateProvince());
			} else {
				o.setZone(zone);
			}
			
			shippingOriginService.save(o);
			
		} catch (ServiceException e) {
			LOGGER.error("Error while getting shipping origin for country [" + address.getCountry() + "]",e);
			throw new ServiceRuntimeException("Error while getting shipping origin for country [" + address.getCountry() + "]",e);
		}


	}

}
