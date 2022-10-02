package com.salesmanager.shop.store.api.v1.product;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableProductPrice;
import com.salesmanager.shop.model.entity.Entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Use inventory
 * @author carlsamson
 *
 */

@Controller
@RequestMapping("/api/v1")
@Api(tags = { "Product price api" })
@SwaggerDefinition(tags = { @Tag(name = "Product price management", description = "Edit price and discount") })
public class ProductPriceApi {


	@Inject
	private ProductService productService;


	private static final Logger LOGGER = LoggerFactory.getLogger(ProductApi.class);

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = { "/private/product/{id}/inventory/{inventoryId}/price"},
			method = RequestMethod.POST)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public Entity create(
			@PathVariable Long id,
			@PathVariable Long inventoryId,
			@Valid @RequestBody PersistableProductPrice price,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {

		
		/*
		 * try { // get the product Product product = productService.getById(id);
		 * 
		 * if (product == null) { throw new ResourceNotFoundException("Product id [" +
		 * productId + "] is not found"); }
		 * 
		 * if (product.getMerchantStore().getId().intValue() !=
		 * merchantStore.getId().intValue()) { throw new UnauthorizedException(
		 * "Product id [" + productId + "] does not belong to store [" +
		 * merchantStore.getCode() + "]"); }
		 * 
		 * Category category = categoryService.getById(categoryId);
		 * 
		 * if (category == null) { throw new ResourceNotFoundException("Category id [" +
		 * categoryId + "] is not found"); }
		 * 
		 * if (category.getMerchantStore().getId().intValue() !=
		 * merchantStore.getId().intValue()) { throw new UnauthorizedException(
		 * "Category id [" + categoryId + "] does not belong to store [" +
		 * merchantStore.getCode() + "]"); }
		 * 
		 * return productFacade.addProductToCategory(category, product, language);
		 * 
		 * } catch (Exception e) {
		 * LOGGER.error("Error while adding product to category", e); try {
		 * response.sendError(503, "Error while adding product to category " +
		 * e.getMessage()); } catch (Exception ignore) { }
		 * 
		 * return null; }
		 */

		//productFacade.updateProductPrice(product, price, language);
		
		return null;


	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/{id}/inventory/{inventoryId}/price/{priceId}"},
			method = RequestMethod.PUT)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void edit(
			@PathVariable Long id,
			@PathVariable Long inventoryId,
			@PathVariable Long priceId,
			@Valid @RequestBody PersistableProductPrice price,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {
		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/{id}/inventory/{inventoryId}/price/{priceId}"},
			method = RequestMethod.DELETE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void delete(
			@PathVariable Long id,
			@PathVariable Long inventoryId,
			@PathVariable Long priceId,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {
		
	}

}
