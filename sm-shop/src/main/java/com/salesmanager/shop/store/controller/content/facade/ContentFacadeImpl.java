package com.salesmanager.shop.store.controller.content.facade;

import java.util.List;

import javax.inject.Inject;

import org.jsoup.helper.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.content.ContentFile;
import com.salesmanager.shop.model.content.ContentFolder;
import com.salesmanager.shop.model.content.ContentImage;
import com.salesmanager.shop.utils.FilePathUtils;
import com.salesmanager.shop.utils.ImageFilePath;

@Component("contentFacade")
public class ContentFacadeImpl implements ContentFacade {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentFacade.class);
	
	private String basePath = Constants.STATIC_URI;
	
	
	@Inject
	private ContentService contentService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;
	
	@Inject
	private FilePathUtils fileUtils;

	@Override
	public ContentFolder getContentFolder(String folder, MerchantStore store) throws Exception {

			List<String> imageNames = contentService.getContentFilesNames(store.getCode(),FileContentType.IMAGE);
			List<String> fileNames = null;//add files since they are bundled with images
					//contentService.getContentFilesNames(store.getCode(),FileContentType.STATIC_FILE);

			ContentFolder contentFolder = null;
			
			//images from CMS
			if(imageNames!=null) {
				
				if(contentFolder==null) {
					contentFolder = new ContentFolder();
					contentFolder.setPath(folder);
				}

				for(String name : imageNames) {
					String path = new StringBuilder().append(imageUtils.getContextPath()).append(imageUtils.buildStaticImageUtils(store, null)).toString();
					ContentImage contentImage = new ContentImage();
					contentImage.setName(name);
					contentImage.setPath(path);
					contentFolder.getContent().add(contentImage);
				}
			}
			
			//files from CMS
			if(fileNames!=null) {
				
				if(contentFolder==null) {
					contentFolder = new ContentFolder();
					contentFolder.setPath(folder);
				}

				for(String name : fileNames) {
					String path = new StringBuilder().append(fileUtils.getContextPath()).append(fileUtils.buildStaticFilePath(store)).toString();
					ContentFile cf = new ContentFile();
					cf.setPath(path);
					cf.setName(name);
					contentFolder.getContent().add(cf);
				}
			
			}
			
			return contentFolder;
			

	}

	@Override
	public String absolutePath(MerchantStore store, String file) {
		String path = new StringBuilder().append(imageUtils.getContextPath()).append(imageUtils.buildStaticImageUtils(store, file)).toString();
		return path;
	}

	@Override
	public void delete(MerchantStore store, String fileName, String fileType) throws Exception {
			Validate.notNull(store, "MerchantStore cannot be null");
			Validate.notNull(fileName, "File name cannot be null");
			

			try {
				FileContentType t = FileContentType.valueOf(fileType);
				contentService.removeFile(store.getCode(), t, fileName);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				LOGGER.error("Cannot remove file ["+ fileName + "]",e.getMessage());
				throw e;
			}
		
	}

}
