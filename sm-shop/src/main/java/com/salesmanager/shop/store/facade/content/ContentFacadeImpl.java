package com.salesmanager.shop.store.facade.content;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.content.Content;
import com.salesmanager.core.model.content.ContentDescription;
import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.content.OutputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.content.ContentDescriptionEntity;
import com.salesmanager.shop.model.content.ContentFile;
import com.salesmanager.shop.model.content.ContentFolder;
import com.salesmanager.shop.model.content.ContentImage;
import com.salesmanager.shop.model.content.ReadableContentEntity;
import com.salesmanager.shop.model.content.ReadableContentFull;
import com.salesmanager.shop.model.content.box.PersistableContentBox;
import com.salesmanager.shop.model.content.box.ReadableContentBox;
import com.salesmanager.shop.model.content.box.ReadableContentBoxFull;
import com.salesmanager.shop.model.content.page.PersistableContentPage;
import com.salesmanager.shop.model.content.page.ReadableContentPage;
import com.salesmanager.shop.model.content.page.ReadableContentPageFull;
import com.salesmanager.shop.model.entity.ReadableEntityList;
import com.salesmanager.shop.store.api.exception.ConstraintException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.content.facade.ContentFacade;
import com.salesmanager.shop.utils.FilePathUtils;
import com.salesmanager.shop.utils.ImageFilePath;

