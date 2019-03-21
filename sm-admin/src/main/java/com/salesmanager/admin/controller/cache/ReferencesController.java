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
import org.springframework.web.bind.annotation.RestController;
import com.salesmanager.admin.model.references.Currency;
import com.salesmanager.admin.model.references.Group;
import com.salesmanager.admin.model.references.MeasureEnum;
import com.salesmanager.admin.model.references.Reference;
import com.salesmanager.admin.utils.CacheHelper;

@RestController
public class ReferencesController {
	
	@Inject
	private CacheHelper cacheHelper;
	
	@RequestMapping("/admin/references/currency")
	@Secured({"ROLE_STORE"})
	public ResponseEntity<List<Currency>> currency(HttpServletRequest request) throws Exception {
		
		Locale locale = LocaleContextHolder.getLocale();
		return new ResponseEntity<List<Currency>>(cacheHelper.getCurrency(), HttpStatus.OK);


	}
	
	@RequestMapping("/admin/references/weights")
	@Secured({"ROLE_STORE"})
	public ResponseEntity<List<Reference>> weight(HttpServletRequest request) throws Exception {
		
		Locale locale = LocaleContextHolder.getLocale();
		return new ResponseEntity<List<Reference>>(cacheHelper.getMeasures(MeasureEnum.weights, locale), HttpStatus.OK);


	}
	
	@RequestMapping("/admin/references/sizes")
	@Secured({"ROLE_STORE"})
	public ResponseEntity<List<Reference>> sizes(HttpServletRequest request) throws Exception {
		
		Locale locale = LocaleContextHolder.getLocale();
		return new ResponseEntity<List<Reference>>(cacheHelper.getMeasures(MeasureEnum.sizes, locale), HttpStatus.OK);


	}
	
    @RequestMapping("/admin/references/groups")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<Group>> groups(HttpServletRequest request) throws Exception {
        
        Locale locale = LocaleContextHolder.getLocale();
        return new ResponseEntity<List<Group>>(
            cacheHelper.getGroups(locale),
            HttpStatus.OK);


    }	

}
