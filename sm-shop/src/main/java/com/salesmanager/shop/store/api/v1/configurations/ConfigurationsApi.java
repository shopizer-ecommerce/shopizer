package com.salesmanager.shop.store.api.v1.configurations;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.customer.PersistableCustomer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = { "Configurations management" })
@SwaggerDefinition(tags = {
		@Tag(name = "Configurations management", description = "Configurations management for modules") })
public class ConfigurationsApi {
	
	
	  /** Configurations of modules */
	  @PostMapping("/private/configurations")
	  @ApiOperation(
	      httpMethod = "POST",
	      value = "Manages configurations",
	      notes = "Requires administration access",
	      produces = "application/json",
	      response = Void.class)
	  @ApiImplicitParams({
	      @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT")
	  })
	  public Void create(
	      @ApiIgnore MerchantStore merchantStore,
	      @ApiIgnore Language language,
	      @Valid @RequestBody PersistableCustomer customer) {
	      //return customerFacade.create(customer, merchantStore, language);
		  return null;

	  }
	
	
	
	
	

}
