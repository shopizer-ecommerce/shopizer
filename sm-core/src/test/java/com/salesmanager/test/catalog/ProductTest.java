package com.salesmanager.test.catalog;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
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
import com.salesmanager.core.model.catalog.product.file.ProductImageSize;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.catalog.product.review.ProductReviewDescription;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.content.OutputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;


public class ProductTest extends com.salesmanager.test.common.AbstractSalesManagerCoreTestCase {
	
	private static final Date date = new Date(System.currentTimeMillis());
	
	private final String IMAGE_NAME = "icon.png";

	/**
	 * This method creates multiple products using multiple catalog APIs
	 * @throws ServiceException
	 */
	@Test
	public void testCreateProduct() throws Exception {

	    Language en = languageService.getByCode("en");
	    Language fr = languageService.getByCode("fr");

	    MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
	    ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);

	    Category book = new Category();
	    book.setMerchantStore(store);
	    book.setCode("book");

	    CategoryDescription bookEnglishDescription = new CategoryDescription();
	    bookEnglishDescription.setName("Book");
	    bookEnglishDescription.setCategory(book);
	    bookEnglishDescription.setLanguage(en);

	    CategoryDescription bookFrenchDescription = new CategoryDescription();
	    bookFrenchDescription.setName("Livre");
	    bookFrenchDescription.setCategory(book);
	    bookFrenchDescription.setLanguage(fr);

	    Set<CategoryDescription> descriptions = new HashSet<CategoryDescription>();
	    descriptions.add(bookEnglishDescription);
	    descriptions.add(bookFrenchDescription);

	    book.setDescriptions(descriptions);

	    categoryService.create(book);

	    Category music = new Category();
	    music.setMerchantStore(store);
	    music.setCode("music");

	    CategoryDescription musicEnglishDescription = new CategoryDescription();
	    musicEnglishDescription.setName("Music");
	    musicEnglishDescription.setCategory(music);
	    musicEnglishDescription.setLanguage(en);

	    CategoryDescription musicFrenchDescription = new CategoryDescription();
	    musicFrenchDescription.setName("Musique");
	    musicFrenchDescription.setCategory(music);
	    musicFrenchDescription.setLanguage(fr);

	    Set<CategoryDescription> descriptions2 = new HashSet<CategoryDescription>();
	    descriptions2.add(musicEnglishDescription);
	    descriptions2.add(musicFrenchDescription);

	    music.setDescriptions(descriptions2);

	    categoryService.create(music);

	    Category novell = new Category();
	    novell.setMerchantStore(store);
	    novell.setCode("novell");

	    CategoryDescription novellEnglishDescription = new CategoryDescription();
	    novellEnglishDescription.setName("Novell");
	    novellEnglishDescription.setCategory(novell);
	    novellEnglishDescription.setLanguage(en);

	    CategoryDescription novellFrenchDescription = new CategoryDescription();
	    novellFrenchDescription.setName("Roman");
	    novellFrenchDescription.setCategory(novell);
	    novellFrenchDescription.setLanguage(fr);

	    Set<CategoryDescription> descriptions3 = new HashSet<CategoryDescription>();
	    descriptions3.add(novellEnglishDescription);
	    descriptions3.add(novellFrenchDescription);

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

	    CategoryDescription techFrenchDescription = new CategoryDescription();
	    techFrenchDescription.setName("Technologie");
	    techFrenchDescription.setCategory(tech);
	    techFrenchDescription.setLanguage(fr);

	    Set<CategoryDescription> descriptions4 = new HashSet<CategoryDescription>();
	    descriptions4.add(techFrenchDescription);
	    descriptions4.add(techFrenchDescription);

	    tech.setDescriptions(descriptions4);
	    
	    tech.setParent(book);

	    categoryService.create(tech);
	    categoryService.addChild(book, tech);

	    Category fiction = new Category();
	    fiction.setMerchantStore(store);
	    fiction.setCode("fiction");

	    CategoryDescription fictionEnglishDescription = new CategoryDescription();
	    fictionEnglishDescription.setName("Fiction");
	    fictionEnglishDescription.setCategory(fiction);
	    fictionEnglishDescription.setLanguage(en);

	    CategoryDescription fictionFrenchDescription = new CategoryDescription();
	    fictionFrenchDescription.setName("Sc Fiction");
	    fictionFrenchDescription.setCategory(fiction);
	    fictionFrenchDescription.setLanguage(fr);

	    Set<CategoryDescription> fictiondescriptions = new HashSet<CategoryDescription>();
	    fictiondescriptions.add(fictionEnglishDescription);
	    fictiondescriptions.add(fictionFrenchDescription);

	    fiction.setDescriptions(fictiondescriptions);
	    
	    fiction.setParent(novell);

