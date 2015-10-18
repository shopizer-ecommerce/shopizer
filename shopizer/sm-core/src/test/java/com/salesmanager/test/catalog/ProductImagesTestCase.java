package com.salesmanager.test.catalog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.model.CategoryDescription;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.file.ProductImageSize;
import com.salesmanager.core.business.catalog.product.model.image.ProductImage;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import com.salesmanager.core.business.catalog.product.model.price.ProductPriceDescription;
import com.salesmanager.core.business.content.model.FileContentType;
import com.salesmanager.core.business.content.model.ImageContentFile;
import com.salesmanager.core.business.content.model.OutputContentFile;
import com.salesmanager.core.business.content.service.ContentService;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;

public class ProductImagesTestCase extends AbstractSalesManagerCoreTestCase {
	
	private static final Date date = new Date(System.currentTimeMillis());
	
	@Autowired
	private ContentService contentService;

	@Test
	public void testCreateProductImage() throws ServiceException, FileNotFoundException, IOException {
		
	    Language en = languageService.getByCode("en");
	    Country country = countryService.getByCode("CA");
	    Zone zone = zoneService.getByCode("QC");


    	
        final MerchantStore store = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
        
        
        
        
	    /**
	     * Create the category
	     */
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
	    
	    
	    /**
	     * Create a manufacturer
	     */
	    Manufacturer packed = new Manufacturer();
	    packed.setMerchantStore(store);
	    packed.setCode("packt");

	    ManufacturerDescription packedd = new ManufacturerDescription();
	    packedd.setLanguage(en);
	    packedd.setManufacturer(packed);
	    packedd.setName("Packed publishing");
	    packed.getDescriptions().add(packedd);

	    manufacturerService.create(packed);
	    

	    
	    
	    /**
	     * Create the product
	     */
	    Product product = new Product();
	    product.setProductHeight(new BigDecimal(4));
	    product.setProductLength(new BigDecimal(3));
	    product.setProductWidth(new BigDecimal(1));
	    product.setSku("TB12345");
	    product.setManufacturer(packed);
	    product.setMerchantStore(store);

	    // Product description
	    ProductDescription description = new ProductDescription();
	    description.setName("Spring in Action");
	    description.setLanguage(en);
	    description.setProduct(product);

	    product.getDescriptions().add(description);
	    product.getCategories().add(book);
	    
	    
	    //availability
	    ProductAvailability availability = new ProductAvailability();
	    availability.setProductDateAvailable(date);
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

	  
	    productService.create(product);
        
        
   
        
        
        final File file1 = new File( "/Users/csamson777/Documents/workspace2/files/images/watch.jpg" );

        if ( !file1.exists() || !file1.canRead() )
        {
            throw new ServiceException( "Can't read" + file1.getAbsolutePath() );
        }

        final byte[] is = IOUtils.toByteArray( new FileInputStream( file1 ) );
        final ByteArrayInputStream inputStream = new ByteArrayInputStream( is );
        final ImageContentFile cmsContentImage = new ImageContentFile();
        cmsContentImage.setFileName( file1.getName() );
        cmsContentImage.setFile( inputStream );
        cmsContentImage.setFileContentType(FileContentType.PRODUCT);
        

        ProductImage productImage = new ProductImage();
        productImage.setProductImage(file1.getName());
        productImage.setProduct(product);

        
        productImageService.addProductImage(product, productImage, cmsContentImage);

        //get productImage
        productImage = productImageService.getById(productImage.getId());
        
        //get physical small image
        OutputContentFile contentFile = productImageService.getProductImage(store.getCode(), product.getSku(), productImage.getProductImage(), ProductImageSize.SMALL);
        
        Assert.assertNotNull(contentFile);
        
        //print image
   	 	OutputStream outputStream = new FileOutputStream ("/Users/csamson777/Documents/workspace2/files/images/small_" + contentFile.getFileName()); 

   	 	ByteArrayOutputStream baos =  contentFile.getFile();
   	 	baos.writeTo(outputStream);
   	 	
   	 	
   	 	//get physical original image
        contentFile = productImageService.getProductImage(store.getCode(), product.getSku(), productImage.getProductImage(), ProductImageSize.LARGE);
        
        Assert.assertNotNull(contentFile);
        
        //print image
   	 	outputStream = new FileOutputStream ("/Users/csamson777/Documents/workspace2/files/images/large_" + contentFile.getFileName()); 

   	 	baos =  contentFile.getFile();
   	 	baos.writeTo(outputStream);
   	 	
   	 	//remove productImage
   	 	productImageService.removeProductImage(productImage);
   	 	
   	 	

	    
	    

	}
	

}