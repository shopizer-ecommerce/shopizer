package com.salesmanager.shop.init.data;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.business.services.catalog.product.availability.ProductAvailabilityService;
import com.salesmanager.core.business.services.catalog.product.image.ProductImageService;
import com.salesmanager.core.business.services.catalog.product.manufacturer.ManufacturerService;
import com.salesmanager.core.business.services.catalog.product.price.ProductPriceService;
import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.business.services.catalog.product.type.ProductTypeService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.currency.CurrencyService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.category.CategoryDescription;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationshipType;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.common.Billing;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.CustomerGender;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.OrderTotal;
import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;
import com.salesmanager.core.model.order.orderproduct.OrderProductPrice;
import com.salesmanager.core.model.order.orderstatus.OrderStatus;
import com.salesmanager.core.model.order.orderstatus.OrderStatusHistory;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.currency.Currency;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.GroupType;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.utils.LocaleUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InitStoreData implements InitData {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitStoreData.class);
	
	@Inject
	protected ProductService productService;

	
	@Inject
	protected ProductPriceService productPriceService;
	
	@Inject
	protected ProductAttributeService productAttributeService;
	
	@Inject
	protected ProductOptionService productOptionService;
	
	@Inject
	protected ProductOptionValueService productOptionValueService;
	
	@Inject
	protected ProductAvailabilityService productAvailabilityService;
	
	@Inject
	protected ProductImageService productImageService;
	
	@Inject
	protected CategoryService categoryService;
	
	@Inject
	protected MerchantStoreService merchantService;
	
	@Inject
	protected ProductTypeService productTypeService;
	
	@Inject
	protected LanguageService languageService;
	
	@Inject
	protected CountryService countryService;
	
	@Inject
	protected ZoneService zoneService;
	
	@Inject
	protected CustomerService customerService;
	
	@Inject
	protected ManufacturerService manufacturerService;

	@Inject
	protected CurrencyService currencyService;
	
	@Inject
	protected OrderService orderService;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Inject
	protected GroupService   groupService;
	
	@Inject
	private ProductRelationshipService productRelationshipService;

	public void initInitialData() throws ServiceException {
		

		LOGGER.info("Starting the initialization of test data");
		Date date = new Date(System.currentTimeMillis());
		
		//2 languages by default
		Language en = languageService.getByCode("en");
		Language fr = languageService.getByCode("fr");
		
		Country canada = countryService.getByCode("CA");
		Zone zone = zoneService.getByCode("QC");
		
		//create a merchant
		MerchantStore store = merchantService.getMerchantStore(MerchantStore.DEFAULT_STORE);
		ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);
		
		
		 Category book = new Category();
		    book.setMerchantStore(store);
		    book.setCode("computerbooks");
		    book.setVisible(true);

		    CategoryDescription bookEnglishDescription = new CategoryDescription();
		    bookEnglishDescription.setName("Computer Books");
		    bookEnglishDescription.setCategory(book);
		    bookEnglishDescription.setLanguage(en);
		    bookEnglishDescription.setSeUrl("computer-books");

		    CategoryDescription bookFrenchDescription = new CategoryDescription();
		    bookFrenchDescription.setName("Livres d'informatique");
		    bookFrenchDescription.setCategory(book);
		    bookFrenchDescription.setLanguage(fr);
		    bookFrenchDescription.setSeUrl("livres-informatiques");

		    List<CategoryDescription> descriptions = new ArrayList<CategoryDescription>();
		    descriptions.add(bookEnglishDescription);
		    descriptions.add(bookFrenchDescription);

		    book.setDescriptions(descriptions);

		    categoryService.create(book);

		    Category novs = new Category();
		    novs.setMerchantStore(store);
		    novs.setCode("novels");
		    novs.setVisible(false);

		    CategoryDescription novsEnglishDescription = new CategoryDescription();
		    novsEnglishDescription.setName("Novels");
		    novsEnglishDescription.setCategory(novs);
		    novsEnglishDescription.setLanguage(en);
		    novsEnglishDescription.setSeUrl("novels");

		    CategoryDescription novsFrenchDescription = new CategoryDescription();
		    novsFrenchDescription.setName("Romans");
		    novsFrenchDescription.setCategory(novs);
		    novsFrenchDescription.setLanguage(fr);
		    novsFrenchDescription.setSeUrl("romans");

		    List<CategoryDescription> descriptions2 = new ArrayList<CategoryDescription>();
		    descriptions2.add(novsEnglishDescription);
		    descriptions2.add(novsFrenchDescription);

		    novs.setDescriptions(descriptions2);

		    categoryService.create(novs);
		    
		    Category tech = new Category();
		    tech.setMerchantStore(store);
		    tech.setCode("tech");

		    CategoryDescription techEnglishDescription = new CategoryDescription();
		    techEnglishDescription.setName("Technology");
		    techEnglishDescription.setCategory(tech);
		    techEnglishDescription.setLanguage(en);
		    techEnglishDescription.setSeUrl("technology");

		    CategoryDescription techFrenchDescription = new CategoryDescription();
		    techFrenchDescription.setName("Technologie");
		    techFrenchDescription.setCategory(tech);
		    techFrenchDescription.setLanguage(fr);
		    techFrenchDescription.setSeUrl("technologie");

		    List<CategoryDescription> descriptions4 = new ArrayList<CategoryDescription>();
		    descriptions4.add(techEnglishDescription);
		    descriptions4.add(techFrenchDescription);

		    tech.setDescriptions(descriptions4);
		    
		    tech.setParent(book);

		    categoryService.create(tech);
		    categoryService.addChild(book, tech);

		    Category web = new Category();
		    web.setMerchantStore(store);
		    web.setCode("web");
		    web.setVisible(true);

		    CategoryDescription webEnglishDescription = new CategoryDescription();
		    webEnglishDescription.setName("Web");
		    webEnglishDescription.setCategory(web);
		    webEnglishDescription.setLanguage(en);
		    webEnglishDescription.setSeUrl("the-web");

		    CategoryDescription webFrenchDescription = new CategoryDescription();
		    webFrenchDescription.setName("Web");
		    webFrenchDescription.setCategory(web);
		    webFrenchDescription.setLanguage(fr);
		    webFrenchDescription.setSeUrl("le-web");

		    List<CategoryDescription> descriptions3 = new ArrayList<CategoryDescription>();
		    descriptions3.add(webEnglishDescription);
		    descriptions3.add(webFrenchDescription);

		    web.setDescriptions(descriptions3);
		    
		    web.setParent(book);

		    categoryService.create(web);
		    categoryService.addChild(book, web);



		    Category fiction = new Category();
		    fiction.setMerchantStore(store);
		    fiction.setCode("fiction");
		    fiction.setVisible(true);

		    CategoryDescription fictionEnglishDescription = new CategoryDescription();
		    fictionEnglishDescription.setName("Fiction");
		    fictionEnglishDescription.setCategory(fiction);
		    fictionEnglishDescription.setLanguage(en);
		    fictionEnglishDescription.setSeUrl("fiction");

		    CategoryDescription fictionFrenchDescription = new CategoryDescription();
		    fictionFrenchDescription.setName("Sc Fiction");
		    fictionFrenchDescription.setCategory(fiction);
		    fictionFrenchDescription.setLanguage(fr);
		    fictionFrenchDescription.setSeUrl("fiction");

		    List<CategoryDescription> fictiondescriptions = new ArrayList<CategoryDescription>();
		    fictiondescriptions.add(fictionEnglishDescription);
		    fictiondescriptions.add(fictionFrenchDescription);

		    fiction.setDescriptions(fictiondescriptions);
		    
		    fiction.setParent(novs);

		    categoryService.create(fiction);
		    categoryService.addChild(novs, fiction);
		    
		    
		    Category business = new Category();
		    business.setMerchantStore(store);
		    business.setCode("business");
		    business.setVisible(true);

		    CategoryDescription businessEnglishDescription = new CategoryDescription();
		    businessEnglishDescription.setName("Business");
		    businessEnglishDescription.setCategory(business);
		    businessEnglishDescription.setLanguage(en);
		    businessEnglishDescription.setSeUrl("business");

		    CategoryDescription businessFrenchDescription = new CategoryDescription();
		    businessFrenchDescription.setName("Affaires");
		    businessFrenchDescription.setCategory(business);
		    businessFrenchDescription.setLanguage(fr);
		    businessFrenchDescription.setSeUrl("affaires");

		    List<CategoryDescription> businessdescriptions = new ArrayList<CategoryDescription>();
		    businessdescriptions.add(businessEnglishDescription);
		    businessdescriptions.add(businessFrenchDescription);

		    business.setDescriptions(businessdescriptions);
		    

		    categoryService.create(business);

		   		    
		    
		    Category cloud = new Category();
		    cloud.setMerchantStore(store);
		    cloud.setCode("cloud");
		    cloud.setVisible(true);

		    CategoryDescription cloudEnglishDescription = new CategoryDescription();
		    cloudEnglishDescription.setName("Cloud computing");
		    cloudEnglishDescription.setCategory(cloud);
		    cloudEnglishDescription.setLanguage(en);
		    cloudEnglishDescription.setSeUrl("cloud-computing");

		    CategoryDescription cloudFrenchDescription = new CategoryDescription();
		    cloudFrenchDescription.setName("Programmation pour le cloud");
		    cloudFrenchDescription.setCategory(cloud);
		    cloudFrenchDescription.setLanguage(fr);
		    cloudFrenchDescription.setSeUrl("programmation-cloud");

		    List<CategoryDescription> clouddescriptions = new ArrayList<CategoryDescription>();
		    clouddescriptions.add(cloudEnglishDescription);
		    clouddescriptions.add(cloudFrenchDescription);

		    cloud.setDescriptions(clouddescriptions);
		    
		    cloud.setParent(tech);

		    categoryService.create(cloud);
		    categoryService.addChild(tech, cloud);

		    // Add products
		    // ProductType generalType = productTypeService.

		    Manufacturer oreilley = new Manufacturer();
		    oreilley.setMerchantStore(store);
		    oreilley.setCode("oreilley");

		    ManufacturerDescription oreilleyd = new ManufacturerDescription();
		    oreilleyd.setLanguage(en);
		    oreilleyd.setName("O\'Reilley");
		    oreilleyd.setManufacturer(oreilley);
		    oreilley.getDescriptions().add(oreilleyd);

		    manufacturerService.create(oreilley);
		    
		    
		    Manufacturer sams = new Manufacturer();
		    sams.setMerchantStore(store);
		    sams.setCode("sams");

		    ManufacturerDescription samsd = new ManufacturerDescription();
		    samsd.setLanguage(en);
		    samsd.setName("Sams");
		    samsd.setManufacturer(sams);
		    sams.getDescriptions().add(samsd);

		    manufacturerService.create(sams);
		    
		    Manufacturer packt = new Manufacturer();
		    packt.setMerchantStore(store);
		    packt.setCode("packt");

		    ManufacturerDescription packtd = new ManufacturerDescription();
		    packtd.setLanguage(en);
		    packtd.setName("Packt");
		    packtd.setManufacturer(packt);
		    packt.getDescriptions().add(packtd);

		    manufacturerService.create(packt);

		    Manufacturer manning = new Manufacturer();
		    manning.setMerchantStore(store);
		    manning.setCode("manning");

		    ManufacturerDescription manningd = new ManufacturerDescription();
		    manningd.setLanguage(en);
		    manningd.setManufacturer(manning);
		    manningd.setName("Manning");
		    manning.getDescriptions().add(manningd);

		    manufacturerService.create(manning);

		    Manufacturer novells = new Manufacturer();
		    novells.setMerchantStore(store);
		    novells.setCode("novells");

		    ManufacturerDescription novellsd = new ManufacturerDescription();
		    novellsd.setLanguage(en);
		    novellsd.setManufacturer(novells);
		    novellsd.setName("Novells publishing");
		    novells.getDescriptions().add(novellsd);

		    manufacturerService.create(novells);

		    
		    // PRODUCT 1

		    Product product = new Product();
		    product.setProductHeight(new BigDecimal(10));
		    product.setProductLength(new BigDecimal(3));
		    product.setProductWidth(new BigDecimal(6));
		    product.setSku("TB12345");
		    product.setManufacturer(manning);
		    product.setType(generalType);
		    product.setMerchantStore(store);
		    product.setProductShipeable(true);
		    
		    // Availability
		    ProductAvailability availability = new ProductAvailability();
		    availability.setProductDateAvailable(date);
		    availability.setProductQuantity(100);
		    availability.setRegion("*");
		    availability.setProduct(product);// associate with product
		    
		    
		    
		    ProductPrice dprice = new ProductPrice();
		    dprice.setDefaultPrice(true);
		    dprice.setProductPriceAmount(new BigDecimal(39.99));
		    dprice.setProductAvailability(availability);

		    ProductPriceDescription dpd = new ProductPriceDescription();
		    dpd.setName("Base price");
		    dpd.setProductPrice(dprice);
		    dpd.setLanguage(en);

		    dprice.getDescriptions().add(dpd);
		    
		    availability.getPrices().add(dprice);
		    product.getAvailabilities().add(availability);

		    // Product description
		    ProductDescription description = new ProductDescription();
		    description.setName("Spring in Action");
		    description.setLanguage(en);
		    description.setSeUrl("Spring-in-Action");
		    description.setProduct(product);

		    product.getDescriptions().add(description);

		    product.getCategories().add(tech);
		    product.getCategories().add(web);


		    productService.create(product);
		    
		    try {
		    	ClassPathResource classPathResource = new ClassPathResource("/demo/spring.png");
		    	InputStream inStream = classPathResource.getInputStream();
		    	this.saveFile(inStream, "spring.png", product);
		    } catch(Exception e) {
		    	LOGGER.error("Error while reading demo file spring.png",e);
		    }
		    

		    // PRODUCT 2

		    Product product2 = new Product();
		    product2.setProductHeight(new BigDecimal(4));
		    product2.setProductLength(new BigDecimal(3));
		    product2.setProductWidth(new BigDecimal(1));
		    product2.setSku("TB2468");
		    product2.setManufacturer(packt);
		    product2.setType(generalType);
		    product2.setMerchantStore(store);
		    product2.setProductShipeable(true);

		    // Product description
		    description = new ProductDescription();
		    description.setName("Node Web Development");
		    description.setLanguage(en);
		    description.setProduct(product2);
		    description.setSeUrl("Node-Web-Development");

		    product2.getDescriptions().add(description);

		    product2.getCategories().add(tech);
		    product2.getCategories().add(web);
		    
		    // Availability
		    ProductAvailability availability2 = new ProductAvailability();
		    availability2.setProductDateAvailable(date);
		    availability2.setProductQuantity(100);
		    availability2.setRegion("*");
		    availability2.setProduct(product2);// associate with product

		    ProductPrice dprice2 = new ProductPrice();
		    dprice2.setDefaultPrice(true);
		    dprice2.setProductPriceAmount(new BigDecimal(29.99));
		    dprice2.setProductAvailability(availability2);

		    dpd = new ProductPriceDescription();
		    dpd.setName("Base price");
		    dpd.setProductPrice(dprice2);
		    dpd.setLanguage(en);

		    dprice2.getDescriptions().add(dpd);
		    
		    availability2.getPrices().add(dprice2);
		    product2.getAvailabilities().add(availability2);
		    
		    productService.create(product2);
		    
		    try {
		    	ClassPathResource classPathResource = new ClassPathResource("/demo/node.jpg");
		    	InputStream inStream = classPathResource.getInputStream();
		    	this.saveFile(inStream, "node.jpg", product2);
		    } catch(Exception e) {
		    	LOGGER.error("Error while reading demo file node.jpg",e);
		    }



		    // PRODUCT 3

		    Product product3 = new Product();
		    product3.setProductHeight(new BigDecimal(4));
		    product3.setProductLength(new BigDecimal(3));
		    product3.setProductWidth(new BigDecimal(1));
		    product3.setSku("NB1111");
		    product3.setManufacturer(oreilley);
		    product3.setType(generalType);
		    product3.setMerchantStore(store);
		    product3.setProductShipeable(true);

		    // Product description
		    description = new ProductDescription();
		    description.setName("Programming for PAAS");
		    description.setLanguage(en);
		    description.setProduct(product3);
		    description.setSeUrl("programming-for-paas");

		    product3.getDescriptions().add(description);

		    product3.getCategories().add(cloud);
		    
		    // Availability
		    ProductAvailability availability3 = new ProductAvailability();
		    availability3.setProductDateAvailable(date);
		    availability3.setProductQuantity(100);
		    availability3.setRegion("*");
		    availability3.setProduct(product3);// associate with product

		    ProductPrice dprice3 = new ProductPrice();
		    dprice3.setDefaultPrice(true);
		    dprice3.setProductPriceAmount(new BigDecimal(19.99));
		    dprice3.setProductAvailability(availability3);

		    dpd = new ProductPriceDescription();
		    dpd.setName("Base price");
		    dpd.setProductPrice(dprice3);
		    dpd.setLanguage(en);

		    dprice3.getDescriptions().add(dpd);
		    
		    availability3.getPrices().add(dprice3);
		    product3.getAvailabilities().add(availability3);
		    
		    
		    productService.create(product3);

		    
		    try {
		    	ClassPathResource classPathResource = new ClassPathResource("/demo/paas.JPG");
		    	InputStream inStream = classPathResource.getInputStream();
		    	this.saveFile(inStream, "paas.JPG", product3);
		    } catch(Exception e) {
		    	LOGGER.error("Error while reading demo file paas.jpg",e);
		    }

		    // PRODUCT 4
		    Product product4 = new Product();
		    product4.setProductHeight(new BigDecimal(4));
		    product4.setProductLength(new BigDecimal(3));
		    product4.setProductWidth(new BigDecimal(1));
		    product4.setSku("SF333345");
		    product4.setManufacturer(sams);
		    product4.setType(generalType);
		    product4.setMerchantStore(store);
		    product4.setProductShipeable(true);

		    // Product description
		    description = new ProductDescription();
		    description.setName("Android development");
		    description.setLanguage(en);
		    description.setProduct(product4);
		    description.setSeUrl("android-application-development");

		    product4.getDescriptions().add(description);

		    product4.getCategories().add(tech);
		    
		    // Availability
		    ProductAvailability availability4 = new ProductAvailability();
		    availability4.setProductDateAvailable(date);
		    availability4.setProductQuantity(100);
		    availability4.setRegion("*");
		    availability4.setProduct(product4);// associate with product


		    ProductPrice dprice4 = new ProductPrice();
		    dprice4.setDefaultPrice(true);
		    dprice4.setProductPriceAmount(new BigDecimal(18.99));
		    dprice4.setProductAvailability(availability4);

		    dpd = new ProductPriceDescription();
		    dpd.setName("Base price");
		    dpd.setProductPrice(dprice4);
		    dpd.setLanguage(en);

		    dprice4.getDescriptions().add(dpd);
		    
		    availability4.getPrices().add(dprice4);
		    product4.getAvailabilities().add(availability4);
 
		    productService.create(product4);


		    
		    try {
		    	ClassPathResource classPathResource = new ClassPathResource("/demo/android.jpg");
		    	InputStream inStream = classPathResource.getInputStream();
		    	this.saveFile(inStream, "android.jpg", product4);
		    } catch(Exception e) {
		    	LOGGER.error("Error while reading demo file android.jpg",e);
		    }

		    // PRODUCT 5
		    Product product5 = new Product();
		    product5.setProductHeight(new BigDecimal(4));
		    product5.setProductLength(new BigDecimal(3));
		    product5.setProductWidth(new BigDecimal(1));
		    product5.setSku("SF333346");
		    product5.setManufacturer(packt);
		    product5.setType(generalType);
		    product5.setMerchantStore(store);
		    product5.setProductShipeable(true);

		    // Product description
		    description = new ProductDescription();
		    description.setName("Android 3.0 Cookbook");
		    description.setLanguage(en);
		    description.setProduct(product5);
		    description.setSeUrl("android-3-cookbook");

		    product5.getDescriptions().add(description);

		    product5.getCategories().add(tech);
		    
		    
		    // Availability
		    ProductAvailability availability5 = new ProductAvailability();
		    availability5.setProductDateAvailable(date);
		    availability5.setProductQuantity(100);
		    availability5.setRegion("*");
		    availability5.setProduct(product5);// associate with product

		   // productAvailabilityService.create(availability5);

		    ProductPrice dprice5 = new ProductPrice();
		    dprice5.setDefaultPrice(true);
		    dprice5.setProductPriceAmount(new BigDecimal(18.99));
		    dprice5.setProductAvailability(availability5);

		    dpd = new ProductPriceDescription();
		    dpd.setName("Base price");
		    dpd.setProductPrice(dprice5);
		    dpd.setLanguage(en);

		    dprice5.getDescriptions().add(dpd);

		    availability5.getPrices().add(dprice5);
		    product5.getAvailabilities().add(availability5);

		    productService.create(product5);


		    
		    try {
		    	ClassPathResource classPathResource = new ClassPathResource("/demo/android2.jpg");
		    	InputStream inStream = classPathResource.getInputStream();
		    	this.saveFile(inStream, "android2.jpg", product5);
		    } catch(Exception e) {
		    	LOGGER.error("Error while reading demo file android2.jpg",e);
		    }

		    // PRODUCT 6

		    Product product6 = new Product();
		    product6.setProductHeight(new BigDecimal(4));
		    product6.setProductLength(new BigDecimal(3));
		    product6.setProductWidth(new BigDecimal(1));
		    product6.setSku("LL333444");
		    product6.setManufacturer(novells);
		    product6.setType(generalType);
		    product6.setMerchantStore(store);
		    product6.setProductShipeable(true);

		    // Product description
		    description = new ProductDescription();
		    description.setName("The Big Switch");
		    description.setLanguage(en);
		    description.setProduct(product6);
		    description.setSeUrl("the-big-switch");

		    product6.getDescriptions().add(description);

		    product6.getCategories().add(business);
		    
		    // Availability
		    ProductAvailability availability6 = new ProductAvailability();
		    availability6.setProductDateAvailable(date);
		    availability6.setProductQuantity(100);
		    availability6.setRegion("*");
		    availability6.setProduct(product6);// associate with product

		    //productAvailabilityService.create(availability6);

		    ProductPrice dprice6 = new ProductPrice();
		    dprice6.setDefaultPrice(true);
		    dprice6.setProductPriceAmount(new BigDecimal(18.99));
		    dprice6.setProductAvailability(availability6);

		    dpd = new ProductPriceDescription();
		    dpd.setName("Base price");
		    dpd.setProductPrice(dprice6);
		    dpd.setLanguage(en);

		    dprice6.getDescriptions().add(dpd);

		    availability6.getPrices().add(dprice6);
		    product6.getAvailabilities().add(availability6);
		    
		    productService.create(product6);


		    
		    try {

		    	ClassPathResource classPathResource = new ClassPathResource("/demo/google.jpg");
		    	InputStream inStream = classPathResource.getInputStream();
		    	this.saveFile(inStream, "google.jpg", product6);
		    } catch(Exception e) {
		    	LOGGER.error("Error while reading demo file google.jpg",e);
		    }
		    
		    //featured items
		    
			ProductRelationship relationship = new ProductRelationship();
			relationship.setActive(true);
			relationship.setCode(ProductRelationshipType.FEATURED_ITEM.name());
			relationship.setStore(store);
			relationship.setRelatedProduct(product);
			
			productRelationshipService.saveOrUpdate(relationship);
			
			relationship = new ProductRelationship();
			relationship.setActive(true);
			relationship.setCode(ProductRelationshipType.FEATURED_ITEM.name());
			relationship.setStore(store);
			relationship.setRelatedProduct(product6);
			
			productRelationshipService.saveOrUpdate(relationship);
			
			
			relationship = new ProductRelationship();
			relationship.setActive(true);
			relationship.setCode(ProductRelationshipType.FEATURED_ITEM.name());
			relationship.setStore(store);
			relationship.setRelatedProduct(product5);
			
			productRelationshipService.saveOrUpdate(relationship);

			
			relationship = new ProductRelationship();
			relationship.setActive(true);
			relationship.setCode(ProductRelationshipType.FEATURED_ITEM.name());
			relationship.setStore(store);
			relationship.setRelatedProduct(product2);
			
			productRelationshipService.saveOrUpdate(relationship);
			
		    
		    //Create a customer (user name[nick] : shopizer password : password)

		    Customer customer = new Customer();
			customer.setMerchantStore(store);
			customer.setEmailAddress("test@shopizer.com");
			customer.setGender(CustomerGender.M);						
			customer.setAnonymous(false);
			customer.setCompany("CSTI Consulting");
			customer.setDateOfBirth(new Date());

			customer.setDefaultLanguage(en);
			customer.setNick("shopizer");
			
			String password = passwordEncoder.encode("password");
			customer.setPassword(password);
			
			List<Group> groups = groupService.listGroup(GroupType.CUSTOMER);
			  

			for(Group group : groups) {
				  if(group.getGroupName().equals(Constants.GROUP_CUSTOMER)) {
					  customer.getGroups().add(group);
				  }
			}
			
		    Delivery delivery = new Delivery();
		    delivery.setAddress("358 Du Languadoc");
		    delivery.setCity( "Boucherville" );
		    delivery.setCountry(canada);
