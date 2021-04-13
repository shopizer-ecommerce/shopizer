package com.salesmanager.shop.store.api.v1.marketplace;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.marketplace.ReadableMarketPlace;
import com.salesmanager.shop.model.marketplace.SignupStore;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.store.api.exception.OperationNotAllowedException;
import com.salesmanager.shop.store.controller.marketplace.facade.MarketPlaceFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;
import com.salesmanager.shop.utils.LanguageUtils;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1")
public class MarketPlaceApi {
	
	@Autowired
	private MarketPlaceFacade marketPlaceFacade;
	
	
	@Autowired
	private UserFacade userFacade;
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;

  /** Get a marketplace from storeCode returns market place details and merchant store */
  @GetMapping("/private/marketplace/{store}")
  @ApiOperation(
      httpMethod = "GET",
      value = "Get market place meta-data",
      notes = "",
      produces = "application/json",
      response = ReadableMarketPlace.class)
  public ReadableMarketPlace marketPlace(
      @PathVariable String store,
      @RequestParam(value = "lang", required = false) String lang) {

    Language language = languageUtils.getServiceLanguage(lang);
    return marketPlaceFacade.get(store, language);
  }
  
  //signup new merchant
  @PostMapping("/store/signup")
  @ApiOperation(
      httpMethod = "POST",
      value = "Signup store",
      notes = "",
      produces = "application/json",
      response = Void.class)
  public void signup(
		  @RequestBody SignupStore store,
		  @ApiIgnore Language language) {
	  
	  //check if user exists
	  ReadableUser user = userFacade.findByUserName(store.getEmail());
	  
	  if(user!=null) {
		  throw new OperationNotAllowedException("User [" + store.getEmail() + "] already exist and cannot be registered");
	  }
	  
	  //check if store exists
	  if(storeFacade.existByCode(store.getCode())) {
		  throw new OperationNotAllowedException("Store [" + store.getCode()+ "] already exist and cannot be registered");
	  }
	  
	  //create user
	  
	  //create store
	  
	  //send notification


  }
}
