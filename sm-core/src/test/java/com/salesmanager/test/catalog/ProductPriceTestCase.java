package com.salesmanager.test.catalog;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.utils.ProductPriceUtils;
import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;

public class ProductPriceTestCase extends AbstractSalesManagerCoreTestCase {
	
	private static final Date date = new Date(System.currentTimeMillis());
	
	@Autowired
	private ProductPriceUtils productPriceUtils;
	
	@Test
	public void testPriceWithCurrency() throws Exception {
		
		
		MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
		
		/** specify currencies **/
		
		List<com.salesmanager.core.business.reference.currency.model.Currency> currencies = currencyService.list();
		//countries iso codes -> http://userpage.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
		Country country = new Country();
		country.setIsoCode("IN");
		
		
		//iso languages codes -> http://www.loc.gov/standards/iso639-2/php/code_list.php
		Language language = new Language();
		language.setCode("en");
		
		store = new MerchantStore();
		store.setCountry(country);
		store.setDefaultLanguage(language);
		
		//all codes and examples -> http://www.xe.com/iso4217.php
		Currency currency = Currency.getInstance("INR");
		com.salesmanager.core.business.reference.currency.model.Currency c = new com.salesmanager.core.business.reference.currency.model.Currency();
		c.setCurrency(currency);
		store.setCurrency(c);
		
		String amount = productPriceUtils.getStoreFormatedAmountWithCurrency(store, new BigDecimal("12345"));
		
		Assert.assertNotNull(amount);
		System.out.println(amount);
		
	}

}