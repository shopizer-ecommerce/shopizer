package com.salesmanager.test.catalog;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.category.CategoryDescription;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionSet;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionType;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;


public class ProductNextGenTest extends com.salesmanager.test.common.AbstractSalesManagerCoreTestCase {
	
	private static final Date date = new Date(System.currentTimeMillis());
	
	private ProductOptionValue nine = null;
	private ProductOptionValue nineHalf = null;
	private ProductOptionValue ten = null;
	private ProductOption size = null;
	
	private ProductOptionSet possibleSizes;
	
	

	/**
	 * This method creates single product with variants using multiple catalog APIs
	 * @throws ServiceException
	 */
	@Test
	public void testCreateProduct() throws Exception {

	    Language en = languageService.getByCode("en");

	    MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
	    ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);

	    /**
	     * Category
	     */
	    Category shoes = new Category();
	    shoes.setMerchantStore(store);
	    shoes.setCode("shoes");

	    CategoryDescription shoesDescription = new CategoryDescription();
	    shoesDescription.setName("Shoes");
	    shoesDescription.setCategory(shoes);
	    shoesDescription.setLanguage(en);

	    Set<CategoryDescription> descriptions = new HashSet<CategoryDescription>();
	    descriptions.add(shoesDescription);

	    shoes.setDescriptions(descriptions);

	    categoryService.create(shoes);
	    //
	    
	    /**
	     * Manufacturer / Brand
	     */

	    Manufacturer brown = new Manufacturer();
	    brown.setMerchantStore(store);
	    brown.setCode("brown");

	    ManufacturerDescription brownd = new ManufacturerDescription();
	    brownd.setLanguage(en);
	    brownd.setName("Brown's");
	    brownd.setManufacturer(brown);
	    brown.getDescriptions().add(brownd);

	    manufacturerService.create(brown);
	    //

	    
	    // PRODUCT
	    
	    // -- non variable informations

	    Product summerShoes = new Product();
	    summerShoes.setProductHeight(new BigDecimal(3));
	    summerShoes.setProductLength(new BigDecimal(9));//average
	    summerShoes.setProductWidth(new BigDecimal(4));
	    summerShoes.setSku("BR12345");
	    summerShoes.setManufacturer(brown);
	    summerShoes.setType(generalType);
	    summerShoes.setMerchantStore(store);
	    
	    //is available
	    summerShoes.setAvailable(true);

	    // Product description
	    ProductDescription description = new ProductDescription();
	    description.setName("Summer shoes");
	    description.setLanguage(en);
	    description.setProduct(summerShoes);

	    summerShoes.getDescriptions().add(description);

	    //add product to shoes category
	    summerShoes.getCategories().add(shoes);
	    
	    // -- end non variable informations
	    
	    // --- add attributes to the product (size)
	    createOptions(summerShoes);
	    
	    // --- options set
	    /**
	     * Option Set facilitates product attributes creation for redundant product creation
	     * it offers a list of possible options and options values administrator can create in order
	     * to easily create attributes 
	     */
	    createOptionsSet(store);
	    
	    // --- create attributes (available options)
	    /**
	     * Add options to product
	     * Those are attributes
	     */
        
        ProductAttribute size_nine = new ProductAttribute();
        size_nine.setProduct(summerShoes);
        size_nine.setProductOption(size);
        size_nine.setAttributeDefault(true);
        size_nine.setProductAttributePrice(new BigDecimal(0));//no price variation
        size_nine.setProductAttributeWeight(new BigDecimal(0));//no weight variation
        size_nine.setProductOptionValue(nine);
        
        summerShoes.getAttributes().add(size_nine);
        

	    ProductAttribute size_nine_half = new ProductAttribute();
	    size_nine_half.setProduct(summerShoes);
	    size_nine_half.setProductOption(size);
	    size_nine_half.setProductAttributePrice(new BigDecimal(0));//no price variation
	    size_nine_half.setProductAttributeWeight(new BigDecimal(0));//weight variation
	    size_nine_half.setProductOptionValue(nineHalf);
	    
