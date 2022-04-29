package com.salesmanager.shop.store.api.v2.product;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.product.instance.PersistableProductInstance;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.model.entity.Entity;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.model.entity.ReadableEntityList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;



/**
 * Api to manage ProductInstance
 * 
 * Product instance also known as product variant
 * allows to specify product size, sku and options related to this product instance
 * @author carlsamson
 *
 */
@Controller
@RequestMapping("/api/v2")
@Api(tags = {"Product instances api"})
@SwaggerDefinition(tags = {
    @Tag(name = "Product instances resource", description = "Manage inventory for a given product")
})
public class ProductInstanceApi {
  

  
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductInstanceApi.class);
  

	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = { "/private/product/{id}/instance" }, method = RequestMethod.POST)
	@ApiImplicitParams({ 
		    @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ApiOperation(httpMethod = "POST", value = "Creates a product instance", notes = "", produces = "application/json", response = Entity.class)
	public Entity create(
			@Valid @RequestBody PersistableProductInstance instance, 
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		//productVariationFacade.create(variation, merchantStore, language);
		return null;

	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/{id}/instance" }, method = RequestMethod.PUT)
	@ApiImplicitParams({ 
		    @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ApiOperation(httpMethod = "PUT", value = "Update product instance", notes = "", produces = "application/json", response = Void.class)
	public @ResponseBody PersistableProduct update(@PathVariable Long id,
			@Valid @RequestBody PersistableProductInstance instance, @ApiIgnore MerchantStore merchantStore) {

		return null;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = { "/private/product/{id}/instance/unique" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	@ApiOperation(httpMethod = "GET", value = "Check if option set code already exists", notes = "", response = EntityExists.class)
	public ResponseEntity<EntityExists> exists(@RequestParam(value = "code") String code,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {
		
		return null;
			
   }
	
	@RequestMapping(value = "/private/product/{id}/instance/{instanceId}", method = RequestMethod.GET)
	@ApiOperation(httpMethod = "GET", value = "Get a productinstance by id", notes = "For administration and shop purpose. Specifying ?merchant is required otherwise it falls back to DEFAULT")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Single product found", response = ReadableProductInstance.class) })
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableProductInstance get(@PathVariable final Long id, @RequestParam(value = "lang", required = false) String lang,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletResponse response)
			throws Exception {
		
		return null;

	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/instances" }, method = RequestMethod.GET)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableEntityList<ReadableProductInstance> list(
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language,
			@RequestParam(value = "page", required = false, defaultValue="0") Integer page,
		    @RequestParam(value = "count", required = false, defaultValue="10") Integer count) {

		return null;

		
	}

}
