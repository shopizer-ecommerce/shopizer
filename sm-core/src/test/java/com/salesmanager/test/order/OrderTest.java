package com.salesmanager.test.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.category.CategoryDescription;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionType;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.catalog.product.price.ProductPriceType;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.common.Billing;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.CustomerGender;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.OrderCriteria;
import com.salesmanager.core.model.order.OrderList;
import com.salesmanager.core.model.order.OrderTotal;
import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import com.salesmanager.core.model.order.orderproduct.OrderProductAttribute;
import com.salesmanager.core.model.order.orderproduct.OrderProductPrice;
import com.salesmanager.core.model.order.orderstatus.OrderStatus;
import com.salesmanager.core.model.order.orderstatus.OrderStatusHistory;
import com.salesmanager.core.model.order.payment.CreditCard;
import com.salesmanager.core.model.payments.CreditCardType;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.currency.Currency;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;


@Ignore
public class OrderTest extends com.salesmanager.test.common.AbstractSalesManagerCoreTestCase {


	

	@Test
	public void getMerchantOrders() throws ServiceException {
		


		Currency currency = currencyService.getByCode(USD_CURRENCY_CODE);
		Country country = countryService.getByCode("US");
		Zone zone = zoneService.getByCode("VT");
		Language en = languageService.getByCode("en");
		
		MerchantStore merchant = merchantService.getByCode( MerchantStore.DEFAULT_STORE );	
	
		/** Create a customer **/
		Customer customer = new Customer();	
		customer.setMerchantStore(merchant);
		customer.setDefaultLanguage(en);
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
		
		
		//create a product with attributes

	    /** CATALOG CREATION **/
	    
	    ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);

	    /**
	     * Create the category
	     */
	    Category shirts = new Category();
	    shirts.setMerchantStore(merchant);
	    shirts.setCode("shirts");

	    CategoryDescription shirtsEnglishDescription = new CategoryDescription();
	    shirtsEnglishDescription.setName("Shirts");
	    shirtsEnglishDescription.setCategory(shirts);
	    shirtsEnglishDescription.setLanguage(en);

	    Set<CategoryDescription> descriptions = new HashSet<CategoryDescription>();
	    descriptions.add(shirtsEnglishDescription);


	    shirts.setDescriptions(descriptions);
	    categoryService.create(shirts);
	    
	    
	    /**
	     * Create a manufacturer
	     */
	    Manufacturer addidas = new Manufacturer();
	    addidas.setMerchantStore(merchant);
	    addidas.setCode("addidas");

	    ManufacturerDescription addidasDesc = new ManufacturerDescription();
	    addidasDesc.setLanguage(en);
	    addidasDesc.setManufacturer(addidas);
	    addidasDesc.setName("Addidas");
	    addidas.getDescriptions().add(addidasDesc);

	    manufacturerService.create(addidas);
	    
	    /**
	     * Create an option
	     */
	    ProductOption option = new ProductOption();
	    option.setMerchantStore(merchant);
	    option.setCode("color");
	    option.setProductOptionType(ProductOptionType.Radio.name());
	    
	    ProductOptionDescription optionDescription = new ProductOptionDescription();
	    optionDescription.setLanguage(en);
	    optionDescription.setName("Color");
	    optionDescription.setDescription("Item color");
	    optionDescription.setProductOption(option);
	    
	    option.getDescriptions().add(optionDescription);
	    
	    productOptionService.saveOrUpdate(option);
	    
	    
	    /** first option value **/
	    ProductOptionValue white = new ProductOptionValue();
	    white.setMerchantStore(merchant);
	    white.setCode("white");
	    
	    ProductOptionValueDescription whiteDescription = new ProductOptionValueDescription();
	    whiteDescription.setLanguage(en);
	    whiteDescription.setName("White");
	    whiteDescription.setDescription("White color");
	    whiteDescription.setProductOptionValue(white);
	    
	    white.getDescriptions().add(whiteDescription);
	    
