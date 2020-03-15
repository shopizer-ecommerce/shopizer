package com.salesmanager.shop.store.api.v1.system;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = {"Health api contains various endpoints that can be used for remote diagnosis"})
@SwaggerDefinition(tags = {
    @Tag(name = "Health api", description = "Endpoints giving information on diagnosis")
})
/**
 * Different endpoints to be used for diagnosis
 * @author carlsamson
 *
 */
public class HealthApi {
	
	/**
	 * Simple alive / ping endpoint
	 */
	@GetMapping(value = "/health/ping")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> alive() {
		
		return new ResponseEntity<>("System is alive", 
			      HttpStatus.OK);
		
	}

}
