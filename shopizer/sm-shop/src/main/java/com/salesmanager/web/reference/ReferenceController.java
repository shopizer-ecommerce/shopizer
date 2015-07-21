package com.salesmanager.web.reference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.core.business.reference.zone.service.ZoneService;
import com.salesmanager.core.utils.CacheUtils;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.DateUtil;
import com.salesmanager.web.utils.LanguageUtils;
import com.salesmanager.web.utils.LocaleUtils;


/**
 * Used for misc reference objects
 * @author csamson777
 *
 */
@Controller
public class ReferenceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceController.class);
	
	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private CacheUtils cache;
	
	@Autowired
	private LanguageUtils languageUtils;


	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/admin/reference/provinces.html","/shop/reference/provinces.html"}, method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String getProvinces(HttpServletRequest request, HttpServletResponse response) {
		
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
		
		
		String returnString = resp.toJSONString();
		return returnString;
		
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
	@RequestMapping(value={"/shop/reference/creditCardDates.html"}, method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String getCreditCardDates(HttpServletRequest request, HttpServletResponse response) {
		

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
		
		return serialized;
	
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/shop/reference/monthsOfYear.html"}, method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String getMonthsOfYear(HttpServletRequest request, HttpServletResponse response) {
		

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
		
		return serialized;
	
	}
	
	

	
	


}
