package com.salesmanager.shop.store.api.v1.user;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.ImmutableMap;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.common.Criteria;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.user.PersistableUser;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.model.user.ReadableUserList;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;
import com.salesmanager.shop.utils.LanguageUtils;
import com.salesmanager.shop.utils.ServiceRequestCriteriaBuilderUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/** Api for managing admin users */
@RestController
@RequestMapping(value = "/api/v1")
public class UserApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);

  @Inject private StoreFacade storeFacade;

  @Inject private UserFacade userFacade;

  @Inject private LanguageUtils languageUtils;

  @Inject private LanguageService languageService;

  // mapping between readable field name and backend field name
  private static final Map<String, String> MAPPING_FIELDS =
      ImmutableMap.<String, String>builder()
          .put("emailAddress", "adminEmail")
          .put("userName", "adminName")
          .build();

  /**
   * Get userName by merchant code and userName
   *
   * @param storeCode
   * @param name
   * @param request
   * @return
   */
  @ResponseStatus(HttpStatus.OK)
  @GetMapping({"/private/{store}/users/{name}", "/private/users/{name}"})
  @ApiOperation(
      httpMethod = "GET",
      value = "Get a specific user profile",
      notes = "",
      produces = MediaType.APPLICATION_JSON_VALUE,
      response = ReadableUser.class)
  public ReadableUser get(
      @PathVariable Optional<String> store,
      @PathVariable String name,
          String storeCode,
      HttpServletRequest request) {
    String storeCd = store.orElse(Constants.DEFAULT_STORE);
    MerchantStore merchantStore = storeFacade.get(storeCd);
    Language language = languageUtils.getRESTLanguage(request, merchantStore);
    return userFacade.findByUserName(name, language);
  }

  /**
   * Creates a new user
   *
   * @param store
   * @param user
   * @return
   */
  @ResponseStatus(HttpStatus.OK)
  @PostMapping(
      value = {"/private/{store}/user/", "/private/user/"},
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
      httpMethod = "POST",
      value = "Creates a new user",
      notes = "",
      response = Void.class)
  public void create(
      @ApiParam(
              name = "store",
              value = "Optional - Store code",
              required = false,
              defaultValue = "DEFAULT")
          @PathVariable
          Optional<String> store,
      @Valid @RequestBody PersistableUser user,
      HttpServletRequest request) {
    /** Must be superadmin or admin */
    String authenticatedUser = userFacade.authenticatedUser();
    if (authenticatedUser == null) {
      throw new UnauthorizedException();
    }
    // only admin and superadmin allowed
    userFacade.authorizedGroup(
        authenticatedUser, Stream.of("SUPERADMIN", "ADMIN").collect(Collectors.toList()));

    String storeCd = Constants.DEFAULT_STORE;
    ;
    if (store.isPresent()) {
      storeCd = store.get();
    }
    MerchantStore merchantStore = storeFacade.get(storeCd);

    /** if user is admin, user must be in that store */
    if (!request.isUserInRole("SUPERADMIN")) {
      userFacade.authorizedStore(authenticatedUser, storeCd);
    }

    userFacade.create(user, merchantStore);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(
      value = {"/private/{store}/user/{userName}", "/private/user/{userName}"},
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
      httpMethod = "PUT",
      value = "Updates a user",
      notes = "",
      response = ReadableUser.class)
  public ReadableUser update(
      @Valid @RequestBody PersistableUser user,
      @PathVariable Optional<String> store,
      @PathVariable String userName,
      HttpServletRequest request) {

    String storeCd = store.orElse(Constants.DEFAULT_STORE);
    String authenticatedUser = userFacade.authenticatedUser();
    if (authenticatedUser == null) {
      throw new UnauthorizedException();
    }
    return userFacade.update(authenticatedUser, storeCd, user);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(
      value = {"/private/{store}/users/", "/private/users/"},
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
      httpMethod = "GET",
      value = "Get list of user",
      notes = "",
      response = ReadableUserList.class)
  public ReadableUserList list(
      @ApiParam(
              name = "store",
              value = "Optional - Store code",
              required = false,
              defaultValue = "DEFAULT")
          @PathVariable
          Optional<String> store,
      @RequestParam(value = "start", required = false) Integer start,
      @RequestParam(value = "length", required = false) Integer count,
      HttpServletRequest request) {

    String authenticatedUser = userFacade.authenticatedUser();
    if (authenticatedUser == null) {
      throw new UnauthorizedException();
    }

    String storeCd = store.orElse(Constants.DEFAULT_STORE);
    Criteria criteria = createCriteria(start, count, request);
    criteria.setStoreCode(storeCd);
    String drawParam = request.getParameter("draw");

    if (!request.isUserInRole("SUPERADMIN")) {
      userFacade.authorizedStore(authenticatedUser, storeCd);
    }

    userFacade.authorizedGroup(
        authenticatedUser, Stream.of("SUPERADMIN", "ADMIN").collect(Collectors.toList()));

    return userFacade.getByCriteria(languageService.defaultLanguage(), drawParam, criteria);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = {"/private/{store}/user/{userName}", "/private/user/{userName}"})
  @ApiOperation(httpMethod = "DELETE", value = "Deletes a user", notes = "", response = Void.class)
  public void delete(
      @PathVariable Optional<String> store,
      @PathVariable String userName,
      HttpServletRequest request) {

    /** Must be superadmin or admin */
    String authenticatedUser = userFacade.authenticatedUser();
    if (authenticatedUser == null) {
      throw new UnauthorizedException();
    }

    String storeCd = store.orElse(Constants.DEFAULT_STORE);

    if (!request.isUserInRole("SUPERADMIN")) {
      userFacade.authorizedStore(authenticatedUser, storeCd);
    }

    userFacade.authorizedGroup(
        authenticatedUser, Stream.of("SUPERADMIN", "ADMIN").collect(Collectors.toList()));

    userFacade.delete(userName);
  }

  private Criteria createCriteria(Integer start, Integer count, HttpServletRequest request) {
    Criteria criteria = ServiceRequestCriteriaBuilderUtils.buildRequest(MAPPING_FIELDS, request);

    Optional.ofNullable(start).ifPresent(criteria::setStartIndex);
    Optional.ofNullable(count).ifPresent(criteria::setMaxCount);

    return criteria;
  }
}
