package com.salesmanager.shop.store.api.v1.references;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.reference.language.Language;

@Controller
@RequestMapping("/api/v1")
public class ReferencesApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReferencesApi.class);

	
	@Inject
	private LanguageService languageService;
	
	
	/**
	 * Search languages by language code
	 * private/languages returns everything
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/languages", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Language>> getLanguages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		try {
			
			List<Language> langs = languageService.getLanguages();
			
			if(CollectionUtils.isEmpty(langs)){
				response.sendError(404, "No languages found");
			}
			
			return ResponseEntity.accepted().body(langs);
		} catch (Exception e) {
			LOGGER.error("Error while getting languages",e);
			try {
				response.sendError(503, "Error while getting languages " + e.getMessage());
			} catch (Exception ignore) {
			}
		}
		
		return null;
		
	}
	
	@RequestMapping( value="/country", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Language>> getCountry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
/*		try {
			
			List<Language> langs = languageService.getLanguages();
			
			if(CollectionUtils.isEmpty(langs)){
				response.sendError(404, "No languages found");
			}
			
			return ResponseEntity.accepted().body(langs);
		} catch (Exception e) {
			LOGGER.error("Error while getting languages",e);
			try {
				response.sendError(503, "Error while getting languages " + e.getMessage());
			} catch (Exception ignore) {
			}
		}*/
		
		return null;
		
	}

}
