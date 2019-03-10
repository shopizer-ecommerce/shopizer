package com.salesmanager.shop.store.api.v1.user;

import static com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;
import java.util.Map;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.ImmutableMap;
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

/**
 * Api for managing admin users
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
public class UserApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);

  @Inject
  private StoreFacade storeFacade;

  @Inject
  private UserFacade userFacade;

  @Inject
  private LanguageUtils languageUtils;
  
  @Inject
  private LanguageService languageService;
  
  private static final Map<String, String> MAPPING_FIELDS = ImmutableMap.<String, String>builder()
      .put("storeCode", "storeCode").build();

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
  @ApiOperation(httpMethod = "GET", value = "Get a specific user profile", notes = "",
      produces = MediaType.APPLICATION_JSON_VALUE, response = ReadableUser.class)
  public ReadableUser getByStore(
      @PathVariable Optional<String> store, @PathVariable String name, @RequestParam(name = "store",
          defaultValue = DEFAULT_STORE, required = false) String storeCode,
      HttpServletRequest request) {
    String storeCd = storeCode;
    if (store.isPresent()) {
      storeCd = store.get();
    }
    MerchantStore merchantStore = storeFacade.get(storeCd);
    Language language = languageUtils.getRESTLanguage(request, merchantStore);
    return userFacade.findByUserName(name, language);
  }

  /**
   * Creates a new user
   * 
   * @param store
   * @param user
   * @param storeCode
   * @return
   */
  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = {"/private/{store}/user/", "/private/user/"},
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "POST", value = "Creates a new user", notes = "",
      response = Void.class)
  public void create(@PathVariable Optional<String> store,
      @Valid @RequestBody PersistableUser user, @RequestParam(name = "store",
          defaultValue = DEFAULT_STORE, required = false) String storeCode,
      HttpServletRequest request) {
    /**
     * Must be superadmin or admin
     */
    String authenticatedUser = userFacade.authenticatedUser();
    if (authenticatedUser == null) {
      throw new UnauthorizedException();
    }

    userFacade.authorizedGroup(authenticatedUser,
        Stream.of("SUPERADMIN", "ADMIN").collect(Collectors.toList()));

    String storeCd = storeCode;
    if (store.isPresent()) {
      storeCd = store.get();
    }
    MerchantStore merchantStore = storeFacade.get(storeCd);

    /**
     * if user is admin, user must be in that store
     */
    if (!request.isUserInRole("SUPERADMIN")) {
      userFacade.authorizedStore(authenticatedUser, storeCd);
    }

    userFacade.create(user, merchantStore);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = {"/private/{store}/users/", "/private/users/"},
  produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(httpMethod = "GET", value = "Get list of user", notes = "",
      response = ReadableUserList.class)
  public ReadableUserList list(
      @RequestParam(value = "start", required = false) Integer start,
      @RequestParam(value = "length", required = false) Integer count,
      @RequestParam(value = "store", required = false) String store, HttpServletRequest request) {

    Criteria criteria = createCriteria(start, count, request);
    String drawParam = request.getParameter("draw");

    return userFacade.getByCriteria(languageService.defaultLanguage(), drawParam, criteria);
  }
  
  private Criteria createCriteria(Integer start, Integer count,
      HttpServletRequest request) {
    Criteria criteria = ServiceRequestCriteriaBuilderUtils
        .buildRequest(MAPPING_FIELDS, request);

    Optional.ofNullable(start).ifPresent(criteria::setStartIndex);
    Optional.ofNullable(count).ifPresent(criteria::setMaxCount);

    String search = criteria.getSearch();
    if (!StringUtils.isBlank(search)) {
      criteria.setCode(search);
      criteria.setName(search);
    }
    return criteria;
  }
}
