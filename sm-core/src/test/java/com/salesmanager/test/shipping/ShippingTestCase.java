package com.salesmanager.test.shipping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute;
import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import com.salesmanager.core.business.catalog.product.model.price.ProductPriceDescription;
import com.salesmanager.core.business.catalog.product.model.type.ProductType;
import com.salesmanager.core.business.common.model.Billing;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.model.CustomerGender;
import com.salesmanager.core.business.common.model.Delivery;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.core.business.shipping.model.PackageDetails;
import com.salesmanager.core.business.shipping.model.ShippingBasisType;
import com.salesmanager.core.business.shipping.model.ShippingConfiguration;
import com.salesmanager.core.business.shipping.model.ShippingPackageType;
import com.salesmanager.core.business.shipping.model.ShippingProduct;
import com.salesmanager.core.business.shipping.model.ShippingQuote;
import com.salesmanager.core.business.shipping.model.ShippingType;
import com.salesmanager.core.business.shipping.service.ShippingService;
import com.salesmanager.core.business.system.model.Environment;
import com.salesmanager.core.business.system.model.IntegrationConfiguration;
import com.salesmanager.core.modules.integration.shipping.model.CustomShippingQuoteWeightItem;
import com.salesmanager.core.modules.integration.shipping.model.CustomShippingQuotesConfiguration;
import com.salesmanager.core.modules.integration.shipping.model.CustomShippingQuotesRegion;
import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;

public class ShippingTestCase extends AbstractSalesManagerCoreTestCase {
	
	private static final Date date = new Date(System.currentTimeMillis());
	
	@Autowired
	private ShippingService shippingService;
	
	@Autowired
	private LanguageService languageService;
	
	/**
	 * This test will invoke a shipping module to get real time shipping quotes
	 * @throws ServiceException
	 */
	@Test
	public void testGetShippingPackages() throws ServiceException {

	    Language en = languageService.getByCode("en");

	    MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
	    ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);
	    
	    //generate 2 products
	    
	    // PRODUCT 1 (height 4 inches x 3 inches length + 5 inches width) 1 pound
	    Product product = new Product();
	    product.setProductHeight(new BigDecimal(4));
	    product.setProductLength(new BigDecimal(3));
	    product.setProductWidth(new BigDecimal(5));
	    product.setProductWeight(new BigDecimal(8));
	    product.setSku("TB12345");
	    product.setType(generalType);
	    product.setMerchantStore(store);

	    // Product description
	    ProductDescription description = new ProductDescription();
	    description.setName("Product 1");
	    description.setLanguage(en);
	    description.setProduct(product);

	    product.getDescriptions().add(description);
	    //productService.create(product);

	    // Availability
	    ProductAvailability availability = new ProductAvailability();
	    availability.setProductDateAvailable(date);
	    availability.setProductQuantity(100);
	    availability.setRegion("*");
	    availability.setProduct(product);// associate with product

	    //productAvailabilityService.create(availability);

	    ProductPrice dprice = new ProductPrice();
	    dprice.setDefaultPrice(true);
	    dprice.setProductPriceAmount(new BigDecimal(29.99));
	    dprice.setProductAvailability(availability);

	    ProductPriceDescription dpd = new ProductPriceDescription();
	    dpd.setName("Base price");
	    dpd.setProductPrice(dprice);
	    dpd.setLanguage(en);

	    dprice.getDescriptions().add(dpd);


	    // PRODUCT 2  (height 3 inches x 4 inches length x 5 inches width) 2 pounds

	    Product product2 = new Product();
	    product2.setProductHeight(new BigDecimal(3));
	    product2.setProductLength(new BigDecimal(4));
	    product2.setProductWidth(new BigDecimal(5));
	    product2.setProductWeight(new BigDecimal(2));
	    product2.setSku("TB2468");
	    product2.setType(generalType);
	    product2.setMerchantStore(store);

	    // Product description
	    description = new ProductDescription();
	    description.setName("Product 2");
	    description.setLanguage(en);
	    description.setProduct(product2);

	    product2.getDescriptions().add(description);


