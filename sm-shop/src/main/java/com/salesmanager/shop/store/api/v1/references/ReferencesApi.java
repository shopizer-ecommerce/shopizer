package com.salesmanager.shop.store.api.v1.references;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.currency.CurrencyService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.currency.Currency;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.references.ReadableCountry;
import com.salesmanager.shop.populator.references.ReadableCountryPopulator;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;


/**
 * Get system Language, Country and Currency objects
 * @author c.samson
 *
 */
@Controller
@RequestMapping("/api/v1")
public class ReferencesApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReferencesApi.class);

	
	@Inject
	private LanguageService languageService;
	
	@Inject
	private CountryService countryService;
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private CurrencyService currencyService;;
	
	@Inject
	private LanguageUtils languageUtils;
	
	/**
	 * Search languages by language code
	 * private/languages returns everything
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/languages", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Language>> getLanguages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		try {
			
			List<Language> langs = languageService.getLanguages();
			
			if(CollectionUtils.isEmpty(langs)){
				response.sendError(404, "No languages found");
			}
			
			return ResponseEntity.ok().body(langs);
		} catch (Exception e) {
			LOGGER.error("Error while getting languages",e);
			try {
				response.sendError(503, "Error while getting languages " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
		
	}
	
	/**
	 * Returns a country with zones (provinces, states)
	 * supports language set in parameter ?lang=en|fr|ru...
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/country", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<ReadableCountry>> getCountry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language lang = languageUtils.getRESTLanguage(request, merchantStore);


			List<Country> country = countryService.listCountryZones(lang);

			if(CollectionUtils.isEmpty(country)){
				response.sendError(404, "No country found");
			}
			
			List<ReadableCountry> countryList = new ArrayList<ReadableCountry>();
			
			
			for(Country c : country) {
			
				/**
				 * Populator will convert to readable format
				 */
				ReadableCountry rc = new ReadableCountry();
				ReadableCountryPopulator populator = new ReadableCountryPopulator();
				populator.populate(c, rc, merchantStore, lang);
				countryList.add(rc);
			
			}
			
			return ResponseEntity.ok().body(countryList);
		} catch (Exception e) {
			LOGGER.error("Error while getting country",e);
			try {
				response.sendError(503, "Error while getting country " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
		
	}
	
	
	/**
	 * Currency
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/currency", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Currency>> getCurrency(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		try {
			
			List<Currency> currency = currencyService.list();
			
			if(CollectionUtils.isEmpty(currency)){
				response.sendError(404, "No languages found");
			}
			
			return ResponseEntity.ok().body(currency);
		} catch (Exception e) {
			LOGGER.error("Error while getting currency",e);
			try {
				response.sendError(503, "Error while getting currency " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
		
	}

}
