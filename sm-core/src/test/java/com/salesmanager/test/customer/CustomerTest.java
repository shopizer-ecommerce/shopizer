package com.salesmanager.test.customer;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.common.Billing;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.CustomerGender;
import com.salesmanager.core.model.customer.attribute.CustomerAttribute;
import com.salesmanager.core.model.customer.attribute.CustomerOption;
import com.salesmanager.core.model.customer.attribute.CustomerOptionDescription;
import com.salesmanager.core.model.customer.attribute.CustomerOptionSet;
import com.salesmanager.core.model.customer.attribute.CustomerOptionValue;
import com.salesmanager.core.model.customer.attribute.CustomerOptionValueDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;



@Ignore
public class CustomerTest extends com.salesmanager.test.common.AbstractSalesManagerCoreTestCase {
	
	@Test
	public void createCustomer() throws ServiceException {
		
		
		Language en = languageService.getByCode("en");
		
		
		MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
		Country country = countryService.getByCode("CA");
		Zone zone = zoneService.getByCode("QC");
		
		/** Core customer attributes **/
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
		

		//create an option value
		CustomerOptionValue yes = new CustomerOptionValue();
		yes.setCode("yes");
		yes.setMerchantStore(store);
		CustomerOptionValueDescription yesDescription = new CustomerOptionValueDescription();
		yesDescription.setLanguage(en);
		yesDescription.setCustomerOptionValue(yes);
		
		CustomerOptionValueDescription yes_sir = new CustomerOptionValueDescription();
		yes_sir.setCustomerOptionValue(yes);
		yes_sir.setDescription("Yes sir!");
		yes_sir.setName("Yes sir!");
		yes_sir.setLanguage(en);
		yes.getDescriptions().add(yes_sir);
		
		//needs to be saved before using it
		customerOptionValueService.create(yes);
		
		CustomerOptionValue no = new CustomerOptionValue();
		no.setCode("no");
		no.setMerchantStore(store);
		CustomerOptionValueDescription noDescription = new CustomerOptionValueDescription();
		noDescription.setLanguage(en);
		noDescription.setCustomerOptionValue(no);
		
		CustomerOptionValueDescription no_sir = new CustomerOptionValueDescription();
		no_sir.setCustomerOptionValue(no);
		no_sir.setDescription("Nope!");
		no_sir.setName("Nope!");
		no_sir.setLanguage(en);
		no.getDescriptions().add(no_sir);
		
		//needs to be saved before using it
		customerOptionValueService.create(no);
		
		
		//create a customer option to be used
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
		
		
		//create a customer option to be used
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
		
		//--
		//now create an option set (association of a customer option with possible customer option values)
		//--
		
		//possible yes
		CustomerOptionSet mailingListSetYes = new CustomerOptionSet();

		mailingListSetYes.setSortOrder(0);
		mailingListSetYes.setCustomerOption(subscribedToMailingList);
		mailingListSetYes.setCustomerOptionValue(yes);
		
		customerOptionSetService.create(mailingListSetYes);

		//possible no
		CustomerOptionSet mailingListSetNo = new CustomerOptionSet();
		//mailingListSetNo.setPk(mailingListSetNoId);
		mailingListSetNo.setSortOrder(1);
		mailingListSetNo.setCustomerOption(subscribedToMailingList);
		mailingListSetNo.setCustomerOptionValue(no);
		
		customerOptionSetService.create(mailingListSetNo);
		
		//possible has returned items
		
		CustomerOptionSet hasReturnedItemsYes = new CustomerOptionSet();
		hasReturnedItemsYes.setSortOrder(0);
		hasReturnedItemsYes.setCustomerOption(hasReturnedItems);
		hasReturnedItemsYes.setCustomerOptionValue(yes);
		
		customerOptionSetService.create(hasReturnedItemsYes);
		
		
		subscribedToMailingList.setSortOrder(2);
		customerOptionService.update(subscribedToMailingList);
		
		CustomerOption option = customerOptionService.getById(subscribedToMailingList.getId());
		
		option.setSortOrder(4);
		customerOptionService.update(option);
		
		List<CustomerOptionSet> optionSetList = customerOptionSetService.listByStore(store, en);
		
		//Assert.assertEquals(3, optionSetList.size());
		System.out.println("Size of options : " + optionSetList.size());
		
		/**
		 * Now create a customer option attribute
		 * A customer attribute is a selected customer option set transformed to an 
		 * attribute for a given customer
		 */
		
		CustomerAttribute customerAttributeMailingList = new CustomerAttribute();
		customerAttributeMailingList.setCustomer(customer);
		customerAttributeMailingList.setCustomerOption(subscribedToMailingList);
		customerAttributeMailingList.setCustomerOptionValue(no);
		
		customer.getAttributes().add(customerAttributeMailingList);
		
		customerService.save(customer);
		
		customerService.delete(customer);
		


		
	}
}
