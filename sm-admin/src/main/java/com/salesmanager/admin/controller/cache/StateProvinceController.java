package com.salesmanager.admin.controller.cache;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.admin.model.references.Country;
import com.salesmanager.admin.model.references.Zone;
import com.salesmanager.admin.utils.CacheHelper;

@RestController
public class StateProvinceController {
	
	@Inject
	private CacheHelper cacheHelper;
	
	@RequestMapping("/admin/references/country")
	@Secured({"ROLE_STORE"})
	public ResponseEntity<List<Country>> country(HttpServletRequest request) throws Exception {
		
		Locale locale = LocaleContextHolder.getLocale();
		return new ResponseEntity<List<Country>>(cacheHelper.getCountry(locale), HttpStatus.OK);
		
	}
	
	@RequestMapping("/admin/references/zones")
	@Secured({"ROLE_STORE"})
	public ResponseEntity<List<Zone>> stateProvinces(@RequestParam("code") String code,HttpServletRequest request) throws Exception {
		
		Locale locale = LocaleContextHolder.getLocale();
		return new ResponseEntity<List<Zone>>(cacheHelper.getZones(code, locale), HttpStatus.OK);
		
	}

}