//		    delivery.setCountryCode(canada.getIsoCode());
		    delivery.setFirstName("Leonardo" );
		    delivery.setLastName("DiCaprio" );
		    delivery.setPostalCode("J4B-8J9" );
		    delivery.setZone(zone);	    
		    
		    Billing billing = new Billing();
		    billing.setAddress("358 Du Languadoc");
		    billing.setCity("Boucherville");
		    billing.setCompany("CSTI Consulting");
		    billing.setCountry(canada);
//		    billing.setCountryCode(canada.getIsoCode());
		    billing.setFirstName("Leonardo" );
		    billing.setLastName("DiCaprio" );
		    billing.setPostalCode("J4B-8J9");
		    billing.setZone(zone);
		    
		    customer.setBilling(billing);
		    customer.setDelivery(delivery);		
			customerService.create(customer);
			
			Currency currency = currencyService.getByCode("CAD");

			OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
			
			//create an order
			
			Order order = new Order();
			order.setDatePurchased(new Date());
			order.setCurrency(currency);
			order.setLastModified(new Date());
			order.setBilling(billing);

			
			order.setLocale(LocaleUtils.getLocale(store));

			order.setCurrencyValue(new BigDecimal(0.98));//compared to based currency (not necessary)
			order.setCustomerId(customer.getId());
			order.setBilling(billing);
			order.setDelivery(delivery);
			order.setCustomerEmailAddress("leo@shopizer.com");
			order.setDelivery(delivery);
			order.setIpAddress("ipAddress" );
			order.setMerchant(store);
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
			//oproductprice.setProductPriceSpecialAmount(new BigDecimal(13.99) );	

			
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

			//OrderTotal
			OrderTotal subtotal = new OrderTotal();	
			subtotal.setModule("summary" );		
			subtotal.setSortOrder(0);
			subtotal.setText("Summary" );
			subtotal.setTitle("Summary" );
			subtotal.setOrderTotalCode("subtotal");
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
			
			LOGGER.info("Ending the initialization of test data");
		
	}
	
	private void saveFile(InputStream fis, String name, Product product) throws Exception {
		
        if(fis==null) {
        	return;
        }
		
		final byte[] is = IOUtils.toByteArray( fis );
        final ByteArrayInputStream inputStream = new ByteArrayInputStream( is );
        final ImageContentFile cmsContentImage = new ImageContentFile();
        cmsContentImage.setFileName( name );
        cmsContentImage.setFile( inputStream );
        cmsContentImage.setFileContentType(FileContentType.PRODUCT);
        

        ProductImage productImage = new ProductImage();
        productImage.setProductImage(name);
        productImage.setProduct(product);

        
        productImageService.addProductImage(product, productImage, cmsContentImage);
		
		
	}


	

}
