package com.salesmanager.shop.store.api.v1.customer;

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
import com.salesmanager.shop.model.user.PersistableUser;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.model.user.UserNameEntity;

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
public class ResetCustomerPasswordApi {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResetCustomerPasswordApi.class);


	@Inject
	private com.salesmanager.shop.store.controller.customer.facade.v1.CustomerFacade customerFacade;
	
	//flow example
	//https://stackabuse.com/spring-security-forgot-password-functionality/#disqus_thread
	
	
	/**
	 * Request a reset password token
	 * @param merchantStore
	 * @param language
	 * @param user
	 * @param request
	 */
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = { "/customer/password/reset/request" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Launch customer password reset flow", notes = "", response = ReadableUser.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void passwordResetRequest(
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language,
			@Valid @RequestBody UserNameEntity user, 
			HttpServletRequest request) {
		
		
		customerFacade.requestPasswordReset(user.getUserName(), request.getContextPath(), merchantStore, language);

		

	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = { "/customer/{store}/reset/{token}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Validate customer password reset token", notes = "", response = ReadableUser.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void passwordReset(
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language,
			@Valid @RequestBody PersistableUser user, HttpServletRequest request) {

		/**
		 * Receives reset token
		 * Needs to validate if user found from token
		 * Needs to validate if token has expired
		 * 
		 * If no problem void is returned otherwise throw OperationNotAllowed
		 * All of this in UserFacade
		 */
		

	}

}
