package com.salesmanager.shop.admin.controller.shipping;

import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.shipping.ShippingOriginService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Controller
public class ShippingOriginController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingOriginController.class);

	
	@Inject
	private ShippingOriginService shippingOriginService;
	
	@Inject
	private CountryService countryService;
	
	@Inject
	private ZoneService zoneService;
	
	/**
	 * Configures the shipping mode, shows shipping countries
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('SHIPPING')")
	@RequestMapping(value="/admin/shipping/origin/get.html", method=RequestMethod.GET)
	public String displayShippingOrigin(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.setMenu(model, request);

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		Language language = (Language)request.getAttribute("LANGUAGE");		
		
		ShippingOrigin shippingOrigin =  shippingOriginService.getByStore(store);
		
		List<Country> countries = countryService.getCountries(language);
		
		if(shippingOrigin==null) {
			shippingOrigin = new ShippingOrigin();
			shippingOrigin.setCountry(store.getCountry());
			shippingOrigin.setState(store.getStorestateprovince());
			shippingOrigin.setZone(store.getZone());
		}

		model.addAttribute("countries", countries);
		model.addAttribute("origin", shippingOrigin);
		return "shipping-origin";
		
		
	}
	
	@PreAuthorize("hasRole('SHIPPING')")
	@RequestMapping(value="/admin/shipping/origin/post.html", method=RequestMethod.POST)
	public String saveShippingOrigin(@Valid @ModelAttribute("origin") ShippingOrigin origin, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		Language language = (Language)request.getAttribute("LANGUAGE");	
		List<Country> countries = countryService.getCountries(language);
		
		ShippingOrigin shippingOrigin =  shippingOriginService.getByStore(store);
		if(shippingOrigin!=null) {
			origin.setId(shippingOrigin.getId());
		}
		
		origin.setMerchantStore(store);
		
		Country country = countryService.getByCode(origin.getCountry().getIsoCode());
		origin.setCountry(country);
		
		if(origin.getZone() !=null) {
			Zone zone = zoneService.getByCode(origin.getZone().getCode());
			origin.setZone(zone);
		}
		
		if(shippingOrigin!=null) {
			shippingOriginService.update(origin);
		} else {
			shippingOriginService.save(origin);
		}

		model.addAttribute("countries", countries);
		model.addAttribute("origin", origin);
		model.addAttribute("success","success");
		return "shipping-origin";
		
	}
	
	@PreAuthorize("hasRole('SHIPPING')")
	@RequestMapping(value="/admin/shipping/origin/delete.html", method=RequestMethod.POST)
	public String deleteShippingOrigin(@ModelAttribute("origin") ShippingOrigin origin, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		ShippingOrigin shippingOrigin =  shippingOriginService.getByStore(store);
		
		Language language = (Language)request.getAttribute("LANGUAGE");	
		List<Country> countries = countryService.getCountries(language);
		

		if(shippingOrigin!=null && origin !=null) {
			if(shippingOrigin.getId().longValue() == origin.getId().longValue()) {
				shippingOriginService.delete(shippingOrigin);
				model.addAttribute("success","success");
			} else {
				return "redirect:/admin/shipping/origin/get.html";
			}
		} else {
			return "redirect:/admin/shipping/origin/get.html";
		}
		
		model.addAttribute("countries", countries);
		model.addAttribute("origin", null);
		model.addAttribute("success","success");
		return "shipping-origin";
		
	}
		
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("shipping", "shipping");
		activeMenus.put("shipping-origin", "shipping-origin");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("shipping");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}
	

}
