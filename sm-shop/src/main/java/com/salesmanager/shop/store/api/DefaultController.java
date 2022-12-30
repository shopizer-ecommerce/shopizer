package com.salesmanager.shop.store.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DefaultController {
	

	@Autowired
	private Environment env;
	
	@GetMapping(value = "/")
	public @ResponseBody String version(Model model) {

		return "{\"version\":\""+  env.getProperty("application-version")  +"\", \"build\":\"" + env.getProperty("build.timestamp") + "\"}";
	}

}
