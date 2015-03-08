package com.salesmanager.web.admin.controller.merchant;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salesmanager.core.business.content.model.Content;
import com.salesmanager.core.business.content.model.ContentDescription;
import com.salesmanager.core.business.content.model.ContentType;
import com.salesmanager.core.business.content.service.ContentService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.merchant.service.MerchantStoreService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.web.admin.entity.merchant.StoreLanding;
import com.salesmanager.web.admin.entity.merchant.StoreLandingDescription;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;

@Controller
public class StoreLandingController {
	
	@Autowired
	MerchantStoreService merchantStoreService;

	@Autowired
	LanguageService languageService;
	
	@Autowired
	ContentService contentService;
	
	@PreAuthorize("hasRole('STORE')")
	@RequestMapping(value="/admin/store/storeLanding.html", method=RequestMethod.GET)
	public String displayStoreLanding(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		List<Language> languages = store.getLanguages();
		
		Content content = contentService.getByCode("LANDING_PAGE", store);
		StoreLanding landing = new StoreLanding();
		
		List<StoreLandingDescription> descriptions = new ArrayList<StoreLandingDescription>();
		
		
		for(Language l : languages) {
			
			StoreLandingDescription landingDescription = null;
			if(content!=null) {
				for(ContentDescription desc : content.getDescriptions()) {
					if(desc.getLanguage().getCode().equals(l.getCode())) {
						landingDescription = new StoreLandingDescription();
						landingDescription.setDescription(desc.getMetatagDescription());
						landingDescription.setHomePageContent(desc.getDescription());
						landingDescription.setKeywords(desc.getMetatagKeywords());
						landingDescription.setTitle(desc.getName());//name is a not empty
						landingDescription.setLanguage(desc.getLanguage());
						break;
					}
				}
			}
			
			if(landingDescription==null) {
				landingDescription = new StoreLandingDescription();
				landingDescription.setLanguage(l);
			}
			

			
			descriptions.add(landingDescription);
		}
		
		landing.setDescriptions(descriptions);

		
		model.addAttribute("store", store);
		model.addAttribute("storeLanding", landing);

		
		return "admin-store-landing";
	}
	
	@PreAuthorize("hasRole('STORE')")
	@RequestMapping(value="/admin/store/saveLanding.html", method=RequestMethod.POST)
	public String saveStoreLanding(@Valid @ModelAttribute("storeLanding") StoreLanding storeLanding, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		
		if (result.hasErrors()) {
			return "admin-store-landing";
		}
		
		//get original store
		Content content = contentService.getByCode("LANDING_PAGE", store);
		
		if(content==null) {
			content = new Content();
			content.setVisible(true);
			content.setContentType(ContentType.SECTION);
			content.setCode("LANDING_PAGE");
			content.setMerchantStore(store);
		}
		

		//List<Language> languages = store.getLanguages();
			
		Map<String,Language> langs = languageService.getLanguagesMap();
		
		
		
/*		for(Language l : languages) {
			
			StoreLandingDescription landingDescription = null;
			for(ContentDescription desc : content.getDescriptions()) {
					if(desc.getLanguage().getCode().equals(l.getCode())) {
						landingDescription = new StoreLandingDescription();
						landingDescription.setDescription(desc.getMetatagDescription());
						landingDescription.setHomePageContent(desc.getDescription());
						landingDescription.setKeywords(desc.getMetatagKeywords());
						landingDescription.setTitle(desc.getName());//name is a not empty
						landingDescription.setLanguage(desc.getLanguage());
					}
			}
		
			
			if(landingDescription==null) {
				landingDescription = new StoreLandingDescription();
				landingDescription.setLanguage(l);
			}
			

			
			descriptions.add(landingDescription);
		}
		
		landing.setDescriptions(descriptions);*/
		
		
		
			

		List<StoreLandingDescription> descriptions = storeLanding.getDescriptions();
		List<ContentDescription> contentDescriptions = new ArrayList<ContentDescription>();
		if(descriptions!=null) {
				
				for(StoreLandingDescription description : descriptions) {
					
					String code = description.getLanguage().getCode();
					Language l = langs.get(code);
					
					ContentDescription contentDescription = null;
					if(content.getDescriptions()!=null && content.getDescriptions().size()>0) {
						
						for(ContentDescription desc : content.getDescriptions()) {
							
							if(desc.getLanguage().getCode().equals(l.getCode())) {
								contentDescription = desc;
								desc.setMetatagDescription(description.getDescription());
								desc.setName(description.getTitle());
								desc.setDescription(description.getHomePageContent());
								desc.setMetatagKeywords(description.getKeywords());
								
								
							}

						}
					}
					
					if(contentDescription==null) {
						
						
						contentDescription = new ContentDescription();
						contentDescription.setContent(content);
						contentDescription.setLanguage(l);
						contentDescription.setMetatagDescription(description.getDescription());
						contentDescription.setName(description.getTitle());
						contentDescription.setDescription(description.getHomePageContent());
						contentDescription.setMetatagKeywords(description.getKeywords());

					}
					
					contentDescriptions.add(contentDescription);



				}
				
				content.setDescriptions(contentDescriptions);
				
			}


		
		contentService.saveOrUpdate(content);

		model.addAttribute("success","success");

		return "admin-store-landing";
	}
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {

		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("store", "store");
		activeMenus.put("storeLanding", "storeLanding");

		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("store");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}

}
