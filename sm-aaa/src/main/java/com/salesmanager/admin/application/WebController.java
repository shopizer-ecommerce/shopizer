package com.salesmanager.admin.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class WebController {
	
	@GetMapping
	public String welcome() {
		//return "dashboard/page1";
		return "redirect:/admin/dashboard";
		
	}
	
	@GetMapping("/page2")
	public String page2() {
		return "dashboard/page2";
	}
	
	@GetMapping("/admin/dashboard")
	public String dashboard() {
		return "dashboard/page1";
	}

}
