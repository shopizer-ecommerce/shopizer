package com.salesmanager.shop.store.api.v1.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.store.api.exception.RestApiException;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;
import com.salesmanager.shop.store.security.PasswordRequest;
import com.salesmanager.shop.store.security.ResetPasswordRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = { "User password reset resource (User password reset Api)" })
@SwaggerDefinition(tags = { @Tag(name = "User password reset resource", description = "User password reset") })
public class ResetUserPasswordApi {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResetUserPasswordApi.class);
	


	@Inject
	private UserFacade userFacade;

	/**
	 * Request a reset password token
	 * @param merchantStore
	 * @param language
	 * @param user
	 * @param request
	 */
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = { "/user/password/reset/request" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Launch user password reset flow", notes = "", response = ReadableUser.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void passwordResetRequest(
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language,
			@Valid @RequestBody ResetPasswordRequest user, HttpServletRequest request) {


		userFacade.requestPasswordReset(user.getUsername(), user.getReturnUrl(), merchantStore, language);

	}
	
	/**
	 * Verify a password token
	 * @param store
	 * @param token
	 * @param merchantStore
	 * @param language
	 * @param request
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = { "/user/{store}/reset/{token}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Validate user password reset token", notes = "", response = Void.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void passwordResetVerify(@PathVariable String store, @PathVariable String token,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, HttpServletRequest request) {

		/**
		 * Receives reset token Needs to validate if user found from token Needs
		 * to validate if token has expired
		 * 
		 * If no problem void is returned otherwise throw OperationNotAllowed
		 * All of this in UserFacade
		 */

		userFacade.verifyPasswordRequestToken(token, store);

	}

	/**
	 * Change password
	 * @param passwordRequest
	 * @param store
	 * @param token
	 * @param merchantStore
	 * @param language
	 * @param request
	 */
	@PostMapping(value = "/user/{store}/password/{token}", produces = {
			"application/json" })
	@ApiOperation(httpMethod = "POST", value = "Change user password", response = Void.class)
	public void changePassword(
			@RequestBody @Valid PasswordRequest passwordRequest, 
			@PathVariable String store,
			@PathVariable String token, @ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language,
			HttpServletRequest request) {

		// validate password
		if (StringUtils.isBlank(passwordRequest.getPassword())
				|| StringUtils.isBlank(passwordRequest.getRepeatPassword())) {
			throw new RestApiException("400", "Password don't match");
		}

		if (!passwordRequest.getPassword().equals(passwordRequest.getRepeatPassword())) {
			throw new RestApiException("400", "Password don't match");
		}

		userFacade.resetPassword(passwordRequest.getPassword(), token, store);

	}


}
