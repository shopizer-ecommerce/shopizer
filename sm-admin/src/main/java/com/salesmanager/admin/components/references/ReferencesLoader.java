package com.salesmanager.admin.components.references;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.salesmanager.admin.controller.exception.AdminAuthenticationException;
import com.salesmanager.admin.model.references.Country;
import com.salesmanager.admin.model.references.Currency;
import com.salesmanager.admin.model.references.Language;


/**
 * Load references from REST api
 * @author c.samson
 *
 */
@Component
public class ReferencesLoader {
	
	@Value("${shopizer.api.url}")
	private String backend;
	
	
	public List<Language> loadLanguages() throws Exception {
		
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        
        String resourceUrl
        = backend + "/languages";
        
        //Invoke web service
        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<List<Language>> resp =
                restTemplate.exchange(resourceUrl,
                            HttpMethod.GET, entity, new ParameterizedTypeReference<List<Language>>() {
                    });

        
        if(!HttpStatus.OK.equals(resp.getStatusCode())) {
        	throw new AdminAuthenticationException("Cannot list languages [ " + resp.getStatusCode().name() + "]");
        }
        
        return resp.getBody();
		
	}
	
	public List<Country> loadCountry(Locale locale) throws Exception {
		
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        
        String resourceUrl
        = backend + "/country";
        
        //Invoke web service
        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<List<Country>> resp =
                restTemplate.exchange(resourceUrl,
                            HttpMethod.GET, entity, new ParameterizedTypeReference<List<Country>>() {
                    });

        
        if(!HttpStatus.OK.equals(resp.getStatusCode())) {
        	throw new AdminAuthenticationException("Cannot list country [ " + resp.getStatusCode().name() + "]");
        }
        
        return resp.getBody();
		
	}
	
	public List<Currency> loadCurrency() throws Exception {
		
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        
        String resourceUrl
        = backend + "/currency";
        
        //Invoke web service
        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<List<Currency>> resp =
                restTemplate.exchange(resourceUrl,
                            HttpMethod.GET, entity, new ParameterizedTypeReference<List<Currency>>() {
                    });

        
        if(!HttpStatus.OK.equals(resp.getStatusCode())) {
        	throw new AdminAuthenticationException("Cannot list currency [ " + resp.getStatusCode().name() + "]");
        }
        
        return resp.getBody();
		
	}

}
