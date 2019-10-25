package com.salesmanager.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.utils.CacheUtils;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.LanguageUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Used for misc reference objects
 * @author csamson777
 *
 */
@Controller
public class ReferenceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceController.class);
	
	@Inject
	private ZoneService zoneService;
	
	@Inject
	private CountryService countryService;
	
	@Inject
	private LanguageService languageService;
	
	@Inject
	private CacheUtils cache;
	
	@Inject
	private LanguageUtils languageUtils;


	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/admin/reference/provinces.html","/shop/reference/provinces.html"}, method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> getProvinces(HttpServletRequest request, HttpServletResponse response) {
		
		String countryCode = request.getParameter("countryCode");
		String lang = request.getParameter("lang");
		LOGGER.debug("Province Country Code " + countryCode);
		AjaxResponse resp = new AjaxResponse();
		
		try {
			
			Language language = null;
			
			if(!StringUtils.isBlank(lang)) {
				language = languageService.getByCode(lang);
			}
			
			if(language==null) {
				language = (Language)request.getAttribute("LANGUAGE");
			}
			
			if(language==null) {
				language = languageService.getByCode(Constants.DEFAULT_LANGUAGE);
			}
			
			
			Map<String,Country> countriesMap = countryService.getCountriesMap(language);
			Country country = countriesMap.get(countryCode);
			List<Zone> zones = zoneService.getZones(country, language);
			if(zones!=null && zones.size()>0) {
				
				
				
				for(Zone zone : zones) {
				
					@SuppressWarnings("rawtypes")
					Map entry = new HashMap();
					entry.put("name", zone.getName());
					entry.put("code", zone.getCode());
					entry.put("id", zone.getId());
		
					resp.addDataEntry(entry);
				
				}
				
				
			}
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
		
		} catch (Exception e) {
			LOGGER.error("GetProvinces()", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/shop/reference/countryName")
	public @ResponseBody String countryName(@RequestParam String countryCode, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			Language language = languageUtils.getRequestLanguage(request, response);
			if(language==null) {
				return countryCode;
			}
			Map<String, Country> countriesMap = countryService.getCountriesMap(language);
			if(countriesMap!=null) {
				Country c = countriesMap.get(countryCode);
				if(c!=null) {
					return c.getName();
				}
			}
		
		} catch (ServiceException e) {
			LOGGER.error("Error while looking up country " + countryCode);
		}
		return countryCode;
	}
	
	@RequestMapping(value="/shop/reference/zoneName")
	public @ResponseBody String zoneName(@RequestParam String zoneCode, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			Language language = languageUtils.getRequestLanguage(request, response);
			if(language==null) {
				return zoneCode;
			}
			Map<String, Zone> zonesMap = zoneService.getZones(language);
			if(zonesMap!=null) {
				Zone z = zonesMap.get(zoneCode);
				if(z!=null) {
					return z.getName();
				}
			}
		
		} catch (ServiceException e) {
			LOGGER.error("Error while looking up zone " + zoneCode);
		}
		return zoneCode;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/shop/reference/creditCardDates.html"}, method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getCreditCardDates(HttpServletRequest request, HttpServletResponse response) {
		

		List<String> years = null;
		String serialized = null;
		try {
			
	
			years = (List<String>)cache.getFromCache(Constants.CREDIT_CARD_YEARS_CACHE_KEY);
			
			if(years==null) {
			
				years = new ArrayList<String>();
				//current year
				
				for(int i = 0 ; i < 10 ; i++) {
					Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
					localCalendar.add(Calendar.YEAR, i);
					String dt = DateUtil.formatYear(localCalendar.getTime());
					years.add(dt);
				}
				//up to year + 10
				
				cache.putInCache(years, Constants.CREDIT_CARD_YEARS_CACHE_KEY);
			
			}
		

		
			final ObjectMapper mapper = new ObjectMapper();
			serialized = mapper.writeValueAsString(years);
		
		} catch(Exception e) {
			LOGGER.error("ReferenceControler ",e);
		}
		
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(serialized,httpHeaders,HttpStatus.OK);
	
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/shop/reference/monthsOfYear.html"}, method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getMonthsOfYear(HttpServletRequest request, HttpServletResponse response) {
		

		List<String> days = null;
		String serialized = null;
		
		try {	
			days = (List<String>)cache.getFromCache(Constants.MONTHS_OF_YEAR_CACHE_KEY);
			if(days==null) {

				days = new ArrayList<String>();
				for(int i = 1 ; i < 13 ; i++) {
					days.add(String.format("%02d", i));
				}

				cache.putInCache(days, Constants.MONTHS_OF_YEAR_CACHE_KEY);
			
			}
		

		
			final ObjectMapper mapper = new ObjectMapper();
			serialized = mapper.writeValueAsString(days);
		
		} catch(Exception e) {
			LOGGER.error("ReferenceControler ",e);
		}
		
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(serialized,httpHeaders,HttpStatus.OK);
	
	}
	
	

	
	


}
