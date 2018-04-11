package com.salesmanager.admin.controller.dashboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashBoardController {
	
	@RequestMapping("/admin/dashboard")
	public String display(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "dashboard/dashboard";
	}

}
