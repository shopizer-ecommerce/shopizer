package com.salesmanager.test.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import com.salesmanager.core.business.catalog.product.model.price.ProductPriceDescription;
import com.salesmanager.core.business.catalog.product.model.type.ProductType;
import com.salesmanager.core.business.common.model.Billing;
import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.model.CustomerGender;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.business.order.model.OrderCriteria;
import com.salesmanager.core.business.order.model.OrderList;
import com.salesmanager.core.business.order.model.OrderTotal;
import com.salesmanager.core.business.order.model.orderproduct.OrderProduct;
import com.salesmanager.core.business.order.model.orderproduct.OrderProductDownload;
import com.salesmanager.core.business.order.model.orderproduct.OrderProductPrice;
import com.salesmanager.core.business.order.model.orderstatus.OrderStatus;
import com.salesmanager.core.business.order.model.orderstatus.OrderStatusHistory;
import com.salesmanager.core.business.order.model.payment.CreditCard;
import com.salesmanager.core.business.payments.model.CreditCardType;
import com.salesmanager.core.business.payments.model.PaymentType;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.currency.model.Currency;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;

public class OrderSalesManagerTestCase extends AbstractSalesManagerCoreTestCase {

//	@Ignore
	@Test
	public void createOrder() throws ServiceException {
		

	    MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
	    
		//create a product
	    ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);
	    
	    Language en = languageService.getByCode("en");

	    Product product = new Product();
	    product.setProductHeight(new BigDecimal(4));
	    product.setProductLength(new BigDecimal(3));
	    product.setProductWidth(new BigDecimal(5));
	    product.setProductWeight(new BigDecimal(8));
	    product.setSku("TESTSKU");
	    product.setType(generalType);
	    product.setMerchantStore(store);

	    // Product description
	    ProductDescription description = new ProductDescription();
	    description.setName("Product 1");
	    description.setLanguage(en);
	    description.setProduct(product);

	    product.getDescriptions().add(description);
	    

	    // Availability
	    ProductAvailability availability = new ProductAvailability();
	    availability.setProductDateAvailable(new Date());
	    availability.setProductQuantity(100);
	    availability.setRegion("*");
	    availability.setProduct(product);// associate with product
	    
	    product.getAvailabilities().add(availability);

	    ProductPrice dprice = new ProductPrice();
	    dprice.setDefaultPrice(true);
	    dprice.setProductPriceAmount(new BigDecimal(29.99));
	    dprice.setProductAvailability(availability);
	    
	    availability.getPrices().add(dprice);

	    ProductPriceDescription dpd = new ProductPriceDescription();
	    dpd.setName("Base price");
	    dpd.setProductPrice(dprice);
	    dpd.setLanguage(en);

	    dprice.getDescriptions().add(dpd);
	    
	    productService.saveOrUpdate(product);
	    
	    //create a Customer
		Country country = countryService.getByCode("CA");
		Zone zone = zoneService.getByCode("QC");
		
		Customer customer = new Customer();
		customer.setMerchantStore(store);
		customer.setEmailAddress("test@test.com");
		customer.setGender(CustomerGender.M);						
		customer.setAnonymous(true);
		customer.setCompany("ifactory");
		customer.setDateOfBirth(new Date());
		customer.setNick("My nick");
		customer.setDefaultLanguage(en);
		
	    Delivery delivery = new Delivery();
	    delivery.setAddress("358 Du Languadoc");
	    delivery.setCity( "Boucherville" );
	    delivery.setCountry(country);
//	    delivery.setCountryCode(CA_COUNTRY_CODE);
	    delivery.setPostalCode("J4B-8J9" );
	    delivery.setFirstName("Carl");
	    delivery.setLastName("Samson");
	    delivery.setZone(zone);	    
	    
	    Billing billing = new Billing();
	    billing.setAddress("358 Du Languadoc");
	    billing.setCity("Boucherville");
	    billing.setCompany("CSTI Consulting");
	    billing.setCountry(country);
//	    billing.setCountryCode(CA_COUNTRY_CODE);
	    billing.setFirstName("Carl");
	    billing.setLastName("Samson");
	    billing.setPostalCode("J4B-8J9");
	    billing.setZone(zone);
	    
	    customer.setBilling(billing);
	    customer.setDelivery(delivery);		
		customerService.create(customer);
		
		Currency currency = currencyService.getByCode(CAD_CURRENCY_CODE);

		OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
		
		
		
		
		Order order = new Order();
		order.setDatePurchased(new Date());
		order.setCurrency(currency);
		order.setLastModified(new Date());
		order.setBilling(billing);
		
		Locale l = Locale.CANADA;
		order.setLocale(l);


