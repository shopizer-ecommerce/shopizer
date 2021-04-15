package com.salesmanager.shop.store.api.v1.product;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.salesmanager.shop.model.catalog.product.variation.PersistableProductVariation;
import com.salesmanager.shop.model.catalog.product.variation.ReadableProductVariation;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.model.entity.ReadableEntityList;
import com.salesmanager.shop.store.controller.product.facade.ProductVariationFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/api/v1")
@Api(tags = {"Product variation api - options and option value used for creating a product instance"})
@SwaggerDefinition(tags = {
    @Tag(name = "Product variation resource", description = "Manage product variation")
})
public class ProductVariationApi {
	
	@Autowired
	private ProductVariationFacade productVariationFacade;
	

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = { "/private/product/variation" }, method = RequestMethod.POST)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void create(
			@Valid @RequestBody PersistableProductVariation variation, 
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		productVariationFacade.create(variation, merchantStore, language);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = { "/private/product/variation/unique" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	@ApiOperation(httpMethod = "GET", value = "Check if option set code already exists", notes = "", response = EntityExists.class)
	public ResponseEntity<EntityExists> exists(
			@RequestParam(value = "code") String code,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {

		boolean isOptionExist = productVariationFacade.exists(code, merchantStore);
		return new ResponseEntity<EntityExists>(new EntityExists(isOptionExist), HttpStatus.OK);
	}


	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/variation/{id}" }, method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ResponseBody
	public ReadableProductVariation get(
			@PathVariable Long id, 
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		return productVariationFacade.get(id, merchantStore, language);

	}


	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/variation/{id}" }, method = RequestMethod.PUT)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void update(
			@Valid @RequestBody PersistableProductVariation variation, 
			@PathVariable Long id,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {
		
		variation.setId(id);
		productVariationFacade.update(id, variation, merchantStore, language);

	}


	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/variation/{id}" }, method = RequestMethod.DELETE)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void delete(
			@PathVariable Long id,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		productVariationFacade.delete(id, merchantStore);

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/variation" }, method = RequestMethod.GET)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableEntityList<ReadableProductVariation> list(
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language,
			@RequestParam(value = "page", required = false, defaultValue="0") Integer page,
		    @RequestParam(value = "count", required = false, defaultValue="10") Integer count) {

		return productVariationFacade.list(merchantStore, language, page, count);

		
	}


}
