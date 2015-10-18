package com.salesmanager.test.catalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.model.CategoryDescription;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.ProductCriteria;
import com.salesmanager.core.business.catalog.product.model.ProductList;
import com.salesmanager.core.business.catalog.product.model.attribute.AttributeCriteria;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductOption;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionDescription;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionType;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionValue;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionValueDescription;
import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import com.salesmanager.core.business.catalog.product.model.price.ProductPriceDescription;
import com.salesmanager.core.business.catalog.product.model.type.ProductType;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;

public class SearchByProductAttributeTestCase extends AbstractSalesManagerCoreTestCase {
	
	private static final Date date = new Date(System.currentTimeMillis());
	

	@Test
	public void testFetchProductByAttribute() throws Exception {

	    Language en = languageService.getByCode("en");


	    MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
	    ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);

	    
	    /** Categories **/
	    
	    Category book = new Category();
	    book.setMerchantStore(store);
	    book.setCode("book");

	    CategoryDescription bookEnglishDescription = new CategoryDescription();
	    bookEnglishDescription.setName("Book");
	    bookEnglishDescription.setCategory(book);
	    bookEnglishDescription.setLanguage(en);

	    List<CategoryDescription> descriptions = new ArrayList<CategoryDescription>();
	    descriptions.add(bookEnglishDescription);

	    book.setDescriptions(descriptions);

	    categoryService.create(book);


	    Category novell = new Category();
	    novell.setMerchantStore(store);
	    novell.setCode("novell");

	    CategoryDescription novellEnglishDescription = new CategoryDescription();
	    novellEnglishDescription.setName("Novell");
	    novellEnglishDescription.setCategory(novell);
	    novellEnglishDescription.setLanguage(en);

	    List<CategoryDescription> descriptions3 = new ArrayList<CategoryDescription>();
	    descriptions3.add(novellEnglishDescription);

	    novell.setDescriptions(descriptions3);
	    
	    novell.setParent(book);

	    categoryService.create(novell);
	    categoryService.addChild(book, novell);

	    Category tech = new Category();
	    tech.setMerchantStore(store);
	    tech.setCode("tech");

	    CategoryDescription techEnglishDescription = new CategoryDescription();
	    techEnglishDescription.setName("Technology");
	    techEnglishDescription.setCategory(tech);
	    techEnglishDescription.setLanguage(en);

	    List<CategoryDescription> descriptions4 = new ArrayList<CategoryDescription>();
	    descriptions4.add(techEnglishDescription);

	    tech.setDescriptions(descriptions4);
	    
	    tech.setParent(book);

	    categoryService.create(tech);
	    categoryService.addChild(book, tech);

	    /** Manufacturers **/

	    Manufacturer novells = new Manufacturer();
	    novells.setMerchantStore(store);
	    novells.setCode("novells");

	    ManufacturerDescription novellsd = new ManufacturerDescription();
	    novellsd.setLanguage(en);
	    novellsd.setManufacturer(novells);
	    novellsd.setName("Novells publishing");
	    novells.getDescriptions().add(novellsd);

	    manufacturerService.create(novells);
	    
	    
	    Manufacturer manning = new Manufacturer();
	    manning.setMerchantStore(store);
	    manning.setCode("manning");

	    ManufacturerDescription manningd = new ManufacturerDescription();
	    manningd.setLanguage(en);
	    manningd.setManufacturer(manning);
	    manningd.setName("Manning publishing");
	    manning.getDescriptions().add(manningd);

	    manufacturerService.create(manning);
	    
	    //Author attribute
	    ProductOption author = new ProductOption();
	    author.setCode("author");
	    author.setMerchantStore(store);
	    author.setReadOnly(true);
	    author.setProductOptionType(ProductOptionType.Text.name());
	    
	    
	    ProductOptionDescription authorEnglishDescription = new ProductOptionDescription();
	    authorEnglishDescription.setLanguage(en);
	    authorEnglishDescription.setName("Author");
	    authorEnglishDescription.setProductOption(author);
	    
	    author.getDescriptions().add(authorEnglishDescription);
	    
	    productOptionService.create(author);
	    
	    //Author name - Jimmy Jones
	    ProductOptionValue jimmyjones = new ProductOptionValue();
	    jimmyjones.setMerchantStore(store);
	    jimmyjones.setCode("jimmyjones");
	    jimmyjones.setProductOptionDisplayOnly(true);
	    
	    ProductOptionValueDescription jimmyjonesd = new ProductOptionValueDescription();
	    jimmyjonesd.setLanguage(en);
	    jimmyjonesd.setName("Jimmy Jones");//mandatory
	    jimmyjonesd.setDescription("Jimmy Jones");//query is based on description
	    jimmyjonesd.setProductOptionValue(jimmyjones);
	    jimmyjones.getDescriptions().add(jimmyjonesd);
	    
