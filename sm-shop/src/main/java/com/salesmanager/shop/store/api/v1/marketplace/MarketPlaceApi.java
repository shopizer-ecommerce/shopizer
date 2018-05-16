package com.salesmanager.shop.store.api.v1.marketplace;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.shop.store.controller.store.facade.StoreFacade;

@Controller
public class MarketPlaceApi {
	
	@Inject
	private StoreFacade storeFacade;
	
	
	void create() throws Exception {
		
	}
	
	/**
	 * Get a marketplace from storeCode
	 * returns market place details and merchant store
	 */
    @ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping( value={"/private/marketplace/{storeCode}"}, method=RequestMethod.GET)
    public @ResponseBody String marketPlace(@PathVariable String store, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	
    	return null;
    }
		

}
