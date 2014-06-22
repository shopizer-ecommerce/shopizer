package com.salesmanager.test.customer;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.salesmanager.core.business.common.model.Billing;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.model.CustomerGender;
import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;

public class CustomerSalesManagerTestCase extends AbstractSalesManagerCoreTestCase {
	
	@Test
	public void createCustomer() throws ServiceException {
		
		
		MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
		Country country = countryService.getByCode("CA");
		Zone zone = zoneService.getByCode("VT");
		
		Customer customer = new Customer();
		customer.setMerchantStore(store);
		customer.setEmailAddress("test@test.com");
		customer.setGender(CustomerGender.M);

		customer.setAnonymous(true);
		customer.setCompany("ifactory");
		customer.setDateOfBirth(new Date());
		customer.setNick("My nick");
		customer.setPassword("123456");
		customer.setDefaultLanguage(store.getDefaultLanguage());
		
	    Delivery delivery = new Delivery();
	    delivery.setAddress("Shipping address");
	    delivery.setCountry(country);
	    delivery.setZone(zone);
	    
	    
	    Billing billing = new Billing();
	    billing.setFirstName("John");
	    billing.setLastName("Bossanova");
	    billing.setAddress("Billing address");
	    billing.setCountry(country);
	    billing.setZone(zone);
	    
	    customer.setBilling(billing);
	    customer.setDelivery(delivery);
		
		customerService.create(customer);
		customer = customerService.getById(customer.getId());
		String countryCode = customer.getBilling().getCountry().getIsoCode();
		String zoneCode = customer.getBilling().getZone().getCode();
		System.out.println(countryCode + zoneCode);
		
		Assert.assertEquals(countryCode, "CA");
		Assert.assertEquals(zoneCode, "VT");
		Assert.assertTrue(customerService.count() == 1);
		Assert.assertNotNull(customerService.getByName("Leonardo"));
		
	}
}