	    // Availability
	    ProductAvailability availability2 = new ProductAvailability();
	    availability2.setProductDateAvailable(date);
	    availability2.setProductQuantity(100);
	    availability2.setRegion("*");
	    availability2.setProduct(product2);// associate with product

	    //productAvailabilityService.create(availability2);

	    ProductPrice dprice2 = new ProductPrice();
	    dprice2.setDefaultPrice(true);
	    dprice2.setProductPriceAmount(new BigDecimal(39.99));
	    dprice2.setProductAvailability(availability2);

	    dpd = new ProductPriceDescription();
	    dpd.setName("Base price");
	    dpd.setProductPrice(dprice2);
	    dpd.setLanguage(en);

	    dprice2.getDescriptions().add(dpd);

	    //add an attribute to product 2 that will augment weight of 1 pound
	    ProductAttribute attribute = new ProductAttribute();
	    attribute.setProduct(product2);
	    attribute.setAttributeDefault(true);
	    attribute.setProductAttributePrice(new BigDecimal(0));//no price variation
	    attribute.setProductAttributeWeight(new BigDecimal(1));//weight variation

	    
	    product2.getAttributes().add(attribute);
	    
	    //create an integration configuration
	    IntegrationConfiguration configuration = new IntegrationConfiguration();
	    configuration.setActive(true);
	    configuration.setEnvironment(Environment.TEST.name());
	    configuration.setModuleCode("canadapost");
	    
	    //configure shipping
	    ShippingConfiguration shippingConfiguration = new ShippingConfiguration();
	    shippingConfiguration.setShippingBasisType(ShippingBasisType.SHIPPING);
	    shippingConfiguration.setShippingType(ShippingType.INTERNATIONAL);
	    shippingConfiguration.setShippingPackageType(ShippingPackageType.ITEM);
	    shippingConfiguration.setBoxHeight(5);
	    shippingConfiguration.setBoxLength(5);
	    shippingConfiguration.setBoxWidth(5);
	    shippingConfiguration.setBoxWeight(1);
	    shippingConfiguration.setMaxWeight(10);
	    
	    //configure module
	    List<String> options = new ArrayList<String>();
	    options.add("PACKAGE");
	    configuration.getIntegrationKeys().put("account", "CPC_CS_TI_INC");
	    configuration.getIntegrationOptions().put("packages",options);
	    
	    shippingService.saveShippingConfiguration(shippingConfiguration, store);
	    shippingService.saveShippingQuoteModuleConfiguration(configuration, store);
	    
	    //now create ShippingProduct
	    ShippingProduct shippingProduct1 = new ShippingProduct(product);
	    ShippingProduct shippingProduct2 = new ShippingProduct(product2);
	    List<ShippingProduct> shippingProducts = new ArrayList<ShippingProduct>();
	    shippingProducts.add(shippingProduct1);
	    shippingProducts.add(shippingProduct2);
	    
	    List<PackageDetails> details = shippingService.getPackagesDetails(shippingProducts, store);
	    
	    Assert.notNull(details);
	    
	    for(PackageDetails pack : details) {
	    	System.out.println("Height " + pack.getShippingHeight());
	    	System.out.println("Length " + pack.getShippingLength());
	    	System.out.println("Width " + pack.getShippingWidth());
	    	System.out.println("Weight " + pack.getShippingWeight());
	    }

	    
	}
	
	@Test
	public void testGetShippingQuotes() throws ServiceException {

	    Language en = languageService.getByCode("en");
	    Country country = countryService.getByCode("CA");
	    Zone zone = zoneService.getByCode("QC");

	    MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
	    ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);
	    
	    //set valid store postal code
	    store.setStorepostalcode("J4B-9J9");

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
	    
	    productService.saveOrUpdate(product);
	    
	    

	    
	    //configure shipping
	    ShippingConfiguration shippingConfiguration = new ShippingConfiguration();
	    shippingConfiguration.setShippingBasisType(ShippingBasisType.SHIPPING);//based on shipping or billing address
	    shippingConfiguration.setShippingType(ShippingType.INTERNATIONAL);
	    shippingConfiguration.setShippingPackageType(ShippingPackageType.ITEM);//individual item pricing or box packaging (see unit test above)
	    //only if package type is package
	    shippingConfiguration.setBoxHeight(5);
	    shippingConfiguration.setBoxLength(5);
	    shippingConfiguration.setBoxWidth(5);
	    shippingConfiguration.setBoxWeight(1);
	    shippingConfiguration.setMaxWeight(10);
	    
	    List<String> supportedCountries = new ArrayList<String>();
	    supportedCountries.add("CA");
	    supportedCountries.add("US");
	    supportedCountries.add("UK");
	    supportedCountries.add("FR");
	    
	    shippingService.setSupportedCountries(store, supportedCountries);
	    
	    //create an integration configuration - CANADA POST
	    /*
	    IntegrationConfiguration configuration = new IntegrationConfiguration();
	    configuration.setActive(true);
	    configuration.setEnvironment(Environment.TEST.name());
	    configuration.setModuleCode("canadapost");
	    
	    //configure module
	    List<String> options = new ArrayList<String>();
	    options.add("PACKAGE");//PACKAGE or ENVELOPE (supported by Canadapost)
	    configuration.getIntegrationKeys().put("account", "CPC_CS_TI_INC");//CPC_DEMO_HTML
	    configuration.getIntegrationOptions().put("packages",options);*/
	    
	    //create an integration configuration - USPS
	    
	    //overwrite shipping US
