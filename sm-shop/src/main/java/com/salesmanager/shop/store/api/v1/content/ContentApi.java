package com.salesmanager.shop.store.api.v1.content;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
import org.springframework.stereotype.Controller;
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

@Controller
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

	@RequestMapping( value={"/content/pages"}, method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod = "GET", value = "Get page names created for a given MerchantStore", notes = "", produces = "application/json", response = List.class)
	@ResponseBody
	public List<ReadableContentPage>  pages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);

			
			List<ReadableContentPage> names = contentFacade.pages(merchantStore, language);

			
			if(names == null){
				response.sendError(404, "No content found : " + names);
			}
			
			return names;
			
			
		} catch (Exception e) {
			LOGGER.error("Error while getting content",e);
			try {
				response.sendError(503, "Error while getting content " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
	}
	
	@RequestMapping( value={"/content/summary"}, method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod = "GET", value = "Get pages summary created for a given MerchantStore", notes = "", produces = "application/json", response = List.class)
	@ResponseBody
	public List<ReadableContentBox>  pagesSummary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);

			
			List<ReadableContentBox> boxes = contentFacade.boxes(ContentType.BOX, "summary_", merchantStore, language);
	
			
			if(boxes == null){
				response.sendError(404, "No content summary found");
			}
			
			return boxes;
			
			
		} catch (Exception e) {
			LOGGER.error("Error while getting content",e);
			try {
				response.sendError(503, "Error while getting content summary " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
	}
	
	
	@RequestMapping( value={"/content/pages/{code}"}, method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod = "GET", value = "Get page content by code for a given MerchantStore", notes = "", produces = "application/json", response = List.class)
	@ResponseBody
	public ReadableContentPage page(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);

			
			ReadableContentPage page = contentFacade.page(code, merchantStore, language);

			
			if(page == null){
				response.sendError(404, "No page found : " + code);
			}
			
			return page;
			
			
		} catch (Exception e) {
			LOGGER.error("Error while getting page",e);
			try {
				response.sendError(503, "Error while getting page " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
	}
	
	@RequestMapping( value={"/content/boxes/{code}"}, method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod = "GET", value = "Get box content by code for a code and a given MerchantStore", notes = "", produces = "application/json", response = List.class)
	@ResponseBody
	public ReadableContentBox box(@PathVariable("code") String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);

			
			ReadableContentBox box = contentFacade.box(code, merchantStore, language);

			
			if(box == null){
				response.sendError(404, "No box found : " + code);
			}
			
			return box;
			
			
		} catch (Exception e) {
			LOGGER.error("Error while getting box",e);
			try {
				response.sendError(503, "Error while getting box " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
	}
	
	@RequestMapping( value={"/content/folder"}, method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ContentFolder folder(@RequestParam(value = "path", required=false) String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);
			
			if(!StringUtils.isBlank(path)) {
				path = URLDecoder.decode(path,"UTF-8");
			}
			
			ContentFolder folder = contentFacade.getContentFolder(path, merchantStore);

			
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
