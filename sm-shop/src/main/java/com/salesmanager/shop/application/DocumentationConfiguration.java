package com.salesmanager.shop.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@springfox.documentation.swagger2.annotations.EnableSwagger2
public class DocumentationConfiguration {
	
	 public static final Contact DEFAULT_CONTACT = new Contact("Shopizer", "http://www.shopizer.com", "");
	 
	 /**
	  * http://localhost:8080/swagger-ui.html#/
	  * http://localhost:8080/v2/api-docs
	  */
	
	 @Bean
	    public Docket api() {

		 	List<ResponseMessage> getMessages = new ArrayList<ResponseMessage>();
		 	getMessages.add(
		 				new ResponseMessageBuilder()
	                				.code(500).message("500 message")
	                				.responseModel(new ModelRef("Error")).build()			
	                );
		 	getMessages.add(
	 				new ResponseMessageBuilder()
                				.code(403).message("Forbidden")
                				.build()		
                );
		 	getMessages.add(
	 				new ResponseMessageBuilder()
                				.code(401).message("Unauthorized")
                				.build()		
                );
		 
	        return new Docket(DocumentationType.SWAGGER_2)
	        		.select()
	        		.apis(RequestHandlerSelectors.basePackage("com.salesmanager.shop.store.api.v1"))

	        		.build()
	        		.apiInfo(apiInfo())
	        		.useDefaultResponseMessages(false)
	                .globalResponseMessage(RequestMethod.GET, getMessages);
	    }
	
	
	 private ApiInfo apiInfo() {
		 return new ApiInfo("Shopizer REST API", 
			  "API for Shopizer e-commerce. Contains public end points as well as private end points requiring basic authentication and remote authentication based on jwt bearer token. URL patterns containing /private/** use bearer token; those are authorized customer and administrators administration actions.",
			 "1.0", "urn:tos",
	          DEFAULT_CONTACT,
	          "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());

	 }
}
