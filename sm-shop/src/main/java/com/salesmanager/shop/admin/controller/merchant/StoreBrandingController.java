package com.salesmanager.shop.admin.controller.merchant;


import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.currency.CurrencyService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;

@Controller
public class StoreBrandingController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StoreBrandingController.class);
	
	
	@Inject
	MerchantStoreService merchantStoreService;
	
	@Inject
	CountryService countryService;
	
	@Inject
	ZoneService zoneService;
	
	@Inject
	LanguageService languageService;
	
	@Inject
	CurrencyService currencyService;
	
	@Inject
	private ContentService contentService;
	

	@Resource(name="templates")
	List<String> templates;
	
	@PreAuthorize("hasRole('STORE')")
	@RequestMapping(value="/admin/store/storeBranding.html", method=RequestMethod.GET)
	public String displayStoreBranding(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		//display templates
		model.addAttribute("templates", templates);
		
		model.addAttribute("store", store);
		

		
		return "admin-store-branding";
	}
	
	/**
	 * https://spring.io/guides/gs/uploading-files/
	 * @param contentImages
	 * @param result
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('STORE')")
	@RequestMapping(value="/admin/store/saveBranding.html", method=RequestMethod.POST)
	public String saveStoreBranding(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		model.addAttribute("templates", templates);
		
		
		model.addAttribute("store", store);

		if(file!=null) {

			String imageName = file.getOriginalFilename();
			InputStream inputStream = file.getInputStream();
			String mimeType = file.getContentType();
			
            InputContentFile cmsContentImage = new InputContentFile();
            cmsContentImage.setFileName(imageName);
            cmsContentImage.setMimeType(mimeType);
            cmsContentImage.setFile( inputStream );
            contentService.addLogo(store.getCode(), cmsContentImage);
			
            //Update store
            store.setStoreLogo(imageName);
            merchantStoreService.update(store);
            request.getSession().setAttribute(Constants.ADMIN_STORE, store);

		} else {
			model.addAttribute("error","error");
			return "admin-store-branding";
		}

		model.addAttribute("success","success");
		return "admin-store-branding";
	}
	
	@PreAuthorize("hasRole('STORE')")
	@RequestMapping(value="/admin/store/saveTemplate.html", method=RequestMethod.POST)
	public String saveTemplate(@ModelAttribute(value="store") final MerchantStore store, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);

		MerchantStore sessionstore = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		sessionstore.setStoreTemplate(store.getStoreTemplate());
		
		merchantStoreService.saveOrUpdate(sessionstore);
		
		request.setAttribute(Constants.ADMIN_STORE, sessionstore);		
		
		//display templates
		model.addAttribute("templates", templates);

		model.addAttribute("success","success");
		model.addAttribute("store", sessionstore);

		return "admin-store-branding";
	}
	
	@PreAuthorize("hasRole('STORE')")
	@RequestMapping(value="/admin/store/removeImage.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> removeImage(HttpServletRequest request, HttpServletResponse response, Locale locale) {

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		AjaxResponse resp = new AjaxResponse();

		try {
			

			
			contentService.removeFile(store.getCode(), FileContentType.LOGO, store.getStoreLogo());
			
			store.setStoreLogo(null);
			merchantStoreService.update(store);
		
		
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
		activeMenus.put("store", "store");
		activeMenus.put("storeBranding", "storeBranding");

		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("store");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}

}
