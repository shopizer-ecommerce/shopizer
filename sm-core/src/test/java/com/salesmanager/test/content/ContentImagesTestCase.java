package com.salesmanager.test.content;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.salesmanager.core.business.content.model.FileContentType;
import com.salesmanager.core.business.content.model.InputContentFile;
import com.salesmanager.core.business.content.model.OutputContentFile;
import com.salesmanager.core.business.content.service.ContentService;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;




/**
 * Test 
 * store logo
 * @author Carl Samson
 *
 */

public class ContentImagesTestCase extends AbstractSalesManagerCoreTestCase {
	
	private static final Date date = new Date(System.currentTimeMillis());
	
	@Autowired
	private ContentService contentService;
	

	
    @Test
    public void createStoreLogo()
        throws ServiceException, FileNotFoundException, IOException
    {

        MerchantStore store = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
        final File file1 = new File( "/Users/csamson777/Pictures/peavey.jpg" );

        if ( !file1.exists() || !file1.canRead() )
        {
            throw new ServiceException( "Can't read" + file1.getAbsolutePath() );
        }

        byte[] is = IOUtils.toByteArray( new FileInputStream( file1 ) );
        ByteArrayInputStream inputStream = new ByteArrayInputStream( is );
        InputContentFile cmsContentImage = new InputContentFile();

        cmsContentImage.setFileName( file1.getName() );
        cmsContentImage.setFile(inputStream);
 
		
        //logo as a content
        contentService.addLogo(store.getCode(), cmsContentImage);
        
        store.setStoreLogo(file1.getName() );
        merchantService.update(store);
        
        //query the store
        store = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
        
        
        
        //get the logo
        String logo = store.getStoreLogo();

		OutputContentFile image =contentService.getContentFile(store.getCode(), FileContentType.LOGO, logo);

        //print image
   	 	OutputStream outputStream = new FileOutputStream ("/Users/csamson777/Pictures/mexique" + image.getFileName()); 

   	 	ByteArrayOutputStream baos =  image.getFile();
   	 	baos.writeTo(outputStream);
		
		
		//remove image
   	 	contentService.removeFile(store.getCode(), FileContentType.LOGO, store.getStoreLogo());
		


    }
	
	

/*    
    @Test
    public void createContentImages()
        throws ServiceException, FileNotFoundException, IOException
    {

        final List<CMSContentImage> contentImagesList=new ArrayList<CMSContentImage>();
        final MerchantStore store = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
        final File file1 = new File( "/Umesh/contentimage/destination.png" );

        if ( !file1.exists() || !file1.canRead() )
        {
            throw new ServiceException( "Can't read" + file1.getAbsolutePath() );
        }

        final byte[] is = IOUtils.toByteArray( new FileInputStream( file1 ) );
        final ByteArrayInputStream inputStream = new ByteArrayInputStream( is );
        final CMSContentImage cmsContentImage = new CMSContentImage();
        cmsContentImage.setImageName( "demoCmsImage3" );
        cmsContentImage.setFile( inputStream );
        contentImagesList.add( cmsContentImage);
        
        final CMSContentImage cmsContentImage1 = new CMSContentImage();
        cmsContentImage1.setImageName( "demoCmsImage4" );
        cmsContentImage1.setFile( inputStream );
        
        contentImagesList.add( cmsContentImage1);
        
        //contentService.addContentImages( store.getCode(), contentImagesList );

    }

    @Test
    public void getContentImage()
        throws ServiceException, FileNotFoundException, IOException
    {

        final MerchantStore store = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
        final String imageName = "demoCmsImage";

        final OutputContentFile outputContentImage = contentService.getContentImage(store.getCode(), FileContentType.IMAGE, imageName );
        //final OutputContentImage outputContentImage = contentService.getContentImage( store, "" );
        System.out.println( outputContentImage.getFile() );
        System.out.println( outputContentImage.getFileName() );

    }
    
    @Test
    public void getAllContentImages() throws ServiceException{
        final MerchantStore store = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
        final List<OutputContentFile> contentImagesList= contentService.getContentImages(store.getCode(), null );
        if(CollectionUtils.isNotEmpty( contentImagesList )){
            System.out.println("Total " + contentImagesList.size()+ " Images found");
           for(final OutputContentFile outputContentImage :contentImagesList){
               System.out.println(outputContentImage.getFileName());
           }
        }
        else{
            System.out.println("No image found for given merchant store");
        }
    }
    
    @Test
    public void removeContentImage() throws ServiceException{
        final MerchantStore store = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
        final InputContentFile contentImage = new InputContentFile( );
        contentImage.setFileContentType(FileContentType.IMAGE );
        contentImage.setFileName("demoCmsImage");
        //contentService.removeImage( store.getCode(), contentImage );
        
    }
    
    @Test
    public void removeAllContentImages() throws ServiceException{
        final MerchantStore store = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
        contentService.removeImages( store.getCode());
    }
    

    @Test
    public void getContentImagesNames() throws Exception{
        final MerchantStore store = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
        final List<String> imageNames = contentService.getContentImagesNames(store.getCode(),FileContentType.IMAGE);
        for(final String imageName:imageNames){
            System.out.println(imageName);
        }
    }
    
    @Test
    public void testGetImages()
        throws ServiceException
    {

        final Product product = productService.getById( 1L );

        final List<OutputContentFile> images = productImageService.getProductImages( product );

        for ( final OutputContentFile image : images )
        {

            System.out.println( image.getFileName() );
            System.out.println( image.getFileContentType() );
        }

    }
	
	
	
	
	@Test
	public void testCreateContentImage() throws ServiceException {
		
	    Language en = languageService.getByCode("en");
	    Country country = countryService.getByCode("CA");
	    Zone zone = zoneService.getByCode("QC");

	    MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
	    
	    

	}
	*/

}