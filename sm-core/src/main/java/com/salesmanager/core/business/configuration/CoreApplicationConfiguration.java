package com.salesmanager.core.business.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
/**
 * added in shopizer-core-config.xml
 * 
 * @author c.samson
 *
 */
@ComponentScan({"com.salesmanager.core.business"})
@ImportResource("classpath:/spring/shopizer-core-context.xml")
@Import(DroolsConfiguration.class)
public class CoreApplicationConfiguration {



}
