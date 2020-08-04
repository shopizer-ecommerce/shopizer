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
import com.salesmanager.core.model.shipping.PackageDetails;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import com.salesmanager.core.model.shipping.ShippingPackageType;
import com.salesmanager.core.model.shipping.ShippingType;
import com.salesmanager.shop.model.references.PersistableAddress;
import com.salesmanager.shop.model.references.ReadableAddress;
import com.salesmanager.shop.model.shipping.ExpeditionConfiguration;
import com.salesmanager.shop.store.api.exception.OperationNotAllowedException;
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
			
			ShippingConfiguration config = getDbConfig(store);
			if(config!=null) {
				expeditionConfiguration.setIternationalShipping(config.getShipType()!=null && config.getShipType().equals(ShippingType.INTERNATIONAL.name())?true:false);
				expeditionConfiguration.setTaxOnShipping(config.isTaxOnShipping());
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
			ShippingConfiguration config = getDbConfig(store);
			config.setTaxOnShipping(expedition.isTaxOnShipping());
			config.setShippingType(expedition.isIternationalShipping()?ShippingType.INTERNATIONAL:ShippingType.NATIONAL);
			this.saveShippingConfiguration(config, store);
			
			shippingService.setSupportedCountries(store, expedition.getShipToCountry());


		} catch (ServiceException e) {
			LOGGER.error("Error while getting expedition configuration", e);
			throw new ServiceRuntimeException("Error while getting Expedition configuration for store[" + store.getCode() + "]", e);
		}

	}
	
	private void saveShippingConfiguration(ShippingConfiguration config, MerchantStore store) throws ServiceRuntimeException {
		try {
			shippingService.saveShippingConfiguration(config, store);
		} catch (ServiceException e) {
			LOGGER.error("Error while saving shipping configuration", e);
			throw new ServiceRuntimeException("Error while saving shipping configuration for store [" + store.getCode() + "]", e);
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
		address.setActive(o.isActive());
		address.setCity(o.getCity());
		address.setPostalCode(o.getPostalCode());
		if(o.getCountry()!=null) {
			address.setCountry(o.getCountry().getIsoCode());
		}
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
			o.setActive(address.isActive());
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

	private ShippingConfiguration getDbConfig(MerchantStore store) {

		try {
			//get original configuration
			ShippingConfiguration config = shippingService.getShippingConfiguration(store);
			if(config==null) {
				config = new ShippingConfiguration();
				config.setShippingType(ShippingType.INTERNATIONAL);
			}

			return config;
		} catch (ServiceException e) {
			LOGGER.error("Error while getting expedition configuration", e);
			throw new ServiceRuntimeException("Error while getting Expedition configuration for store[" + store.getCode() + "]", e);
		}
		
	}

	@Override
	public void createPackage(PackageDetails packaging, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(packaging, "PackageDetails cannot be null");
		ShippingConfiguration config = getDbConfig(store);
		
		if(this.packageExists(config, packaging)) {
			throw new OperationNotAllowedException("Package with unique code [" + packaging.getCode() + "] already exist");
		}
		
		com.salesmanager.core.model.shipping.Package pack = toPackage(packaging);
		
		
		//need to check if code exists
		config.getPackages().add(pack);
		this.saveShippingConfiguration(config, store);
	
	}
	
	private boolean packageExists(ShippingConfiguration configuration, PackageDetails packageDetails) {
		
		Validate.notNull(configuration,"ShippingConfiguration cannot be null");
		Validate.notNull(packageDetails, "PackageDetails cannot be null");
		Validate.notEmpty(packageDetails.getCode(), "PackageDetails code cannot be empty");
		
		List<com.salesmanager.core.model.shipping.Package> packages = configuration.getPackages().stream().filter(p -> p.getCode().equalsIgnoreCase(packageDetails.getCode())).collect(Collectors.toList());
		
		if(packages.isEmpty()) {
			return false;
		} else {
			return true;
		}
		
		
	}
	
	private com.salesmanager.core.model.shipping.Package packageDetails(ShippingConfiguration configuration, String code) {
		
		Validate.notNull(configuration,"ShippingConfiguration cannot be null");
		Validate.notNull(code, "PackageDetails code cannot be null");

		List<com.salesmanager.core.model.shipping.Package> packages = configuration.getPackages().stream().filter(p -> p.getCode().equalsIgnoreCase(code)).collect(Collectors.toList());
		
		if(!packages.isEmpty()) {
			return packages.get(0);
		} else {
			return null;
		}

		
	}

	@Override
	public PackageDetails getPackage(String code, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notEmpty(code,"Packaging unique code cannot be empty");
		
		ShippingConfiguration config = getDbConfig(store);
		
		com.salesmanager.core.model.shipping.Package p = this.packageDetails(config, code);
		
		if(p == null) {
			throw new ResourceNotFoundException("Package with unique code [" + code + "] not found");
		}
		
		return toPackageDetails(p);
	}

	@Override
	public List<PackageDetails> listPackages(MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		ShippingConfiguration config = getDbConfig(store);
		
		return config.getPackages().stream().map(p -> this.toPackageDetails(p)).collect(Collectors.toList());

	}

	@Override
	public void updatePackage(String code, PackageDetails packaging, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(packaging, "PackageDetails cannot be null");
		Validate.notEmpty(code,"Packaging unique code cannot be empty");
		
		ShippingConfiguration config = getDbConfig(store);
		
		com.salesmanager.core.model.shipping.Package p = this.packageDetails(config, code);
		
		if(p == null) {
			throw new ResourceNotFoundException("Package with unique code [" + packaging.getCode() + "] not found");
		}
		
		com.salesmanager.core.model.shipping.Package pack = toPackage(packaging);
		pack.setCode(code);
		
		//need to check if code exists
		List<com.salesmanager.core.model.shipping.Package> packs = config.getPackages().stream().filter(pa -> !pa.getCode().equals(code)).collect(Collectors.toList());
		packs.add(pack);
		
		config.setPackages(packs);
		this.saveShippingConfiguration(config, store);
		
	}

	@Override
	public void deletePackage(String code, MerchantStore store) {
		
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notEmpty(code,"Packaging unique code cannot be empty");
		
		ShippingConfiguration config = getDbConfig(store);
		
		List<com.salesmanager.core.model.shipping.Package> packages = config.getPackages();
		
		List<com.salesmanager.core.model.shipping.Package> packList = config.getPackages().stream().filter(p -> p.getCode().equalsIgnoreCase(code)).collect(Collectors.toList());
		
		if(!packList.isEmpty()) {
			packages.removeAll(packList);
			config.setPackages(packages);
			this.saveShippingConfiguration(config, store);
		} 
		
	}
	
	private PackageDetails toPackageDetails(com.salesmanager.core.model.shipping.Package pack) {
		PackageDetails details = new PackageDetails();
		details.setCode(pack.getCode());
		details.setShippingHeight(pack.getBoxHeight());
		details.setShippingLength(pack.getBoxLength());
		details.setShippingMaxWeight(pack.getMaxWeight());
		//details.setShippingQuantity(pack.getShippingQuantity());
		details.setShippingWeight(pack.getBoxWeight());
		details.setShippingWidth(pack.getBoxWidth());
		details.setTreshold(pack.getTreshold());
		details.setType(pack.getShipPackageType().name());
		return details;
	}
	
	private com.salesmanager.core.model.shipping.Package toPackage(PackageDetails pack) {
		com.salesmanager.core.model.shipping.Package details = new com.salesmanager.core.model.shipping.Package();
		details.setCode(pack.getCode());
		details.setBoxHeight(pack.getShippingHeight());
		details.setBoxLength(pack.getShippingLength());
		details.setMaxWeight(pack.getShippingMaxWeight());
		//details.setShippingQuantity(pack.getShippingQuantity());
		details.setBoxWeight(pack.getShippingWeight());
		details.setBoxWidth(pack.getShippingWidth());
		details.setTreshold(pack.getTreshold());
		details.setShipPackageType(ShippingPackageType.valueOf(pack.getType()));
		return details;
	}

}
