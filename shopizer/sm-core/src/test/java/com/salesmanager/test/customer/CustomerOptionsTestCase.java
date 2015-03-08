package com.salesmanager.test.customer;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.salesmanager.core.business.common.model.Billing;
import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.model.CustomerGender;
import com.salesmanager.core.business.customer.model.attribute.CustomerOption;
import com.salesmanager.core.business.customer.model.attribute.CustomerOptionDescription;
import com.salesmanager.core.business.customer.model.attribute.CustomerOptionSet;
import com.salesmanager.core.business.customer.model.attribute.CustomerOptionValue;
import com.salesmanager.core.business.customer.model.attribute.CustomerOptionValueDescription;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;

public class CustomerOptionsTestCase extends AbstractSalesManagerCoreTestCase {
	
	@Test
	public void testCreateAndGetCustomerOptions() throws ServiceException {
		
		
		MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
		Country country = countryService.getByCode("CA");
		Zone zone = zoneService.getByCode("VT");
		Language en = languageService.getByCode("en");
		
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
	    billing.setAddress("Billing address");
	    billing.setCountry(country);
	    billing.setZone(zone);
	    
	    customer.setBilling(billing);
	    customer.setDelivery(delivery);
		
		customerService.create(customer);
		customer = customerService.getById(customer.getId());

		
		//create option value
		CustomerOptionValue yes = new CustomerOptionValue();
		yes.setCode("yes");
		yes.setMerchantStore(store);
		CustomerOptionValueDescription yesDescription = new CustomerOptionValueDescription();
		yesDescription.setLanguage(en);
		yesDescription.setCustomerOptionValue(yes);
		
		customerOptionValueService.create(yes);
		
		CustomerOptionValue no = new CustomerOptionValue();
		no.setCode("no");
		no.setMerchantStore(store);
		CustomerOptionValueDescription noDescription = new CustomerOptionValueDescription();
		noDescription.setLanguage(en);
		noDescription.setCustomerOptionValue(no);
		
		customerOptionValueService.create(no);
		
		CustomerOption subscribedToMailingList = new CustomerOption();
		subscribedToMailingList.setActive(true);
		subscribedToMailingList.setPublicOption(true);
		subscribedToMailingList.setCode("subscribedToMailingList");
		subscribedToMailingList.setMerchantStore(store);
		
		CustomerOptionDescription mailingListDesciption= new CustomerOptionDescription();
		mailingListDesciption.setName("Subscribed to mailing list");
		mailingListDesciption.setDescription("Subscribed to mailing list");
		mailingListDesciption.setLanguage(en);
		mailingListDesciption.setCustomerOption(subscribedToMailingList);
		
		Set<CustomerOptionDescription> mailingListDesciptionList = new HashSet<CustomerOptionDescription>();
		mailingListDesciptionList.add(mailingListDesciption);
		subscribedToMailingList.setDescriptions(mailingListDesciptionList);
		
		customerOptionService.create(subscribedToMailingList);
		
		
		
		CustomerOption hasReturnedItems = new CustomerOption();
		hasReturnedItems.setActive(true);
		hasReturnedItems.setPublicOption(true);
		hasReturnedItems.setCode("hasReturnedItems");
		hasReturnedItems.setMerchantStore(store);
		
		CustomerOptionDescription hasReturnedItemsDesciption= new CustomerOptionDescription();
		hasReturnedItemsDesciption.setName("Has returned items");
		hasReturnedItemsDesciption.setDescription("Has returned items");
		hasReturnedItemsDesciption.setLanguage(en);
		hasReturnedItemsDesciption.setCustomerOption(hasReturnedItems);
		
		Set<CustomerOptionDescription> hasReturnedItemsList = new HashSet<CustomerOptionDescription>();
		hasReturnedItemsList.add(hasReturnedItemsDesciption);
		hasReturnedItems.setDescriptions(hasReturnedItemsList);

		customerOptionService.create(hasReturnedItems);
		
		subscribedToMailingList.setSortOrder(3);
		
		customerOptionService.update(subscribedToMailingList);
		
		
/*		CustomerOptionSetId mailingListSetYesId = new CustomerOptionSetId();
		mailingListSetYesId.setCustomerOption(subscribedToMailingList);
		mailingListSetYesId.setCustomerOptionValue(yes);*/
		
		CustomerOptionSet mailingListSetYes = new CustomerOptionSet();
		
		//mailingListSetYes.setPk(mailingListSetYesId);
		mailingListSetYes.setSortOrder(0);
		mailingListSetYes.setCustomerOption(subscribedToMailingList);
		mailingListSetYes.setCustomerOptionValue(yes);
		
		customerOptionSetService.create(mailingListSetYes);
		//subscribedToMailingList.getCustomerOptions().add(mailingListSetYes);
		//customerOptionService.update(subscribedToMailingList);
		
/*		CustomerOptionSetId mailingListSetNoId = new CustomerOptionSetId();
		mailingListSetNoId.setCustomerOption(subscribedToMailingList);
		mailingListSetNoId.setCustomerOptionValue(no);*/
		
		CustomerOptionSet mailingListSetNo = new CustomerOptionSet();
		//mailingListSetNo.setPk(mailingListSetNoId);
		mailingListSetNo.setSortOrder(1);
		mailingListSetNo.setCustomerOption(subscribedToMailingList);
		mailingListSetNo.setCustomerOptionValue(no);
		
		customerOptionSetService.create(mailingListSetNo);
		
		//subscribedToMailingList.getCustomerOptions().add(mailingListSetNo);
		//customerOptionService.update(subscribedToMailingList);
		
		
/*		CustomerOptionSetId hasReturnedItemsYesId = new CustomerOptionSetId();
		hasReturnedItemsYesId.setCustomerOption(hasReturnedItems);
		hasReturnedItemsYesId.setCustomerOptionValue(yes);*/
		
		CustomerOptionSet hasReturnedItemsYes = new CustomerOptionSet();
		//hasReturnedItemsYes.setPk(hasReturnedItemsYesId);
		hasReturnedItemsYes.setSortOrder(0);
		hasReturnedItemsYes.setCustomerOption(hasReturnedItems);
		hasReturnedItemsYes.setCustomerOptionValue(yes);
		
		customerOptionSetService.create(hasReturnedItemsYes);
		
		
		//hasReturnedItems.getCustomerOptions().add(hasReturnedItemsYes);
		//customerOptionService.update(hasReturnedItems);
		
		subscribedToMailingList.setSortOrder(2);
		customerOptionService.update(subscribedToMailingList);
		
		CustomerOption option = customerOptionService.getById(subscribedToMailingList.getId());
		
		option.setSortOrder(4);
		customerOptionService.update(option);
		
		List<CustomerOptionSet> optionSetList = customerOptionSetService.listByStore(store, en);
		
		//Assert.assertEquals(3, optionSetList.size());
		System.out.println("Size of options : " + optionSetList.size());
		

		
		
		
		
	}
}