		order.setCurrencyValue(new BigDecimal(0.98));//compared to based currency (not necessary)
		order.setCustomerId(customer.getId());
		order.setDelivery(delivery);
		order.setIpAddress("ipAddress" );
		order.setMerchant(store);
		order.setCustomerEmailAddress(customer.getEmailAddress());

		order.setOrderDateFinished(new Date());//committed date

		orderStatusHistory.setComments("We received your order");
		orderStatusHistory.setCustomerNotified(1);
		orderStatusHistory.setStatus(OrderStatus.ORDERED);
		orderStatusHistory.setDateAdded(new Date() );
		orderStatusHistory.setOrder(order);
		order.getOrderHistory().add( orderStatusHistory );		

		order.setPaymentType(PaymentType.PAYPAL);
		order.setPaymentModuleCode("paypal");
		order.setStatus( OrderStatus.DELIVERED);
		order.setTotal(new BigDecimal(23.99));
		
		
		//OrderProductDownload - Digital download
		OrderProductDownload orderProductDownload = new OrderProductDownload();
		orderProductDownload.setDownloadCount(1);
		orderProductDownload.setMaxdays(31);		
		orderProductDownload.setOrderProductFilename("Your digital file name");
		
		//OrderProductPrice
		OrderProductPrice oproductprice = new OrderProductPrice();
		oproductprice.setDefaultPrice(true);	
		oproductprice.setProductPrice(new BigDecimal(19.99) );
		oproductprice.setProductPriceCode("baseprice" );
		oproductprice.setProductPriceName("Base Price" );

		//OrderProduct
		OrderProduct oproduct = new OrderProduct();
		oproduct.getDownloads().add( orderProductDownload);
		oproduct.setOneTimeCharge( new BigDecimal(19.99) );
		oproduct.setOrder(order);		
		oproduct.setProductName( "Product name" );
		oproduct.setProductQuantity(1);
		oproduct.setSku("TB12345" );		
		oproduct.getPrices().add(oproductprice ) ;
		
		oproductprice.setOrderProduct(oproduct);		
		orderProductDownload.setOrderProduct(oproduct);
		order.getOrderProducts().add(oproduct);

		//requires 
		//OrderProduct
		//OrderProductPrice
		//OrderTotal
		

		
		//OrderTotal
		OrderTotal subtotal = new OrderTotal();	
		subtotal.setModule("summary" );		
		subtotal.setSortOrder(0);
		subtotal.setText("Summary" );
		subtotal.setTitle("Summary" );
		subtotal.setOrderTotalCode("summary");
		subtotal.setValue(new BigDecimal(19.99 ) );
		subtotal.setOrder(order);
		
		order.getOrderTotal().add(subtotal);
		
		OrderTotal tax = new OrderTotal();	
		tax.setModule("tax" );		
		tax.setSortOrder(1);
		tax.setText("Tax" );
		tax.setTitle("Tax" );
		tax.setOrderTotalCode("tax");
		tax.setValue(new BigDecimal(4) );
		tax.setOrder(order);
		
		order.getOrderTotal().add(tax);
		
		OrderTotal total = new OrderTotal();	
		total.setModule("total" );		
		total.setSortOrder(2);
		total.setText("Total" );
		total.setTitle("Total" );
		total.setOrderTotalCode("total");
		total.setValue(new BigDecimal(23.99) );
		total.setOrder(order);
		
		order.getOrderTotal().add(total);
		
