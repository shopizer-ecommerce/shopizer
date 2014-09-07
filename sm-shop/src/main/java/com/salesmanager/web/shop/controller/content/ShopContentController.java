package com.salesmanager.web.shop.controller.content;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanager.core.business.content.model.ContentDescription;
import com.salesmanager.core.business.content.service.ContentService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.entity.shop.PageInformation;
import com.salesmanager.web.shop.controller.ControllerConstants;

@Controller
public class ShopContentController {
	
	
	@Autowired
	private ContentService contentService;

	
	@RequestMapping("/shop/pages/{friendlyUrl}.html")
	public String displayContent(@PathVariable final String friendlyUrl, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);

		Language language = (Language)request.getAttribute("LANGUAGE");
		
		ContentDescription contentDescription = contentService.getBySeUrl(store, friendlyUrl);
		
		if(contentDescription!=null) {
			
			//meta information
			PageInformation pageInformation = new PageInformation();
			pageInformation.setPageDescription(contentDescription.getMetatagDescription());
			pageInformation.setPageKeywords(contentDescription.getMetatagKeywords());
			pageInformation.setPageTitle(contentDescription.getTitle());
			pageInformation.setPageUrl(contentDescription.getName());
			
			request.setAttribute(Constants.REQUEST_PAGE_INFORMATION, pageInformation);
			
			
		}
		
		//TODO breadcrumbs
		
		model.addAttribute("content",contentDescription);
		
		/** template **/
		StringBuilder template = new StringBuilder().append(ControllerConstants.Tiles.Content.content).append(".").append(store.getStoreTemplate());

		return template.toString();
		
		
	}
	
}
