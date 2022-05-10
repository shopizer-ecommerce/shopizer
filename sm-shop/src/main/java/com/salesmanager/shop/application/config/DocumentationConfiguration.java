package com.salesmanager.shop.application.config;

import static io.swagger.models.auth.In.HEADER;
import static java.util.Collections.singletonList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class DocumentationConfiguration {

	public static final Contact DEFAULT_CONTACT = new Contact("Shopizer", "https://www.shopizer.com", "");
	
	private static final String HOST = "localhost:8080";

	/**
	 * http://localhost:8080/swagger-ui.html#/ http://localhost:8080/v2/api-docs
	 */

	@Bean
	public Docket api() {

		final List<ResponseMessage> getMessages = new ArrayList<ResponseMessage>();
		getMessages.add(new ResponseMessageBuilder().code(500).message("500 message")
				.responseModel(new ModelRef("Error")).build());
		getMessages.add(new ResponseMessageBuilder().code(403).message("Forbidden").build());
		getMessages.add(new ResponseMessageBuilder().code(401).message("Unauthorized").build());

		Set<String> produces = new HashSet<>();
		produces.add("application/json");

		Set<String> consumes = new HashSet<>();
		consumes.add("application/json");

		return new Docket(DocumentationType.SWAGGER_2)
				.host(HOST)
				.select()
				.apis(requestHandlers()).build()
				.securitySchemes(Collections.singletonList(new ApiKey("JWT", AUTHORIZATION, HEADER.name())))
		        .securityContexts(singletonList(
		            SecurityContext.builder()
		                .securityReferences(
		                    singletonList(SecurityReference.builder()
		                        .reference("JWT")
		                        .scopes(new AuthorizationScope[0])
		                        .build()
		                    )
		                )
		                .build())
		        )
				.produces(produces).consumes(consumes).globalResponseMessage(RequestMethod.GET, getMessages)
	            .globalResponseMessage(RequestMethod.GET, getMessages);

	}
	
	final Predicate<RequestHandler> requestHandlers() {
		
		   Set<Predicate<RequestHandler>> matchers = new HashSet<Predicate<RequestHandler>>();
		   matchers.add(RequestHandlerSelectors.basePackage("com.salesmanager.shop.store.api.v1"));
		   matchers.add(RequestHandlerSelectors.basePackage("com.salesmanager.shop.store.api.v2"));
		   
		   return Predicates.or(matchers);

	}

	@SuppressWarnings("rawtypes")
	private ApiInfo apiInfo() {
		return new ApiInfo("Shopizer REST API",
				"API for Shopizer e-commerce. Contains public end points as well as private end points requiring basic authentication and remote authentication based on jwt bearer token. URL patterns containing /private/** use bearer token; those are authorized customer and administrators administration actions.",
				"1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<VendorExtension>());

	}

	private static ArrayList<? extends SecurityScheme> securitySchemes() {
		return (ArrayList<? extends SecurityScheme>) Stream.of(new ApiKey("Bearer", "Authorization", "header"))
				.collect(Collectors.toList());
	}

}