	    summerShoes.getAttributes().add(size_nine_half);


	    ProductAttribute size_ten = new ProductAttribute();
	    size_ten.setProduct(summerShoes);
	    size_ten.setProductOption(size);
	    size_ten.setProductAttributePrice(new BigDecimal(0));//no price variation
	    size_ten.setProductAttributeWeight(new BigDecimal(0));//weight variation
	    size_ten.setProductOptionValue(ten);
	    
	    summerShoes.getAttributes().add(size_ten);	    
	    
	    
	    // ---- variable informations - inventory - variants - prices
	    ProductAvailability availability = createInventory(store, 100, new BigDecimal("99.99"));
	    summerShoes.getAvailabilities().add(availability);
	    // ---- add available sizes
	    
	    //DEFAULT (total quantity of 100 distributed)
	    
	    //TODO use pre 3.0 variation
	    
	    //40 of 9
/*	    ProductVariant size_nine_DEFAULT = new ProductVariant();
	    size_nine_DEFAULT.setAttribute(size_nine);
	    size_nine_DEFAULT.setProductQuantity(40);
	    size_nine_DEFAULT.setProductAvailability(availability);*/
	    
	    //availability.getVariants().add(size_nine_DEFAULT);
	    
	    //30 of 9.5
/*	    ProductVariant size_nine_half_DEFAULT = new ProductVariant();
	    size_nine_half_DEFAULT.setAttribute(size_nine_half);
	    size_nine_half_DEFAULT.setProductQuantity(30);
	    size_nine_half_DEFAULT.setProductAvailability(availability);*/
	    
	    //availability.getVariants().add(size_nine_half_DEFAULT);
	    
	    //30 of ten
/*	    ProductVariant size_ten_DEFAULT = new ProductVariant();
	    size_ten_DEFAULT.setAttribute(size_nine_half);
	    size_ten_DEFAULT.setProductQuantity(30);
	    size_ten_DEFAULT.setProductAvailability(availability);*/
	    
	    //availability.getVariants().add(size_ten_DEFAULT);
	    
	    //inventory for store DEFAULT and product summerShoes
	    availability.setProduct(summerShoes);
	    availability.setMerchantStore(store);

	    
	    /**
	     * Create product
	     */
	    productService.saveProduct(summerShoes);

	    //ObjectMapper mapper = new ObjectMapper();
	    //Converting the Object to JSONString
	    //String jsonString = mapper.writeValueAsString(summerShoes);
	    //System.out.println(jsonString);
	    
	    Product p = productService.getById(summerShoes.getId());
	    assertNotNull(p);
	    
	    //List<ProductAvailability> avs = p.getAvailabilities().stream().filter(a -> !a.getVariants().isEmpty()).collect(Collectors.toList());
	    //assertThat(avs, not(empty()));
	    
	    //test product list service
	    
	    //list products per category
	    //list 5 items
	    ProductCriteria productCriteria = new ProductCriteria();
	    productCriteria.setCategoryIds(Stream.of(shoes.getId())
	    	      .collect(Collectors.toList()));
	    
	    Page<Product> listByCategory = productService.listByStore(store, en, productCriteria, 0, 5);
	    List<Product> productsByCategory = listByCategory.getContent();
	    assertThat(productsByCategory,  not(empty()));
	    
