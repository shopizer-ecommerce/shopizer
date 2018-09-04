package com.salesmanager.shop.store.controller.content.facade;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jsoup.helper.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.content.Content;
import com.salesmanager.core.model.content.ContentDescription;
import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.content.ContentFolder;
import com.salesmanager.shop.model.content.ContentImage;
import com.salesmanager.shop.model.content.ReadableContentBox;
import com.salesmanager.shop.model.content.ReadableContentPage;
import com.salesmanager.shop.utils.FilePathUtils;
import com.salesmanager.shop.utils.ImageFilePath;

@Component("contentFacade")
public class ContentFacadeImpl implements ContentFacade {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentFacade.class);

	
	@Inject
	private ContentService contentService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;
	
	@Inject
	private FilePathUtils fileUtils;

	@SuppressWarnings("unused")
	@Override
	public ContentFolder getContentFolder(String folder, MerchantStore store) throws Exception {

			List<String> imageNames = contentService.getContentFilesNames(store.getCode(),FileContentType.IMAGE);
			List<String> fileNames = null;//add files since they are bundled with images

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
			
/*			//files from CMS
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
			
			}*/
			
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

	@Override
	public List<ReadableContentPage> pages(MerchantStore store, Language language) throws Exception {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		
		List<Content> contents = contentService.listByType(ContentType.PAGE, store, language);
		List<ReadableContentPage> returnContents = new ArrayList<ReadableContentPage>(); 
		for(Content content : contents) {
			if(content.isVisible()) {//mke sure content is available
				//TODO segmentation
				ReadableContentPage page = new ReadableContentPage();
				for(ContentDescription description : content.getDescriptions()) {
					if(description.getLanguage().getCode().equals(language.getCode())) {
						page.setName(description.getSeUrl());
						page.setPageContent(description.getDescription());
						break;
					}
				}
				page.setDisplayedInMenu(content.isLinkToMenu());
				page.setContentType(ContentType.PAGE.name());
				page.setPath(fileUtils.buildStaticFilePath(store, page.getName()));
				returnContents.add(page);
				
			}
		}
		return returnContents;
	}

	@Override
	public ReadableContentPage page(String code, MerchantStore store, Language language) throws Exception {

		
		Validate.notNull(code, "Content code cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		
		Content content = contentService.getByCode(code, store, language);
		
		
		if(content==null) {
			return null;
		}
		
		ReadableContentPage page = new ReadableContentPage();
		for(ContentDescription description : content.getDescriptions()) {
			if(description.getLanguage().getCode().equals(language.getCode())) {
				page.setName(description.getSeUrl());
				page.setPageContent(description.getDescription());
				break;
			}
		}
		
		return page;

	}

	@Override
	public List<ReadableContentBox> boxes(ContentType type, String codePrefix, MerchantStore store, Language language) throws Exception {
		
		Validate.notNull(codePrefix, "content code prefix cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		
		
		
		List<Content> contents = contentService.getByCodeLike(type, codePrefix, store, language);
		List<ReadableContentBox> returns = new ArrayList<ReadableContentBox>();
		for(Content content: contents) {
			ReadableContentBox box = new ReadableContentBox();
			for(ContentDescription description : content.getDescriptions()) {
				if(description.getLanguage().getCode().equals(language.getCode())) {
					box.setName(description.getName());
					box.setBoxContent(description.getDescription());
					break;
				}
			}
			box.setImage(imageUtils.buildStaticImageUtils(store, content.getCode() + ".jpg"));
			returns.add(box);
		}
		
		return returns;

	}

	@Override
	public ReadableContentBox box(String code, MerchantStore store, Language language) throws Exception {
		Validate.notNull(code, "Content code cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		
		Content content = contentService.getByCode(code, store, language);
		
		
		if(content==null) {
			return null;
		}
		
		ReadableContentBox box = new ReadableContentBox();
		for(ContentDescription description : content.getDescriptions()) {
			if(description.getLanguage().getCode().equals(language.getCode())) {
				box.setName(description.getSeUrl());
				box.setBoxContent(description.getDescription());
				break;
			}
		}
		
		return box;
	}

}