	    categoryService.create(fiction);
	    categoryService.addChild(book, fiction);

	    Manufacturer oreilley = new Manufacturer();
	    oreilley.setMerchantStore(store);
	    oreilley.setCode("oreilley");

	    ManufacturerDescription oreilleyd = new ManufacturerDescription();
	    oreilleyd.setLanguage(en);
	    oreilleyd.setName("O\'reilley");
	    oreilleyd.setManufacturer(oreilley);
	    oreilley.getDescriptions().add(oreilleyd);

	    manufacturerService.create(oreilley);

	    Manufacturer packed = new Manufacturer();
	    packed.setMerchantStore(store);
	    packed.setCode("packed");

	    ManufacturerDescription packedd = new ManufacturerDescription();
	    packedd.setLanguage(en);
	    packedd.setManufacturer(packed);
	    packedd.setName("Packed publishing");
	    packed.getDescriptions().add(packedd);

	    manufacturerService.create(packed);

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
	    product.setProductHeight(new BigDecimal(4));
	    product.setProductLength(new BigDecimal(3));
	    product.setProductWidth(new BigDecimal(1));
	    product.setSku("CT12345");
	    product.setManufacturer(oreilley);
	    product.setType(generalType);
	    product.setMerchantStore(store);

	    // Product description
	    ProductDescription description = new ProductDescription();
	    description.setName("Spring in Action");
	    description.setLanguage(en);
	    description.setProduct(product);

	    product.getDescriptions().add(description);

	    //add category
	    product.getCategories().add(tech);

	    

	    // Availability
	    ProductAvailability availability = new ProductAvailability();
	    availability.setProductDateAvailable(date);
	    availability.setProductQuantity(100);
	    availability.setRegion("*");
	    availability.setProduct(product);// associate with product

	    //productAvailabilityService.create(availability);
	    product.getAvailabilities().add(availability);

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
	    
	    
	    /**
	     * Create product
	     */
	    productService.saveProduct(product);
	    
	    
	    /**
	     * Creates a review
	     * TODO requires customer
	     */
	    //testReview(product);

	    List<Product> products = productService.listByStore(store);
	    
	    System.out.println("Total number of items " + products.size());
	    
	    //count products by category
		String lineage = new StringBuilder().append(book.getLineage()).toString();
		
		List<Category> categories = categoryService.getListByLineage(store, lineage);
		
		List<Long> ids = new ArrayList<Long>();
		if(categories!=null && categories.size()>0) {
			for(Category c : categories) {
				ids.add(c.getId());
			}
		} 
		
		List<Object[]> objs = categoryService.countProductsByCategories(store, ids);

		for(Object[] ob : objs) {
			Long c = (Long) ob[0];
			//System.out.println("Category " + c.getCode() + " has " + ob[1] + " items");
		}

		//get manufacturer for given categories
		List<Manufacturer> manufacturers = manufacturerService.listByProductsByCategoriesId(store, ids, en);
	    
		//System.out.println("Number of manufacturer for all category " + manufacturers.size());
		
		//Update product -- get first from the list
		Product updatableProduct = products.get(0);

		//Get first availability, which is the only one created
		ProductAvailability updatableAvailability = updatableProduct.getAvailabilities().iterator().next();
		
		//Get first price, which is the only one created
		ProductPrice updatablePrice = updatableAvailability.getPrices().iterator().next();
		updatablePrice.setProductPriceAmount(new BigDecimal(19.99));
		
		
		//Add a second price
	    ProductPrice anotherPrice = new ProductPrice();
	    anotherPrice.setCode("eco");
	    anotherPrice.setProductPriceAmount(new BigDecimal(1.99));
	    anotherPrice.setProductAvailability(updatableAvailability);

	    ProductPriceDescription anotherPriceD = new ProductPriceDescription();
	    anotherPriceD.setName("Eco price");
	    anotherPriceD.setProductPrice(anotherPrice);
	    anotherPriceD.setLanguage(en);

	    anotherPrice.getDescriptions().add(anotherPriceD);
	    updatableAvailability.getPrices().add(anotherPrice);
		
		//Update product
		productService.update(updatableProduct);
		
		
		//go and get products again
		products = productService.listByStore(store);

		updatableProduct = products.get(0);
		
		//test attributes
		this.testAttributes(updatableProduct);
		
		
		//test insert, view image
		testInsertImage(updatableProduct);
		testViewImage(updatableProduct);
		
