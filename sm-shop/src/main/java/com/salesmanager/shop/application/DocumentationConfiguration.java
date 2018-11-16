package com.salesmanager.shop.application;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@springfox.documentation.swagger2.annotations.EnableSwagger2
public class DocumentationConfiguration {
  
     @Inject
     private BuildProperties buildProperties;
     
     /**
      * Artifact's name from the pom.xml file
        buildProperties.getName();
        // Artifact version
        buildProperties.getVersion();
        // Date and Time of the build
        buildProperties.getTime();
        // Artifact ID from the pom file
        buildProperties.getArtifact();
        // Group ID from the pom file
        buildProperties.getGroup();
      */
	
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

	 
	 /**
	  * For beans
	  * @return
	  * @ApiModelProperty(notes = "The auto-generated version of the product")
	  */
	 private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Shopizer REST API v" + buildProperties.getVersion())
	                .description("\"API for Shopizer e-commerce. Contains public end points as well as private end points requiring basic authentication and remote authentication based on jwt bearer token. URL patterns containing /private/** use bearer token; those are authorized customer and administrators administration actions.\"")
	                .version(buildProperties.getVersion())
	                .license("Apache License Version 2.0")
	                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	                .contact(DEFAULT_CONTACT)
	                .build();
	 }
}
