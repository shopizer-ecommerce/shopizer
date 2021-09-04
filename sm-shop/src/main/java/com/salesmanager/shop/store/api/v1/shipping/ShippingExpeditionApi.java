package com.salesmanager.shop.store.api.v1.shipping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.references.ReadableCountry;
import com.salesmanager.shop.model.shipping.ExpeditionConfiguration;
import com.salesmanager.shop.store.controller.shipping.facade.ShippingFacade;
import com.salesmanager.shop.utils.AuthorizationUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1")
@Api(tags = { "Shipping - Expedition management resource (Shipping Management Api) - ship to country" })
@SwaggerDefinition(tags = { @Tag(name = "Shipping - Expedition management resource", description = "Manage shipping expedition") })
public class ShippingExpeditionApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingExpeditionApi.class);

	@Autowired
	private AuthorizationUtils authorizationUtils;
	
	@Autowired
	private ShippingFacade shippingFacade;

	@RequestMapping(value = { "/private/shipping/expedition" }, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ExpeditionConfiguration expedition(
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {


		String user = authorizationUtils.authenticatedUser();
		authorizationUtils.authorizeUser(user, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN,
				Constants.GROUP_SHIPPING, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()), merchantStore);

		return shippingFacade.getExpeditionConfiguration(merchantStore, language);

	}
	
	 @GetMapping("/shipping/country")
	  public List<ReadableCountry> 
	 	getCountry(
				@ApiIgnore MerchantStore merchantStore,
				@ApiIgnore Language language) {
	    return shippingFacade.shipToCountry(merchantStore, language);
	  }
	
	
	@RequestMapping(value = { "/private/shipping/expedition" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void saveExpedition(
			@RequestBody ExpeditionConfiguration expedition,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {


		String user = authorizationUtils.authenticatedUser();
		authorizationUtils.authorizeUser(user, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN,
				Constants.GROUP_SHIPPING, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()), merchantStore);

		shippingFacade.saveExpeditionConfiguration(expedition, merchantStore);

	}

}
