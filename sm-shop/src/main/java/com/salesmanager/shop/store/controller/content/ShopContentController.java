package com.salesmanager.shop.store.controller.content;

import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.content.Content;
import com.salesmanager.core.model.content.ContentDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.shop.PageInformation;
import com.salesmanager.shop.store.controller.ControllerConstants;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class ShopContentController {
	
	
	@Inject
	private ContentService contentService;

	
	@RequestMapping("/shop/pages/{friendlyUrl}.html")
	public String displayContent(@PathVariable final String friendlyUrl, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);

		ContentDescription contentDescription = contentService.getBySeUrl(store, friendlyUrl);
		
		Content content = null;
		
		if(contentDescription!=null) {
			
			content = contentDescription.getContent();
			
			if(!content.isVisible()) {
				return "redirect:/shop";
			}
			
			//meta information
			PageInformation pageInformation = new PageInformation();
			pageInformation.setPageDescription(contentDescription.getMetatagDescription());
			pageInformation.setPageKeywords(contentDescription.getMetatagKeywords());
			pageInformation.setPageTitle(contentDescription.getTitle());
			pageInformation.setPageUrl(contentDescription.getName());
			
			request.setAttribute(Constants.REQUEST_PAGE_INFORMATION, pageInformation);
			
			
			
			
		}
		
		//TODO breadcrumbs
		request.setAttribute(Constants.LINK_CODE, contentDescription.getSeUrl());
		model.addAttribute("content",contentDescription);

		if(!StringUtils.isBlank(content.getProductGroup())) {
			model.addAttribute("productGroup",content.getProductGroup());
		}
		
		/** template **/
		StringBuilder template = new StringBuilder().append(ControllerConstants.Tiles.Content.content).append(".").append(store.getStoreTemplate());

		return template.toString();
		
		
	}
	
}