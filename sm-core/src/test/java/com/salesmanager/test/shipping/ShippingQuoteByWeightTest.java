package com.salesmanager.test.shipping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Ignore;
import org.springframework.util.Assert;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.shipping.ShippingService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.common.Billing;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.CustomerGender;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.shipping.ShippingBasisType;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingPackageType;
import com.salesmanager.core.model.shipping.ShippingProduct;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.shipping.ShippingType;
import com.salesmanager.core.model.system.Environment;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.modules.integration.shipping.model.CustomShippingQuoteWeightItem;
import com.salesmanager.core.modules.integration.shipping.model.CustomShippingQuotesConfiguration;
import com.salesmanager.core.modules.integration.shipping.model.CustomShippingQuotesRegion;

@Ignore
public class ShippingQuoteByWeightTest extends com.salesmanager.test.common.AbstractSalesManagerCoreTestCase {
	
	private static final Date date = new Date(System.currentTimeMillis());
	
	@Inject
	private ShippingService shippingService;
	
	@Inject
	private LanguageService languageService;
	

	
	
	@Ignore
	//@Test
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
	    
	    productService.create(product);
	    //productService.saveOrUpdate(product);
	    

	    // Availability
	    ProductAvailability availability = new ProductAvailability();
	    availability.setProductDateAvailable(new Date());
	    availability.setProductQuantity(100);
	    availability.setRegion("*");
	    availability.setProduct(product);// associate with product
	    
	    product.getAvailabilities().add(availability);

	    productAvailabilityService.create(availability);

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
	    
	    productPriceService.create(dprice);
	    
	    //get product
	    product = productService.getByCode("TESTSKU", en);


	    
	    
	    //check the product
	    Set<ProductAvailability> avails = product.getAvailabilities();
	    for(ProductAvailability as : avails) {
		    Set<ProductPrice> availabilityPrices = as.getPrices();
		    for(ProductPrice ps : availabilityPrices) {
		    	System.out.println(ps.getProductPriceAmount().toString());
		    }
	    }
	    
	    //check availability
	    Set<ProductPrice> availabilityPrices = availability.getPrices();
	    for(ProductPrice ps : availabilityPrices) {
	    	System.out.println(ps.getProductPriceAmount().toString());
	    }
	    

	    
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
	    FinalPrice price = pricingService.calculateProductPrice(product);
	    shippingProduct1.setFinalPrice(price);
	    
	    List<ShippingProduct> shippingProducts = new ArrayList<ShippingProduct>();
	    shippingProducts.add(shippingProduct1);
	    
		Customer customer = new Customer();
		customer.setMerchantStore(store);
		customer.setEmailAddress("test@test.com");
		customer.setGender(CustomerGender.M);
		customer.setDefaultLanguage(en);

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
	    delivery.setPostalCode("J5C-6J4");
	    
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
	    billing.setFirstName("Carl");
	    billing.setLastName("Samson");
	    
	    customer.setBilling(billing);
	    customer.setDelivery(delivery);
		
		customerService.create(customer);
		
		Long dummyCartId = 0L;//for correlation
	    
	    ShippingQuote shippingQuote = shippingService.getShippingQuote(dummyCartId, store, delivery, shippingProducts, en);

	    Assert.notNull(shippingQuote);
	    
	}



}