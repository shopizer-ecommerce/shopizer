package com.salesmanager.shop.store.api.v1.content;

import static com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;

import com.salesmanager.shop.store.api.exception.RestApiException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.content.ContentFile;
import com.salesmanager.shop.model.content.ContentFolder;
import com.salesmanager.shop.model.content.ContentName;
import com.salesmanager.shop.model.content.ReadableContentBox;
import com.salesmanager.shop.model.content.ReadableContentPage;
import com.salesmanager.shop.store.controller.content.facade.ContentFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
public class ContentApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(ContentApi.class);

  @Inject
	private ContentFacade contentFacade;
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	@Inject
	private ContentService contentService;

	@GetMapping("/content/pages")
	@ApiOperation(httpMethod = "GET", value = "Get page names created for a given MerchantStore", notes = "", produces = "application/json", response = List.class)
	public List<ReadableContentPage> getContentPages(@RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
	    HttpServletRequest request) {

			MerchantStore merchantStore = storeFacade.get(storeCode);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);
      return contentFacade.getContentPage(merchantStore, language);
	}

  @GetMapping("/content/summary")
  @ApiOperation(
      httpMethod = "GET",
      value = "Get pages summary created for a given MerchantStore",
      notes = "",
      produces = "application/json",
      response = List.class)
  public List<ReadableContentBox> pagesSummary(
      @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
      HttpServletRequest request) {

    MerchantStore merchantStore = storeFacade.get(storeCode);
    Language language = languageUtils.getRESTLanguage(request, merchantStore);
    return contentFacade.getContentBoxes(ContentType.BOX, "summary_", merchantStore, language);
  }

  @GetMapping("/content/pages/{code}")
  @ApiOperation(
      httpMethod = "GET",
      value = "Get page content by code for a given MerchantStore",
      notes = "",
      produces = "application/json",
      response = List.class)
  public ReadableContentPage page(
      @RequestParam("code") String code,
      @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
      HttpServletRequest request) {
			MerchantStore merchantStore = storeFacade.get(storeCode);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);
      return contentFacade.getContentPage(code, merchantStore, language);
	}

  @GetMapping("/content/boxes/{code}")
  @ApiOperation(
      httpMethod = "GET",
      value = "Get box content by code for a code and a given MerchantStore",
      notes = "",
      produces = "application/json",
      response = List.class)
  public ReadableContentBox box(
      @PathVariable("code") String code,
      @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
      HttpServletRequest request) {

    MerchantStore merchantStore = storeFacade.get(storeCode);
    Language language = languageUtils.getRESTLanguage(request, merchantStore);
    return contentFacade.getContentBox(code, merchantStore, language);
	}

  @GetMapping("/content/folder")
  public ContentFolder folder(
      @RequestParam(value = "path", required = false) String path,
      @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
      HttpServletRequest request,
      HttpServletResponse response)
      throws Exception {

			MerchantStore merchantStore = storeFacade.get(storeCode);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);

      String decodedPath = decodeContentPath(path);
			ContentFolder folder = contentFacade.getContentFolder(decodedPath, merchantStore);
			return folder;
	}

  private String decodeContentPath(String path) throws UnsupportedEncodingException {
	  try{
      return StringUtils.isBlank(path) ? path : URLDecoder.decode(path,"UTF-8");
    } catch (UnsupportedEncodingException e) {
	    throw new RestApiException(e);
    }

  }

  /**
   * Need type, name and entity
   *
   * @param file
   * @param storeCode
   * @param request
   * @throws Exception
   */
  @PostMapping("/private/content")
  @ResponseStatus(HttpStatus.CREATED)
  public HttpEntity<String> upload(
      @Valid ContentFile file,
      @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
      HttpServletRequest request) {

    MerchantStore merchantStore = storeFacade.get(storeCode);
    Language language = languageUtils.getRESTLanguage(request, merchantStore);

    String fileName = file.getName();

    contentFacade.addContentFile(file, merchantStore.getCode());
    String fileUrl = contentFacade.absolutePath(merchantStore, fileName);
    return new HttpEntity<String>(fileUrl);
  }

  /**
   * Deletes a files from CMS
   *
   * @param name
   * @param storeCode
   * @param request
   */
  @DeleteMapping("/private/content")
  public void delete(
      @Valid ContentName name,
      @RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
      HttpServletRequest request) {

    MerchantStore merchantStore = storeFacade.get(storeCode);
    Language language = languageUtils.getRESTLanguage(request, merchantStore);
    contentFacade.delete(merchantStore, name.getName(), name.getContentType());
  }
}
