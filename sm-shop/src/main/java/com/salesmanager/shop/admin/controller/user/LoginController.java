package com.salesmanager.shop.admin.controller.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
	

	@RequestMapping(value="/admin/logon.html", method=RequestMethod.GET)
	public String displayLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//WEB-INF/views/admin/logon.jsp
		return "admin/logon";
		
		
	}
	

	@RequestMapping(value="/admin/denied.html", method=RequestMethod.GET)
	public String displayDenied(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		//logoff the user
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	         new SecurityContextLogoutHandler().logout(request, response, auth);
	         //new PersistentTokenBasedRememberMeServices().logout(request, response, auth);
	    }
		
		return "admin/logon";
		
		
	}
	
	@RequestMapping(value="/admin/unauthorized.html", method=RequestMethod.GET)
	public String unauthorized(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "admin/unauthorized";
	}

}
