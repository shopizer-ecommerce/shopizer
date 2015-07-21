package com.salesmanager.test.core;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.catalog.product.service.attribute.ProductAttributeService;
import com.salesmanager.core.business.catalog.product.service.attribute.ProductOptionService;
import com.salesmanager.core.business.catalog.product.service.attribute.ProductOptionValueService;
import com.salesmanager.core.business.catalog.product.service.availability.ProductAvailabilityService;
import com.salesmanager.core.business.catalog.product.service.image.ProductImageService;
import com.salesmanager.core.business.catalog.product.service.manufacturer.ManufacturerService;
import com.salesmanager.core.business.catalog.product.service.price.ProductPriceService;
import com.salesmanager.core.business.catalog.product.service.review.ProductReviewService;
import com.salesmanager.core.business.catalog.product.service.type.ProductTypeService;
import com.salesmanager.core.business.customer.service.CustomerService;
import com.salesmanager.core.business.customer.service.attribute.CustomerOptionService;
import com.salesmanager.core.business.customer.service.attribute.CustomerOptionSetService;
import com.salesmanager.core.business.customer.service.attribute.CustomerOptionValueService;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.util.EntityManagerUtils;
import com.salesmanager.core.business.merchant.service.MerchantStoreService;
import com.salesmanager.core.business.order.service.OrderService;
import com.salesmanager.core.business.payments.service.PaymentService;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.currency.service.CurrencyService;
import com.salesmanager.core.business.reference.init.service.InitializationDatabase;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.reference.zone.service.ZoneService;
import com.salesmanager.core.business.shoppingcart.service.ShoppingCartService;
import com.salesmanager.core.business.system.service.EmailService;

@ContextConfiguration(locations = {
		"classpath:spring/test-spring-context.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	SalesManagerCoreTestExecutionListener.class
})
public abstract class AbstractSalesManagerCoreTestCase {
	
	
	@Autowired
	protected InitializationDatabase                initializationDatabase;
	  
	
	protected static final String ENGLISH_LANGUAGE_CODE = "en";
	
	protected static final String FRENCH_LANGUAGE_CODE = "fr";
	
	protected static final String EURO_CURRENCY_CODE = "EUR";
	
	protected static final String FR_COUNTRY_CODE = "FR";
	
	protected static final String CAD_CURRENCY_CODE = "CAD";
	
	protected static final String CA_COUNTRY_CODE = "CA";
	
	protected static final String VT_ZONE_CODE = "VT";
	
	@Autowired
	private EntityManagerUtils entityManagerUtils;
	
	@Autowired
	protected ProductService productService;

	
	@Autowired
	protected ProductPriceService productPriceService;
	
	@Autowired
	protected ProductAttributeService productAttributeService;
	
	@Autowired
	protected ProductOptionService productOptionService;
	
	@Autowired
	protected ProductOptionValueService productOptionValueService;
	
	@Autowired
	protected ProductAvailabilityService productAvailabilityService;
	
	@Autowired
	protected ProductReviewService productReviewService;
	
	@Autowired
	protected ProductImageService productImageService;
	
	@Autowired
	protected CategoryService categoryService;
	
	@Autowired
	protected MerchantStoreService merchantService;
	
	@Autowired
	protected ProductTypeService productTypeService;
	
	@Autowired
	protected LanguageService languageService;
	
	@Autowired
	protected CountryService countryService;
	
	@Autowired
	protected CurrencyService currencyService;
	
	@Autowired
	protected ManufacturerService manufacturerService;
	
	@Autowired
	protected ZoneService zoneService;
	
	@Autowired
	protected CustomerService customerService;
	
	@Autowired
	protected CustomerOptionService customerOptionService;
	
	@Autowired
	protected CustomerOptionValueService customerOptionValueService;
	
	@Autowired
	protected CustomerOptionSetService customerOptionSetService;
	
	@Autowired
	protected OrderService orderService;
	
	@Autowired
	protected PaymentService paymentService;
	
	@Autowired
	protected ShoppingCartService shoppingCartService;
	
	@Autowired
	protected EmailService emailService;


	@Before
	public void init() throws ServiceException {
		
		populate();

	}
	
	@After
	public void close() throws ServiceException {

	}
	

	
	private void populate() throws ServiceException {
		
		
		initializationDatabase.populate("TEST");
		

	}
}
