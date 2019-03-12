package com.salesmanager.shop.store.api.v1.security;

import static com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.security.ReadablePermission;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import io.swagger.annotations.ApiOperation;


/**
 * Api for managing security
 * @author carlsamson
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
public class SecurityApi {
  
  @Inject
  private StoreFacade storeFacade;
  
  @ResponseStatus(HttpStatus.OK)
  @GetMapping({"/private/{group}/permissions"})
  @ApiOperation(httpMethod = "GET", value = "Get permissions by group", notes = "",
      produces = MediaType.APPLICATION_JSON_VALUE, response = List.class)
  public List<ReadablePermission> listPermissions(
      @PathVariable String group, 
      @RequestParam(name = "store",
          defaultValue = DEFAULT_STORE, required = false) String storeCode,
      HttpServletRequest request) {
    
    String storeCd = storeCode;

    MerchantStore merchantStore = storeFacade.get(storeCd);
    return null;
    //return userFacade.findByUserName(name, language);
  }

}