	    //list products per color attribute
	    Page<Product> listByOptionValue = productService.listByStore(store, en, productCriteria, 0, 5);
	    productCriteria = new ProductCriteria();
	    productCriteria.setOptionValueIds(Stream.of(nineHalf.getId())
	    	      .collect(Collectors.toList()));
	    List<Product> productsByOption = listByOptionValue.getContent();
	    assertThat(productsByOption,  not(empty()));
	    
	    

	}
	
	private void createOptionsSet(MerchantStore store) throws Exception {
		
		//add a set of option / values for major sizes
		possibleSizes = new ProductOptionSet();
		possibleSizes.setCode("majorSizes");
		possibleSizes.setStore(store);
		possibleSizes.setOption(size);
		
		
		List<ProductOptionValue> values = new ArrayList<ProductOptionValue>();
		values.add(nine);
		values.add(ten);
		
		possibleSizes.setValues(values);
		

		productOptionSetService.create(possibleSizes);
		
	}
	
	/**
	 * Manage product inventory
	 * @param product
	 * @throws Exception
	 */
	private ProductAvailability createInventory(MerchantStore store, int quantity, BigDecimal price) throws Exception {
		
		//add inventory
	    ProductAvailability availability = new ProductAvailability();
	    availability.setProductDateAvailable(date);
	    availability.setProductQuantity(quantity);
	    availability.setRegion("*");
	    availability.setAvailable(true);
	    
	    Language en = languageService.getByCode("en");

	    ProductPrice dprice = new ProductPrice();
	    dprice.setDefaultPrice(true);
	    dprice.setProductPriceAmount(price);
	    dprice.setProductAvailability(availability);

	    ProductPriceDescription dpd = new ProductPriceDescription();
	    dpd.setName("Base price");
	    dpd.setProductPrice(dprice);
	    dpd.setLanguage(en);

	    dprice.getDescriptions().add(dpd);
	    availability.getPrices().add(dprice);
	    
	    return availability;
		
		
		
	}

	
	/**
	 * Add possible choices
	 * @param product
	 * @throws Exception
	 */
	private void createOptions(Product product) throws Exception {
		
		
		/**
		 * An attribute can be created dynamicaly but the attached Option and Option value need to exist
		 */
		
		MerchantStore store = product.getMerchantStore();
		Language en = languageService.getByCode("en");
		

	     /**
         * Create size option
         */
        size = new ProductOption();
        size.setMerchantStore(store);
        size.setCode("SHOESIZE");
        size.setProductOptionType(ProductOptionType.Radio.name());
        
        ProductOptionDescription sizeDescription = new ProductOptionDescription();
        sizeDescription.setLanguage(en);
        sizeDescription.setName("Size");
        sizeDescription.setDescription("Show size");
        sizeDescription.setProductOption(size);
        
        size.getDescriptions().add(sizeDescription);
        
        //create option
        productOptionService.saveOrUpdate(size);
	    
        
        /**
         * Create size values (9, 9.5, 10)
         */
	    
	    //option value 9
	    nine = new ProductOptionValue();
	    nine.setMerchantStore(store);
	    nine.setCode("nine");
	    
	    ProductOptionValueDescription nineDescription = new ProductOptionValueDescription();
	    nineDescription.setLanguage(en);
	    nineDescription.setName("9");
	    nineDescription.setDescription("Size 9");
	    nineDescription.setProductOptionValue(nine);
	    
	    nine.getDescriptions().add(nineDescription);
	    
	    //create an option value
	    productOptionValueService.saveOrUpdate(nine);
	    
	    
	    //option value 9.5
	    nineHalf = new ProductOptionValue();
	    nineHalf.setMerchantStore(store);
	    nineHalf.setCode("nineHalf");
	    
	    ProductOptionValueDescription nineHalfDescription = new ProductOptionValueDescription();
	    nineHalfDescription.setLanguage(en);
	    nineHalfDescription.setName("9.5");
	    nineHalfDescription.setDescription("Size 9.5");
	    nineHalfDescription.setProductOptionValue(nineHalf);
	    
	    nineHalf.getDescriptions().add(nineHalfDescription);
	    
	    //create an option value
	    productOptionValueService.saveOrUpdate(nineHalf);
	    
	    
	    //option value 10
	    ten = new ProductOptionValue();
	    ten.setMerchantStore(store);
	    ten.setCode("ten");
	    
	    ProductOptionValueDescription tenDescription = new ProductOptionValueDescription();
	    tenDescription.setLanguage(en);
	    tenDescription.setName("10");
	    tenDescription.setDescription("Size 10");
	    tenDescription.setProductOptionValue(ten);
	    
	    ten.getDescriptions().add(tenDescription);
	    
	    //create an option value
	    productOptionValueService.saveOrUpdate(ten);
	    
	    
	    // end options / options values


	}
	


	
	
	
	




}