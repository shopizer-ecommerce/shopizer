package com.salesmanager.test.isolated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.model.CategoryDescription;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import com.salesmanager.core.business.catalog.product.model.price.ProductPriceDescription;
import com.salesmanager.core.business.catalog.product.model.type.ProductType;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.catalog.product.service.availability.ProductAvailabilityService;
import com.salesmanager.core.business.catalog.product.service.manufacturer.ManufacturerService;
import com.salesmanager.core.business.catalog.product.service.price.ProductPriceService;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.merchant.service.MerchantStoreService;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.country.model.CountryDescription;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.currency.model.Currency;
import com.salesmanager.core.business.reference.currency.service.CurrencyService;
import com.salesmanager.core.business.reference.language.model.Language;

@Component
public class TestSupportFactory {

  @Autowired
  protected ProductService             productService;

  @Autowired
  protected ProductAvailabilityService productAvailabilityService;

  @Autowired
  protected ProductPriceService        productPriceService;

  @Autowired
  protected ManufacturerService        manufacturerService;

  @Autowired
  protected MerchantStoreService       merchantStoreService;

  @Autowired
  protected CountryService             countryService;

  @Autowired
  protected CurrencyService            currencyService;

  public Product createAndStoreRandomProduct(Manufacturer manufacturer, ProductType type, Category category, MerchantStore store,
      Language language) throws ServiceException {
    Random rnd = new Random();

    String[] descriptions = { "Live Book", "Nature Book", "Science Book", "Mathematics Book", "Physics Book" };

    Product product = new Product();
    product.setProductHeight(new BigDecimal(rnd.nextInt(100000)));
    product.setProductLength(new BigDecimal(rnd.nextInt(100000)));
    product.setProductWidth(new BigDecimal(rnd.nextInt(100000)));
    product.setSku("LL" + rnd.nextLong());
    product.setManufacturer(manufacturer);
    product.setType(type);
    product.setMerchantStore(store);

    // Product description
    ProductDescription description = new ProductDescription();
    description.setName(getRandomString(descriptions));
    description.setLanguage(language);
    description.setProduct(product);

    product.getDescriptions().add(description);

    product.getCategories().add(category);
    productService.create(product);

    // Availability
    ProductAvailability availability = new ProductAvailability();
    availability.setProductDateAvailable(new Date());
    availability.setProductQuantity(rnd.nextInt(200));
    availability.setRegion("*");
    availability.setProduct(product);// associate with product

    productAvailabilityService.create(availability);

    ProductPrice price = new ProductPrice();
    price.setDefaultPrice(true);
    price.setProductPriceAmount(new BigDecimal(18.99));
    price.setProductAvailability(availability);

    ProductPriceDescription dpd = new ProductPriceDescription();
    dpd.setName("Base price");
    dpd.setProductPrice(price);
    dpd.setLanguage(language);

    price.getDescriptions().add(dpd);

    productPriceService.create(price);

    return product;
  }

  public Category createCategory(Category parent, MerchantStore store, Language[] languages) {

    String[] categoryDescriptions = { "Novell", "Roman", "Thriller", "Comedy", "Fantasy", "Horror", "Comics" };

    Category cat = new Category();
    if (parent == null) {
      cat.setDepth(0);
      cat.setLineage("/");
    } else {
      cat.setDepth(parent.getDepth());
      cat.setLineage("/" + parent.getId() + "/");
    }

    cat.setMerchantStore(store);

    List<CategoryDescription> descriptions = new ArrayList<CategoryDescription>();

    String name = getRandomString(categoryDescriptions);
    cat.setCode(name);
    
    for (Language language : languages) {
     
      CategoryDescription desc = new CategoryDescription();
      desc.setName(name);
      desc.setCategory(cat);
      desc.setLanguage(language);
      descriptions.add(desc);

    }
    cat.setDescriptions(descriptions);
    return cat;
  }

  public Country createCountry(Language language) throws ServiceException {
    Country ca = new Country();
    ca.setIsoCode("CA");

    CountryDescription countryDescription = new CountryDescription();
    countryDescription.setCountry(ca);
    countryDescription.setLanguage(language);
    countryDescription.setName("Canada");
    countryDescription.setDescription("Canada Country");

    List<CountryDescription> descriptions = new ArrayList<CountryDescription>();
    descriptions.add(countryDescription);
    ca.setDescriptions(descriptions);

    return ca;
  }

  public Currency createCurrency() throws ServiceException {
    Currency currency = new Currency();
    currency.setCurrency(java.util.Currency.getInstance(Locale.CANADA));
    currency.setSupported(true);
    return currency;
  }

  public Language createLanguage(String code) {
    Language language = new Language();
    language.setCode(code);
    return language;
  }

  public MerchantStore createMerchantStore(String storeName, Country ca, Currency currency, Language language)
      throws ServiceException {
    MerchantStore store = new MerchantStore();
    store.setCountry(ca);
    store.setCurrency(currency);
    store.setDefaultLanguage(language);
    store.setInBusinessSince(new Date());
    store.setStorename(storeName);
    store.setCode(storeName);
    store.setStoreEmailAddress("test@test.com");
    return store;
  }

  public ProductType createProductType() {
    ProductType generalType = new ProductType();
    generalType.setCode(ProductType.GENERAL_TYPE);
    return generalType;
  }

  public Manufacturer createRandomManufacturer(MerchantStore store, Language language) throws ServiceException {
    String[] manufacturers = { "O\'reilley", "Hueber", "Langenscheidt", "Readers Digest", "Klett Verlag" };

    Manufacturer manufacturer = new Manufacturer();
    manufacturer.setMerchantStore(store);

    ManufacturerDescription oreilleyd = new ManufacturerDescription();
    oreilleyd.setLanguage(language);
    oreilleyd.setName(getRandomString(manufacturers));
    oreilleyd.setManufacturer(manufacturer);
    manufacturer.getDescriptions().add(oreilleyd);

    return manufacturer;
  }

  private String getRandomString(String[] strings) {
    return strings[new Random().nextInt(strings.length)];
  }
}
