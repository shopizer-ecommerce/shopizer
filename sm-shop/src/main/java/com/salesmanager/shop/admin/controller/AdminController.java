package com.salesmanager.shop.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.user.User;
import com.salesmanager.shop.constants.Constants;



@Controller
public class AdminController {
	
	@Inject
	CountryService countryService;
	
	@Inject
	UserService userService;
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value={"/admin/home.html","/admin/"}, method=RequestMethod.GET)
	public String displayDashboard(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Language language = (Language)request.getAttribute("LANGUAGE");
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("home", "home");
		
		model.addAttribute("activeMenus",activeMenus);
		
		
		//get store information
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		Map<String,Country> countries = countryService.getCountriesMap(language);
		
		Country storeCountry = store.getCountry();
		Country country = countries.get(storeCountry.getIsoCode());
		
		String sCurrentUser = request.getRemoteUser();
		User currentUser = userService.getByUserName(sCurrentUser);
		
		model.addAttribute("store", store);
		model.addAttribute("country", country);
		model.addAttribute("user", currentUser);
		//get last 10 orders
		//OrderCriteria orderCriteria = new OrderCriteria();
		//orderCriteria.setMaxCount(10);
		//orderCriteria.setOrderBy(CriteriaOrderBy.DESC);
		
		return ControllerConstants.Tiles.adminDashboard;
	}
	
	@RequestMapping( value=Constants.ADMIN_URI , method=RequestMethod.GET)
	public String displayStoreLanding(HttpServletRequest request, HttpServletResponse response) {

		return "redirect:" + Constants.ADMIN_URI + Constants.SLASH;
	}



}
