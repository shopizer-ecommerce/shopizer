package com.salesmanager.admin.application;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class ServletInitializer {

	//public class ServletInitializer extends SpringBootServletInitializer
	//@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SmAdminApplication.class);
	}

}
