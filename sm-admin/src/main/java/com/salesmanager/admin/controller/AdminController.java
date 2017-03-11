package com.salesmanager.admin.controller;

import com.salesmanager.admin.system.SmAccurator;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.user.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class AdminController {
	
	@Inject
	CountryService countryService;
	
	@Inject
	UserService userService;

	@Inject
	SmAccurator smAccurator;
	
	//@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value={"/admin/home.html","/admin/","/admin"}, method=RequestMethod.GET)
	public String displayDashboard(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        model.addAttribute("health", smAccurator.getHealthStatus());
        model.addAttribute("metrics", smAccurator.getMetrics());
        model.addAttribute("systemInfo", smAccurator.getMetrics());
	    return ControllerConstants.Tiles.adminDashboard;
	}
	



}
