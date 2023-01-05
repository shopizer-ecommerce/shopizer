package com.salesmanager.shop.store.api.v1.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductAttribute;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.api.PersistableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductAttributeEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductAttributeList;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionList;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionValueList;
import com.salesmanager.shop.model.entity.CodeEntity;
import com.salesmanager.shop.model.entity.Entity;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.store.controller.product.facade.ProductOptionFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/api/v1")
@Api(tags = { "Product attributes and options / options values management resource (Product Option Management Api)" })
@SwaggerDefinition(tags = {
		@Tag(name = "Product attributes and options / options values management resource", description = "Edit product attributes / options and product option values") })
public class ProductAttributeOptionApi {

	@Autowired
	private ProductOptionFacade productOptionFacade;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = { "/private/product/option" }, method = RequestMethod.POST)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableProductOptionEntity createOption(
			@Valid @RequestBody PersistableProductOptionEntity option, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletRequest request, HttpServletResponse response) {

		ReadableProductOptionEntity entity = productOptionFacade.saveOption(option, merchantStore, language);
		return entity;

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = { "/private/product/option/unique" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	@ApiOperation(httpMethod = "GET", value = "Check if option code already exists", notes = "", response = EntityExists.class)
	public ResponseEntity<EntityExists> optionExists(@RequestParam(value = "code") String code,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

		boolean isOptionExist = productOptionFacade.optionExists(code, merchantStore);
		return new ResponseEntity<EntityExists>(new EntityExists(isOptionExist), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = { "/private/product/option/value/unique" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	@ApiOperation(httpMethod = "GET", value = "Check if option value code already exists", notes = "", response = EntityExists.class)
	public ResponseEntity<EntityExists> optionValueExists(@RequestParam(value = "code") String code,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {
		boolean isOptionExist = productOptionFacade.optionValueExists(code, merchantStore);
		return new ResponseEntity<EntityExists>(new EntityExists(isOptionExist), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = { "/private/product/option/value" }, method = RequestMethod.POST)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableProductOptionValue createOptionValue(
			@Valid @RequestBody PersistableProductOptionValue optionValue,
			//@RequestParam(name = "file", required = false) MultipartFile file, 
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, 
			HttpServletRequest request, 
			HttpServletResponse response) {

		ReadableProductOptionValue entity = productOptionFacade.saveOptionValue( optionValue,
				merchantStore, language);
		return entity;

	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = { "/private/product/option/value/{id}/image" }, method = RequestMethod.POST)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void addOptionValueImage(
			@PathVariable Long id,
			@RequestParam(name = "file", required = true) MultipartFile file, 
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, 
			HttpServletRequest request, 
			HttpServletResponse response) {

		productOptionFacade.addOptionValueImage(file, id, merchantStore, language);


	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/option/value/{id}/image" }, method = RequestMethod.DELETE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void removeOptionValueImage(
			@PathVariable Long id,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, 
			HttpServletRequest request, HttpServletResponse response) {

		productOptionFacade.removeOptionValueImage(id, merchantStore, language);

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/option/{id}" }, method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ResponseBody
	public ReadableProductOptionEntity getOption(@PathVariable Long id, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletRequest request, HttpServletResponse response) {

		return productOptionFacade.getOption(id, merchantStore, language);

	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/option/value/{id}" }, method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ResponseBody
	public ReadableProductOptionValue getOptionValue(@PathVariable Long id, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletRequest request, HttpServletResponse response) {

		return productOptionFacade.getOptionValue(id, merchantStore, language);

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/option/{optionId}" }, method = RequestMethod.PUT)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void updateOption(@Valid @RequestBody PersistableProductOptionEntity option, @PathVariable Long optionId,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletRequest request,
			HttpServletResponse response) {
		option.setId(optionId);
		productOptionFacade.saveOption(option, merchantStore, language);
		return;

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/option/{optionId}" }, method = RequestMethod.DELETE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void deleteOption(@PathVariable Long optionId, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletRequest request, HttpServletResponse response) {

		productOptionFacade.deleteOption(optionId, merchantStore);
		return;

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/option/value/{id}" }, method = RequestMethod.PUT)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void updateOptionValue(
			@PathVariable Long id,
			@Valid @RequestBody PersistableProductOptionValue optionValue,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletRequest request, 
			HttpServletResponse response) {

		optionValue.setId(id);
		productOptionFacade.saveOptionValue(optionValue, merchantStore, language);
		return;

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/option/value/{id}" }, method = RequestMethod.DELETE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void deleteOptionValue(
			@PathVariable Long id,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletRequest request, 
			HttpServletResponse response) {

		productOptionFacade.deleteOptionValue(id, merchantStore);
		return;

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/options" }, method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableProductOptionList options(
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, 
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "count", required = false, defaultValue = "10") Integer count) {

		return productOptionFacade.options(merchantStore, language, name, page, count);

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/options/values" }, method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody ReadableProductOptionValueList optionsValues(
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "count", required = false, defaultValue = "10") Integer count) {

		return productOptionFacade.optionValues(merchantStore, language, name, page, count);

	}
	
	/**
	 * Product attributes
	 * @param id
	 * @param merchantStore
	 * @param language
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/{id}/attributes" }, method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ApiOperation(httpMethod = "GET", value = "Get product attributes", notes = "",
    response = ReadableProductAttributeList.class)
	public @ResponseBody ReadableProductAttributeList attributes(
			@PathVariable Long id,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, 
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "count", required = false, defaultValue = "10") Integer count) {

		return productOptionFacade.getAttributesList(id, merchantStore, language, page, count);

	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/{id}/attribute/{attributeId}" }, method = RequestMethod.GET)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ApiOperation(httpMethod = "GET", value = "Get product attributes", notes = "",
      response = EntityExists.class)
	public @ResponseBody ReadableProductAttributeEntity getAttribute(
			@PathVariable Long id,
			@PathVariable Long attributeId,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletRequest request, HttpServletResponse response) {

		ReadableProductAttributeEntity entity = productOptionFacade.getAttribute(id, attributeId, merchantStore, language);
		return entity;

	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = { "/private/product/{id}/attribute" }, method = RequestMethod.POST)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody Entity createAttribute(
			@PathVariable Long id,
			@Valid @RequestBody PersistableProductAttribute attribute, 
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, 
			HttpServletRequest request, 
			HttpServletResponse response) {

		ReadableProductAttributeEntity attributeEntity = productOptionFacade.saveAttribute(id, attribute, merchantStore, language);

		Entity entity = new Entity();
		entity.setId(attributeEntity.getId());
		return entity;


	}
	
	/**
	 * Create multiple attributes
	 * @param id
	 * @param attributeId
	 * @param merchantStore
	 * @param language
	 * @param request
	 * @param response
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = { "/private/product/{id}/attributes" }, method = RequestMethod.POST)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ApiOperation(httpMethod = "POST", value = "Saves multiple attributes", produces = "application/json", response = CodeEntity.class)
	public List<CodeEntity> createAttributes(
			@PathVariable Long id,
			@Valid @RequestBody List<PersistableProductAttribute> attributes, 
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {
		
		
		return productOptionFacade.createAttributes(attributes, id, merchantStore);

	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/{id}/attribute/{attributeId}" }, method = RequestMethod.PUT)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void updateAttribute(@PathVariable Long id, @Valid @RequestBody PersistableProductAttribute attribute, @PathVariable Long attributeId,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletRequest request,
			HttpServletResponse response) {

		attribute.setId(attributeId);
		productOptionFacade.saveAttribute(id, attribute, merchantStore, language);
		return;

	}
	


	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = { "/private/product/{id}/attribute/{attributeId}" }, method = RequestMethod.DELETE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void deleteAttribute(@PathVariable Long id,@PathVariable Long attributeId, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletRequest request, HttpServletResponse response) {

		productOptionFacade.deleteAttribute(id, attributeId, merchantStore);
		return;

	}

}