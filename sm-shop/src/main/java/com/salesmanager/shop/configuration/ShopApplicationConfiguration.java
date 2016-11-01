package com.salesmanager.shop.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.salesmanager.shop.*")
@EnableWebMvc
@EnableAutoConfiguration
@ImportResource({"spring/shopizer-shop-context.xml","spring/shopizer-core-context.xml"})
public class ShopApplicationConfiguration {

}