		Product refreshed = productService.getBySku("CT12345", store, en);
		productService.delete(refreshed);

		
	    
	}

	
	private void testAttributes(Product product) throws Exception {
		
		
		/**
		 * An attribute can be created dynamicaly but the attached Option and Option value need to exist
		 */
		
		MerchantStore store = product.getMerchantStore();
		
		Language en = languageService.getByCode("en");
		
	    /**
	     * Create size option
	     */
	    ProductOption color = new ProductOption();
	    color.setMerchantStore(store);
	    color.setCode("COLOR");
	    color.setProductOptionType(ProductOptionType.Radio.name());
	    
	    ProductOptionDescription optionDescription = new ProductOptionDescription();
	    optionDescription.setLanguage(en);
	    optionDescription.setName("Color");
	    optionDescription.setDescription("Color of an item");
	    optionDescription.setProductOption(color);
	    
	    color.getDescriptions().add(optionDescription);
	    
	    //create option
	    productOptionService.saveOrUpdate(color);
	    
	     /**
         * Create size option
         */
        ProductOption size = new ProductOption();
        size.setMerchantStore(store);
        size.setCode("SIZE");
        size.setProductOptionType(ProductOptionType.Radio.name());
        
        ProductOptionDescription sizeDescription = new ProductOptionDescription();
        sizeDescription.setLanguage(en);
        sizeDescription.setName("Size");
        sizeDescription.setDescription("Size of an item");
        sizeDescription.setProductOption(size);
        
        size.getDescriptions().add(sizeDescription);
        
        //create option
        productOptionService.saveOrUpdate(size);
	    
	    
	    //option value
	    ProductOptionValue red = new ProductOptionValue();
	    red.setMerchantStore(store);
	    red.setCode("red");
	    
	    ProductOptionValueDescription redDescription = new ProductOptionValueDescription();
	    redDescription.setLanguage(en);
	    redDescription.setName("Red");
	    redDescription.setDescription("Red color");
	    redDescription.setProductOptionValue(red);
	    
	    red.getDescriptions().add(redDescription);
	    
	    //create an option value
	    productOptionValueService.saveOrUpdate(red);
	    
	    //another option value
	    ProductOptionValue blue = new ProductOptionValue();
	    blue.setMerchantStore(store);
	    blue.setCode("blue");
	    
	    ProductOptionValueDescription blueDescription = new ProductOptionValueDescription();
	    blueDescription.setLanguage(en);
	    blueDescription.setName("Blue");
	    blueDescription.setDescription("Color blue");
	    blueDescription.setProductOptionValue(blue);
	    
	    blue.getDescriptions().add(blueDescription);

	    //create another option value
	    productOptionValueService.saveOrUpdate(blue);
	    
	    //option value
        ProductOptionValue small = new ProductOptionValue();
        small.setMerchantStore(store);
        small.setCode("small");
        
        ProductOptionValueDescription smallDescription = new ProductOptionValueDescription();
        smallDescription.setLanguage(en);
        smallDescription.setName("Small");
        smallDescription.setDescription("Small size");
        smallDescription.setProductOptionValue(small);
        
        small.getDescriptions().add(smallDescription);
        
        //create an option value
        productOptionValueService.saveOrUpdate(small);
        
        //another option value
        ProductOptionValue medium = new ProductOptionValue();
        medium.setMerchantStore(store);
        medium.setCode("medium");
        
        ProductOptionValueDescription mediumDescription = new ProductOptionValueDescription();
        mediumDescription.setLanguage(en);
        mediumDescription.setName("Medium");
        mediumDescription.setDescription("Medium size");
        mediumDescription.setProductOptionValue(medium);
        
        medium.getDescriptions().add(mediumDescription);

        //create another option value
        productOptionValueService.saveOrUpdate(medium);
        
        
        ProductAttribute color_blue = new ProductAttribute();
        color_blue.setProduct(product);
        color_blue.setProductOption(color);
        color_blue.setAttributeDefault(true);
        color_blue.setProductAttributePrice(new BigDecimal(0));//no price variation
        color_blue.setProductAttributeWeight(new BigDecimal(1));//weight variation
        color_blue.setProductOptionValue(blue);
        
        productAttributeService.create(color_blue);
        
        product.getAttributes().add(color_blue);
        
	    
	    /** create attributes **/
	    //attributes
	    ProductAttribute color_red = new ProductAttribute();
	    color_red.setProduct(product);
	    color_red.setProductOption(color);
	    color_red.setAttributeDefault(true);
	    color_red.setProductAttributePrice(new BigDecimal(0));//no price variation
	    color_red.setProductAttributeWeight(new BigDecimal(1));//weight variation
	    color_red.setProductOptionValue(red);
	    
	    productAttributeService.create(color_red);
	    
	    product.getAttributes().add(color_red);


	    ProductAttribute smallAttr = new ProductAttribute();
	    smallAttr.setProduct(product);
	    smallAttr.setProductOption(size);
	    smallAttr.setAttributeDefault(true);
	    smallAttr.setProductAttributePrice(new BigDecimal(0));//no price variation
	    smallAttr.setProductAttributeWeight(new BigDecimal(1));//weight variation
	    smallAttr.setProductOptionValue(small);
	    
	    productAttributeService.create(smallAttr);
	    
	    product.getAttributes().add(smallAttr);
	    
	    productService.update(product);
	    
	    /**
	     * get options facets
	     */
	    
	    List<ProductAttribute> attributes = productAttributeService.getProductAttributesByCategoryLineage(store, product.getCategories().iterator().next().getLineage(), en);
	    Assert.assertTrue((long) attributes.size() > 0);

	}
	
	/**
	 * Images
	 * @param product
	 * @throws Exception
	 */
	private void testInsertImage(Product product) throws Exception {
		
		
		ProductImage productImage = new ProductImage();
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classloader.getResourceAsStream("img/" + IMAGE_NAME);

        
        ImageContentFile cmsContentImage = new ImageContentFile();
        cmsContentImage.setFileName( IMAGE_NAME );
        cmsContentImage.setFile( inputStream );
        cmsContentImage.setFileContentType(FileContentType.PRODUCT);
        

        productImage.setProductImage(IMAGE_NAME);
        productImage.setProduct(product);
        
        //absolutely required otherwise the file is not created on disk
        productImage.setImage(inputStream);
        
        product.getImages().add(productImage);
        
        productService.update(product);//saves the ProductImage entity and the file on disk
        
        
		
		
	}
	
	private void testViewImage(Product product) throws Exception {
		
		
		ProductImage productImage = product.getProductImage();

        //get physical small image
        OutputContentFile contentFile = productImageService.getProductImage(product.getMerchantStore().getCode(), product.getSku(), productImage.getProductImage(), ProductImageSize.SMALL);
        
        Assert.assertNotNull(contentFile);

   	 	//get physical original image
        contentFile = productImageService.getProductImage(product.getMerchantStore().getCode(), product.getSku(), productImage.getProductImage(), ProductImageSize.LARGE);
        
        Assert.assertNotNull(contentFile);

		
	}
	
	
	//REVIEW
	private void testReview(Product product) throws Exception {
	  
	     ProductReview review = new ProductReview();
	     review.setProduct(product);
	     review.setReviewRating(4d);
	     Language en = languageService.getByCode("en");
	        
	     ProductReviewDescription reviewDescription = new ProductReviewDescription();
	     reviewDescription.setLanguage(en);
	     reviewDescription.setDescription("This is a product review");
	     reviewDescription.setName("A review for you");
	     reviewDescription.setProductReview(review);
	     review.getDescriptions().add(reviewDescription);
	        
	     productReviewService.create(review);
	  
	}
	
	private void testCreateRelationShip(Product product) throws Exception {
		
		MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
		Language en = languageService.getByCode("en");
		Manufacturer oreilley = manufacturerService.getByCode(store, "oreilley");
		ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);
		
		Category tech = categoryService.getByCode(store, "tech");
		
		
		//create new related product
	    // PRODUCT 1

	    Product related = new Product();
	    related.setProductHeight(new BigDecimal(4));
	    related.setProductLength(new BigDecimal(3));
	    related.setProductWidth(new BigDecimal(1));
	    related.setSku("TB67891");
	    related.setManufacturer(oreilley);
	    related.setType(generalType);
	    related.setMerchantStore(store);

	    // Product description
	    ProductDescription description = new ProductDescription();
	    description.setName("Spring 4 in Action");
	    description.setLanguage(en);
	    description.setProduct(related);

	    product.getDescriptions().add(description);

	    //add category
	    product.getCategories().add(tech);

	    

	    // Availability
	    ProductAvailability availability = new ProductAvailability();
	    availability.setProductDateAvailable(date);
	    availability.setProductQuantity(200);
	    availability.setRegion("*");
	    availability.setProduct(related);// associate with product

	    //productAvailabilityService.create(availability);
	    related.getAvailabilities().add(availability);

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
	    
	    related.getAvailabilities().add(availability);
	    
	    productService.save(related);
	    
	    ProductRelationship relationship = new ProductRelationship();
	    
	    relationship.setActive(true);
	    relationship.setCode("spring");
	    relationship.setProduct(product);
	    relationship.setRelatedProduct(related);
	    relationship.setStore(store);
	    
	    
	    //because relationships are nor joined fetched, make sure you query relationships first, then ad to an existing list
	    //so relationship and review are they only objects not joined fetch when querying a product
	    //need to do a subsequent query
	    List<ProductRelationship> relationships = productRelationshipService.listByProduct(product);
	    
	    
	    relationships.add(relationship);
	    
	    product.setRelationships(new HashSet<ProductRelationship>(relationships));
	    
	    productService.save(product);
		
		
	}
	




}