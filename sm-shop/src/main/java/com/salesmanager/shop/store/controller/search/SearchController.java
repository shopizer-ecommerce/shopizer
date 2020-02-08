package com.salesmanager.shop.store.controller.search;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.search.SearchService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.search.SearchKeywords;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.catalog.SearchProductList;
import com.salesmanager.shop.model.catalog.SearchProductRequest;
import com.salesmanager.shop.store.controller.ControllerConstants;
import com.salesmanager.shop.store.controller.search.facade.SearchFacade;
import com.salesmanager.shop.store.model.search.AutoCompleteRequest;
import com.salesmanager.shop.utils.ImageFilePath;

@Controller
public class SearchController {
	
	@Inject
	private MerchantStoreService merchantStoreService;

	@Inject
	private SearchService searchService;
	
	@Inject
	private SearchFacade searchFacade;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
	
	private final static int AUTOCOMPLETE_ENTRIES_COUNT = 15;

	
	
	/**
	 * Retrieves a list of keywords for a given series of character typed by the end user
	 * This is used for auto complete on search input field
	 * @param json
	 * @param store
	 * @param language
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/services/public/search/{store}/{language}/autocomplete.json", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String autocomplete(@RequestParam("q") String query, @PathVariable String store, @PathVariable final String language, Model model, HttpServletRequest request, HttpServletResponse response)  {

		MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);

		if(merchantStore!=null) {
			if(!merchantStore.getCode().equals(store)) {
				merchantStore = null; //reset for the current request
			}
		}
		
		try {
		
			if(merchantStore== null) {
					merchantStore = merchantStoreService.getByCode(store);
			}
			
			if(merchantStore==null) {
				LOGGER.error("Merchant store is null for code " + store);
				response.sendError(503, "Merchant store is null for code " + store);//TODO localized message
				return null;
			}
			
			AutoCompleteRequest req = new AutoCompleteRequest(store,language);
			/** formatted toJSONString because of te specific field names required in the UI **/
			SearchKeywords keywords = searchService.searchForKeywords(req.getCollectionName(), query, AUTOCOMPLETE_ENTRIES_COUNT);
			return keywords.toJSONString();

			
		} catch (Exception e) {
			LOGGER.error("Exception while autocomplete " + e);
		}
		
		return null;
		
	}

	
	/**
	 * Search results page
	 * @param searchRequest
	 * @param model
	 * @param language
	 * @param store
	 * @return
	 */
	@RequestMapping(value="/services/public/search.json", method=RequestMethod.POST)
	@ResponseBody
	public SearchProductList search(
	    @RequestBody SearchProductRequest searchRequest,
	    Model model, 
	    Language language, 
	    MerchantStore store) {

		return searchFacade.search(store, language, searchRequest);

		
	}
	
	/**
	 * Displays the search page after a search query post
	 * @param query
	 * @param model
	 * @param request
	 * @param response
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/shop/search/search.html"}, method=RequestMethod.POST)
	public String displaySearch(@RequestParam("q") String query, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
		
		String q = request.getParameter("q");

		model.addAttribute("q",q);
		
		/** template **/
		StringBuilder template = new StringBuilder().append(ControllerConstants.Tiles.Search.search).append(".").append(store.getStoreTemplate());
		return template.toString();
	}
	
	
}