	    productOptionValueService.saveOrUpdate(white);
	    
	    
	    ProductOptionValue black = new ProductOptionValue();
	    black.setMerchantStore(merchant);
	    black.setCode("black");
	    
	    /** second option value **/
	    ProductOptionValueDescription blackDesc = new ProductOptionValueDescription();
	    blackDesc.setLanguage(en);
	    blackDesc.setName("Black");
	    blackDesc.setDescription("Black color");
	    blackDesc.setProductOptionValue(black);
	    
	    black.getDescriptions().add(blackDesc);

	    productOptionValueService.saveOrUpdate(black);
	    
	    
	    /**
	     * Create a complex product
	     */
	    Product product = new Product();
	    product.setProductHeight(new BigDecimal(4));
	    product.setProductLength(new BigDecimal(3));
	    product.setProductWidth(new BigDecimal(1));
	    product.setSku("TB12345");
	    product.setManufacturer(addidas);
	    product.setType(generalType);
	    product.setMerchantStore(merchant);

	    // Product description
	    ProductDescription description = new ProductDescription();
	    description.setName("Short sleeves shirt");
	    description.setLanguage(en);
	    description.setProduct(product);

	    product.getDescriptions().add(description);
	    product.getCategories().add(shirts);
	    
	    
	    //availability
	    ProductAvailability availability = new ProductAvailability();
	    availability.setProductDateAvailable(new Date());
	    availability.setProductQuantity(100);
	    availability.setRegion("*");
	    availability.setProduct(product);// associate with product
	    
	    //price
	    ProductPrice dprice = new ProductPrice();
	    dprice.setDefaultPrice(true);
	    dprice.setProductPriceAmount(new BigDecimal(29.99));
	    dprice.setProductAvailability(availability);
	    
	    

	    ProductPriceDescription dpd = new ProductPriceDescription();
	    dpd.setName("Base price");
	    dpd.setProductPrice(dprice);
	    dpd.setLanguage(en);

	    dprice.getDescriptions().add(dpd);
	    availability.getPrices().add(dprice);
	    product.getAvailabilities().add(availability);
	    
	    
	    //attributes
	    //white
	    ProductAttribute whiteAttribute = new ProductAttribute();
	    whiteAttribute.setProduct(product);
	    whiteAttribute.setProductOption(option);
	    whiteAttribute.setAttributeDefault(true);
	    whiteAttribute.setProductAttributePrice(new BigDecimal(0));//no price variation
	    whiteAttribute.setProductAttributeWeight(new BigDecimal(0));//no weight variation
	    whiteAttribute.setProductOption(option);
	    whiteAttribute.setProductOptionValue(white);
	    
	    product.getAttributes().add(whiteAttribute);
	    //black
	    ProductAttribute blackAttribute = new ProductAttribute();
	    blackAttribute.setProduct(product);
	    blackAttribute.setProductOption(option);
	    blackAttribute.setProductAttributePrice(new BigDecimal(5));//5 + dollars
	    blackAttribute.setProductAttributeWeight(new BigDecimal(0));//no weight variation
	    blackAttribute.setProductOption(option);
	    blackAttribute.setProductOptionValue(black);
	    
	    product.getAttributes().add(blackAttribute);

	    productService.create(product);

			

		
		/** Create an order **/
		Order order = new Order();
		

		
		/** payment details **/
		CreditCard creditCard = new CreditCard();
		creditCard.setCardType(CreditCardType.VISA);

		creditCard.setCcCvv("123");
		creditCard.setCcExpires("12/30/2020" );
		creditCard.setCcNumber( "123456789");
		creditCard.setCcOwner("ccOwner" );
		order.setCreditCard(creditCard);
		
		/** order core attributes **/
		order.setDatePurchased(new Date());
		order.setCurrency(currency);
		order.setMerchant(merchant);
		order.setLastModified(new Date());
		order.setCurrencyValue(new BigDecimal(1));//no price variation because of the currency
		order.setCustomerId(new Long(1) );
		order.setDelivery(delivery);
		order.setIpAddress("ipAddress" );
		order.setMerchant(merchant);
		order.setOrderDateFinished(new Date());		

