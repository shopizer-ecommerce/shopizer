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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/v1")
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

		try {
			
			MerchantStore merchantStore = storeFacade.get(storeCode);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);

      String decodedPath = decodeContentPath(path);
			
			ContentFolder folder = contentFacade.getContentFolder(decodedPath, merchantStore);

			if(folder == null){
				response.sendError(404, "No Folder found for path : " + path);
			}
			
			return folder;
			
			
		} catch (Exception e) {
			LOGGER.error("Error while getting folder",e);
			try {
				response.sendError(503, "Error while getting folder " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
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
	 * @param requestEntity
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping( value={"/private/content"}, method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public HttpEntity<String> upload(@Valid @RequestBody ContentFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);
			

			byte[] payload = file.getFile();
			InputStream targetStream = new ByteArrayInputStream(payload);
		    
			String fileName = file.getName();
			
            String type = file.getContentType() .split("/")[0];
            FileContentType fileType = FileContentType.STATIC_FILE;
            if(type.equals("image")){
            	fileType = FileContentType.IMAGE;
            } 

            InputContentFile cmsContent = new InputContentFile();
            cmsContent.setFileName(fileName);
            cmsContent.setMimeType( file.getContentType() );
            cmsContent.setFile( targetStream );
            cmsContent.setFileContentType(fileType);

            contentService.addContentFile( merchantStore.getCode(), cmsContent );
            
            String fileUrl = contentFacade.absolutePath(merchantStore, fileName);
            HttpEntity<String> responseEntity = new HttpEntity<>(fileUrl);
            return responseEntity;
			
	
		} catch (Exception e) {
			LOGGER.error("Error while adding file folder",e);
			try {
				response.sendError(503, "Error while adding file " + e.getMessage());
			} catch (Exception ignore) {
			}
			
		}
		
		return null;

	}
	
	/**
	 * Deletes a files from CMS
	 * @param path
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value={"/private/content"}, method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Void> delete(@Valid @RequestBody ContentName name, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);
			

			contentFacade.delete(merchantStore, name.getName(), name.getContentType());


			return new ResponseEntity<Void>(HttpStatus.OK);
			
			
		} catch (Exception e) {
			LOGGER.error("Error while deleting file name ["+ name + "]",e);
			try {
				response.sendError(503, "Error while deleting file name ["+ name + "]" + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
	}

	


}
