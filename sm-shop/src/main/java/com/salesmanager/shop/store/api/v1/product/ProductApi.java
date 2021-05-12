package com.salesmanager.shop.store.api.v1.product;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.LightPersistableProduct;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.ProductPriceRequest;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;
import com.salesmanager.shop.model.catalog.product.ReadableProductPrice;
import com.salesmanager.shop.model.catalog.product.product.definition.PersistableProductDefinition;
import com.salesmanager.shop.model.catalog.product.product.definition.ReadableProductDefinition;
import com.salesmanager.shop.model.entity.Entity;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.product.facade.ProductDefinitionFacade;
import com.salesmanager.shop.store.controller.product.facade.ProductFacade;
import com.salesmanager.shop.utils.ImageFilePath;

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
@RequestMapping("/api")
@Api(tags = { "Product display and management resource (Product display and Management Api. Serves api v1 and v2 with backward compatibility)" })
@SwaggerDefinition(tags = {
		@Tag(name = "Product management resource", description = "View product, Add product, edit product and delete product") })
public class ProductApi {

	@Inject
	private CategoryService categoryService;

	@Inject
	private ProductService productService;

	@Inject
	private ProductFacade productFacade;

	@Autowired
	private ProductDefinitionFacade productDefinitionFacade;

	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductApi.class);

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = { "/v1/private/product", "/auth/products" }, // private
																			// for
																			// api
																			// //auth
																			// for
																			// user
																			// adding
																			// products
			method = RequestMethod.POST)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody PersistableProduct create(@Valid @RequestBody PersistableProduct product,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletRequest request,
			HttpServletResponse response) {

		productFacade.saveProduct(merchantStore, product, language);
		return product;

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/v1/private/product/{id}", "/auth/product/{id}" }, method = RequestMethod.PUT)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ApiOperation(httpMethod = "PUT", value = "Update product", notes = "", produces = "application/json", response = PersistableProduct.class)
	public @ResponseBody PersistableProduct update(@PathVariable Long id,
			@Valid @RequestBody PersistableProduct product, @ApiIgnore MerchantStore merchantStore,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			// Make sure we have consistency in this request
			if (!id.equals(product.getId())) {
				response.sendError(400, "Error url id does not match object id");
				return null;
			}

			PersistableProduct saved = productFacade.saveProduct(merchantStore, product,
					merchantStore.getDefaultLanguage());
			return saved;
		} catch (Exception e) {
			LOGGER.error("Error while updating product", e);
			try {
				response.sendError(503, "Error while updating product " + e.getMessage());
			} catch (Exception ignore) {
			}

			return null;
		}
	}

	/** updates price quantity **/
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = "/v1/private/product/{id}", produces = { APPLICATION_JSON_VALUE })
	@ApiOperation(httpMethod = "PATCH", value = "Update product inventory", notes = "Updates product inventory", produces = "application/json", response = Void.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	public void update(@PathVariable Long id, @Valid @RequestBody LightPersistableProduct product,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {
		productFacade.update(id, product, merchantStore, language);
		return;

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/v1/private/product/{id}", "/auth/product/{id}" }, method = RequestMethod.DELETE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void delete(@PathVariable Long id, @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

		productFacade.deleteProduct(id, merchantStore);
	}

	/**
	 * Filtering product lists based on product attributes ?category=1
	 * &manufacturer=2 &type=... &lang=en|fr NOT REQUIRED, will use request
	 * language &start=0 NOT REQUIRED, can be used for pagination &count=10 NOT
	 * REQUIRED, can be used to limit item count
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1/products", method = RequestMethod.GET)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableProductList list(@RequestParam(value = "lang", required = false) String lang,
			@RequestParam(value = "category", required = false) Long category,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "sku", required = false) String sku,
			@RequestParam(value = "manufacturer", required = false) Long manufacturer,
			@RequestParam(value = "optionValues", required = false) List<Long> optionValueIds,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "owner", required = false) Long owner,
			@RequestParam(value = "page", required = false) Integer page, // current
																			// page
																			// 0
																			// ..
																			// n
																			// allowing
																			// navigation
			@RequestParam(value = "count", required = false) Integer count, // count
																			// per
																			// page
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ProductCriteria criteria = new ProductCriteria();

		// do not use legacy pagination anymore
		if (lang != null) {
			criteria.setLanguage(lang);
		} else {
			criteria.setLanguage(language.getCode());
		}
		if (!StringUtils.isBlank(status)) {
			criteria.setStatus(status);
		}
		if (category != null) {
			List<Long> categoryIds = new ArrayList<Long>();
			categoryIds.add(category);
			criteria.setCategoryIds(categoryIds);
		}
		if (manufacturer != null) {
			criteria.setManufacturerId(manufacturer);
		}

		if (CollectionUtils.isNotEmpty(optionValueIds)) {
			criteria.setOptionValueIds(optionValueIds);
		}

		if (owner != null) {
			criteria.setOwnerId(owner);
		}

		if (page != null) {
			criteria.setStartPage(page);
		}

		if (count != null) {
			criteria.setMaxCount(count);
		}

		if (!StringUtils.isBlank(name)) {
			criteria.setProductName(name);
		}

		if (!StringUtils.isBlank(sku)) {
			criteria.setCode(sku);
		}

		// TODO
		// RENTAL add filter by owner
		// REPOSITORY to use the new filters

		try {
			return productFacade.getProductListsByCriterias(merchantStore, language, criteria);

		} catch (Exception e) {

			LOGGER.error("Error while filtering products product", e);
			try {
				response.sendError(503, "Error while filtering products " + e.getMessage());
			} catch (Exception ignore) {
			}

			return null;
		}
	}

	/**
	 * API for getting a product
	 *
	 * @param id
	 * @param lang
	 *            ?lang=fr|en|...
	 * @param response
	 * @return ReadableProduct
	 * @throws Exception
	 *             <p>
	 *             /api/v1/products/123
	 */
	@RequestMapping(value = "/v1/products/{id}", method = RequestMethod.GET)
	@ApiOperation(httpMethod = "GET", value = "Get a product by id", notes = "For administration and shop purpose. Specifying ?merchant is required otherwise it falls back to DEFAULT")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Single product found", response = ReadableProduct.class) })
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableProduct get(@PathVariable final Long id, @RequestParam(value = "lang", required = false) String lang,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletResponse response)
			throws Exception {
		ReadableProduct product = productFacade.getProduct(merchantStore, id, language);

		if (product == null) {
			response.sendError(404, "Product not fount for id " + id);
			return null;
		}

		return product;
	}

	@RequestMapping(value = "/v1/product/{id}/price", method = RequestMethod.POST)
	@ApiOperation(httpMethod = "POST", value = "Calculate product price with variants", notes = "Product price calculation from variamts")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Price calculated", response = ReadableProductPrice.class) })
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableProductPrice price(@PathVariable final Long id, @RequestBody ProductPriceRequest variants,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

		return productFacade.getProductPrice(id, variants, merchantStore, language);

	}

	/**
	 * API for getting a product
	 *
	 * @param friendlyUrl
	 * @param lang
	 *            ?lang=fr|en
	 * @param response
	 * @return ReadableProduct
	 * @throws Exception
	 *             <p>
	 *             /api/v1/products/123
	 */
	@RequestMapping(value = { "/v1/products/slug/{friendlyUrl}",
			"/products/friendly/{friendlyUrl}" }, method = RequestMethod.GET)
	@ApiOperation(httpMethod = "GET", value = "Get a product by friendlyUrl (slug)", notes = "For administration and shop purpose. Specifying ?merchant is "
			+ "required otherwise it falls back to DEFAULT")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Single product found", response = ReadableProduct.class) })
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableProduct getByfriendlyUrl(@PathVariable final String friendlyUrl,
			@RequestParam(value = "lang", required = false) String lang, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletResponse response) throws Exception {
		ReadableProduct product = productFacade.getProductBySeUrl(merchantStore, friendlyUrl, language);

		if (product == null) {
			response.sendError(404, "Product not fount for id " + friendlyUrl);
			return null;
		}

		return product;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = { "/v1/private/product/unique" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT") })
	@ApiOperation(httpMethod = "GET", value = "Check if product code already exists", notes = "", response = EntityExists.class)
	public ResponseEntity<EntityExists> exists(@RequestParam(value = "code") String code,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

		boolean exists = productFacade.exists(code, merchantStore);
		return new ResponseEntity<EntityExists>(new EntityExists(exists), HttpStatus.OK);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = { "/v1/private/product/{productId}/category/{categoryId}",
			"/v1/auth/product/{productId}/category/{categoryId}" }, method = RequestMethod.POST)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableProduct addProductToCategory(@PathVariable Long productId,
			@PathVariable Long categoryId, @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language,
			HttpServletResponse response) throws Exception {

		try {
			// get the product
			Product product = productService.getById(productId);

			if (product == null) {
				throw new ResourceNotFoundException("Product id [" + productId + "] is not found");
			}

			if (product.getMerchantStore().getId().intValue() != merchantStore.getId().intValue()) {
				throw new UnauthorizedException(
						"Product id [" + productId + "] does not belong to store [" + merchantStore.getCode() + "]");
			}

			Category category = categoryService.getById(categoryId);

			if (category == null) {
				throw new ResourceNotFoundException("Category id [" + categoryId + "] is not found");
			}

			if (category.getMerchantStore().getId().intValue() != merchantStore.getId().intValue()) {
				throw new UnauthorizedException(
						"Category id [" + categoryId + "] does not belong to store [" + merchantStore.getCode() + "]");
			}

			return productFacade.addProductToCategory(category, product, language);

		} catch (Exception e) {
			LOGGER.error("Error while adding product to category", e);
			try {
				response.sendError(503, "Error while adding product to category " + e.getMessage());
			} catch (Exception ignore) {
			}

			return null;
		}
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/v1/private/product/{productId}/category/{categoryId}",
			"/v1/auth/product/{productId}/category/{categoryId}" }, method = RequestMethod.DELETE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableProduct removeProductFromCategory(@PathVariable Long productId,
			@PathVariable Long categoryId, @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language,
			HttpServletResponse response) {

		try {
			Product product = productService.getById(productId);

			if (product == null) {
				throw new ResourceNotFoundException("Product id [" + productId + "] is not found");
			}

			if (product.getMerchantStore().getId().intValue() != merchantStore.getId().intValue()) {
				throw new UnauthorizedException(
						"Product id [" + productId + "] does not belong to store [" + merchantStore.getCode() + "]");
			}

			Category category = categoryService.getById(categoryId);

			if (category == null) {
				throw new ResourceNotFoundException("Category id [" + categoryId + "] is not found");
			}

			if (category.getMerchantStore().getId().intValue() != merchantStore.getId().intValue()) {
				throw new UnauthorizedException(
						"Category id [" + categoryId + "] does not belong to store [" + merchantStore.getCode() + "]");
			}

			return productFacade.removeProductFromCategory(category, product, language);

		} catch (Exception e) {
			LOGGER.error("Error while removing product from category", e);
			try {
				response.sendError(503, "Error while removing product from category " + e.getMessage());
			} catch (Exception ignore) {
			}

			return null;
		}
	}

	/**
	 * ------------ V2
	 * 
	 * --- product definition
	 */

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = { "/v2/private/product/definition" })
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
	@PutMapping(value = { "/v2/private/product/definition/{id}" })
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void updateV2(@PathVariable Long id, @Valid @RequestBody PersistableProductDefinition product,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

		productDefinitionFacade.update(id, product, merchantStore, language);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = { "/v2/private/product/definition/{id}" })
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableProductDefinition getV2(
			@PathVariable Long id, 
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {

		ReadableProductDefinition def = productDefinitionFacade.getProduct(merchantStore, id, language);
		return def;

	}
}
