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

import com.salesmanager.admin.model.references.Language;
import com.salesmanager.admin.utils.CacheHelper;

@RestController
public class LanguageController {
	
	@Inject
	private CacheHelper cacheHelper;
	
	@RequestMapping("/admin/references/languages")
	@Secured({"ROLE_STORE"})
	public ResponseEntity<List<Language>> languages(HttpServletRequest request) throws Exception {
		
		Locale locale = LocaleContextHolder.getLocale();
		return new ResponseEntity<List<Language>>(cacheHelper.getLanguages(locale), HttpStatus.OK);

	}

}