		order.setPaymentType(PaymentType.CREDITCARD);
		order.setPaymentModuleCode("payment Module Code");
		order.setShippingModuleCode("UPS" );
		order.setStatus( OrderStatus.ORDERED);
		order.setCustomerAgreement(true);
		order.setConfirmedAddress(true);
		order.setTotal(dprice.getProductPriceAmount());
		order.setCustomerEmailAddress(customer.getEmailAddress());
		
		order.setBilling(billing);
		order.setDelivery(delivery);
		
		
		/** ORDER PRODUCT **/
		
		//OrderProduct
		OrderProduct oproduct = new OrderProduct();
		oproduct.setDownloads(null);
		oproduct.setOneTimeCharge(dprice.getProductPriceAmount());
		oproduct.setOrder(order);		
		oproduct.setProductName( description.getName() );
		oproduct.setProductQuantity(1);
		oproduct.setSku(product.getSku());
		
		//set order product price
		OrderProductPrice orderProductPrice = new OrderProductPrice();
		orderProductPrice.setDefaultPrice(true);//default price (same as default product price)
		orderProductPrice.setOrderProduct(oproduct);
		orderProductPrice.setProductPrice(dprice.getProductPriceAmount());
		orderProductPrice.setProductPriceCode(ProductPriceType.ONE_TIME.name());
		
		
		oproduct.getPrices().add(orderProductPrice);
		
		//order product attribute
		
		OrderProductAttribute orderProductAttribute = new OrderProductAttribute();
		orderProductAttribute.setOrderProduct(oproduct);
		orderProductAttribute.setProductAttributePrice(new BigDecimal("0.00"));//no extra charge
		orderProductAttribute.setProductAttributeName(whiteDescription.getName());
		orderProductAttribute.setProductOptionId(option.getId());
		orderProductAttribute.setProductOptionValueId(white.getId());
		
		oproduct.getOrderAttributes().add(orderProductAttribute);
		
		order.getOrderProducts().add(oproduct);
		
		/** ORDER TOTAL **/
		
		OrderTotal subTotal = new OrderTotal();
		subTotal.setOrder(order);
		subTotal.setOrderTotalCode(Constants.OT_SUBTOTAL_MODULE_CODE);
		subTotal.setSortOrder(0);
		subTotal.setTitle("Sub Total");
		subTotal.setValue(dprice.getProductPriceAmount());
		
		order.getOrderTotal().add(subTotal);
		
		
		OrderTotal total = new OrderTotal();
		total.setOrder(order);
		total.setOrderTotalCode(Constants.OT_TOTAL_MODULE_CODE);
		total.setSortOrder(1);
		total.setTitle("Total");
		total.setValue(dprice.getProductPriceAmount());
		
		order.getOrderTotal().add(total);

		
		
		
		/** ORDER HISTORY **/
		
		//create a log entry in order history
		OrderStatusHistory history = new OrderStatusHistory();
		history.setOrder(order);
		history.setDateAdded(new Date());
		history.setStatus(OrderStatus.ORDERED);
		history.setComments("We received your order");
		
		order.getOrderHistory().add(history);
		
		/** CREATE ORDER **/
		
		orderService.create(order);
		
		
		/** SEARCH ORDERS **/

		OrderCriteria criteria = new OrderCriteria();
		criteria.setStartIndex(0);
		criteria.setMaxCount(10);
	
		OrderList ordserList = orderService.listByStore(merchant, criteria);

		
		Assert.assertNotNull(ordserList);
		Assert.assertTrue("Merchant Orders are null." , ordserList.getOrders() != null);
		Assert.assertTrue("Merchant Orders count is not one." , (ordserList.getOrders() != null && ordserList.getOrders().size() == 1) );
	}
	


}