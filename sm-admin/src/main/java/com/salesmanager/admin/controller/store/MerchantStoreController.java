package com.salesmanager.admin.controller.store;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MerchantStoreController {
	
	
	@RequestMapping("/admin/store")
	@Secured({"ROLE_STORE"})
	public String display(@RequestParam(value = "code", required=false) String code, Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		request.setAttribute("action", "READ");
		return "store/store";
	}
	
	@RequestMapping("/admin/store/create")
	@Secured({"ROLE_STORE"})
	public String create(Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		request.setAttribute("action", "CREATE");
		return "store/store";
	}

}
