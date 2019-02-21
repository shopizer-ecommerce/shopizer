package com.salesmanager.shop.store.api.v1.user;

import static com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;
import com.salesmanager.shop.utils.LanguageUtils;
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

  
  /**
   * Get userName by merchant code and userName
   * @param storeCode
   * @param name
   * @param request
   * @return
   */
  @GetMapping({"/private/{store}/users/{name}","/private/users/{name}"})
  @ApiOperation(httpMethod = "GET", value = "Get a specific user profile",
  notes = "", produces = "application/json", response = ReadableUser.class)
  public ReadableUser getByStore(
     @PathVariable Optional<String> store, 
     @PathVariable String name,
     @RequestParam(name = "store", defaultValue = DEFAULT_STORE, required=false) String storeCode,
     HttpServletRequest request) {
    String storeCd = storeCode;
    if (store.isPresent()) {
      storeCd = store.get();
    }
    MerchantStore merchantStore = storeFacade.get(storeCd);
    Language language = languageUtils.getRESTLanguage(request, merchantStore);
    return userFacade.findByUserNameWithPermissions(name, language);
  }

}
