package com.salesmanager.admin.controller.catalogue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CatalogueController {
	
	@RequestMapping("/admin/catalogue")
	public String display(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "login/login";
	}

}
