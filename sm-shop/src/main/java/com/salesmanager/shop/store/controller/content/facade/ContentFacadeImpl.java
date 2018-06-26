package com.salesmanager.shop.store.controller.content.facade;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.model.content.ContentFolder;
import com.salesmanager.shop.model.content.ContentImage;
import com.salesmanager.shop.utils.ImageFilePath;

@Component("contentFacade")
public class ContentFacadeImpl implements ContentFacade {
	
	@Inject
	private ContentService contentService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public ContentFolder getContentFolder(String folder, MerchantStore store) throws Exception {

			List<String> imageNames = contentService.getContentFilesNames(store.getCode(),FileContentType.IMAGE);
			List<String> fileNames = contentService.getContentFilesNames(store.getCode(),FileContentType.STATIC_FILE);

			ContentFolder contentFolder = null;
			
			//images from CMS
			if(imageNames!=null) {
				
				if(contentFolder==null) {
					contentFolder = new ContentFolder();
					contentFolder.setPath(folder);
				}

				for(String name : imageNames) {
					String path = new StringBuilder().append(imageUtils.getContextPath()).append(imageUtils.buildStaticImageUtils(store, null)).toString();
					ContentImage contentImage = new ContentImage(name);
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
					String path = new StringBuilder().append(imageUtils.buildStaticContentFilePath(store, null)).toString();
				}
			
			}
			
			return contentFolder;
			

	}

}
