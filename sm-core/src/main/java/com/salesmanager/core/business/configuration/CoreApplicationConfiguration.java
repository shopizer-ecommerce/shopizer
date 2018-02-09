package com.salesmanager.core.business.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.salesmanager.core.model"})
@ComponentScan({"com.salesmanager.core.business.services","com.salesmanager.core.business.utils"})
@ImportResource("classpath:/spring/shopizer-core-context.xml")
public class CoreApplicationConfiguration {

}
