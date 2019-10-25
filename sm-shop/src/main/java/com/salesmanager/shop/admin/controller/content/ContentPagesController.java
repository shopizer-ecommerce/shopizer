package com.salesmanager.shop.admin.controller.content;

import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.content.Content;
import com.salesmanager.core.model.content.ContentDescription;
import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
public class ContentPagesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentPagesController.class);
	
	@Inject
	private ContentService contentService;
	
	@Inject
	LanguageService languageService;
	
	@Inject
	ProductRelationshipService productRelationshipService;
	
	
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/pages/list.html", method=RequestMethod.GET)
	public String listContentPages(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);

		return ControllerConstants.Tiles.Content.contentPages;
		
		
	}
	
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/pages/create.html", method=RequestMethod.GET)
	public String createPage(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		Content content = new Content();
		content.setMerchantStore(store);
		content.setContentType(ContentType.PAGE);
		
		
		List<Language> languages = store.getLanguages();
		
		
		for(Language l : languages) {
			
			ContentDescription description = new ContentDescription();
			description.setLanguage(l);
			content.getDescriptions().add(description);
		}
		
		List<ProductRelationship> relationships = productRelationshipService.getGroups(store);
		if(!CollectionUtils.isEmpty(relationships)) {
			model.addAttribute("productGroups", relationships);
		}
		
		
		
		model.addAttribute("content",content);
		

		return ControllerConstants.Tiles.Content.contentPagesDetails;
		
		
	}
	
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/pages/details.html", method=RequestMethod.GET)
	public String getContentDetails(@RequestParam("id") Long id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		Content content = contentService.getById(id);
		

		
		if(content==null) {
			LOGGER.error("Content entity null for id " + id);
			return "redirect:/admin/content/pages/listContent.html";
		}
		
		if(content.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			LOGGER.error("Content id " + id + " does not belong to merchant " + store.getId());
			return "redirect:/admin/content/pages/listContent.html";
		}
		
		if(!content.getContentType().name().equals(ContentType.PAGE.name())) {
			LOGGER.error("This controller does not handle content type " + content.getContentType().name());
			return "redirect:/admin/content/pages/listContent.html";
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
		
		List<ProductRelationship> relationships = productRelationshipService.getGroups(store);
		if(!CollectionUtils.isEmpty(relationships)) {
			model.addAttribute("productGroups", relationships);
		}
		
		return ControllerConstants.Tiles.Content.contentPagesDetails;
		
		
	}
	
	
	
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/remove.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> removeContent(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String id = request.getParameter("id");

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		
		try {
			
			//get the content first
			Long lid = Long.parseLong(id);
			
			Content dbContent = contentService.getById(lid);
			
			if(dbContent==null) {
				LOGGER.error("Invalid content id ", id);
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			if(dbContent!=null && dbContent.getMerchantStore().getId().intValue()!= store.getId().intValue()) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			contentService.delete(dbContent);

			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting product", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	
	@SuppressWarnings({ "unchecked"})
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/page.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> pageStaticContent(@RequestParam("contentType") String contentType, HttpServletRequest request, HttpServletResponse response) {
		AjaxResponse resp = new AjaxResponse();

		try {
			

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

			Language language = (Language)request.getAttribute("LANGUAGE");
			
			

			
			ContentType cType = ContentType.PAGE;
			if(ContentType.BOX.name().equals(contentType)) {
				cType = ContentType.BOX;
			} 
			List<Content> contentList = contentService.listByType(cType, store, language);
			
			if(contentList!=null) {

				for(Content content : contentList) {
					
					List<ContentDescription> descriptions = content.getDescriptions();
					ContentDescription description = descriptions.get(0);
					for(ContentDescription desc : descriptions) {
						if(desc.getLanguage().getCode().equals(language.getCode())) {
							description = desc;
							break;
						}
					}
					

					@SuppressWarnings("rawtypes")
					Map entry = new HashMap();
					entry.put("id", content.getId());
					entry.put("code", content.getCode());
					entry.put("name", description.getName());
					resp.addDataEntry(entry);

				}
			
			}
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);

		} catch (Exception e) {
			LOGGER.error("Error while paging content", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/pages/save.html", method=RequestMethod.POST)
	public String saveContent(@Valid @ModelAttribute Content content, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		setMenu(model,request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
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
		
		if(content.getSortOrder()==null) {
			content.setSortOrder(0);
		}

		content.setContentType(ContentType.PAGE);
		content.setMerchantStore(store);

		contentService.saveOrUpdate(content);
		
		List<ProductRelationship> relationships = productRelationshipService.getGroups(store);
		if(!CollectionUtils.isEmpty(relationships)) {
			model.addAttribute("productGroups", relationships);
		}
		
		
		model.addAttribute("content",content);
		model.addAttribute("success","success");
		return ControllerConstants.Tiles.Content.contentPagesDetails;
		
		
	}
	
	/**
	 * Check if the content code filled in by the
	 * user is unique
	 * @param request
	 * @param response
	 * @param locale
	 * @return
	 */
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value="/admin/content/checkContentCode.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> checkContentCode(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		
		String code = request.getParameter("code");
		String id = request.getParameter("id");

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		   if(StringUtils.isBlank(code)) {
				resp.setStatus(AjaxResponse.CODE_ALREADY_EXIST);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		   }
		
		try {
			
		Content content = contentService.getByCode(code, store);
		
		
		if(!StringUtils.isBlank(id)) {
			try {
				Long lid = Long.parseLong(id);
				
				if(content!=null && content.getCode().equals(code) && content.getId().longValue()==lid) {
					resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
					String returnString = resp.toJSONString();
					return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
				}
			} catch (Exception e) {
				resp.setStatus(AjaxResponse.CODE_ALREADY_EXIST);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}

		} else {
			if(content!=null) {
				resp.setStatus(AjaxResponse.CODE_ALREADY_EXIST);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
		}

			

			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		} catch (Exception e) {
			LOGGER.error("Error while getting content", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("content", "content");
		activeMenus.put("content-pages", "content-pages");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("content");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}
	

}
