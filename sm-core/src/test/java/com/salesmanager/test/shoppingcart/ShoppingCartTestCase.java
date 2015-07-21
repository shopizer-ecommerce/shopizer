package com.salesmanager.test.shoppingcart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.model.CategoryDescription;
import com.salesmanager.core.business.catalog.product.model.Product;
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
import com.salesmanager.core.business.catalog.product.model.price.FinalPrice;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import com.salesmanager.core.business.catalog.product.model.price.ProductPriceDescription;
import com.salesmanager.core.business.catalog.product.model.type.ProductType;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCart;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCartAttributeItem;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCartItem;
import com.salesmanager.core.utils.ProductPriceUtils;
import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;

/**
 * Test 
 * 
 * - Add a product to persistent shopping cart
 * - Retrieve an item from the persistent shopping cart
 * - Rebuild a shopping cart item after the product definition has been modified
 * @author Carl Samson
 *
 */
public class ShoppingCartTestCase extends AbstractSalesManagerCoreTestCase {
	
	@Autowired
	private ProductPriceUtils productPriceUtil;
	
    @Test
    public void createShoppingCart()
        throws ServiceException
    {

        MerchantStore store = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
        
		
	    Language en = languageService.getByCode("en");


	    /** CATALOG CREATION **/
	    
	    ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);

	    /**
	     * Create the category
	     */
	    Category shirts = new Category();
	    shirts.setMerchantStore(store);
	    shirts.setCode("shirts");

	    CategoryDescription shirtsEnglishDescription = new CategoryDescription();
	    shirtsEnglishDescription.setName("Shirts");
	    shirtsEnglishDescription.setCategory(shirts);
	    shirtsEnglishDescription.setLanguage(en);

	    List<CategoryDescription> descriptions = new ArrayList<CategoryDescription>();
	    descriptions.add(shirtsEnglishDescription);


	    shirts.setDescriptions(descriptions);
	    categoryService.create(shirts);
	    
	    
	    /**
	     * Create a manufacturer
	     */
	    Manufacturer addidas = new Manufacturer();
	    addidas.setMerchantStore(store);
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
	    option.setMerchantStore(store);
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
	    white.setMerchantStore(store);
	    white.setCode("white");
	    
	    ProductOptionValueDescription whiteDescription = new ProductOptionValueDescription();
	    whiteDescription.setLanguage(en);
	    whiteDescription.setName("White");
	    whiteDescription.setDescription("White color");
	    whiteDescription.setProductOptionValue(white);
	    
	    white.getDescriptions().add(whiteDescription);
	    
	    productOptionValueService.saveOrUpdate(white);
	    
	    
	    ProductOptionValue black = new ProductOptionValue();
	    black.setMerchantStore(store);
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
	    product.setMerchantStore(store);

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

	    productService.saveOrUpdate(product);
	    
	    /** Create Shopping cart **/
	    
	    ShoppingCart shoppingCart = new ShoppingCart();
	    shoppingCart.setMerchantStore(store);
	    
	    UUID cartCode = UUID.randomUUID();
	    shoppingCart.setShoppingCartCode(cartCode.toString());

	    ShoppingCartItem item = new ShoppingCartItem(shoppingCart,product);
	    item.setShoppingCart(shoppingCart);
	    FinalPrice price = productPriceUtil.getFinalPrice(product);
	    
	    item.setItemPrice(price.getFinalPrice());
	    item.setQuantity(1);
	    
	    /** user selects black **/
	    ShoppingCartAttributeItem attributeItem = new ShoppingCartAttributeItem(item,blackAttribute);
	    item.getAttributes().add(attributeItem);
	    
	    shoppingCart.getLineItems().add(item);
	    
	    
	    shoppingCartService.create(shoppingCart);
	    
	    /** Retrieve cart **/
	    
	    ShoppingCart retrievedCart = shoppingCartService.getByCode(cartCode.toString(), store);
	    
	    Assert.assertNotNull(retrievedCart);
	    
	    
	    
	    
    }
	
	@Test
	public void retrieveAlteredShoppingCart() throws Exception {

        MerchantStore store = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
        
		
	    Language en = languageService.getByCode("en");


	    /** CATALOG CREATION **/
	    
	    ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);

	    /**
	     * Create the category
	     */
	    Category shirts = new Category();
	    shirts.setMerchantStore(store);
	    shirts.setCode("shirts");

	    CategoryDescription shirtsEnglishDescription = new CategoryDescription();
	    shirtsEnglishDescription.setName("Shirts");
	    shirtsEnglishDescription.setCategory(shirts);
	    shirtsEnglishDescription.setLanguage(en);

	    List<CategoryDescription> descriptions = new ArrayList<CategoryDescription>();
	    descriptions.add(shirtsEnglishDescription);


	    shirts.setDescriptions(descriptions);
	    categoryService.create(shirts);
	    
	    
	    /**
	     * Create a manufacturer
	     */
	    Manufacturer addidas = new Manufacturer();
	    addidas.setMerchantStore(store);
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
	    option.setMerchantStore(store);
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
	    white.setMerchantStore(store);
	    white.setCode("white");
	    
	    ProductOptionValueDescription whiteDescription = new ProductOptionValueDescription();
	    whiteDescription.setLanguage(en);
	    whiteDescription.setName("White");
	    whiteDescription.setDescription("White color");
	    whiteDescription.setProductOptionValue(white);
	    
	    white.getDescriptions().add(whiteDescription);
	    
	    productOptionValueService.saveOrUpdate(white);
	    
	    
	    ProductOptionValue black = new ProductOptionValue();
	    black.setMerchantStore(store);
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
	    product.setMerchantStore(store);

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

	    productService.saveOrUpdate(product);
	    
	    /** Create Shopping cart **/
	    
	    ShoppingCart shoppingCart = new ShoppingCart();
	    shoppingCart.setMerchantStore(store);
	    
	    UUID cartCode = UUID.randomUUID();
	    shoppingCart.setShoppingCartCode(cartCode.toString());

	    ShoppingCartItem item = new ShoppingCartItem(shoppingCart,product);
	    item.setShoppingCart(shoppingCart);
	    FinalPrice price = productPriceUtil.getFinalPrice(product);
	    
	    item.setItemPrice(price.getFinalPrice());
	    item.setQuantity(1);
	    
	    /** user selects black **/
	    ShoppingCartAttributeItem attributeItem = new ShoppingCartAttributeItem(item,blackAttribute);
	    item.getAttributes().add(attributeItem);
	    
	    shoppingCart.getLineItems().add(item);
	    
	    
	    shoppingCartService.create(shoppingCart);
	    
	    
	    /** Modify product definition **/
	    
	    Product retrievedProduct = productService.getById(product.getId());
	    
	    Set<ProductAttribute> attributes = retrievedProduct.getAttributes();
	    
	    Assert.assertNotNull(attributes);
	    
	    for(ProductAttribute attr : attributes) {
	    	productAttributeService.delete(attr);
	    }
	    
	    
	    
	    /** Retrieve cart **/
	    
	    ShoppingCart retrievedCart = shoppingCartService.getByCode(cartCode.toString(), store);
	    
	    Assert.assertNotNull(retrievedCart);
	    
  

	}
	

}