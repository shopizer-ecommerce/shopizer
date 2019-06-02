package com.salesmanager.shop.store.api.v1.content;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.content.ContentFile;
import com.salesmanager.shop.model.content.ContentFolder;
import com.salesmanager.shop.model.content.ContentName;
import com.salesmanager.shop.model.content.PersistableContentPage;
import com.salesmanager.shop.model.content.ReadableContentBox;
import com.salesmanager.shop.model.content.ReadableContentPage;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.RestApiException;
import com.salesmanager.shop.store.controller.content.facade.ContentFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/v1")
public class ContentApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(ContentApi.class);

  @Inject
  private ContentFacade contentFacade;

  @Inject
  private StoreFacade storeFacade;

  @Inject
  private LanguageUtils languageUtils;

  @GetMapping(value = "/content/pages", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get page names created for a given MerchantStore",
      notes = "", produces = "application/json", response = List.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public List<ReadableContentPage> getContentPages(
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    return contentFacade.getContentPage(merchantStore, language);
  }

  @GetMapping(value = "/content/summary", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get pages summary created for a given MerchantStore",
      notes = "", produces = "application/json", response = List.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public List<ReadableContentBox> pagesSummary(
      @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    return contentFacade.getContentBoxes(ContentType.BOX, "summary_", merchantStore, language);
  }

  @GetMapping(value = "/{store}/content/pages/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get page content by code for a given MerchantStore",
      notes = "", produces = "application/json", response = ReadableContentPage.class)
  public ReadableContentPage page(@PathVariable("store") String store,
      @PathVariable("code") String code, HttpServletRequest request) {

    MerchantStore merchantStore = storeFacade.get(store);
    Language language = languageUtils.getRESTLanguage(request, merchantStore);
    ReadableContentPage page = null;
    try {
      page = contentFacade.getContentPage(code, merchantStore, language);
    } catch (ResourceNotFoundException e) {
      LOGGER.debug("Resource not found [" + code + "] for store [" + store + "]");
    }
    return page;
  }

  @GetMapping(value = "/content/boxes/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET",
      value = "Get box content by code for a code and a given MerchantStore", notes = "",
      produces = "application/json", response = List.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableContentBox getBoxByCode(@PathVariable("code") String code,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {
    return contentFacade.getContentBox(code, merchantStore, language);
  }

  @GetMapping(value = "/content/folder", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ContentFolder folder(@RequestParam(value = "path", required = false) String path,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) throws Exception {
    String decodedPath = decodeContentPath(path);
    return contentFacade.getContentFolder(decodedPath, merchantStore);
  }


  @GetMapping(value = "/{code}/content/images", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get store content images", notes = "",
      response = ContentFolder.class)
  public ContentFolder images(@PathVariable String code,
      @RequestParam(value = "path", required = false) String path, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    MerchantStore merchantStore = storeFacade.get(code);
    Language language = languageUtils.getRESTLanguage(request, merchantStore);

    String decodedPath = decodeContentPath(path);
    ContentFolder folder = contentFacade.getContentFolder(decodedPath, merchantStore);
    return folder;
  }

  @GetMapping(value = "/{store}/content/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get store content based on content code", notes = "",
      response = ContentFolder.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public ReadableContentBox content(@PathVariable String code,
      @RequestParam(value = "path", required = false) String path, HttpServletRequest request,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {
    return contentFacade.getContentBox(code, merchantStore, language);
  }

  private String decodeContentPath(String path) throws UnsupportedEncodingException {
    try {
      return StringUtils.isBlank(path) ? path : URLDecoder.decode(path, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new RestApiException(e);
    }

  }

  /**
   * Need type, name and entity
   *
   * @param file
   */
  @PostMapping(value = "/private/content")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public HttpEntity<String> upload(@RequestBody @Valid ContentFile file,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {
    String fileName = file.getName();
    contentFacade.addContentFile(file, merchantStore.getCode());
    String fileUrl = contentFacade.absolutePath(merchantStore, fileName);
    return new HttpEntity<String>(fileUrl);
  }


  @PostMapping(value = "/private/{code}/content/pages/{pageCode}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(httpMethod = "POST", value = "Update content page", notes = "",
      response = Void.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public void savePage(@RequestBody @Valid PersistableContentPage page,
      @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language,
      @PathVariable(name = "pageCode") String pageCode) {
    page.setCode(pageCode);
    contentFacade.saveContentPage(page, merchantStore, language);
  }


  /**
   * Deletes a files from CMS
   *
   * @param name
   */
  @DeleteMapping(value = "/private/content")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
      @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")})
  public void delete(@Valid ContentName name, @ApiIgnore MerchantStore merchantStore,
      @ApiIgnore Language language) {
    contentFacade.delete(merchantStore, name.getName(), name.getContentType());
  }
}
