package com.salesmanager.shop.store.api.v1.user;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.security.Principal;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.user.UserCriteria;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.model.entity.UniqueEntity;
import com.salesmanager.shop.model.user.PersistableUser;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.model.user.ReadableUserList;
import com.salesmanager.shop.model.user.UserPassword;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

/** Api for managing admin users */
@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = { "User management resource (User Management Api)" })
@SwaggerDefinition(tags = { @Tag(name = "User management resource", description = "Manage administration users") })
public class UserApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);


	@Inject
	private UserFacade userFacade;

	/**
	 * Get userName by merchant code and userName
	 *
	 * @param storeCode
	 * @param name
	 * @param request
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping({ "/private/users/{id}" })
	@ApiOperation(httpMethod = "GET", value = "Get a specific user profile by user id", notes = "", produces = MediaType.APPLICATION_JSON_VALUE, response = ReadableUser.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", responseContainer = "User", response = ReadableUser.class),
			@ApiResponse(code = 400, message = "Error while getting User"),
			@ApiResponse(code = 401, message = "Login required") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	public ReadableUser get(@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, @PathVariable Long id,
			HttpServletRequest request) {

		String authenticatedUser = userFacade.authenticatedUser();
		if (authenticatedUser == null) {
			throw new UnauthorizedException();
		}
		// only admin and superadmin allowed
		userFacade.authorizedGroup(authenticatedUser, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()));

		return userFacade.findById(id, merchantStore, language);
	}

	/**
	 * Creates a new user
	 *
	 * @param store
	 * @param user
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = { "/private/user/" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Creates a new user", notes = "", response = ReadableUser.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableUser create(
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language,
			@Valid @RequestBody PersistableUser user, HttpServletRequest request) {
		/** Must be superadmin or admin */
		String authenticatedUser = userFacade.authenticatedUser();
		if (authenticatedUser == null) {
			throw new UnauthorizedException();
		}
		// only admin and superadmin allowed
		userFacade.authorizedGroup(authenticatedUser, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()));

		/** if user is admin, user must be in that store */
		if (!userFacade.userInRoles(authenticatedUser, Arrays.asList(Constants.GROUP_SUPERADMIN))) {
			if (!userFacade.authorizedStore(authenticatedUser, merchantStore.getCode())) {
				throw new UnauthorizedException("Operation unauthorized for user [" + authenticatedUser
						+ "] and store [" + merchantStore.getCode() + "]");
			}
		}

		return userFacade.create(user, merchantStore);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = { "/private/user/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	@ApiOperation(httpMethod = "PUT", value = "Updates a user", notes = "", response = ReadableUser.class)
	public ReadableUser update(@Valid @RequestBody PersistableUser user, @PathVariable Long id,
			@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language

	) {

		String authenticatedUser = userFacade.authenticatedUser();// requires
																	// user
																	// doing
																	// action

		userFacade.authorizedGroups(authenticatedUser, user);

		return userFacade.update(id, authenticatedUser, merchantStore, user);
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = { "/private/user/{id}/password" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PATCH", value = "Updates a user password", notes = "", response = Void.class)
	public void password(@Valid @RequestBody UserPassword password, @PathVariable Long id) {

		String authenticatedUser = userFacade.authenticatedUser();
		if (authenticatedUser == null) {
			throw new UnauthorizedException();
		}
		userFacade.changePassword(id, authenticatedUser, password);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = { "/private/users" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Get list of user", notes = "", response = ReadableUserList.class)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
		@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableUserList list(
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "count", required = false, defaultValue = "20") Integer count,
			@RequestParam(value = "emailAddress", required = false) String emailAddress) {

		String authenticatedUser = userFacade.authenticatedUser();
		if (authenticatedUser == null) {
			throw new UnauthorizedException();
		}

		UserCriteria criteria = new UserCriteria();
		if(!StringUtils.isBlank(emailAddress)) {
			criteria.setAdminEmail(emailAddress);
		}
		
		criteria.setStoreCode(merchantStore.getCode());

		if (!userFacade.userInRoles(authenticatedUser, Arrays.asList(Constants.GROUP_SUPERADMIN))) {
			if (!userFacade.authorizedStore(authenticatedUser, merchantStore.getCode())) {
				throw new UnauthorizedException("Operation unauthorized for user [" + authenticatedUser
						+ "] and store [" + merchantStore + "]");
			}
		}

		userFacade.authorizedGroup(authenticatedUser, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()));
		return userFacade.listByCriteria(criteria, page, count, language);
	}
	
	@PatchMapping(value = "/private/user/{id}/enabled", produces = { APPLICATION_JSON_VALUE })
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT") })
	public void updateEnabled(
			@PathVariable Long id, 
			@Valid @RequestBody PersistableUser user,
			@ApiIgnore MerchantStore merchantStore
			) {
		
		// superadmin, admin and retail_admin
		String authenticatedUser = userFacade.authenticatedUser();
		if (authenticatedUser == null) {
			throw new UnauthorizedException();
		}

		userFacade.authorizedGroup(authenticatedUser, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()));

		user.setId(id);
		userFacade.updateEnabled(merchantStore, user);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = { "/private/user/{id}" })
	@ApiOperation(httpMethod = "DELETE", value = "Deletes a user", notes = "", response = Void.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "string", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	public void delete(@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language, @PathVariable Long id,
			HttpServletRequest request) {

		/** Must be superadmin or admin */
		String authenticatedUser = userFacade.authenticatedUser();
		if (authenticatedUser == null) {
			throw new UnauthorizedException();
		}

		if (!userFacade.userInRoles(authenticatedUser, Arrays.asList(Constants.GROUP_SUPERADMIN))) {
			userFacade.authorizedStore(authenticatedUser, merchantStore.getCode());
		}

		userFacade.authorizedGroup(authenticatedUser, Stream.of(Constants.GROUP_SUPERADMIN, Constants.GROUP_ADMIN, Constants.GROUP_ADMIN_RETAIL).collect(Collectors.toList()));

		userFacade.delete(id, merchantStore.getCode());
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = { "/private/user/unique" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Check if username already exists", notes = "", response = EntityExists.class)
	public ResponseEntity<EntityExists> exists(@ApiIgnore MerchantStore merchantStore, @ApiIgnore Language language,
			@RequestBody UniqueEntity userName) {

		boolean isUserExist = true;// default user exist
		try {
			// will throw an exception if not fount
			userFacade.findByUserName(userName.getUnique(), userName.getMerchant(), language);

		} catch (ResourceNotFoundException e) {
			isUserExist = false;
		}
		return new ResponseEntity<EntityExists>(new EntityExists(isUserExist), HttpStatus.OK);
	}

	/**
	 * Get logged in customer profile
	 * 
	 * @param merchantStore
	 * @param language
	 * @param request
	 * @return
	 */
	@GetMapping("/private/user/profile")
	@ApiImplicitParams({ @ApiImplicitParam(name = "lang", dataType = "string", defaultValue = "en") })
	public ReadableUser getAuthUser(@ApiIgnore Language language, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String userName = principal.getName();
		ReadableUser user = userFacade.findByUserName(userName, null, language);

		if (!user.isActive()) {
			throw new UnauthorizedException("User " + userName + " not not active");
		}

		return user;

	}
}