		orderService.create(order);
		Assert.assertTrue(orderService.count() == 1);
	}
	
	@Ignore
	@Test
	public void getMerchantOrders() throws ServiceException {
		
		List<Order> merchantOrders= new ArrayList<Order>();
		
		Language language = languageService.getByCode(ENGLISH_LANGUAGE_CODE);
		Currency currency = currencyService.getByCode(EURO_CURRENCY_CODE);
		Country country = countryService.getByCode(FR_COUNTRY_CODE);
		Zone zone = zoneService.getByCode("VT");
		
		MerchantStore merchant = new MerchantStore();		
		merchant.setCurrency(currency);
		merchant.setStorename("Test Store");
		merchant.setCountry(country);
		merchant.setDefaultLanguage(language);		
		merchant.setStorecity("Test Store City");
		merchant.setCode( merchantService.count()+"");
		Language en = languageService.getByCode("en");
		Language fr = languageService.getByCode("fr");
		List<Language> supportedLanguages = new ArrayList<Language>();
		supportedLanguages.add(en);
		supportedLanguages.add(fr);
		merchant.setLanguages( supportedLanguages );
		merchant.setStoreEmailAddress("store_email@email.com");
		merchant.setStorephone("Merchant Store Phone");
		merchant.setStorepostalcode("12061");		
		merchantService.create(merchant);	
		
		
		Customer customer = new Customer();	
		customer.setMerchantStore(merchant);
		customer.setEmailAddress("email@email.com");
		customer.setPassword("-1999");
		customer.setNick("My New nick");
		customer.setCompany(" Apple");	
		customer.setGender(CustomerGender.M);
		customer.setDateOfBirth(new Date());		
		
		Billing billing = new Billing();
	    billing.setAddress("Billing address");
	    billing.setCity("Billing city");
	    billing.setCompany("Billing company");
	    billing.setCountry(country);
//	    billing.setCountryCode(CA_COUNTRY_CODE);
	    billing.setFirstName("Carl");
	    billing.setLastName("Samson");
	    billing.setPostalCode("Billing postal code");
	    billing.setState("Billing state");
	    billing.setZone(zone);
	    
	    Delivery delivery = new Delivery();
	    delivery.setAddress("Shipping address");
	    delivery.setCountry(country);
	    delivery.setZone(zone);	    
	    
	    customer.setBilling(billing);
	    customer.setDelivery(delivery);
	    
		customerService.create(customer);		
				
		OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
		Order order = new Order();
		order.setDatePurchased(new Date());
		order.setCurrency(currency);
		order.setMerchant(merchant);
		order.setLastModified(new Date());
		
		CreditCard creditCard = new CreditCard();
		creditCard.setCardType(CreditCardType.VISA);

		creditCard.setCcCvv("123");
		creditCard.setCcExpires("12/30/2020" );
		creditCard.setCcNumber( "123456789");
		creditCard.setCcOwner("ccOwner" );

		order.setCreditCard(creditCard);
		
		order.setCurrencyValue(new BigDecimal(19.99));
		order.setCustomerId(new Long(1) );
		order.setDelivery(delivery);
		order.setIpAddress("ipAddress" );
		order.setMerchant(merchant);
		order.setOrderDateFinished(new Date());		
		orderStatusHistory.setDateAdded(new Date() );
		orderStatusHistory.setOrder(order);
		order.setPaymentType(PaymentType.CREDITCARD);
		order.setPaymentModuleCode("payment Module Code");
		order.setShippingModuleCode("UPS" );
		order.setStatus( OrderStatus.ORDERED);
		order.setTotal(new BigDecimal(23.99));
		
		//OrderProduct
		OrderProduct oproduct = new OrderProduct();
		oproduct.setDownloads(null);
		oproduct.setOneTimeCharge( new BigDecimal(16.99) );
		oproduct.setOrder(order);		
		oproduct.setProductName( "Order Product Name" );
		oproduct.setProductQuantity(5);
		oproduct.setSku("Order Product sku" );		


		orderService.create(order);

	
		merchantOrders = orderService.listByStore(merchant);

		
		Assert.assertTrue("Merchant Orders are null." , merchantOrders != null);
		Assert.assertTrue("Merchant Orders count is not one." , (merchantOrders != null && merchantOrders.size() == 1) );
	}
	
	
	@Test
	public void testSearchOrders() throws ServiceException {
		
		MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
		Country country = countryService.getByCode("CA");
		Zone zone = zoneService.getByCode("VT");
		
		//create 3 customers
		Customer firstCustomer = new Customer();
		firstCustomer.setMerchantStore(store);
		firstCustomer.setEmailAddress("test@test.com");
		firstCustomer.setGender(CustomerGender.M);
		firstCustomer.setAnonymous(true);
		firstCustomer.setCompany("ifactory");
		firstCustomer.setDateOfBirth(new Date());
		firstCustomer.setNick("My nick");
		firstCustomer.setPassword("123456");

	    Delivery delivery = new Delivery();
	    delivery.setAddress("Shipping address");
	    delivery.setCountry(country);
	    delivery.setZone(zone);
	    
	    
	    Billing billing = new Billing();
	    billing.setAddress("Billing address");
	    billing.setCountry(country);
	    billing.setZone(zone);
	    
	    firstCustomer.setBilling(billing);
	    firstCustomer.setDelivery(delivery);
		
		customerService.create(firstCustomer);
		
		Customer secondCustomer = new Customer();
		secondCustomer.setMerchantStore(store);
		secondCustomer.setEmailAddress("test@test.com");
		secondCustomer.setGender(CustomerGender.M);
		secondCustomer.setDateOfBirth(new Date());
		secondCustomer.setPassword("123456");

		
		secondCustomer.setBilling(billing);
		secondCustomer.setDelivery(delivery);
		
		customerService.create(secondCustomer);
		
		Customer thirdCustomer = new Customer();
		thirdCustomer.setMerchantStore(store);
		thirdCustomer.setEmailAddress("test@test.com");
		thirdCustomer.setGender(CustomerGender.M);
		thirdCustomer.setDateOfBirth(new Date());
		thirdCustomer.setPassword("123456");

		
		thirdCustomer.setBilling(billing);
		thirdCustomer.setDelivery(delivery);
		
		customerService.create(thirdCustomer);
		
		//create a few orders
		Order order = new Order();
		order.setDatePurchased(new Date());
		order.setCurrency(store.getCurrency());
		order.setMerchant(store);
		order.setLastModified(new Date());
		
		CreditCard creditCard = new CreditCard();
		creditCard.setCardType(CreditCardType.VISA);

		creditCard.setCcCvv("123");
		creditCard.setCcExpires("12/30/2020" );
		creditCard.setCcNumber( "123456789");
		creditCard.setCcOwner("ccOwner" );

		order.setCreditCard(creditCard);
		order.setCurrencyValue(new BigDecimal(19.99));
		order.setCustomerId(new Long(1) );
		order.setDelivery(delivery);
		order.setIpAddress("ipAddress" );

		order.setPaymentType(PaymentType.CREDITCARD);
		order.setPaymentModuleCode("beanstream");
		order.setShippingModuleCode("ups" );
		order.setStatus( OrderStatus.ORDERED);
		order.setTotal(new BigDecimal(23.99));
		
		OrderProductPrice oproductprice = new OrderProductPrice();
		oproductprice.setDefaultPrice(true);	
		oproductprice.setProductPrice(new BigDecimal(19.99) );
		oproductprice.setProductPriceCode("baseprice" );
		oproductprice.setProductPriceName("Base Price" );

		//OrderProduct
		OrderProduct oproduct = new OrderProduct();
		oproduct.setOneTimeCharge( new BigDecimal(19.99) );
		oproduct.setOrder(order);		
		oproduct.setProductName( "Product name" );
		oproduct.setProductQuantity(1);
		oproduct.setSku("TB12345" );		
		oproduct.getPrices().add(oproductprice ) ;
		
		oproductprice.setOrderProduct(oproduct);		
		order.getOrderProducts().add(oproduct);

		OrderTotal orderTotal = new OrderTotal();
		orderTotal.setModule("total");
		orderTotal.setOrder(order);
		orderTotal.setText("Total");
		orderTotal.setTitle("total");
		orderTotal.setValue(new BigDecimal(23.99));
		
		order.getOrderTotal().add(orderTotal);
		
		orderService.create(order);
		
		
		Order secondOrder = new Order();
		secondOrder.setDatePurchased(new Date());
		secondOrder.setCurrency(store.getCurrency());
		secondOrder.setMerchant(store);
		secondOrder.setLastModified(new Date());
		
		creditCard = new CreditCard();
		creditCard.setCardType(CreditCardType.VISA);

		creditCard.setCcCvv("123");
		creditCard.setCcExpires("12/30/2020" );
		creditCard.setCcNumber( "123456789");
		creditCard.setCcOwner("ccOwner" );

		order.setCreditCard(creditCard);
		secondOrder.setCurrencyValue(new BigDecimal(19.99));
		secondOrder.setCustomerId(secondCustomer.getId() );
		secondOrder.setDelivery(delivery);
		secondOrder.setIpAddress("ipAddress" );
		order.setPaymentType(PaymentType.CREDITCARD);
		order.setPaymentModuleCode("beanstream");
		order.setShippingModuleCode("ups" );
		secondOrder.setShippingModuleCode("ups" );
		secondOrder.setStatus( OrderStatus.ORDERED);
		secondOrder.setTotal(new BigDecimal(23.99));
		
		oproductprice = new OrderProductPrice();
		oproductprice.setDefaultPrice(true);	
		oproductprice.setProductPrice(new BigDecimal(19.99) );
		oproductprice.setProductPriceCode("baseprice" );
		oproductprice.setProductPriceName("Base Price" );
		
		//OrderProduct
		oproduct = new OrderProduct();
		oproduct.setOneTimeCharge( new BigDecimal(19.99) );
		oproduct.setOrder(secondOrder);		
		oproduct.setProductName( "Product name" );
		oproduct.setProductQuantity(1);
		oproduct.setSku("TB12345" );		
		oproduct.getPrices().add(oproductprice ) ;
		
		oproductprice.setOrderProduct(oproduct);		
		secondOrder.getOrderProducts().add(oproduct);

		orderTotal = new OrderTotal();
		orderTotal.setModule("total");
		orderTotal.setOrder(secondOrder);
		orderTotal.setText("Total");
		orderTotal.setTitle("total");
		orderTotal.setValue(new BigDecimal(23.99));
		
		order.getOrderTotal().add(orderTotal);

		
		orderService.create(secondOrder);
		
		OrderCriteria orderCriteria = new OrderCriteria();
		orderCriteria.setCustomerName("Cruise");
		orderCriteria.setStartIndex(0);
		orderCriteria.setMaxCount(5);
		
		OrderList orderList = orderService.listByStore(store, orderCriteria);
		
		Assert.assertNotNull(orderList);
		
		System.out.println("Total count " + orderList.getTotalCount());
		
		
	}

}