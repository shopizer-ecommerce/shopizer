package com.salesmanager.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
	
	@RequestMapping("/")
	public String landing() {
		
		//check if user is logged in, if he is logged in display dashboard else display login
		return "login/login";
	}

}
