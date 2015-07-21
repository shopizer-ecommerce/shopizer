package com.salesmanager.test.manufacturer;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.currency.model.Currency;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;



public class ManufacturerTestCase extends AbstractSalesManagerCoreTestCase {
	
	private static final Date date = new Date(System.currentTimeMillis());
	

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testCreateManufacturerService() throws ServiceException {
		

		
		
		Language DEFAULT_LANGUAGE = languageService.getByCode("en");
		Language FRENCH = languageService.getByCode("fr");
		Currency currency = currencyService.getByCode("CAD");
		Country ca = super.countryService.getByCode("CA");
		

		
		//create a merchant
		MerchantStore store = new MerchantStore();
		store.setCountry(ca);
		store.setCurrency(currency);
		store.setDefaultLanguage(DEFAULT_LANGUAGE);
		store.setInBusinessSince(date);
		store.setStorename("store name");
		store.setStoreEmailAddress("test@test.com");
		merchantService.create(store);
		
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setMerchantStore(store);
		manufacturer.setCode("testmanufacturer");
		
		ManufacturerDescription fd = new ManufacturerDescription();
		fd.setLanguage(FRENCH);
		fd.setName("Sony french");
		fd.setManufacturer(manufacturer);
		
		ManufacturerDescription ed = new ManufacturerDescription();
		ed.setLanguage(DEFAULT_LANGUAGE);
		ed.setName("Sony english");
		ed.setManufacturer(manufacturer);
		
		Set descriptions = new HashSet();
		descriptions.add(fd);
		descriptions.add(ed);
		
		manufacturer.setDescriptions(descriptions);
		
		
		manufacturerService.create(manufacturer);
		
		manufacturerService.delete(manufacturer);

		
	}
	


}
