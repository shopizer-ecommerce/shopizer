package com.salesmanager.shop.store.api.v1.user;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


/**
 * Api for managing admin users
 * @author carlsamson
 *
 */
@Controller
@RequestMapping("/api/v1")
public class UserApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private UserFacade userFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	@RequestMapping( value="/private/users/{name}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ReadableUser get(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(request);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);

			ReadableUser user = userFacade.findByUserName(name, language);
			
			if(user == null){
				response.sendError(404, "No User found for name : " + name);
			}
			
			/**
			 * Add permissions on top of the groups
			 */
			
			List<ReadableGroup> groups = user.getGroups();
			List<Integer> ids = new ArrayList<Integer>();
			for(ReadableGroup g : groups) {
				ids.add(g.getId().intValue());
			}
			
	    	List<ReadablePermission> permissions = userFacade.findPermissionsByGroups(ids);
	    	user.setPermissions(permissions);
			
			return user;
		} catch (Exception e) {
			LOGGER.error("Error while getting user",e);
			try {
				response.sendError(503, "Error while getting user " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
		
	}

}
