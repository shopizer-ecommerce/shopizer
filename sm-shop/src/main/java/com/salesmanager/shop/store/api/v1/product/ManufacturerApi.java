package com.salesmanager.shop.store.api.v1.product;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
import com.salesmanager.core.business.services.catalog.product.manufacturer.ManufacturerService;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.manufacturer.PersistableManufacturer;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturerList;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.model.entity.ListCriteria;
import com.salesmanager.shop.store.controller.manufacturer.facade.ManufacturerFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Manufacturer management Collection, Manufacturer ...
 *
 * @author c.samson
 */
@Controller
@RequestMapping("/api/v1")
@Api(tags = { "Manufacturer / Brand management resource (Manufacturer / Brand Management Api)" })
@SwaggerDefinition(tags = {
		@Tag(name = "Manufacturer / Brand Management Api", description = "Edit Manufacturer / Brand") })
public class ManufacturerApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerApi.class);

	@Inject
	private ManufacturerService manufacturerService;

	@Inject
	private ManufacturerFacade manufacturerFacade;

	/**
	 * Method for creating a manufacturer
	 *
	 * @param manufacturer
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/private/manufacturer", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public PersistableManufacturer create(@Valid @RequestBody PersistableManufacturer manufacturer,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletResponse response) {

		try {
			manufacturerFacade.saveOrUpdateManufacturer(manufacturer, merchantStore, language);

			return manufacturer;

		} catch (Exception e) {
			LOGGER.error("Error while creating manufacturer", e);
			try {
				response.sendError(503, "Error while creating manufacturer " + e.getMessage());
			} catch (Exception ignore) {
			}

			return null;
		}
	}

	@RequestMapping(value = "/manufacturers/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableManufacturer get(@PathVariable Long id, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletResponse response) {

		try {
			ReadableManufacturer manufacturer = manufacturerFacade.getManufacturer(id, merchantStore, language);

			if (manufacturer == null) {
				response.sendError(404, "No Manufacturer found for ID : " + id);
			}

			return manufacturer;

		} catch (Exception e) {
			LOGGER.error("Error while getting manufacturer", e);
			try {
				response.sendError(503, "Error while getting manufacturer " + e.getMessage());
			} catch (Exception ignore) {
			}
		}

		return null;
	}

	
	@RequestMapping(value = "/private/manufacturers/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ApiOperation(httpMethod = "GET", value = "List manufacturers by store", notes = "This request supports paging or not. Paging supports page number and request count", response = ReadableManufacturerList.class)
	public ReadableManufacturerList listByStore(
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "count", required = false, defaultValue = "10") Integer count) {

		ListCriteria listCriteria = new ListCriteria();
		listCriteria.setName(name);
		return manufacturerFacade.listByStore(merchantStore, language, listCriteria, page, count);
	}
	
	
	@RequestMapping(value = "/manufacturers/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ApiOperation(httpMethod = "GET", value = "List manufacturers by store", notes = "This request supports paging or not. Paging supports page number and request count", response = ReadableManufacturerList.class)
	public ReadableManufacturerList list(@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "count", required = false, defaultValue = "10") Integer count) {

		ListCriteria listCriteria = new ListCriteria();
		listCriteria.setName(name);
		return manufacturerFacade.getAllManufacturers(merchantStore, language, listCriteria, page, count);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = { "/private/manufacturer/unique" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT") })
	@ApiOperation(httpMethod = "GET", value = "Check if manufacturer code already exists", notes = "", response = EntityExists.class)
	public ResponseEntity<EntityExists> exists(@RequestParam(value = "code") String code,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language) {

		boolean exists = manufacturerFacade.manufacturerExist(merchantStore, code);
		return new ResponseEntity<EntityExists>(new EntityExists(exists), HttpStatus.OK);

	}

	@RequestMapping(value = "/private/manufacturer/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void update(@PathVariable Long id,
			@Valid @RequestBody PersistableManufacturer manufacturer, @ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language, HttpServletRequest request, HttpServletResponse response) {

		try {
			manufacturer.setId(id);
			manufacturerFacade.saveOrUpdateManufacturer(manufacturer, merchantStore, language);
		} catch (Exception e) {
			LOGGER.error("Error while creating manufacturer", e);
			try {
				response.sendError(503, "Error while creating manufacturer " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
	}

	@RequestMapping(value = "/manufacturer/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void delete(@PathVariable Long id, @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language,
			HttpServletResponse response) {

		try {
			Manufacturer manufacturer = manufacturerService.getById(id);

			if (manufacturer != null) {
				manufacturerFacade.deleteManufacturer(manufacturer, merchantStore, language);
			} else {
				response.sendError(404, "No Manufacturer found for ID : " + id);
			}

		} catch (Exception e) {
			LOGGER.error("Error while deleting manufacturer id " + id, e);
			try {
				response.sendError(503, "Error while deleting manufacturer id " + id + " - " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
	}

	@RequestMapping(value = "/category/{id}/manufacturers", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod = "GET", value = "Get all manufacturers for all items in a given category", notes = "", produces = "application/json", response = List.class)
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public List<ReadableManufacturer> manufacturerList(@PathVariable final Long id, // category
																					// id
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletResponse response)
			throws Exception {

		return manufacturerFacade.getByProductInCategory(merchantStore, language, id);

	}

}
