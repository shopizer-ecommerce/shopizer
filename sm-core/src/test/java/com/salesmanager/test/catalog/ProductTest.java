package com.salesmanager.test.catalog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
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

import junit.framework.Assert;



@Ignore
public class ProductTest extends com.salesmanager.test.common.AbstractSalesManagerCoreTestCase {
	
	private static final Date date = new Date(System.currentTimeMillis());
	
	private final String IMAGE_FOLDER = "C:/doc/";
	private final String IMAGE_NAME = "Hadoop.jpg";

	/**
	 * This method creates multiple products using multiple catelog APIs
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

	    List<CategoryDescription> descriptions = new ArrayList<CategoryDescription>();
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

	    List<CategoryDescription> descriptions2 = new ArrayList<CategoryDescription>();
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

	    List<CategoryDescription> descriptions3 = new ArrayList<CategoryDescription>();
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

	    List<CategoryDescription> descriptions4 = new ArrayList<CategoryDescription>();
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

	    List<CategoryDescription> fictiondescriptions = new ArrayList<CategoryDescription>();
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
	    product.setSku("TB12345");
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
	    productService.create(product);
	    

	    ProductReview review = new ProductReview();
	    review.setProduct(product);
	    review.setReviewRating(new Double(4));
	    
	    ProductReviewDescription reviewDescription = new ProductReviewDescription();
	    reviewDescription.setLanguage(en);
	    reviewDescription.setDescription("This is a product review");
	    reviewDescription.setName("A review for you");
	    reviewDescription.setProductReview(review);
	    review.getDescriptions().add(reviewDescription);
	    
	    productReviewService.create(review);
	    
	    review = new ProductReview();
	    review.setProduct(product);
	    review.setReviewRating(new Double(5));
	    
	    reviewDescription = new ProductReviewDescription();
	    reviewDescription.setLanguage(en);
	    reviewDescription.setDescription("This is a second product review");
	    reviewDescription.setName("A new review for you");
	    reviewDescription.setProductReview(review);
	    review.getDescriptions().add(reviewDescription);
	    
	    productReviewService.create(review);
	    
  
	    
	    List<Product> products = productService.listByStore(store);
	    
	    System.out.println("Total number of items " + products.size());
	    
	    //count products by category
		String lineage = new StringBuilder().append(book.getLineage()).toString();
		
		List<Category> categories = categoryService.listByLineage(store, lineage);
		
		List<Long> ids = new ArrayList<Long>();
		if(categories!=null && categories.size()>0) {
			for(Category c : categories) {
				System.out.println("Contains category " + c.getCode());
				ids.add(c.getId());
			}
		} 
		
		List<Object[]> objs = categoryService.countProductsByCategories(store, ids);

		for(Object[] ob : objs) {
			
			
			Category c = (Category) ob[0];
			System.out.println("Category " + c.getCode() + " has " + ob[1] + " items");
			
		}

		//get manufacturer for given categories
		List<Manufacturer> manufacturers = manufacturerService.listByProductsByCategoriesId(store, ids, en);
	    
		System.out.println("Number of manufacturer for all category " + manufacturers.size());
		
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
		
		
		//test insert, view image
		testInsertImage(updatableProduct);
		testViewImage(updatableProduct);
		
		
		updatableProduct.setDateAvailable(new java.util.Date());
		productService.create(updatableProduct);
		
		//go and get products again
		products = productService.listByStore(store);

		updatableProduct = products.get(0);
		
		//test create, view attribute
		testInsertAttribute(updatableProduct);
		testViewAttribute(updatableProduct);
		
		//go and get products again
		products = productService.listByStore(store);

		updatableProduct = products.get(0);
		
		testCreateRelationShip(updatableProduct);
		
		
		//Now remove product
		productService.delete(updatableProduct);
		
		
	    
	}
	
	
	private void testViewAttribute(Product product) throws Exception {
		
		//todo fetch product
		
		Set<ProductAttribute> attributes = product.getAttributes();
		
		for(ProductAttribute attribute : attributes) {
			
			
			ProductOption option = attribute.getProductOption();
			ProductOptionValue optionValue = attribute.getProductOptionValue();
			
			System.out.println("Option id " + option.getId() + " OptionValue id " + optionValue.getId());
			
			
			
		}
		
		
	}
	
	private void testInsertAttribute(Product product) throws Exception {
		
		
		/**
		 * An attribute can be created dynamicaly but the attached Option and Option value need to exist
		 */
		
		MerchantStore store = product.getMerchantStore();
		
		Language en = languageService.getByCode("en");
		
	    /**
	     * Create an option
	     */
	    ProductOption option = new ProductOption();
	    option.setMerchantStore(store);
	    option.setCode("copy");
	    option.setProductOptionType(ProductOptionType.Radio.name());
	    
