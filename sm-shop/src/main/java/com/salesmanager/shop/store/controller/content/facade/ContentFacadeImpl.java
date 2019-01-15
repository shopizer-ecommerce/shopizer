package com.salesmanager.shop.store.controller.content.facade;

import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.shop.model.content.ContentFile;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
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
import org.springframework.stereotype.Service;

@Component("contentFacade")
public class ContentFacadeImpl implements ContentFacade {

  private static final Logger LOGGER = LoggerFactory.getLogger(ContentFacade.class);

  public static final String FILE_CONTENT_DELIMETER = "/";

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
    try {
      List<String> imageNames =
          Optional.ofNullable(
                  contentService.getContentFilesNames(store.getCode(), FileContentType.IMAGE))
              .orElseThrow(
                  () -> new ResourceNotFoundException("No Folder found for path : " + folder));

      List<String> fileNames = null; // add files since they are bundled with images

      // images from CMS
      List<ContentImage> contentImages =
          imageNames.stream()
              .map(name -> convertToContentImage(name, store))
              .collect(Collectors.toList());

      ContentFolder contentFolder = new ContentFolder();
      contentFolder.setPath(folder);
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
    return new StringBuilder()
        .append(imageUtils.getContextPath())
        .append(imageUtils.buildStaticImageUtils(store, file))
        .toString();
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

	@Override
	public List<ReadableContentPage> getContentPage(MerchantStore store, Language language) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");

    try {
      return contentService.listByType(ContentType.PAGE, store, language)
          .stream()
          .filter(Content::isVisible)
          .map(content -> convertContentToReadableContentPage(store, language, content))
          .collect(Collectors.toList());
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Error while getting content " + e.getMessage(), e);
    }
	}

  private ReadableContentPage convertContentToReadableContentPage(MerchantStore store,
      Language language, Content content) {
    ReadableContentPage page = new ReadableContentPage();
    Optional<ContentDescription> contentDescription = findAppropriateContentDescription(content.getDescriptions(), language);

    if (contentDescription.isPresent()) {
      page.setName(contentDescription.get().getSeUrl());
      page.setPageContent(contentDescription.get().getDescription());
    }
    page.setDisplayedInMenu(content.isLinkToMenu());
    page.setContentType(ContentType.PAGE.name());
    page.setPath(fileUtils.buildStaticFilePath(store, page.getName()));
    return page;
  }

  @Override
	public ReadableContentPage getContentPage(String code, MerchantStore store, Language language) {

		Validate.notNull(code, "Content code cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");

    try {
      Content content =
          Optional.ofNullable(contentService.getByCode(code, store, language))
              .orElseThrow(() -> new ResourceNotFoundException("No page found : " + code));

      ReadableContentPage page = new ReadableContentPage();
      Optional<ContentDescription> appropriateContentDescription =
          findAppropriateContentDescription(content.getDescriptions(), language);
      if (appropriateContentDescription.isPresent()) {
        page.setName(appropriateContentDescription.get().getSeUrl());
        page.setPageContent(appropriateContentDescription.get().getDescription());
      }
      return page;
    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Error while getting page " + e.getMessage(), e);
    }
	}

	@Override
	public List<ReadableContentBox> getContentBoxes(ContentType type, String codePrefix, MerchantStore store, Language language) {

		Validate.notNull(codePrefix, "content code prefix cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");

    return contentService.getByCodeLike(type, codePrefix, store, language)
        .stream()
        .map(content -> convertContentToReadableContentBox(store, language, content))
        .collect(Collectors.toList());
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
    if (type.equals("image")) {
      fileType = FileContentType.IMAGE;
    }
    return fileType;
  }

  private ReadableContentBox convertContentToReadableContentBox(MerchantStore store,
      Language language, Content content) {
    ReadableContentBox box = new ReadableContentBox();
    Optional<ContentDescription> contentDescription = findAppropriateContentDescription(content.getDescriptions(), language);
    if (contentDescription.isPresent()) {
      box.setName(contentDescription.get().getName());
      box.setBoxContent(contentDescription.get().getDescription());
    }
    String staticImageFilePath = imageUtils.buildStaticImageUtils(store, content.getCode() + ".jpg");
    box.setImage(staticImageFilePath);
    return box;
  }

  private Optional<ContentDescription> findAppropriateContentDescription(List<ContentDescription> contentDescriptions, Language language) {
    return contentDescriptions
        .stream()
        .filter(description -> description.getLanguage().getCode().equals(language.getCode()))
        .findFirst();
  }

  @Override
  public ReadableContentBox getContentBox(String code, MerchantStore store, Language language) {
    Validate.notNull(code, "Content code cannot be null");
    Validate.notNull(store, "MerchantStore cannot be null");
    Validate.notNull(language, "Language cannot be null");

    try {
      Content content =
          Optional.ofNullable(contentService.getByCode(code, store, language))
              .orElseThrow(() -> new ResourceNotFoundException("No box found : " + code));

      Optional<ContentDescription> contentDescription =
          findAppropriateContentDescription(content.getDescriptions(), language);

      ReadableContentBox box = new ReadableContentBox();
      if (contentDescription.isPresent()) {
        box.setName(contentDescription.get().getSeUrl());
        box.setBoxContent(contentDescription.get().getDescription());
      }
      return box;
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(e);
    }
  }
}
