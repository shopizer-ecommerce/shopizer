package com.salesmanager.shop.application;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.salesmanager.core.business.configuration.CoreApplicationConfiguration;

@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@ImportResource({"spring/shopizer-shop-context.xml","spring/shopizer-core-context.xml"})
@Import(CoreApplicationConfiguration.class)//import sm-core configurations
public class ShopApplicationConfiguration {

}