	    ProductOptionDescription optionDescription = new ProductOptionDescription();
	    optionDescription.setLanguage(en);
	    optionDescription.setName("Book type");
	    optionDescription.setDescription("Offered in hard and soft copy");
	    optionDescription.setProductOption(option);
	    
	    option.getDescriptions().add(optionDescription);
	    
	    //create option
	    productOptionService.saveOrUpdate(option);
	    
	    
	    //option value
	    ProductOptionValue soft = new ProductOptionValue();
	    soft.setMerchantStore(store);
	    soft.setCode("soft");
	    
	    ProductOptionValueDescription softDescription = new ProductOptionValueDescription();
	    softDescription.setLanguage(en);
	    softDescription.setName("Soft");
	    softDescription.setDescription("Soft copy");
	    softDescription.setProductOptionValue(soft);
	    
	    soft.getDescriptions().add(softDescription);
	    
	    //create an option value
	    productOptionValueService.saveOrUpdate(soft);
	    
	    //another option value
	    ProductOptionValue hard = new ProductOptionValue();
	    hard.setMerchantStore(store);
	    hard.setCode("hard");
	    
	    ProductOptionValueDescription hardDescription = new ProductOptionValueDescription();
	    hardDescription.setLanguage(en);
	    hardDescription.setName("Hard");
	    hardDescription.setDescription("Hard copy");
	    hardDescription.setProductOptionValue(hard);
	    
	    hard.getDescriptions().add(hardDescription);

	    //create another option value
	    productOptionValueService.saveOrUpdate(hard);
	    
	    /** create attributes **/
	    //attributes
	    ProductAttribute attribute = new ProductAttribute();
	    attribute.setProduct(product);
	    attribute.setProductOption(option);
	    attribute.setAttributeDefault(true);
	    attribute.setProductAttributePrice(new BigDecimal(0));//no price variation
	    attribute.setProductAttributeWeight(new BigDecimal(1));//weight variation
	    attribute.setProductOptionValue(hard);
	    
	    product.getAttributes().add(attribute);
	    
	    //another attribute
	    attribute = new ProductAttribute();
	    attribute.setProduct(product);
	    attribute.setProductOption(option);
	    attribute.setProductAttributePrice(new BigDecimal(0));//no price variation
	    attribute.setProductAttributeWeight(new BigDecimal(0));//no weight variation
	    attribute.setProductOption(option);
	    attribute.setProductOptionValue(soft);
	    
	    product.getAttributes().add(attribute);
	    
	    productService.update(product);
		
		
	}
	
	/**
	 * Images
	 * @param product
	 * @throws Exception
	 */
	private void testInsertImage(Product product) throws Exception {
		
		
		ProductImage productImage = new ProductImage();
		
        File file1 = new File( IMAGE_FOLDER + IMAGE_NAME);

        if ( !file1.exists() || !file1.canRead() )
        {
            throw new ServiceException( "Can't read" + file1.getAbsolutePath() );
        }

        byte[] is = IOUtils.toByteArray( new FileInputStream( file1 ) );
        ByteArrayInputStream inputStream = new ByteArrayInputStream( is );
        
        ImageContentFile cmsContentImage = new ImageContentFile();
        cmsContentImage.setFileName( file1.getName() );
        cmsContentImage.setFile( inputStream );
        cmsContentImage.setFileContentType(FileContentType.PRODUCT);
        

        productImage.setProductImage(file1.getName());
        productImage.setProduct(product);
        
        //absolutely required otherwise the file is not created on disk
        productImage.setImage(inputStream);
        
        product.getImages().add(productImage);
        
        productService.update(product);//saves the ProductImage entity and the file on disk
		
		
	}
	
	private void testViewImage(Product product) throws Exception {
		
		
		ProductImage productImage = product.getProductImage();
		
		//equivalent
        //productImage = productImageService.getById(productImage.getId());
        
        //get physical small image
        OutputContentFile contentFile = productImageService.getProductImage(product.getMerchantStore().getCode(), product.getSku(), productImage.getProductImage(), ProductImageSize.SMALL);
        
        Assert.assertNotNull(contentFile);
        
        //print small image
  	 	OutputStream outputStream = new FileOutputStream (IMAGE_FOLDER + "productImage_small_" + contentFile.getFileName()); 

   	 	ByteArrayOutputStream baos =  contentFile.getFile();
   	 	baos.writeTo(outputStream);
   	 	
   	 	
   	 	//get physical original image
        contentFile = productImageService.getProductImage(product.getMerchantStore().getCode(), product.getSku(), productImage.getProductImage(), ProductImageSize.LARGE);
        
        Assert.assertNotNull(contentFile);
        
        //print large image
   	 	outputStream = new FileOutputStream (IMAGE_FOLDER + "productImage_large_" + contentFile.getFileName()); 

   	 	baos =  contentFile.getFile();
   	 	baos.writeTo(outputStream);
		
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