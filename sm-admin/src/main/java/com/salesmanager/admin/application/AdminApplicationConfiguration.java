package com.salesmanager.admin.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({"com.salesmanager.admin"})
public class AdminApplicationConfiguration extends WebMvcConfigurerAdapter{
	


}
