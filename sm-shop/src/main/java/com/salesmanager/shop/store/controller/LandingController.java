package com.salesmanager.shop.store.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationshipType;
import com.salesmanager.core.model.content.Content;
import com.salesmanager.core.model.content.ContentDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.shop.Breadcrumb;
import com.salesmanager.shop.model.shop.BreadcrumbItem;
import com.salesmanager.shop.model.shop.BreadcrumbItemType;
import com.salesmanager.shop.model.shop.PageInformation;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;
import com.salesmanager.shop.utils.LabelUtils;



@Controller
public class LandingController {
	
	
	private final static String LANDING_PAGE = "LANDING_PAGE";
	
	
	@Inject
	private ContentService contentService;
	
	@Inject
	private ProductRelationshipService productRelationshipService;

	
	@Inject
	private LabelUtils messages;
	
	@Inject
	private PricingService pricingService;
	
	@Inject
	private MerchantStoreService merchantService;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LandingController.class);
	private final static String HOME_LINK_CODE="HOME";
	
	@RequestMapping(value={Constants.SHOP_URI + "/home.html",Constants.SHOP_URI +"/", Constants.SHOP_URI}, method=RequestMethod.GET)
	public String displayLanding(Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		Language language = (Language)request.getAttribute(Constants.LANGUAGE);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);

		request.setAttribute(Constants.LINK_CODE, HOME_LINK_CODE);
		
		Content content = contentService.getByCode(LANDING_PAGE, store, language);
		
		/** Rebuild breadcrumb **/
		BreadcrumbItem item = new BreadcrumbItem();
		item.setItemType(BreadcrumbItemType.HOME);
		item.setLabel(messages.getMessage(Constants.HOME_MENU_KEY, locale));
		item.setUrl(Constants.HOME_URL);

		
		Breadcrumb breadCrumb = new Breadcrumb();
		breadCrumb.setLanguage(language);
		
		List<BreadcrumbItem> items = new ArrayList<BreadcrumbItem>();
		items.add(item);
		
		breadCrumb.setBreadCrumbs(items);
		request.getSession().setAttribute(Constants.BREADCRUMB, breadCrumb);
		request.setAttribute(Constants.BREADCRUMB, breadCrumb);
		/** **/
		
		if(content!=null) {
			
			ContentDescription description = content.getDescription();

			model.addAttribute("page",description);
			
			PageInformation pageInformation = new PageInformation();
			pageInformation.setPageTitle(description.getName());
			pageInformation.setPageDescription(description.getMetatagDescription());
			pageInformation.setPageKeywords(description.getMetatagKeywords());
			
			request.setAttribute(Constants.REQUEST_PAGE_INFORMATION, pageInformation);
			
		}
		
		ReadableProductPopulator populator = new ReadableProductPopulator();
		populator.setPricingService(pricingService);
		populator.setimageUtils(imageUtils);

		
		//featured items
		List<ProductRelationship> relationships = productRelationshipService.getByType(store, ProductRelationshipType.FEATURED_ITEM, language);
		List<ReadableProduct> featuredItems = new ArrayList<ReadableProduct>();
		Date today = new Date();
		for(ProductRelationship relationship : relationships) {
			Product product = relationship.getRelatedProduct();
			if(product.isAvailable() && DateUtil.dateBeforeEqualsDate(product.getDateAvailable(), today)) {
				ReadableProduct proxyProduct = populator.populate(product, new ReadableProduct(), store, language);
			    featuredItems.add(proxyProduct);
			}
		}
		
		String tmpl = store.getStoreTemplate();
		if(StringUtils.isBlank(tmpl)) {
		    tmpl = "generic";
		}

		
		model.addAttribute("featuredItems", featuredItems);
		
		/** template **/
		StringBuilder template = new StringBuilder().append("landing.").append(tmpl);
		return template.toString();
	}
	
	@RequestMapping(value={Constants.SHOP_URI + "/stub.html"}, method=RequestMethod.GET)
	public String displayHomeStub(Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		return "index";
	}
	
	@RequestMapping( value=Constants.SHOP_URI + "/{store}", method=RequestMethod.GET)
	public String displayStoreLanding(@PathVariable final String store, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			request.getSession().invalidate();
			request.getSession().removeAttribute(Constants.MERCHANT_STORE);
			
			MerchantStore merchantStore = merchantService.getByCode(store);
			if(merchantStore!=null) {
				request.getSession().setAttribute(Constants.MERCHANT_STORE, merchantStore);
			} else {
				LOGGER.error("MerchantStore does not exist for store code " + store);
			}
			
		} catch(Exception e) {
			LOGGER.error("Error occured while getting store code " + store, e);
		}
		

		
		return "redirect:" + Constants.SHOP_URI;
	}
		

}
