package com.salesmanager.shop.store.api.v2.product;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.product.definition.PersistableProductDefinition;
import com.salesmanager.shop.model.catalog.product.product.definition.ReadableProductDefinition;
import com.salesmanager.shop.model.entity.Entity;
import com.salesmanager.shop.store.controller.product.facade.ProductCommonFacade;
import com.salesmanager.shop.store.controller.product.facade.ProductDefinitionFacade;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;

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
 * API to create, read, update and delete a Product API.
 *
 * @author Carl Samson
 */
@Controller
@RequestMapping("/api/v2")
@Api(tags = {
		"Product display and management resource (Product display and Management Api such as adding a product to category. Serves api v1 and v2 with backward compatibility)" })
@SwaggerDefinition(tags = {
		@Tag(name = "Product management resource, add product to category", description = "View product, Add product, edit product and delete product") })
public class ProductApiV2 {


	@Autowired
	private ProductDefinitionFacade productDefinitionFacade;
	
	@Autowired
	private ProductFacade productFacadeV2;
	
	@Autowired
	private ProductCommonFacade productCommonFacade;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductApiV2.class);


	/**
	 * ------------ V2
	 * 
	 * --- product definition
	 */

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = { "/private/product/definition" })
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody Entity createV2(@Valid @RequestBody PersistableProductDefinition product,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

		// make sure product id is null
		product.setId(null);
		Long id = productDefinitionFacade.saveProductDefinition(merchantStore, product, language);
		Entity returnEntity = new Entity();
		returnEntity.setId(id);
		return returnEntity;

	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = { "/private/product/definition/{id}" })
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void updateV2(@PathVariable Long id, @Valid @RequestBody PersistableProductDefinition product,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

		productDefinitionFacade.update(id, product, merchantStore, language);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = { "/private/product/definition/{id}" })
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableProductDefinition getV2(@PathVariable Long id, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		ReadableProductDefinition def = productDefinitionFacade.getProduct(merchantStore, id, language);
		return def;

	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/definition/{id}" }, method = RequestMethod.DELETE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void deleteV2(@PathVariable Long id, @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

		productCommonFacade.deleteProduct(id, merchantStore);
	}
	
	/**
	 * API for getting a product
	 *
	 * @param friendlyUrl
	 * @param lang        ?lang=fr|en
	 * @param response
	 * @return ReadableProduct
	 * @throws Exception
	 *                   <p>
	 *                   /api/products/123
	 */
	@RequestMapping(value = { "/products/slug/{friendlyUrl}",
			"/products/friendly/{friendlyUrl}" }, method = RequestMethod.GET)
	@ApiOperation(httpMethod = "GET", value = "Get a product by friendlyUrl (slug) version 2", notes = "For shop purpose. Specifying ?merchant is "
			+ "required otherwise it falls back to DEFAULT")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Single product found", response = ReadableProduct.class) })
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableProduct getByfriendlyUrl(
			@PathVariable final String friendlyUrl,
			@RequestParam(value = "lang", required = false) String lang, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletResponse response) throws Exception {
		
		ReadableProduct product = productFacadeV2.getProductBySeUrl(merchantStore, friendlyUrl, language);

		if (product == null) {
			response.sendError(404, "Product not fount for id " + friendlyUrl);
			return null;
		}

		return product;
	}

	
	/**
	 * API for getting a product using v2
	 *
	 * @param id
	 * @param lang     ?lang=fr|en|...
	 * @param response
	 * @return ReadableProduct
	 * @throws Exception
	 *                   <p>
	 *                   /api/products/123
	 */
	@RequestMapping(value = "/product/{uniqueCode}", method = RequestMethod.GET)
	@ApiOperation(httpMethod = "GET", value = "Get a product by id", notes = "For Shop purpose. Specifying ?merchant is required otherwise it falls back to DEFAULT")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Single product found", response = ReadableProduct.class) })
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableProduct get(@PathVariable final String uniqueCode, 
			@RequestParam(value = "lang", required = false) String lang,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {
		ReadableProduct product = productFacadeV2.getProductByCode(merchantStore, uniqueCode, language);



		return product;
	}
}
