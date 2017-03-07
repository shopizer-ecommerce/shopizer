package com.salesmanager.shop.admin.controller.content;

import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.admin.model.content.ContentFiles;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.utils.ImageFilePath;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * Manage static content type image
 * - Add images
 * - Remove images
 * @author Carl Samson
 *
 */
@Controller
public class ContentImageController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentImageController.class);
	
	@Inject
	private ContentService contentService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;
	
	/**
	 * Entry point for the file browser used from the javascript
	 * content editor
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value={"/admin/content/fileBrowser.html"}, method=RequestMethod.GET)
	public String displayFileBrowser(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {


		return ControllerConstants.Tiles.ContentImages.fileBrowser;
		
	}
	
	
	
	/**
	 * Get images for a given merchant store
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value={"/admin/content/contentImages.html","/admin/content/contentManagement.html"}, method=RequestMethod.GET)
	public String getContentImages(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.setMenu(model, request);
		return ControllerConstants.Tiles.ContentImages.contentImages;
		
	}
	
	
	@SuppressWarnings({ "unchecked"})
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/images/paging.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> pageImages(HttpServletRequest request, HttpServletResponse response) {
		AjaxResponse resp = new AjaxResponse();

		try {
			

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			List<String> imageNames = contentService.getContentFilesNames(store.getCode(),FileContentType.IMAGE);
			
			if(imageNames!=null) {

				for(String name : imageNames) {

					@SuppressWarnings("rawtypes")
					Map entry = new HashMap();
					entry.put("picture", new StringBuilder().append(request.getContextPath()).append(imageUtils.buildStaticImageUtils(store, name)).toString());
					
					entry.put("name", name);
					entry.put("id", name);
					resp.addDataEntry(entry);

				}
			
			}
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);

		} catch (Exception e) {
			LOGGER.error("Error while paging content images", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	/**
	 * Controller methods which allow Admin to add content images to underlying
	 * Infinispan cache.
	 * @param model model object
	 * @param request http request object
	 * @param response http response object
	 * @return view allowing user to add content images
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/createContentImages.html", method=RequestMethod.GET)
    public String displayContentImagesCreate(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
      
	    return ControllerConstants.Tiles.ContentImages.addContentImages;

    }
	
	/**
	 * Method responsible for adding content images to underlying Infinispan cache.
	 * It will add given content image(s) for given merchant store in the cache.
	 * Following steps will be performed in order to add images
	 * <pre>
	 * 1. Validate form data
	 * 2. Get Merchant Store based on merchant Id.
	 * 3. Call {@link InputContentFile} to add image(s).
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
	@RequestMapping(value="/admin/content/saveContentImages.html", method=RequestMethod.POST)
	public String saveContentImages(@ModelAttribute(value="contentFiles") @Valid final ContentFiles contentImages, final BindingResult bindingResult,final Model model, final HttpServletRequest request) throws Exception{
	    
		this.setMenu(model, request);
	    if (bindingResult.hasErrors()) {
	        LOGGER.info( "Found {} Validation errors", bindingResult.getErrorCount());
	       return ControllerConstants.Tiles.ContentImages.addContentImages;
	       
        }
	    final List<InputContentFile> contentImagesList=new ArrayList<InputContentFile>();
        final MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
        if(CollectionUtils.isNotEmpty( contentImages.getFile() )){
            LOGGER.info("Saving {} content images for merchant {}",contentImages.getFile().size(),store.getId());
            for(final MultipartFile multipartFile:contentImages.getFile()){
                if(!multipartFile.isEmpty()){
                    ByteArrayInputStream inputStream = new ByteArrayInputStream( multipartFile.getBytes() );
                    InputContentFile cmsContentImage = new InputContentFile();
                    cmsContentImage.setFileName(multipartFile.getOriginalFilename() );
                    cmsContentImage.setMimeType( multipartFile.getContentType() );
                    cmsContentImage.setFile( inputStream );
                    cmsContentImage.setFileContentType(FileContentType.IMAGE);
                    contentImagesList.add( cmsContentImage);
                }
            }
            
            if(CollectionUtils.isNotEmpty( contentImagesList )){
                contentService.addContentFiles( store.getCode(), contentImagesList );
            }
            else{
                // show error message on UI
            }
        }
       
        return ControllerConstants.Tiles.ContentImages.contentImages;
	}
	
	
	/**
	 * Removes a content image from the CMS
	 * @param request
	 * @param response
	 * @param locale
	 * @return
	 */
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/removeImage.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> removeImage(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String imageName = request.getParameter("name");

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		AjaxResponse resp = new AjaxResponse();

		
		try {
			

			
			contentService.removeFile(store.getCode(), FileContentType.IMAGE, imageName);

		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting product", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("content", "content");
		activeMenus.put("content-images", "content-images");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("content");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}

}
