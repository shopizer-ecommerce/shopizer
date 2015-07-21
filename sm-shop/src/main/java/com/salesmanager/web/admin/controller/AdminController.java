package com.salesmanager.web.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanager.core.business.common.model.CriteriaOrderBy;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.OrderCriteria;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.user.model.User;
import com.salesmanager.core.business.user.service.UserService;
import com.salesmanager.web.constants.Constants;

@Controller
public class AdminController {
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value={"/admin/home.html","/admin/","/admin"}, method=RequestMethod.GET)
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
		OrderCriteria orderCriteria = new OrderCriteria();
		orderCriteria.setMaxCount(10);
		orderCriteria.setOrderBy(CriteriaOrderBy.DESC);
		
		return ControllerConstants.Tiles.adminDashboard;
	}
	



}
