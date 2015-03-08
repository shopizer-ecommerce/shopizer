package com.salesmanager.web.admin.controller.shipping;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.shipping.model.ShippingConfiguration;
import com.salesmanager.core.business.shipping.model.ShippingType;
import com.salesmanager.core.business.shipping.service.ShippingService;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;


@Controller
public class ShippingConfigsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingConfigsController.class);

	
	@Autowired
	private ShippingService shippingService;
	
	@Autowired
	private CountryService countryService;
	
	/**
	 * Configures the shipping mode, shows shipping countries
	 * @param request
	 * @param response
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('SHIPPING')")
	@RequestMapping(value="/admin/shipping/shippingConfigs.html", method=RequestMethod.GET)
	public String displayShippingConfigs(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.setMenu(model, request);

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		

		
		ShippingConfiguration shippingConfiguration =  shippingService.getShippingConfiguration(store);
		
		if(shippingConfiguration==null) {
			shippingConfiguration = new ShippingConfiguration();
			shippingConfiguration.setShippingType(ShippingType.INTERNATIONAL);
		}
		

		model.addAttribute("configuration", shippingConfiguration);
		return "shipping-configs";
		
		
	}
	
	@PreAuthorize("hasRole('SHIPPING')")
	@RequestMapping(value="/admin/shipping/saveShippingConfiguration.html", method=RequestMethod.POST)
	public String saveShippingConfiguration(@ModelAttribute("configuration") ShippingConfiguration configuration, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		//get original configuration
		ShippingConfiguration shippingConfiguration =  shippingService.getShippingConfiguration(store);
		
		if(shippingConfiguration==null) {
			shippingConfiguration = new ShippingConfiguration();
		}
		
		shippingConfiguration.setShippingType(configuration.getShippingType());
		
		shippingService.saveShippingConfiguration(shippingConfiguration, store);
		
		model.addAttribute("configuration", shippingConfiguration);
		model.addAttribute("success","success");
		return "shipping-configs";
		
	}
	
	@SuppressWarnings({ "unchecked"})
	@PreAuthorize("hasRole('SHIPPING')")
	@RequestMapping(value="/admin/shipping/countries/paging.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String pageCountries(HttpServletRequest request, HttpServletResponse response) {
		String countryName = request.getParameter("name");
		AjaxResponse resp = new AjaxResponse();

		try {
			
			Language language = (Language)request.getAttribute("LANGUAGE");
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			//get list of countries
			Map<String,Country> countries = countryService.getCountriesMap(language);
			
			//get inclusions
			List<String> includedCountries = shippingService.getSupportedCountries(store);
			

			for(String key : countries.keySet()) {
				
				Country country = (Country)countries.get(key);

				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("code", country.getIsoCode());
				entry.put("name", country.getName());
				
				if(includedCountries.contains(key)) {
					entry.put("supported", true);
				} else {
					entry.put("supported", false);
				}
				
				if(!StringUtils.isBlank(countryName)) {
					if(country.getName().contains(countryName)){
						resp.addDataEntry(entry);
					}
				} else {
					resp.addDataEntry(entry);
				}
			}
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);

		} catch (Exception e) {
			LOGGER.error("Error while paging shipping countries", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	
	@PreAuthorize("hasRole('SHIPPING')")
	@RequestMapping(value="/admin/shipping/countries/update.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String updateCountry(HttpServletRequest request, HttpServletResponse response) {
		String values = request.getParameter("_oldValues");
		String supported = request.getParameter("supported");
		
		
		

		
		
		AjaxResponse resp = new AjaxResponse();

		try {
			
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("rawtypes")
			Map conf = mapper.readValue(values, Map.class);
			
			String countryCode = (String)conf.get("code");

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			//get list of countries
			List<String> includedCountries = shippingService.getSupportedCountries(store);
			
			if(!StringUtils.isBlank(supported)) {
				if("true".equals(supported)) {
					includedCountries.add(countryCode);
				} else {
					includedCountries.remove(countryCode);
				}
			}
			
			
			shippingService.setSupportedCountries(store, includedCountries);
			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
			

		
		} catch (Exception e) {
			LOGGER.error("Error while paging shipping countries", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("shipping", "shipping");
		activeMenus.put("shipping-configs", "shipping-configs");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("shipping");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}
	

}
