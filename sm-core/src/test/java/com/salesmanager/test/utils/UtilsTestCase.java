package com.salesmanager.test.utils;



import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.salesmanager.core.business.common.model.Address;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.currency.model.Currency;
import com.salesmanager.core.business.reference.currency.service.CurrencyService;
import com.salesmanager.core.modules.utils.Encryption;
import com.salesmanager.core.modules.utils.GeoLocation;
import com.salesmanager.core.utils.CacheUtils;
import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;

public class UtilsTestCase extends AbstractSalesManagerCoreTestCase {
	
	
	@Autowired
	private CountryService countryService;
	
	
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private Encryption encryption;
	
	@Autowired
	private CacheUtils cache;
	
	@Autowired
	private GeoLocation geoLoaction;
	

	
	@Test
	public void testCache() throws Exception {
		

		
		@SuppressWarnings("rawtypes")
		List countries = countryService.list();
		


		
		//CacheUtils cache = CacheUtils.getInstance();
		cache.putInCache(countries, "COUNTRIES");
		
		@SuppressWarnings("rawtypes")
		List objects = (List) cache.getFromCache("COUNTRIES");
		
		Assert.assertNotNull(objects);
		
	}
	
	@Test
	public void testCurrency() throws Exception {
		
		Currency currency = currencyService.getByCode("BGN");
		
		java.util.Currency c = currency.getCurrency();
		
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
		numberFormat.setCurrency(c);
		
		System.out.println("Done");
		
	}
	
	@Test
	public void testGeoLocation() throws Exception {
		
		Address address = geoLoaction.getAddress("96.21.132.0");
		if(address!=null) {
			System.out.println(address.getCountry());
		}
		
	}
	

}
