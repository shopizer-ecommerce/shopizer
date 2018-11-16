package com.salesmanager.admin.controller.dashboard;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashBoardController {
	
	@RequestMapping("/admin/dashboard")
	@Secured({"ROLE_AUTH"})
	public String display(Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return "dashboard/dashboard";
	}

}
