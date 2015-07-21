package com.salesmanager.web.admin.controller.content;

import java.io.ByteArrayInputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.salesmanager.core.business.content.model.FileContentType;
import com.salesmanager.core.business.content.model.InputContentFile;
import com.salesmanager.core.business.content.service.ContentService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.controller.ControllerConstants;
import com.salesmanager.web.admin.entity.content.ContentFiles;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.FilePathUtils;


@Controller
public class StaticContentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StaticContentController.class);
	
	@Autowired
	private ContentService contentService;
	

	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value={"/admin/content/static/contentFiles.html","/admin/content/static/contentManagement.html"}, method=RequestMethod.GET)
	public String getContentImages(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.setMenu(model, request);
		return ControllerConstants.Tiles.ContentFiles.contentFiles;
		
	}

	
	/**
	 * Display files in a List grid
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/static/page.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String pageStaticContent(HttpServletRequest request, HttpServletResponse response) {
		AjaxResponse resp = new AjaxResponse();

		try {
			

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			List<String> fileNames = contentService.getContentFilesNames(store.getCode(), FileContentType.STATIC_FILE);
			
			Map<String,String> configurations = (Map<String, String>)request.getSession().getAttribute(Constants.STORE_CONFIGURATION);
			String scheme = Constants.HTTP_SCHEME;
			if(configurations!=null) {
				scheme = (String)configurations.get("scheme");
			}
			

			StringBuilder storePath = new StringBuilder();
			storePath.append(scheme).append("://")
			.append(store.getDomainName())
			.append(request.getContextPath());
			

			if(fileNames!=null) {

				for(String name : fileNames) {
					
					String mimeType = URLConnection.getFileNameMap().getContentTypeFor(name);
					
					StringBuilder filePath = new StringBuilder();

					filePath.append(storePath.toString()).append(FilePathUtils.buildStaticFilePath(store,name));

					
					
					@SuppressWarnings("rawtypes")
					Map entry = new HashMap();
					entry.put("name", name);
					entry.put("path", filePath.toString());
					entry.put("mimeType", mimeType);
					resp.addDataEntry(entry);

				}
			
			}
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);

		} catch (Exception e) {
			LOGGER.error("Error while paging content images", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	

	
	/**
	 * Method responsible for adding content files to underlying Infinispan cache.
	 * It will add given content file(s) for given merchant store in the cache.
	 * Following steps will be performed in order to add files
	 * <pre>
	 * 1. Validate form data
	 * 2. Get Merchant Store based on merchant Id.
	 * 3. Call {@link InputContentFile} to add file(s).
	 * </pre>
	 * 
	 * @param contentImages
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/static/saveFiles.html", method=RequestMethod.POST)
	public String saveFiles(@ModelAttribute(value="contentFiles") @Valid final ContentFiles contentFiles, final BindingResult bindingResult,final Model model, final HttpServletRequest request) throws Exception{
	    
		this.setMenu(model, request);
	    if (bindingResult.hasErrors()) {
	        LOGGER.info( "Found {} Validation errors", bindingResult.getErrorCount());
	        return ControllerConstants.Tiles.ContentFiles.contentFiles;
	       
        }
	    final List<InputContentFile> contentFilesList=new ArrayList<InputContentFile>();
        final MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
        if(CollectionUtils.isNotEmpty( contentFiles.getFile() )){
            LOGGER.info("Saving {} content files for merchant {}",contentFiles.getFile().size(),store.getId());
            for(final MultipartFile multipartFile:contentFiles.getFile()){
                if(!multipartFile.isEmpty()){
                    ByteArrayInputStream inputStream = new ByteArrayInputStream( multipartFile.getBytes() );
                    InputContentFile cmsContentImage = new InputContentFile();
                    cmsContentImage.setFileName(multipartFile.getOriginalFilename() );
                    cmsContentImage.setFileContentType( FileContentType.STATIC_FILE );
                    cmsContentImage.setFile( inputStream );
                    contentFilesList.add( cmsContentImage);
                }
            }
            
            if(CollectionUtils.isNotEmpty( contentFilesList )){
            	contentService.addContentFiles( store.getCode(), contentFilesList );
            }
            else{
                // show error message on UI
            }
        }
        
        return ControllerConstants.Tiles.ContentFiles.contentFiles;
	}
	
	
	/**
	 * Removes a static file from the CMS
	 * @param request
	 * @param response
	 * @param locale
	 * @return
	 */
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/static/removeFile.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String removeFile(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String fileName = request.getParameter("name");

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		AjaxResponse resp = new AjaxResponse();

		
		try {
			

			
			contentService.removeFile(store.getCode(), FileContentType.STATIC_FILE, fileName);

			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting product", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	
	

	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("content", "content");
		activeMenus.put("content-files", "content-files");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("content");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}

}
