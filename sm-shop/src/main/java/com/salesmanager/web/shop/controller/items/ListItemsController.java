package com.salesmanager.web.shop.controller.items;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.catalog.product.service.manufacturer.ManufacturerService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.entity.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.web.entity.shop.PageInformation;
import com.salesmanager.web.populator.manufacturer.ReadableManufacturerPopulator;
import com.salesmanager.web.shop.controller.ControllerConstants;
import com.salesmanager.web.utils.PageBuilderUtils;

/**
 * Drives various product listings
 * @author carlsamson
 *
 */
@Controller
public class ListItemsController {
	
	@Inject
	ManufacturerService manufacturerService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ListItemsController.class);
	
	@RequestMapping("/shop/listing/{url}.html")
	public String displayListingPage(@PathVariable String url, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		
		Language language = (Language)request.getAttribute("LANGUAGE");
		
		Manufacturer manufacturer = manufacturerService.getByUrl(store, language, url);
		
		
		
		if(manufacturer==null) {
			LOGGER.error("No manufacturer found for url " + url);
			//redirect on page not found
			return PageBuilderUtils.build404(store);
			
		}
		
		ReadableManufacturer readableManufacturer = new ReadableManufacturer();
		
		ReadableManufacturerPopulator populator = new ReadableManufacturerPopulator();
		readableManufacturer = populator.populate(manufacturer, readableManufacturer, store, language);
		
		//meta information
		PageInformation pageInformation = new PageInformation();
		pageInformation.setPageDescription(readableManufacturer.getDescription().getMetaDescription());
		pageInformation.setPageKeywords(readableManufacturer.getDescription().getKeyWords());
		pageInformation.setPageTitle(readableManufacturer.getDescription().getTitle());
		pageInformation.setPageUrl(readableManufacturer.getDescription().getFriendlyUrl());
		
		model.addAttribute("manufacturer", readableManufacturer);
		
		/** template **/
		StringBuilder template = new StringBuilder().append(ControllerConstants.Tiles.Items.items_manufacturer).append(".").append(store.getStoreTemplate());

		return template.toString();
	}
	

}
