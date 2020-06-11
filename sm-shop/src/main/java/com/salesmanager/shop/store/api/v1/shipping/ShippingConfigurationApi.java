package com.salesmanager.shop.store.api.v1.shipping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/api/v1")
@Api(tags = { "Shipping configuration resource (Shipping Management Api)" })
@SwaggerDefinition(tags = { @Tag(name = "Shipping management resource", description = "Manage shipping configuration") })
public class ShippingConfigurationApi {
	
	
	//get shipping origin
	
	//create shipping origin
	
	//update shipping origin
	
	//get shipping configuration
	
	//save shipping configuration
	
	//get packing
	
	//save packing

}
