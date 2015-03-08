package com.salesmanager.test.payment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.salesmanager.core.business.common.model.Billing;
import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.model.CustomerGender;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.business.payments.model.CreditCardType;
import com.salesmanager.core.business.payments.model.CreditCardPayment;
import com.salesmanager.core.business.payments.model.PaymentType;
import com.salesmanager.core.business.payments.model.TransactionType;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.core.business.system.model.IntegrationConfiguration;
import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;

public class PaymentTestCase extends AbstractSalesManagerCoreTestCase {
	
	private static final Date date = new Date(System.currentTimeMillis());
	

	
	@Test
	public void testBeanStreamPayment() throws ServiceException {
		
	    Language en = languageService.getByCode("en");
	    Country country = countryService.getByCode("CA");
	    Zone zone = zoneService.getByCode("QC");

	    MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
	    
	    
	    //create customer
		Customer customer = new Customer();
		customer.setMerchantStore(store);
		customer.setEmailAddress("test@test.com");
		customer.setGender(CustomerGender.M);
		customer.setAnonymous(true);
		customer.setCompany("ifactory");
		customer.setDateOfBirth(new Date());
		customer.setNick("My nick");
		customer.setPassword("123456");

		
	    Delivery delivery = new Delivery();
	    delivery.setAddress("Shipping address");
	    delivery.setCity("Boucherville");
	    delivery.setCountry(country);
	    delivery.setZone(zone);
	    delivery.setPostalCode("J4B-8J9");

	    Billing billing = new Billing();
	    billing.setAddress("Billing address");
	    billing.setCountry(country);
	    billing.setZone(zone);
	    billing.setPostalCode("J4B-8J9");
	    
	    customer.setBilling(billing);
	    customer.setDelivery(delivery);
	    

	    
	    //create Payment
	    CreditCardPayment payment = new CreditCardPayment();
	    payment.setModuleName("beanstream");
	    payment.setPaymentType(PaymentType.CREDITCARD);
		payment.setCardOwner("Test User");
		payment.setCredidCardValidationNumber("0421");
		payment.setCreditCardNumber("545412345678");
		payment.setExpirationMonth("04");
		payment.setExpirationYear("16");
		payment.setCreditCard(CreditCardType.MASTERCARD);
		payment.setTransactionType(TransactionType.AUTHORIZECAPTURE);
	    
		
		IntegrationConfiguration paymentConfiguration = new IntegrationConfiguration();
		
		paymentConfiguration.setActive(true);
		paymentConfiguration.setEnvironment(IntegrationConfiguration.TEST_ENVIRONMENT);
		paymentConfiguration.setModuleCode("beanstream");
		
		Map<String,String> integrationKeys = new HashMap<String,String>();
		integrationKeys.put("merchantid", "123456");
		integrationKeys.put("username", "accnt");
		integrationKeys.put("password", "pass123");
		integrationKeys.put("transaction", "CAPTURE");
		
		paymentConfiguration.setIntegrationKeys(integrationKeys);
		
		paymentService.savePaymentModuleConfiguration(paymentConfiguration, store);
		
		Order order = new Order();
		order.setTotal(new BigDecimal(20));
		
		//paypal requires item list List<ShoppinCartItem> for the rest i set null
		paymentService.processPayment(customer, store, payment, null, order);

		
		
	}
	

}