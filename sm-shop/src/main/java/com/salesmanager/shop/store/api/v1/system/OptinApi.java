package com.salesmanager.shop.store.api.v1.system;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.system.optin.OptinService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.core.model.system.optin.OptinType;
import com.salesmanager.shop.model.system.PersistableOptin;
import com.salesmanager.shop.model.system.ReadableOptin;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;

import io.swagger.annotations.ApiOperation;


/**
 * Optin a customer to events such s newsletter
 * @author carlsamson
 *
 */
@Controller
@RequestMapping("/api/v1")
public class OptinApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OptinApi.class);
	

	
	@Inject
	private OptinService optinService;
	

	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	
	/**
	 * Create new optin
	 */
	@RequestMapping( value={"/optin"}, method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(httpMethod = "POST", value = "Creates an optin event type definition", notes = "", produces = "application/json")
	@ResponseBody
	public ResponseEntity<ReadableOptin> create(@Valid @RequestBody PersistableOptin optin, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		MerchantStore merchantStore = storeFacade.getByCode(request);
		Language language = languageUtils.getRESTLanguage(request, merchantStore);
		
		
		try {
			
			Optin optinEntity = new Optin();
			
			optinEntity.setCode(optin.getCode());
			optinEntity.setDescription(optin.getDescription());
			optinEntity.setOptinType(OptinType.valueOf(optin.getOptinType()));
			optinEntity.setMerchant(merchantStore);
			
			
			optinService.create(optinEntity);
			
			ReadableOptin readable = new ReadableOptin();
			readable.setCode(optin.getCode());
			readable.setDescription(optin.getDescription());
			readable.setOptinType(optin.getOptinType());
			
			return new ResponseEntity<ReadableOptin>(HttpStatus.OK);


			
		} catch (Exception e) {
			LOGGER.error("Error while creating optin",e);
			try {
				response.sendError(503, "Error while creating optin " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}

		
	}





}
