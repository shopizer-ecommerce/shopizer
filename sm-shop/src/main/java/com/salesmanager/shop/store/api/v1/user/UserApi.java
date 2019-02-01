package com.salesmanager.shop.store.api.v1.user;

import static com.salesmanager.core.business.constants.Constants.DEFAULT_STORE;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.security.ReadableGroup;
import com.salesmanager.shop.model.security.ReadablePermission;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;
import com.salesmanager.shop.utils.LanguageUtils;
import org.springframework.web.bind.annotation.RestController;


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

	@GetMapping("/private/users/{name}")
  public ReadableUser get(
      @PathVariable String name,
			@RequestParam(name = "store", defaultValue = DEFAULT_STORE) String storeCode,
			HttpServletRequest request) {
			MerchantStore merchantStore = storeFacade.get(storeCode);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);
			return userFacade.findByUserNameWithPermissions(name, language);
	}

}
