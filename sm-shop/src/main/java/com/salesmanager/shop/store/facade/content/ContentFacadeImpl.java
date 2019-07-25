package com.salesmanager.shop.store.facade.content;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
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
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.content.ContentFile;
import com.salesmanager.shop.model.content.ContentFolder;
import com.salesmanager.shop.model.content.ContentImage;
import com.salesmanager.shop.model.content.ObjectContent;
import com.salesmanager.shop.model.content.PersistableContentPage;
import com.salesmanager.shop.model.content.ReadableContentBox;
import com.salesmanager.shop.model.content.ReadableContentPage;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.content.facade.ContentFacade;
import com.salesmanager.shop.utils.FilePathUtils;
import com.salesmanager.shop.utils.ImageFilePath;
import io.searchbox.strings.StringUtils;

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

  @Override
  public ContentFolder getContentFolder(String folder, MerchantStore store) throws Exception {
    try {
      List<String> imageNames = Optional
          .ofNullable(contentService.getContentFilesNames(store.getCode(), FileContentType.IMAGE))
          .orElseThrow(() -> new ResourceNotFoundException("No Folder found for path : " + folder));

      // images from CMS
      List<ContentImage> contentImages = imageNames.stream()
          .map(name -> convertToContentImage(name, store)).collect(Collectors.toList());

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

  @Override
  public List<ReadableContentPage> getContentPage(MerchantStore store, Language language) {
    Validate.notNull(store, "MerchantStore cannot be null");
    Validate.notNull(language, "Language cannot be null");

    try {
      return contentService.listByType(ContentType.PAGE, store, language).stream()
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
    Optional<ContentDescription> contentDescription =
        findAppropriateContentDescription(content.getDescriptions(), language);

    if (contentDescription.isPresent()) {
      page.setName(contentDescription.get().getName());
      page.setPageContent(contentDescription.get().getDescription());
    }
    page.setSlug(contentDescription.get().getSeUrl());
    page.setDisplayedInMenu(content.isLinkToMenu());
    page.setTitle(contentDescription.get().getTitle());
    page.setMetaDetails(contentDescription.get().getMetatagDescription());
    page.setContentType(ContentType.PAGE.name());
    page.setCode(content.getCode());
    page.setPath(fileUtils.buildStaticFilePath(store, contentDescription.get().getSeUrl()));
    return page;
  }

  private Content convertContentPageToContent(MerchantStore store, Language language,
      PersistableContentPage content) {
    Content contentModel = new Content();

    ContentDescription contentDescription = createContentDescription(store, content, language);
    contentDescription.setContent(contentModel);

    contentModel.setCode(content.getCode());
    contentModel.setContentType(ContentType.PAGE);
    contentModel.setMerchantStore(store);
    contentModel.setVisible(true);

    return contentModel;
  }

  private Content convertContentPageToContent(MerchantStore store, Language language,
      Content content, PersistableContentPage contentPage) {

    ContentDescription contentDescription = createContentDescription(store, contentPage, language);
    setContentDescriptionToContentModel(content,contentDescription,language);
 
    contentDescription.setContent(content);

    content.setCode(content.getCode());
    content.setContentType(ContentType.PAGE);
    content.setMerchantStore(store);
    content.setVisible(true);

    return content;
  }


  private ContentDescription createContentDescription(MerchantStore store, ObjectContent content,
      Language language) {
      
    ContentDescription contentDescription = new ContentDescription();
    contentDescription.setLanguage(language);
    contentDescription.setMetatagDescription(content.getMetaDetails());
    contentDescription.setTitle(content.getTitle());
    contentDescription.setName(content.getName());
    contentDescription.setDescription(content.getPageContent());
    contentDescription.setMetatagTitle(content.getTitle());

    return contentDescription;
  }

  private void setContentDescriptionToContentModel(Content content,
      ContentDescription contentDescription, Language language) {

    Optional<ContentDescription> contentDescriptionModel =
        findAppropriateContentDescription(content.getDescriptions(), language);

    if (contentDescriptionModel.isPresent()) {
      contentDescriptionModel.get()
          .setMetatagDescription(contentDescription.getMetatagDescription());
      contentDescriptionModel.get().setTitle(contentDescription.getTitle());
      contentDescriptionModel.get().setName(contentDescription.getName());
      contentDescriptionModel.get().setDescription(contentDescription.getDescription());
      contentDescriptionModel.get().setMetatagTitle(contentDescription.getTitle());
    } else {
      content.getDescriptions().add(contentDescription);
    }

  }

  @Override
  public ReadableContentPage getContentPage(String code, MerchantStore store, Language language) {

    Validate.notNull(code, "Content code cannot be null");
    Validate.notNull(store, "MerchantStore cannot be null");
    Validate.notNull(language, "Language cannot be null");

    try {
      Content content = Optional.ofNullable(contentService.getByCode(code, store, language))
          .orElseThrow(() -> new ResourceNotFoundException("No page found : " + code));

      return convertContentToReadableContentPage(store, language, content);

    } catch (ServiceException e) {
      throw new ServiceRuntimeException("Error while getting page " + e.getMessage(), e);
    }
  }

  @Override
  public List<ReadableContentBox> getContentBoxes(ContentType type, String codePrefix,
      MerchantStore store, Language language) {

    Validate.notNull(codePrefix, "content code prefix cannot be null");
    Validate.notNull(store, "MerchantStore cannot be null");
    Validate.notNull(language, "Language cannot be null");

    return contentService.getByCodeLike(type, codePrefix, store, language).stream()
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
    Optional<ContentDescription> contentDescription =
        findAppropriateContentDescription(content.getDescriptions(), language);
    if (contentDescription.isPresent()) {
      box.setName(contentDescription.get().getName());
      box.setBoxContent(contentDescription.get().getDescription());
    }
    String staticImageFilePath =
        imageUtils.buildStaticImageUtils(store, content.getCode() + ".jpg");
    box.setImage(staticImageFilePath);
    return box;
  }

  private Optional<ContentDescription> findAppropriateContentDescription(
      List<ContentDescription> contentDescriptions, Language language) {
    return contentDescriptions.stream()
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
              .orElseThrow(
                  () ->
                      new ResourceNotFoundException(
                          "Resource not found [" + code + "] for store [" + store.getCode() + "]"));

      Optional<ContentDescription> contentDescription =
          findAppropriateContentDescription(content.getDescriptions(), language);

      ReadableContentBox box = new ReadableContentBox();
      if (contentDescription.isPresent()) {
        box.setName(contentDescription.get().getSeUrl());
        box.setBoxContent("<![CDATA[" + contentDescription.get().getDescription().replaceAll("\r\n", "").replaceAll("\t", "") + "]]>");
      }
      return box;
    } catch (ServiceException e) {
      throw new ServiceRuntimeException(e);
    }
  }

  @Override
  public void saveContentPage(PersistableContentPage page, MerchantStore merchantStore,
      Language language) {
    Validate.notNull(page);
    Validate.notNull(merchantStore);
    Validate.notNull(page.getCode());

    try {
      // check if exists
      Content content = contentService.getByCode(page.getCode(), merchantStore);
      if (content != null) {
        content = convertContentPageToContent(merchantStore, language, content, page);
      } else {
        content = convertContentPageToContent(merchantStore, language, page);
      }
      contentService.saveOrUpdate(content);
    } catch (Exception e) {
      throw new ServiceRuntimeException(e);
    }

  }

  @Override
  public void addContentFiles(List<ContentFile> files, String merchantStoreCode) {
    for(ContentFile file : files) {
      addContentFile(file, merchantStoreCode);
    }
    
  }
}
