package com.salesmanager.shop.admin.controller.configurations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.utils.CacheUtils;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;




@Controller
public class CacheController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);
	
	@Inject
	private CacheUtils cache;



	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/cache/cacheManagement.html", method=RequestMethod.GET)
	public String displayAccounts(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.setMenu(model, request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		//get cache keys
		List<String> cacheKeysList = cache.getCacheKeys(store);

		model.addAttribute("keys", cacheKeysList);

		return ControllerConstants.Tiles.Configuration.cache;
		
	}
	
	
	@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value="/admin/cache/clear.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> clearCache(HttpServletRequest request, HttpServletResponse response) {
		String cacheKey = request.getParameter("cacheKey");

		AjaxResponse resp = new AjaxResponse();

		try {

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

			StringBuilder key = new StringBuilder();
			key.append(store.getId()).append("_").append(cacheKey);
			
			if(cacheKey!=null) {
				cache.removeFromCache(key.toString());
			} else {
				cache.removeAllFromCache(store);
			}

			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		} catch (Exception e) {
			LOGGER.error("Error while updateing groups", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	

	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("cache", "cache");

		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("cache");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}
	

}