@Component("contentFacade")
public class ContentFacadeImpl implements ContentFacade {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContentFacade.class);

	public static final String FILE_CONTENT_DELIMETER = "/";

	@Inject
	private ContentService contentService;

	@Inject
	private LanguageService languageService;

	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Inject
	private FilePathUtils fileUtils;

	@Override
	public ContentFolder getContentFolder(String folder, MerchantStore store) throws Exception {
		try {
			List<String> imageNames = Optional
					.ofNullable(contentService.getContentFilesNames(store.getCode(), FileContentType.IMAGE))
					.orElseThrow(() -> new ResourceNotFoundException("No Folder found for path : " + folder));

			// images from CMS
			List<ContentImage> contentImages = imageNames.stream().map(name -> convertToContentImage(name, store))
					.collect(Collectors.toList());

			ContentFolder contentFolder = new ContentFolder();
			if (!StringUtils.isBlank(folder)) {
				contentFolder.setPath(URLEncoder.encode(folder, "UTF-8"));
			}
			contentFolder.getContent().addAll(contentImages);
			return contentFolder;

		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while getting folder " + e.getMessage(), e);
		}
	}

	private ContentImage convertToContentImage(String name, MerchantStore store) {
		String path = absolutePath(store, null);
		ContentImage contentImage = new ContentImage();
		contentImage.setName(name);
		contentImage.setPath(path);
		return contentImage;
	}

	@Override
	public String absolutePath(MerchantStore store, String file) {
		return new StringBuilder().append(imageUtils.getContextPath())
				.append(imageUtils.buildStaticImageUtils(store, file)).toString();
	}

	@Override
	public void delete(MerchantStore store, String fileName, String fileType) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(fileName, "File name cannot be null");
		try {
			FileContentType t = FileContentType.valueOf(fileType);
			contentService.removeFile(store.getCode(), t, fileName);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReadableEntityList<ReadableContentPage> getContentPages(MerchantStore store, Language language, int page,
			int count) {
		Validate.notNull(store, "MerchantStore cannot be null");

		@SuppressWarnings("rawtypes")
		ReadableEntityList items = new ReadableEntityList();
		Page<Content> contentPages;
		try {
			contentPages = contentService.listByType(ContentType.PAGE, store, page, count);

			items.setTotalPages(contentPages.getTotalPages());
			items.setNumber(contentPages.getContent().size());
			items.setRecordsTotal(contentPages.getNumberOfElements());

			List<ReadableContentBox> boxes = contentPages.getContent().stream()
					.map(content -> convertContentToReadableContentBox(store, language, content))
					.collect(Collectors.toList());

			items.setItems(boxes);
			return items;

		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while getting content ", e);
		}

	}

	private ReadableContentPage contentDescriptionToReadableContent(MerchantStore store, Content content,
			ContentDescription contentDescription) {

		ReadableContentPage page = new ReadableContentPage();

		ContentDescription desc = new ContentDescription();

		desc.setName(contentDescription.getName());
		desc.setDescription(contentDescription.getDescription());

		page.setId(content.getId());
		desc.setSeUrl(contentDescription.getSeUrl());
		page.setLinkToMenu(content.isLinkToMenu());
		desc.setTitle(contentDescription.getTitle());
		desc.setMetatagDescription(contentDescription.getMetatagDescription());
		page.setContentType(ContentType.PAGE.name());
		page.setCode(content.getCode());
		page.setPath(fileUtils.buildStaticFilePath(store.getCode(), contentDescription.getSeUrl()));
		return page;

	}

	@Deprecated
	private ReadableContentFull convertContentToReadableContentFull(MerchantStore store, Language language,
			Content content) {
		ReadableContentFull contentFull = new ReadableContentFull();

		try {
			List<ContentDescriptionEntity> descriptions = this.createContentDescriptionEntitys(store, content,
					language);

			contentFull.setDescriptions(descriptions);
			contentFull.setId(content.getId());
			contentFull.setDisplayedInMenu(content.isLinkToMenu());
			contentFull.setContentType(content.getContentType().name());
			contentFull.setCode(content.getCode());
			contentFull.setId(content.getId());
			contentFull.setVisible(content.isVisible());

			return contentFull;

		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while creating ReadableContentFull", e);
		}
	}

	@Deprecated
	private ReadableContentEntity convertContentToReadableContentEntity(MerchantStore store, Language language,
			Content content) {

		ReadableContentEntity contentEntity = new ReadableContentEntity();

		ContentDescriptionEntity description = this.create(content.getDescription());

		contentEntity.setDescription(description);
		contentEntity.setId(content.getId());
		contentEntity.setDisplayedInMenu(content.isLinkToMenu());
		contentEntity.setContentType(content.getContentType().name());
		contentEntity.setCode(content.getCode());
		contentEntity.setId(content.getId());
		contentEntity.setVisible(content.isVisible());

		return contentEntity;

	}

	private Content convertContentPageToContent(MerchantStore store, Content model, PersistableContentPage content) throws Exception {
		
		
		
		Content contentModel = new Content();
		if(model != null) {
			contentModel = model;
		}

		List<ContentDescription> descriptions = buildDescriptions(contentModel, content.getDescriptions());
		contentModel.setCode(content.getCode());
		contentModel.setContentType(ContentType.PAGE);
		contentModel.setMerchantStore(store);
		contentModel.setLinkToMenu(content.isLinkToMenu());
		contentModel.setVisible(content.isVisible());
		contentModel.setDescriptions(descriptions);
		contentModel.setId(content.getId());
		return contentModel;
	}

	private Content convertContentBoxToContent(MerchantStore store, Content model, PersistableContentBox content) throws Exception {
		Content contentModel = new Content();
		if(model != null) {
			contentModel = model;
		}

		List<ContentDescription> descriptions = buildDescriptions(contentModel, content.getDescriptions());
		for(ContentDescription cd : descriptions) {
			cd.setContent(contentModel);
		}

		contentModel.setCode(content.getCode());
		contentModel.setContentType(ContentType.BOX);
		contentModel.setMerchantStore(store);
		contentModel.setVisible(content.isVisible());
		contentModel.setDescriptions(descriptions);
		contentModel.setId(content.getId());
		return contentModel;
	}

	/*
	 * private Content convertContentPageToContent(MerchantStore store, Language
	 * language, Content content, PersistableContentEntity contentPage) throws
	 * ServiceException {
	 * 
	 * ContentType contentType =
	 * ContentType.valueOf(contentPage.getContentType()); if (contentType ==
	 * null) { throw new
	 * ServiceRuntimeException("Invalid specified contentType [" +
	 * contentPage.getContentType() + "]"); }
	 * 
	 * List<ContentDescription> descriptions = createContentDescription(store,
	 * content, contentPage); descriptions.stream().forEach(c ->
	 * c.setContent(content));
	 * 
	 * content.setDescriptions(descriptions);
	 * 
	 * // ContentDescription contentDescription = //
	 * createContentDescription(store, contentPage, language); //
	 * setContentDescriptionToContentModel(content,contentDescription,language);
	 * 
	 * // contentDescription.setContent(content);
	 * 
	 * if (contentPage.getId() != null && contentPage.getId().longValue() > 0) {
	 * content.setId(contentPage.getId()); }
	 * content.setVisible(contentPage.isVisible());
	 * content.setLinkToMenu(contentPage.isDisplayedInMenu());
	 * content.setContentType(ContentType.valueOf(contentPage.getContentType()))
	 * ; content.setMerchantStore(store);
	 * 
	 * return content; }
	 */

	@Deprecated
	private List<ContentDescriptionEntity> createContentDescriptionEntitys(MerchantStore store, Content contentModel,
			Language language) throws ServiceException {

		List<ContentDescriptionEntity> descriptions = new ArrayList<ContentDescriptionEntity>();

		if (!CollectionUtils.isEmpty(contentModel.getDescriptions())) {
			for (ContentDescription description : contentModel.getDescriptions()) {
				if (language != null && !language.getId().equals(description.getLanguage().getId())) {
					continue;
				}

				ContentDescriptionEntity contentDescription = create(description);
				descriptions.add(contentDescription);
			}
		}

		return descriptions;
	}

	@Deprecated
	private ContentDescriptionEntity create(ContentDescription description) {

		ContentDescriptionEntity contentDescription = new ContentDescriptionEntity();
		contentDescription.setLanguage(description.getLanguage().getCode());
		contentDescription.setTitle(description.getTitle());
		contentDescription.setName(description.getName());
		contentDescription.setFriendlyUrl(description.getSeUrl());
		contentDescription.setDescription(description.getDescription());
		if (description.getId() != null && description.getId().longValue() > 0) {
			contentDescription.setId(description.getId());
		}

		return contentDescription;

	}

	/*
	 * private List<ContentDescription> createContentDescription(
	 * PersistableContentPage content) throws ServiceException {
	 * Validate.notNull(contentModel, "Content cannot be null");
	 * 
	 * List<ContentDescription> descriptions = new
	 * ArrayList<ContentDescription>(); for (NamedEntity objectContent :
	 * content.getDescriptions()) { Language lang =
	 * languageService.getByCode(objectContent.getLanguage());
	 * ContentDescription contentDescription = new ContentDescription(); if
	 * (contentModel != null) {
	 * setContentDescriptionToContentModel(contentModel, contentDescription,
	 * lang); } contentDescription.setLanguage(lang);
	 * contentDescription.setMetatagDescription(objectContent.getMetaDescription
	 * ()); contentDescription.setTitle(objectContent.getTitle());
	 * contentDescription.setName(objectContent.getName());
	 * contentDescription.setSeUrl(objectContent.getFriendlyUrl());
	 * contentDescription.setDescription(objectContent.getDescription());
	 * contentDescription.setMetatagTitle(objectContent.getTitle());
	 * descriptions.add(contentDescription); } return descriptions; }
	 */
	private List<ContentDescription> buildDescriptions(Content contentModel,
			List<com.salesmanager.shop.model.content.common.ContentDescription> persistableDescriptions)
			throws Exception {
		List<ContentDescription> descriptions = new ArrayList<ContentDescription>();
		for (com.salesmanager.shop.model.content.common.ContentDescription objectContent : persistableDescriptions) {
			Language lang = languageService.getByCode(objectContent.getLanguage());
			Validate.notNull(lang, "language cannot be null");
			ContentDescription contentDescription = null;
			if(!CollectionUtils.isEmpty(contentModel.getDescriptions())) {
				for(ContentDescription descriptionModel : contentModel.getDescriptions()) {
					if(descriptionModel.getLanguage().getCode().equals(lang.getCode())) {
						contentDescription = descriptionModel;
						break;
					}
				}
			}
			
			if(contentDescription == null) {
				contentDescription = new ContentDescription();
			}
			
			//if (contentModel != null) {
			//	setContentDescriptionToContentModel(contentModel, contentDescription, lang);
			//}
			contentDescription.setMetatagDescription(objectContent.getMetaDescription());
			contentDescription.setTitle(objectContent.getTitle());
			contentDescription.setName(objectContent.getName());
			contentDescription.setSeUrl(objectContent.getFriendlyUrl());
			contentDescription.setDescription(objectContent.getDescription());
			contentDescription.setMetatagTitle(objectContent.getTitle());
			contentDescription.setContent(contentModel);
			contentDescription.setLanguage(lang);
			descriptions.add(contentDescription);
			//contentDescription.setId(objectContent.getId());
			/**
			contentDescription.setMetatagDescription(objectContent.getMetaDescription());
			contentDescription.setTitle(objectContent.getTitle());
			contentDescription.setName(objectContent.getName());
			contentDescription.setSeUrl(objectContent.getFriendlyUrl());
			contentDescription.setDescription(objectContent.getDescription());
			contentDescription.setMetatagTitle(objectContent.getTitle());
			descriptions.add(contentDescription);
			**/
		}
		return descriptions;
	}

	private void setContentDescriptionToContentModel(Content content, ContentDescription contentDescription,
			Language language) {

		Optional<ContentDescription> contentDescriptionModel = findAppropriateContentDescription(
				content.getDescriptions(), language);

		if (contentDescriptionModel.isPresent()) {
			contentDescription.setMetatagDescription(contentDescriptionModel.get().getMetatagDescription());
			contentDescription.setDescription(contentDescriptionModel.get().getDescription());
			contentDescription.setId(contentDescriptionModel.get().getId());
			contentDescription.setAuditSection(contentDescriptionModel.get().getAuditSection());
			contentDescription.setLanguage(contentDescriptionModel.get().getLanguage());
			contentDescription.setTitle(contentDescriptionModel.get().getTitle());
			contentDescription.setName(contentDescriptionModel.get().getName());
		} 

	}

	@Override
	public ReadableContentPage getContentPage(String code, MerchantStore store, Language language) {

		Validate.notNull(code, "Content code cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");

		try {
			Content content = null;

			if (language == null) {
				content = Optional.ofNullable(contentService.getByCode(code, store))
						.orElseThrow(() -> new ResourceNotFoundException("No page found : " + code));
			} else {
				content = Optional.ofNullable(contentService.getByCode(code, store, language))
						.orElseThrow(() -> new ResourceNotFoundException("No page found : " + code));
			}

			return convertContentToReadableContentPage(store, language, content);

		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while getting page " + e.getMessage(), e);
		}
	}

	@Override
	public ReadableEntityList<ReadableContentBox> getContentBoxes(ContentType type, String codePrefix,
			MerchantStore store, Language language, int page, int count) {

		Validate.notNull(codePrefix, "content code prefix cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");

		/*
		 * return contentService.getByCodeLike(type, codePrefix, store,
		 * language).stream() .map(content ->
		 * convertContentToReadableContentBox(store, language, content))
		 * .collect(Collectors.toList());
		 */

		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ReadableEntityList<ReadableContentBox> getContentBoxes(ContentType type, MerchantStore store,
			Language language, int page, int count) {

		Validate.notNull(store, "MerchantStore cannot be null");

		ReadableEntityList items = new ReadableEntityList();
		Page<Content> contentBoxes;
		try {
			contentBoxes = contentService.listByType(type, store, page, count);

			items.setTotalPages(contentBoxes.getTotalPages());
			items.setNumber(contentBoxes.getContent().size());
			items.setRecordsTotal(contentBoxes.getNumberOfElements());

			List<ReadableContentBox> boxes = contentBoxes.getContent().stream()
					.map(content -> convertContentToReadableContentBox(store, language, content))
					.collect(Collectors.toList());

			items.setItems(boxes);

			return items;

		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while getting content ", e);
		}

	}

	@Override
	public void addContentFile(ContentFile file, String merchantStoreCode) {
		try {
			byte[] payload = file.getFile();
			String fileName = file.getName();

			try (InputStream targetStream = new ByteArrayInputStream(payload)) {

				String type = file.getContentType().split(FILE_CONTENT_DELIMETER)[0];
				FileContentType fileType = getFileContentType(type);

				InputContentFile cmsContent = new InputContentFile();
				cmsContent.setFileName(fileName);
				cmsContent.setMimeType(file.getContentType());
				cmsContent.setFile(targetStream);
				cmsContent.setFileContentType(fileType);

				contentService.addContentFile(merchantStoreCode, cmsContent);
			}
		} catch (ServiceException | IOException e) {
			throw new ServiceRuntimeException(e);
		}
	}

	private FileContentType getFileContentType(String type) {
		FileContentType fileType = FileContentType.STATIC_FILE;
		if (type.equals("image")) {// for now we consider this route from api
									// only
			fileType = FileContentType.API_IMAGE;
		}
		return fileType;
	}

	private ReadableContentBox convertContentToReadableContentBox(MerchantStore store, Language language,
			Content content) {
		if (language != null) {
			ReadableContentBox box = new ReadableContentBox();
			this.setDescription(content, box, language);
			box.setCode(content.getCode());
			box.setId(content.getId());
			box.setVisible(content.isVisible());
			return box;
		} else {
			ReadableContentBoxFull box = new ReadableContentBoxFull();
			List<com.salesmanager.shop.model.content.common.ContentDescription> descriptions = content.getDescriptions()
					.stream().map(d -> this.contentDescription(d)).collect(Collectors.toList());
			this.setDescription(content, box, store.getDefaultLanguage());
			box.setDescriptions(descriptions);
			box.setCode(content.getCode());
			box.setId(content.getId());
			box.setVisible(content.isVisible());
			return box;
		}
		// TODO revise this
		// String staticImageFilePath = imageUtils.buildStaticImageUtils(store,
		// content.getCode() + ".jpg");
		// box.setImage(staticImageFilePath);
	}
	
	private void setDescription(Content content, ReadableContentBox box, Language lang) {
		
		Optional<ContentDescription> contentDescription = findAppropriateContentDescription(
				content.getDescriptions(), lang);
		if (contentDescription.isPresent()) {
			com.salesmanager.shop.model.content.common.ContentDescription desc = this
					.contentDescription(contentDescription.get());
			box.setDescription(desc);
		}
		
	}

	private ReadableContentPage convertContentToReadableContentPage(MerchantStore store, Language language,
			Content content) {
		if (language != null) {
			ReadableContentPage page = new ReadableContentPage();
			Optional<ContentDescription> contentDescription = findAppropriateContentDescription(
					content.getDescriptions(), language);
			if (contentDescription.isPresent()) {
				com.salesmanager.shop.model.content.common.ContentDescription desc = this
						.contentDescription(contentDescription.get());
				page.setDescription(desc);
			}
			page.setCode(content.getCode());
			page.setId(content.getId());
			page.setVisible(content.isVisible());
			return page;
		} else {
			ReadableContentPageFull page = new ReadableContentPageFull();
			List<com.salesmanager.shop.model.content.common.ContentDescription> descriptions = content.getDescriptions()
					.stream().map(d -> this.contentDescription(d)).collect(Collectors.toList());
			page.setDescriptions(descriptions);
			page.setCode(content.getCode());
			page.setId(content.getId());
			page.setVisible(content.isVisible());
			return page;
		}

	}

	private com.salesmanager.shop.model.content.common.ContentDescription contentDescription(
			ContentDescription description) {
		Validate.notNull(description, "ContentDescription cannot be null");
		com.salesmanager.shop.model.content.common.ContentDescription desc = new com.salesmanager.shop.model.content.common.ContentDescription();
		desc.setDescription(description.getDescription());//return description as is
		desc.setName(description.getName());
		desc.setTitle(description.getTitle());
		desc.setFriendlyUrl(description.getSeUrl());
		desc.setId(description.getId());
		desc.setLanguage(description.getLanguage().getCode());
		return desc;
	}

	private ReadableContentBox convertContentToReadableLegacyContentBox(MerchantStore store, Language language,
			Content content) {
		/*
		 * ReadableContentBox box = new ReadableContentBox();
		 * Optional<ContentDescription> contentDescription =
		 * findAppropriateContentDescription(content.getDescriptions(),
		 * language); if (contentDescription.isPresent()) {
		 * box.setName(contentDescription.get().getName());
		 * box.setBoxContent(contentDescription.get().getDescription()); }
		 * String staticImageFilePath = imageUtils.buildStaticImageUtils(store,
		 * content.getCode() + ".jpg"); box.setImage(staticImageFilePath);
		 * return box;
		 */

		return null;
	}

	private Optional<ContentDescription> findAppropriateContentDescription(List<ContentDescription> contentDescriptions,
			Language language) {
		return contentDescriptions.stream()
				.filter(description -> description.getLanguage().getCode().equals(language.getCode())).findFirst();
	}

	@Override
	public ReadableContentBox getContentBox(String code, MerchantStore store, Language language) {
		Validate.notNull(code, "Content code cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");

		try {
			Content content = null;
			ReadableContentBox box = new ReadableContentBox();
			
			if(language != null) {
				
				content = 	Optional.ofNullable(contentService.getByCode(code, store, language))
						.orElseThrow(() -> new ResourceNotFoundException(
								"Resource not found [" + code + "] for store [" + store.getCode() + "]"));
				
				Optional<ContentDescription> contentDescription = findAppropriateContentDescription(
						content.getDescriptions(), language);

				if (contentDescription.isPresent()) {
					com.salesmanager.shop.model.content.common.ContentDescription desc = this
							.contentDescription(contentDescription.get());//return cdata description
					desc.setDescription(this.fixContentDescription(desc.getDescription()));
					box.setDescription(desc);
				}
				
				return box;
				
			} else {

				content = 	Optional.ofNullable(contentService.getByCode(code, store))
						.orElseThrow(() -> new ResourceNotFoundException(
								"Resource not found [" + code + "] for store [" + store.getCode() + "]"));
				
				ReadableContentBoxFull full = new ReadableContentBoxFull(); //all languages
				
				List<com.salesmanager.shop.model.content.common.ContentDescription> descriptions = content.getDescriptions()
						.stream().map(d -> this.contentDescription(d)).collect(Collectors.toList());
				
				/**
				Optional<ContentDescription> contentDescription = findAppropriateContentDescription(
						content.getDescriptions(), store.getDefaultLanguage());
				
				if(contentDescription.isPresent()) {
					com.salesmanager.shop.model.content.common.ContentDescription desc = this
							.contentDescription(contentDescription.get());
					full.setDescription(desc);
				}
				**/
				
				
				full.setDescriptions(descriptions);
				full.setCode(content.getCode());
				full.setId(content.getId());
				full.setVisible(content.isVisible());
				
				return full;
				
			}
					

		} catch (ServiceException e) {
			throw new ServiceRuntimeException(e);
		}
	}
	
	private String fixContentDescription(String description) {
		Validate.notNull(description, "description cannot be empty");
		return "<![CDATA[" + description.replaceAll("\r\n", "").replaceAll("\t", "") + "]]>";
		
		
	}

	@Override
	public Long saveContentPage(PersistableContentPage page, MerchantStore merchantStore, Language language) {
		Validate.notNull(page);
		Validate.notNull(page.getCode(), "Content code must not be null");
		Validate.notNull(merchantStore);

		try {
			Content content = null;

			content = contentService.getByCode(page.getCode(), merchantStore);
			if (content != null) {
				throw new ConstraintException("Page with code [" + page.getCode() + "] already exist for store ["
						+ merchantStore.getCode() + "]");
			}

			content = convertContentPageToContent(merchantStore, content, page);
			contentService.saveOrUpdate(content);
			return content.getId();
		} catch (Exception e) {
			throw new ServiceRuntimeException(e);
		}
	}

	@Override
	public Long saveContentBox(PersistableContentBox box, MerchantStore merchantStore, Language language) {
		Validate.notNull(box);
		Validate.notNull(box.getCode(), "Content box must not be null");
		Validate.notNull(merchantStore);

		try {
			Content content = null;

			content = contentService.getByCode(box.getCode(), merchantStore);
			if (content != null) {
				throw new ConstraintException("Content box with code [" + box.getCode() + "] already exist for store ["
						+ merchantStore.getCode() + "]");
			}

			content = convertContentBoxToContent(merchantStore, content, box);
			contentService.saveOrUpdate(content);
			return content.getId();
		} catch (Exception e) {
			throw new ServiceRuntimeException(e);
		}

	}

	@Override
	public void addContentFiles(List<ContentFile> files, String merchantStoreCode) {
		for (ContentFile file : files) {
			addContentFile(file, merchantStoreCode);
		}

	}

	@Override
	public void delete(MerchantStore store, Long id) {
		Validate.notNull(store, "MerchantStore not null");
		Validate.notNull(id, "Content id must not be null");
		// select content first
		Content content = contentService.getById(id);
		if (content != null) {
			if (content.getMerchantStore().getId().intValue() != store.getId().intValue()) {
				throw new ResourceNotFoundException(
						"No content found with id [" + id + "] for store [" + store.getCode() + "]");
			}
		}

		try {
			contentService.delete(content);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while deleting content " + e.getMessage(), e);
		}

	}

	@Override
	public ReadableContentFull getContent(String code, MerchantStore store, Language language) {
		Validate.notNull(store, "MerchantStore not null");
		Validate.notNull(code, "Content code must not be null");

		try {
			Content content = contentService.getByCode(code, store);
			if (content == null) {
				throw new ResourceNotFoundException(
						"No content found with code [" + code + "] for store [" + store.getCode() + "]");
			}

			return this.convertContentToReadableContentFull(store, language, content);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while getting content [" + code + "]", e);
		}

	}

	@Override
	public List<ReadableContentEntity> getContents(Optional<String> type, MerchantStore store, Language language) {

		/**
		 * get all types
		 */
		List<ContentType> types = new ArrayList<ContentType>();
		types.add(ContentType.BOX);
		types.add(ContentType.PAGE);
		types.add(ContentType.SECTION);

		try {
			return contentService.listByType(types, store, language).stream()
					.map(content -> convertContentToReadableContentEntity(store, language, content))
					.collect(Collectors.toList());

		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while getting contents", e);
		}

	}

	@Override
	public ReadableContentPage getContentPageByName(String name, MerchantStore store, Language language) {
		Validate.notNull(name, "Content name cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");

		try {

			ContentDescription contentDescription = Optional.ofNullable(contentService.getBySeUrl(store, name))
					.orElseThrow(() -> new ResourceNotFoundException("No page found : " + name));

			return this.contentDescriptionToReadableContent(store, contentDescription.getContent(), contentDescription);

		} catch (Exception e) {
			throw new ServiceRuntimeException("Error while getting page " + e.getMessage(), e);
		}
	}

	@Override
	public void renameFile(MerchantStore store, FileContentType fileType, String originalName, String newName) {
		Optional<String> path = Optional.ofNullable(null);
		try {
			contentService.renameFile(store.getCode(), fileType, path, originalName, newName);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while renaming file " + e.getMessage(), e);
		}

	}

	@Override
	public OutputContentFile download(MerchantStore store, FileContentType fileType, String fileName) {

		try {
			return contentService.getContentFile(store.getCode(), fileType, fileName);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while downloading file " + e.getMessage(), e);
		}

	}



	@Override
	public void updateContentPage(Long id, PersistableContentPage page, MerchantStore merchantStore,
			Language language) {
		Validate.notNull(page);
		Validate.notNull(id, "Content id must not be null");
		Validate.notNull(merchantStore);

		try {
			Content content = null;

			content = contentService.getById(id, merchantStore);
			if (content == null) {
				throw new ConstraintException("Page with id [" + id + "] does not exist for store ["
						+ merchantStore.getCode() + "]");
			}

			page.setId(id);
			content = convertContentPageToContent(merchantStore, content, page);
			contentService.saveOrUpdate(content);

		} catch (Exception e) {
			throw new ServiceRuntimeException(e);
		}

	}

	@Override
	public void deleteContent(Long id, MerchantStore merchantStore) {
		Validate.notNull(id, "Content id must not be null");
		Validate.notNull(merchantStore);
		
		try {
			Content content = null;

			content = contentService.getById(id, merchantStore);
			if (content == null) {
				throw new ConstraintException("Content with id [" + id + "] does not exist for store ["
						+ merchantStore.getCode() + "]");
			}

			contentService.delete(content);

		} catch (Exception e) {
			throw new ServiceRuntimeException(e);
		}

	}

	@Override
	public void updateContentBox(Long id, PersistableContentBox box, MerchantStore merchantStore, Language language) {
		Validate.notNull(box);
		Validate.notNull(id, "Content id must not be null");
		Validate.notNull(merchantStore);

		try {
			Content content = null;

			content = contentService.getById(id, merchantStore);
			if (content == null) {
				throw new ConstraintException("Page with id [" + id + "] does not exist for store ["
						+ merchantStore.getCode() + "]");
			}

			box.setId(id);
			content = convertContentBoxToContent(merchantStore, content, box);
			contentService.saveOrUpdate(content);

		} catch (Exception e) {
			throw new ServiceRuntimeException(e);
		}

	}

	@Override
	public boolean codeExist(String code, String type, MerchantStore store) {
		return contentService.exists(code, ContentType.valueOf(type), store);
	}

}