/*	    Country us = countryService.getByCode("US");
	    Zone NY = zoneService.getByCode("NY");//store (origin) has to be in the US
	    store.setCountry(us);
	    store.setZone(NY);
	    store.setStorepostalcode("10451");*/
	    
/*	    IntegrationConfiguration configuration = new IntegrationConfiguration();
	    configuration.setActive(true);
	    configuration.setEnvironment(Environment.TEST.name());
	    configuration.setModuleCode("usps");
	    
	    //configure module
	    List<String> options = new ArrayList<String>();
	    options.add("Package");//Package or Envelope (supported by USPS)
	    configuration.getIntegrationKeys().put("account", "636CSTIC6187");
	    configuration.getIntegrationOptions().put("packages",options);*/
	    
	    
	    //create an integration configuration - USPS
	    
	    IntegrationConfiguration configuration = new IntegrationConfiguration();
	    configuration.setActive(true);
	    configuration.setEnvironment(Environment.TEST.name());
	    configuration.setModuleCode("ups");
	    
	    //configure module

	    configuration.getIntegrationKeys().put("userId", "csamson777");
	    configuration.getIntegrationKeys().put("accessKey", "AC66279FF8020AE0");
	    configuration.getIntegrationKeys().put("password", "william");
	    
	    List<String> options = new ArrayList<String>();
	    options.add("21");
	    configuration.getIntegrationOptions().put("packages",options);

	    shippingService.saveShippingConfiguration(shippingConfiguration, store);
	    shippingService.saveShippingQuoteModuleConfiguration(configuration, store);
	    
	    //now create ShippingProduct
	    ShippingProduct shippingProduct1 = new ShippingProduct(product);
	    List<ShippingProduct> shippingProducts = new ArrayList<ShippingProduct>();
	    shippingProducts.add(shippingProduct1);
	    
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
	    
	    //overwrite delivery to US (USPS)
