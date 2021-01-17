package com.salesmanager.shop.init.data;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.salesmanager.core.business.modules.cms.product.ProductFileManager;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.image.ProductImageService;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.content.OutputContentFile;
import com.salesmanager.shop.constants.Constants;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.init.InitializationDatabase;
import com.salesmanager.core.business.services.system.MerchantConfigurationService;
import com.salesmanager.core.business.services.system.SystemConfigurationService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.business.utils.CoreConfiguration;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.MerchantConfig;
import com.salesmanager.shop.admin.security.WebUserServices;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Component
public class InitializationLoader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitializationLoader.class);

	@Value("${db.init.data:true}")
    private boolean initDefaultData;

	
	@Inject
	private MerchantConfigurationService merchantConfigurationService;
	
	@Inject
	private InitializationDatabase initializationDatabase;
	
	//@Inject
	//private InitData initData;
	
	@Inject
	private SystemConfigurationService systemConfigurationService;
	
	@Inject
	private WebUserServices userDetailsService;

	@Inject
	protected PermissionService  permissionService;
	
	@Inject
	protected GroupService   groupService;
	
	@Inject
	private CoreConfiguration configuration;
	
	@Inject
	protected MerchantStoreService merchantService;

	@Inject
	private ContentService contentService;

	@Inject
	private ProductService productService;

	@Inject
	private ProductFileManager productFileManager;

	
	@PostConstruct
	public void init() {
		
		try {
			
			//Check flag to populate or not the database
			if(!this.initDefaultData) {
				return;
			}
			
			if (initializationDatabase.isEmpty()) {
				

				//All default data to be created
				
				LOGGER.info(String.format("%s : Shopizer database is empty, populate it....", "sm-shop"));
		
				 initializationDatabase.populate("sm-shop");
				
				 MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);

                  userDetailsService.createDefaultAdmin();
                  MerchantConfig config = new MerchantConfig();
				  config.setAllowPurchaseItems(true);
				  config.setDisplayAddToCartOnFeaturedItems(true);
				  
				  merchantConfigurationService.saveMerchantConfig(config, store);


			} else {
				//check if images are initialized
				MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
				if(StringUtils.isNotEmpty(store.getStoreLogo())) {
					OutputContentFile image =contentService.getContentFile(store.getCode(), FileContentType.LOGO, store.getStoreLogo());
					if(image== null) {
						loadDemoImages(store);
					}
				}
			}
			
		} catch (Exception e) {
			LOGGER.error("Error in the init method",e);
		}
			
	}

	private void loadDemoImages(MerchantStore store) throws IOException, ServiceException {
		loadLogo(store);
		loadProductImages(store);
		loadContentImages(store);
	}

	private void loadContentImages(MerchantStore store) throws ServiceException, IOException {
		final List<InputContentFile> contentImagesList=new ArrayList<InputContentFile>();
		List<String> contentFileNames= Arrays.asList("bag-picture-text.jpg", "banner-resized.jpg");

			for(String name: contentFileNames){
				ClassPathResource imageFile = new ClassPathResource("demo/" + name);
					InputContentFile cmsContentImage = new InputContentFile();
					cmsContentImage.setFileName(name );
					cmsContentImage.setMimeType( MediaType.IMAGE_JPEG_VALUE );
					cmsContentImage.setFile( imageFile.getInputStream() );
					cmsContentImage.setFileContentType(FileContentType.IMAGE);
					contentImagesList.add( cmsContentImage);

			}
			if(CollectionUtils.isNotEmpty( contentImagesList )){
				contentService.addContentFiles( store.getCode(), contentImagesList );
			}
	}

	private void loadProductImages(MerchantStore store) throws IOException, ServiceException {
		Map<Long, String> images = new HashMap<>();
		images.put(1l, "image3.jpg");
		images.put(2l, "image1.jpg");
		images.put(2l, "image4.jpg");
		images.put(3l, "image4.1.jpg");
		images.put(3l, "image4.jpg");
		images.put(4l, "image9.jpg");
		images.put(5l, "image7.jpg");
		images.put(6l, "image5.jpg");
		images.put(7l, "image8.jpg");
		images.put(8l, "image6.jpg");
		images.put(9l, "image11.jpg");
		images.put(10l, "image2.jpg");

		for (Long id : images.keySet()) {
			Product product = productService.findOne(id, store);
			ProductImage productImage = new ProductImage();
			String fileName = images.get(id);
			ClassPathResource imageFile = new ClassPathResource("demo/" + fileName);
			productImage.setImage(imageFile.getInputStream());
			productImage.setProductImage(fileName);
			productImage.setProduct(product);
			productImage.setDefaultImage(false);//default image is uploaded in the product details
			InputStream inputStream = productImage.getImage();
			ImageContentFile cmsContentImage = new ImageContentFile();
			cmsContentImage.setFileName(productImage.getProductImage());
			cmsContentImage.setFile(inputStream);
			cmsContentImage.setFileContentType(FileContentType.PRODUCT);
			productFileManager.addProductImage(productImage, cmsContentImage);
		}


	}

	private void loadLogo(MerchantStore store) throws IOException, ServiceException {
		//Load images for demo database
		ClassPathResource imageFile = new ClassPathResource("demo/Vintage-Bags.jpg");

		String imageName = store.getStoreLogo();
		InputStream inputStream = imageFile.getInputStream();
		String mimeType = MediaType.IMAGE_JPEG_VALUE;

		InputContentFile cmsContentImage = new InputContentFile();
		cmsContentImage.setFileName(imageName);
		cmsContentImage.setMimeType(mimeType);
		cmsContentImage.setFile(inputStream);
		contentService.addLogo(store.getCode(), cmsContentImage);
	}


}
