package com.salesmanager.shop.store.api.v1.shipping;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.references.PersistableAddress;
import com.salesmanager.shop.model.references.ReadableAddress;
import com.salesmanager.shop.model.shipping.ExpeditionConfiguration;
import com.salesmanager.shop.store.controller.shipping.facade.ShippingFacade;
import com.salesmanager.shop.utils.AuthorizationUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1")
@Api(tags = { "Shipping configuration resource (Shipping Management Api)" })
@SwaggerDefinition(tags = { @Tag(name = "Shipping management resource", description = "Manage shipping configuration") })
public class ShippingConfigurationApi {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingConfigurationApi.class);

	@Autowired
	private AuthorizationUtils authorizationUtils;
	
	@Autowired
	private ShippingFacade shippingFacade;
	
	
	@RequestMapping(value = { "/private/origin" }, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ReadableAddress shippingOrigin(
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {


		String user = authorizationUtils.authenticatedUser();
		authorizationUtils.authorizeUser(user, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN,
				Constants.GROUP_SHIPPING, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()), merchantStore);

		return shippingFacade.getShippingOrigin(merchantStore);

	}
	
	@RequestMapping(value = { "/private/origin" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void saveShippingOrigin(
			PersistableAddress address,
			@ApiIgnore MerchantStore merchantStore,
			@ApiIgnore Language language) {


		String user = authorizationUtils.authenticatedUser();
		authorizationUtils.authorizeUser(user, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN,
				Constants.GROUP_SHIPPING, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()), merchantStore);

		shippingFacade.saveShippingOrigin(address, merchantStore);

	}

	
	//get shipping configuration
	
	//save shipping configuration
	
	//get packing
	
	//save packing

}
