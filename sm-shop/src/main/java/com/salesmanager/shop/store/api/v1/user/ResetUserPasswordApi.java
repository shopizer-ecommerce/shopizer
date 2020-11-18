package com.salesmanager.shop.store.api.v1.user;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.user.PersistableUser;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = { "User password management resource (User password Management Api)" })
@SwaggerDefinition(tags = { @Tag(name = "User password management resource", description = "User password management") })
public class ResetUserPasswordApi {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResetUserPasswordApi.class);


	@Inject
	private UserFacade userFacade;
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = { "/user/password/reset/request" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Launch user password reset flow", notes = "", response = ReadableUser.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void passwordResetRequest(
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language,
			@Valid @RequestBody PersistableUser user, HttpServletRequest request) {

		/**
		 * User sends username (unique in the system)
		 * System retrieves user
		 * if user exists, system sends an email with reset password link
		 * 
		 * email link
		 * - Saved as a new column in User
		 * - Expiration date of the link
		 * 
		 * link contains
		 * - Encrypted string
		 * 
		 * Request
		 * - Decrypt link
		 * - Validate link and email
		 * - Send email to user with reset password link
		 */
		

	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = { "/user/password/reset" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Launch user password reset flow", notes = "", response = ReadableUser.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void passwordReset(
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language,
			@Valid @RequestBody PersistableUser user, HttpServletRequest request) {

		/**
		 * Reset password
		 */
		

	}

}