	    productOptionValueService.create(jimmyjones);
	    
	    
	    //Author name - Lucy Scott
	    ProductOptionValue lucyscott = new ProductOptionValue();
	    lucyscott.setMerchantStore(store);
	    lucyscott.setCode("lucyscott");
	    lucyscott.setProductOptionDisplayOnly(true);
	    
	    ProductOptionValueDescription lucyscottd = new ProductOptionValueDescription();
	    lucyscottd.setLanguage(en);
	    lucyscottd.setName("Lucy Scott");//mandatory
	    lucyscottd.setDescription("Lucy Scott");//query is based on description
	    lucyscottd.setProductOptionValue(lucyscott);
	    lucyscott.getDescriptions().add(lucyscottd);
	    
	    productOptionValueService.create(lucyscott);
	    
	    //Author name - Carlos Santana Scott
	    ProductOptionValue carlossantana = new ProductOptionValue();
	    carlossantana.setMerchantStore(store);
	    carlossantana.setCode("carlossantana");
	    carlossantana.setProductOptionDisplayOnly(true);
	    
	    ProductOptionValueDescription carlossantanad = new ProductOptionValueDescription();
	    carlossantanad.setLanguage(en);
	    carlossantanad.setName("Carlos Santana");
	    carlossantanad.setDescription("Carlos Santana");
	    carlossantanad.setProductOptionValue(carlossantana);
	    carlossantana.getDescriptions().add(carlossantanad);
	    
	    productOptionValueService.create(carlossantana);
	    
	    
	    // PRODUCT 1 - technical book
	    Product product = new Product();
	    product.setProductHeight(new BigDecimal(4));
	    product.setProductLength(new BigDecimal(3));
	    product.setProductWidth(new BigDecimal(1));
	    product.setSku("TB12345");
	    product.setManufacturer(manning);
	    product.setType(generalType);
	    product.setMerchantStore(store);

	    // Product description
	    ProductDescription description = new ProductDescription();
	    description.setName("Spring in Action");
	    description.setLanguage(en);
	    description.setProduct(product);

	    product.getDescriptions().add(description);
	    product.getCategories().add(tech);

	    productService.create(product);

	    // Availability
	    ProductAvailability availability = new ProductAvailability();
	    availability.setProductDateAvailable(date);
	    availability.setProductQuantity(100);
	    availability.setRegion("*");
	    availability.setProduct(product);// associate with product

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

	    productPriceService.create(dprice);
	    

	    //set author attribute
	    ProductAttribute product1Author = new ProductAttribute();
	    product1Author.setAttributeDisplayOnly(true);
	    product1Author.setProduct(product);
	    product1Author.setProductOption(author);
	    product1Author.setProductOptionValue(jimmyjones);
	    
	    productAttributeService.create(product1Author);
	    
	    

	    // PRODUCT 2 - technical book

	    Product product2 = new Product();
	    product2.setProductHeight(new BigDecimal(4));
	    product2.setProductLength(new BigDecimal(3));
	    product2.setProductWidth(new BigDecimal(1));
	    product2.setSku("TB2468");
	    product2.setManufacturer(manning);
	    product2.setType(generalType);
	    product2.setMerchantStore(store);

	    // Product description
	    description = new ProductDescription();
	    description.setName("This is Node.js");
	    description.setLanguage(en);
	    description.setProduct(product2);

	    product2.getDescriptions().add(description);

	    product2.getCategories().add(tech);
	    productService.create(product2);

	    // Availability
	    ProductAvailability availability2 = new ProductAvailability();
	    availability2.setProductDateAvailable(date);
	    availability2.setProductQuantity(100);
	    availability2.setRegion("*");
	    availability2.setProduct(product2);// associate with product

	    productAvailabilityService.create(availability2);

	    ProductPrice dprice2 = new ProductPrice();
	    dprice2.setDefaultPrice(true);
	    dprice2.setProductPriceAmount(new BigDecimal(39.99));
	    dprice2.setProductAvailability(availability2);

	    dpd = new ProductPriceDescription();
	    dpd.setName("Base price");
	    dpd.setProductPrice(dprice2);
	    dpd.setLanguage(en);

	    dprice2.getDescriptions().add(dpd);

	    productPriceService.create(dprice2);
	    
	    //set author attribute
	    ProductAttribute product2Author = new ProductAttribute();
	    product2Author.setAttributeDisplayOnly(true);
	    product2Author.setProduct(product2);
	    product2Author.setProductOption(author);
	    product2Author.setProductOptionValue(jimmyjones);
	    
