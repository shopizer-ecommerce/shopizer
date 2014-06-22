package com.salesmanager.web.admin.controller.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.salesmanager.core.business.content.model.Content;
import com.salesmanager.core.business.content.model.ContentDescription;
import com.salesmanager.core.business.content.model.ContentType;
import com.salesmanager.core.business.content.service.ContentService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.web.admin.controller.ControllerConstants;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;

@Controller
public class ContentBoxesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentBoxesController.class);
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	LanguageService languageService;
	
	@ModelAttribute("boxPositions") 
    public Set<Map.Entry<String, String>> boxPositions() { 
        final Map<String, String> map = new HashMap<String, String>(); 

        map.put("LEFT", "LEFT");
        map.put("RIGHT", "RIGHT");


        return (map.entrySet()); 
    } 


	
	
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/boxes/list.html", method=RequestMethod.GET)
	public String listContentBoxes(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);

		model.addAttribute("boxes", true);
		return ControllerConstants.Tiles.Content.contentPages;
		
		
	}
	
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/boxes/create.html", method=RequestMethod.GET)
	public String createBox(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("boxes", true);
		setMenu(model,request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		Content content = new Content();
		content.setMerchantStore(store);
		content.setContentType(ContentType.BOX);
		
		
		List<Language> languages = store.getLanguages();
		
		
		for(Language l : languages) {
			
			ContentDescription description = new ContentDescription();
			description.setLanguage(l);
			content.getDescriptions().add(description);
		}
		
		//add positions
		List<String> positions = new ArrayList<String>();
		positions.add("LEFT");
		positions.add("RIGHT");
		
		model.addAttribute("positions",positions);
		model.addAttribute("content",content);
		

		return ControllerConstants.Tiles.Content.contentPagesDetails;
		
		
	}
	
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/boxes/details.html", method=RequestMethod.GET)
	public String getContentDetails(@RequestParam("id") Long id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("boxes", true);
		setMenu(model,request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		Content content = contentService.getById(id);
		

		List<String> positions = new ArrayList<String>();
		positions.add("LEFT");
		positions.add("RIGHT");
		
		model.addAttribute("positions",positions);
		
		if(content==null) {
			LOGGER.error("Content entity null for id " + id);
			return "redirect:/admin/content/boxes/listContent.html";
		}
		
		if(content.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			LOGGER.error("Content id " + id + " does not belong to merchant " + store.getId());
			return "redirect:/admin/content/boxes/listContent.html";
		}
		
		if(!content.getContentType().name().equals(ContentType.BOX.name())) {
			LOGGER.error("This controller does not handle content type " + content.getContentType().name());
			return "redirect:/admin/content/boxes/listContent.html";
		}
		
		List<Language> languages = store.getLanguages();
		
		List<ContentDescription> descriptions = new ArrayList<ContentDescription>();
		for(Language l : languages) {
			for(ContentDescription description : content.getDescriptions()) {
				if(description.getLanguage().getCode().equals(l.getCode())) {
					descriptions.add(description);
				}
			}
		}
		content.setDescriptions(descriptions);
		
		model.addAttribute("content",content);
		

		return ControllerConstants.Tiles.Content.contentPagesDetails;
		
		
	}
	


	
	
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/boxes/save.html", method=RequestMethod.POST)
	public String saveContent(@Valid @ModelAttribute Content content, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("boxes", true);
		setMenu(model,request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		List<String> positions = new ArrayList<String>();
		positions.add("LEFT");
		positions.add("RIGHT");
		
		model.addAttribute("positions",positions);
		
		if (result.hasErrors()) {
			return ControllerConstants.Tiles.Content.contentPagesDetails;
		}
		
		Map<String,Language> langs = languageService.getLanguagesMap();
		
		List<ContentDescription> descriptions = content.getDescriptions();
		for(ContentDescription description : descriptions) {
			Language l = langs.get(description.getLanguage().getCode());
			description.setLanguage(l);
			description.setContent(content);
		}
		
		content.setContentType(ContentType.BOX);
		content.setMerchantStore(store);
		contentService.saveOrUpdate(content);
		
		
		model.addAttribute("content",content);
		model.addAttribute("success","success");
		return ControllerConstants.Tiles.Content.contentPagesDetails;
		
		
	}
	
	
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("content", "content");
		activeMenus.put("content-boxes", "content-boxes");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("content");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}
	

}
