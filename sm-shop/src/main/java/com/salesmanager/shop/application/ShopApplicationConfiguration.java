package com.salesmanager.shop.application;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.salesmanager.core.business.configuration.CoreApplicationConfiguration;

@Configuration
@ComponentScan({"com.salesmanager.shop","com.salesmanager.core.business"})
@EnableAutoConfiguration
@Import(CoreApplicationConfiguration.class)//import sm-core configurations
@ImportResource("spring/shopizer-shop-context.xml")
public class ShopApplicationConfiguration {

}
