package com.salesmanager.test.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.salesmanager.core.business.configuration.DroolsConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.salesmanager.core.business"})
@ImportResource("spring/test-shopizer-context.xml")
@Import(DroolsConfiguration.class)
public class ConfigurationTest {
	
}
