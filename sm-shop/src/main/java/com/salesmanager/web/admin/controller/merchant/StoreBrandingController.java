package com.salesmanager.web.admin.controller.merchant;


import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import com.salesmanager.core.business.content.model.FileContentType;
import com.salesmanager.core.business.content.model.InputContentFile;
import com.salesmanager.core.business.content.service.ContentService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.merchant.service.MerchantStoreService;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.currency.service.CurrencyService;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.reference.zone.service.ZoneService;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.entity.content.ContentFiles;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;

@Controller
public class StoreBrandingController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StoreBrandingController.class);
	
	
	@Autowired
	MerchantStoreService merchantStoreService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	ZoneService zoneService;
	
	@Autowired
	LanguageService languageService;
	
	@Autowired
	CurrencyService currencyService;
	
	@Autowired
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
	
	@PreAuthorize("hasRole('STORE')")
	@RequestMapping(value="/admin/store/saveBranding.html", method=RequestMethod.POST)
	public String saveStoreBranding(@ModelAttribute(value="contentImages") @Valid final ContentFiles contentImages, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		
		if(contentImages.getFile()!=null && contentImages.getFile().size()>0) {

			String imageName = contentImages.getFile().get(0).getOriginalFilename();
            InputStream inputStream = contentImages.getFile().get(0).getInputStream();
            InputContentFile cmsContentImage = new InputContentFile();
            cmsContentImage.setFileName(imageName);
            cmsContentImage.setMimeType( contentImages.getFile().get(0).getContentType());
            cmsContentImage.setFile( inputStream );
            contentService.addLogo(store.getCode(), cmsContentImage);
			
            //Update store
            store.setStoreLogo(imageName);
            merchantStoreService.update(store);
            request.getSession().setAttribute(Constants.ADMIN_STORE, store);
  
		}
		
		//display templates
		model.addAttribute("templates", templates);
		
		model.addAttribute("success","success");
		model.addAttribute("store", store);

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
	@RequestMapping(value="/admin/store/removeImage.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String removeImage(HttpServletRequest request, HttpServletResponse response, Locale locale) {

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
		
		return returnString;
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