/*	    delivery.setPostalCode("90002");
	    delivery.setCountry(us);
	    Zone california = zoneService.getByCode("CA");
	    delivery.setZone(california);*/
	    
	    
	    Billing billing = new Billing();
	    billing.setAddress("Billing address");
	    billing.setCountry(country);
	    billing.setZone(zone);
	    billing.setPostalCode("J4B-8J9");
	    
	    customer.setBilling(billing);
	    customer.setDelivery(delivery);
		
		customerService.create(customer);
	    
	    ShippingQuote shippingQuote = shippingService.getShippingQuote(store, delivery, shippingProducts, en);

	    Assert.notNull(shippingQuote);
	    
	}

	
	
	
	
	
	@Test
	public void testGetCustomShippingQuotesByWeight() throws ServiceException {

	    Language en = languageService.getByCode("en");
	    Country country = countryService.getByCode("CA");
	    Zone zone = zoneService.getByCode("QC");

	    MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
	    ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);
	    
	    //set valid store postal code
	    store.setStorepostalcode("J4B-9J9");

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
	    
	    productService.saveOrUpdate(product);
	    
	    

	    
	    //configure shipping
	    ShippingConfiguration shippingConfiguration = new ShippingConfiguration();
	    shippingConfiguration.setShippingBasisType(ShippingBasisType.SHIPPING);//based on shipping or billing address
	    shippingConfiguration.setShippingType(ShippingType.INTERNATIONAL);
	    shippingConfiguration.setShippingPackageType(ShippingPackageType.ITEM);//individual item pricing or box packaging (see unit test above)
	    //only if package type is package
	    shippingConfiguration.setBoxHeight(5);
	    shippingConfiguration.setBoxLength(5);
	    shippingConfiguration.setBoxWidth(5);
	    shippingConfiguration.setBoxWeight(1);
	    shippingConfiguration.setMaxWeight(10);
	    
	    List<String> supportedCountries = new ArrayList<String>();
	    supportedCountries.add("CA");
	    supportedCountries.add("US");
	    supportedCountries.add("UK");
	    supportedCountries.add("FR");
	    
	    shippingService.setSupportedCountries(store, supportedCountries);
	    

	    CustomShippingQuotesConfiguration customConfiguration = new CustomShippingQuotesConfiguration();
		customConfiguration.setModuleCode("weightBased");
		customConfiguration.setActive(true);
		
		CustomShippingQuotesRegion northRegion = new CustomShippingQuotesRegion();
		northRegion.setCustomRegionName("NORTH");
		
		List<String> countries = new ArrayList<String>();
		countries.add("CA");
		countries.add("US");
		
		northRegion.setCountries(countries);
		
		CustomShippingQuoteWeightItem caQuote4 = new CustomShippingQuoteWeightItem();
		caQuote4.setMaximumWeight(4);
		caQuote4.setPrice(new BigDecimal(20));
		CustomShippingQuoteWeightItem caQuote10 = new CustomShippingQuoteWeightItem();
		caQuote10.setMaximumWeight(10);
		caQuote10.setPrice(new BigDecimal(50));
		CustomShippingQuoteWeightItem caQuote100 = new CustomShippingQuoteWeightItem();
		caQuote100.setMaximumWeight(100);
		caQuote100.setPrice(new BigDecimal(120));
		List<CustomShippingQuoteWeightItem> quotes = new ArrayList<CustomShippingQuoteWeightItem>();
		quotes.add(caQuote4);
		quotes.add(caQuote10);
		quotes.add(caQuote100);
		
		northRegion.setQuoteItems(quotes);
		
		customConfiguration.getRegions().add(northRegion);
	    
	    
	    //create an integration configuration - USPS
	    
	    IntegrationConfiguration configuration = new IntegrationConfiguration();
	    configuration.setActive(true);
	    configuration.setEnvironment(Environment.TEST.name());
	    configuration.setModuleCode("weightBased");
	    
	    //configure module



	    shippingService.saveShippingConfiguration(shippingConfiguration, store);
	    shippingService.saveShippingQuoteModuleConfiguration(configuration, store);//create the basic configuration
	    shippingService.saveCustomShippingConfiguration("weightBased", customConfiguration, store);//and the custom configuration
	    
	    //now create ShippingProduct
	    ShippingProduct shippingProduct1 = new ShippingProduct(product);
	    List<ShippingProduct> shippingProducts = new ArrayList<ShippingProduct>();
	    shippingProducts.add(shippingProduct1);
	    
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
	    
	    //overwrite delivery to US
/*	    delivery.setPostalCode("90002");
	    delivery.setCountry(us);
	    Zone california = zoneService.getByCode("CA");
	    delivery.setZone(california);*/
	    
	    
	    Billing billing = new Billing();
	    billing.setAddress("Billing address");
	    billing.setCountry(country);
	    billing.setZone(zone);
	    billing.setPostalCode("J4B-8J9");
	    
	    customer.setBilling(billing);
	    customer.setDelivery(delivery);
		
		customerService.create(customer);
	    
	    ShippingQuote shippingQuote = shippingService.getShippingQuote(store, delivery, shippingProducts, en);

	    Assert.notNull(shippingQuote);
	    
	}



}