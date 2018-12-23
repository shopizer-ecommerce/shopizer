package com.salesmanager.shop.store.api.v1.store;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.shop.PersistableMerchantStore;
import com.salesmanager.shop.model.shop.ReadableMerchantStore;
import com.salesmanager.shop.model.system.ReadableOptin;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/v1")
public class MerchantStoreApi {
	

	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	@Inject LanguageService languageService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MerchantStoreApi.class);
	
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value={"/store/{store}"}, method=RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Get merchant store", notes = "", produces = "application/json", response = ReadableMerchantStore.class)
    public @ResponseBody ReadableMerchantStore store(@PathVariable String store, @RequestParam(value = "lang", required=false) String lang, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	Language l = languageUtils.getServiceLanguage(lang);
    	
    	
    	
    	ReadableMerchantStore readableStore = storeFacade.getByCode(store, l);
    	
		if(readableStore==null) {
			response.sendError(404,  "MerchanStore not found for merchant store [" + store + "]");
			return null;
		}
    	
    	return readableStore;
    }
    
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping( value={"/private/store"}, method=RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Creates a new store", notes = "", produces = "application/json", response = ReadableMerchantStore.class)
    public ResponseEntity<ReadableMerchantStore> create(@Valid @RequestBody PersistableMerchantStore store, HttpServletRequest request, HttpServletResponse response) throws Exception {


    	
    	//check if store code exists
    	MerchantStore mStore = storeFacade.get(store.getCode());
    	if(mStore != null) {
    		response.sendError(503, "MerhantStore " + store.getCode() + " already exists");
    	}
    	
    	try {
    	
    		storeFacade.create(store);
    		
    		ReadableMerchantStore readable = storeFacade.getByCode(store.getCode(), languageService.defaultLanguage());

    		return new ResponseEntity<ReadableMerchantStore>(readable,HttpStatus.OK);
    	
		} catch (Exception e) {
			LOGGER.error("Error while creating store product",e);
			try {
				response.sendError(503, "Error while creating store " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
    }

}