	    productAttributeService.create(product2Author);

	    // PRODUCT 3 - Novell

	    Product product3 = new Product();
	    product3.setProductHeight(new BigDecimal(4));
	    product3.setProductLength(new BigDecimal(3));
	    product3.setProductWidth(new BigDecimal(1));
	    product3.setSku("NB1111");
	    product3.setManufacturer(novells);
	    product3.setType(generalType);
	    product3.setMerchantStore(store);

	    // Product description
	    description = new ProductDescription();
	    description.setName("A nice book for you");
	    description.setLanguage(en);
	    description.setProduct(product3);

	    product3.getDescriptions().add(description);

	    product3.getCategories().add(novell);
	    productService.create(product3);

	    // Availability
	    ProductAvailability availability3 = new ProductAvailability();
	    availability3.setProductDateAvailable(date);
	    availability3.setProductQuantity(100);
	    availability3.setRegion("*");
	    availability3.setProduct(product3);// associate with product

	    productAvailabilityService.create(availability3);

	    ProductPrice dprice3 = new ProductPrice();
	    dprice3.setDefaultPrice(true);
	    dprice3.setProductPriceAmount(new BigDecimal(19.99));
	    dprice3.setProductAvailability(availability3);

	    dpd = new ProductPriceDescription();
	    dpd.setName("Base price");
	    dpd.setProductPrice(dprice3);
	    dpd.setLanguage(en);

	    dprice3.getDescriptions().add(dpd);

	    productPriceService.create(dprice3);
	    
	    //set author attribute
	    ProductAttribute product3Author = new ProductAttribute();
	    product3Author.setAttributeDisplayOnly(true);
	    product3Author.setProduct(product3);
	    product3Author.setProductOption(author);
	    product3Author.setProductOptionValue(lucyscott);
	    
	    productAttributeService.create(product3Author);
	    
	    
	    // PRODUCT 4 - Novell

	    Product product4 = new Product();
	    product4.setProductHeight(new BigDecimal(4));
	    product4.setProductLength(new BigDecimal(3));
	    product4.setProductWidth(new BigDecimal(1));
	    product4.setSku("NB1111678");
	    product4.setManufacturer(novells);
	    product4.setType(generalType);
	    product4.setMerchantStore(store);

	    // Product description
	    description = new ProductDescription();
	    description.setName("Look at the sky");
	    description.setLanguage(en);
	    description.setProduct(product4);

	    product4.getDescriptions().add(description);

	    product4.getCategories().add(novell);
	    productService.create(product4);

	    // Availability
	    ProductAvailability availability4 = new ProductAvailability();
	    availability4.setProductDateAvailable(date);
	    availability4.setProductQuantity(100);
	    availability4.setRegion("*");
	    availability4.setProduct(product4);// associate with product

	    productAvailabilityService.create(availability4);

	    ProductPrice dprice4 = new ProductPrice();
	    dprice4.setDefaultPrice(true);
	    dprice4.setProductPriceAmount(new BigDecimal(17.99));
	    dprice4.setProductAvailability(availability4);

	    dpd = new ProductPriceDescription();
	    dpd.setName("Base price");
	    dpd.setProductPrice(dprice4);
	    dpd.setLanguage(en);

	    dprice4.getDescriptions().add(dpd);

	    productPriceService.create(dprice4);
	    
	    //set author attribute
	    ProductAttribute product4Author = new ProductAttribute();
	    product4Author.setAttributeDisplayOnly(true);
	    product4Author.setProduct(product4);
	    product4Author.setProductOption(author);
	    product4Author.setProductOptionValue(carlossantana);
	    
	    productAttributeService.create(product4Author);
	    
	    
	    
	    //get product by author Jimmy Jones
	    ProductCriteria fetchCriteria = new ProductCriteria();
	    //fetchCriteria.setCode("TB1234");
	    
	    List<AttributeCriteria> attributesCriteriaList = new ArrayList<AttributeCriteria>();
	    
	    AttributeCriteria fetchAttributeCriteria = new AttributeCriteria();
	    fetchAttributeCriteria.setAttributeCode("author");
	    fetchAttributeCriteria.setAttributeValue("Jimmy Jones");

	    attributesCriteriaList.add(fetchAttributeCriteria);
 
	    
	    fetchCriteria.setAttributeCriteria(attributesCriteriaList);
	    
	    ProductList productList = productService.listByStore(store, en, fetchCriteria);
	    
	    Assert.assertNotNull(productList.getProducts());
	    
	    System.out.println(productList.getProducts().size());//should be 2
	    
	    
		
	}

}