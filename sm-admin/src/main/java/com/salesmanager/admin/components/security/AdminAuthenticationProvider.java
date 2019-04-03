package com.salesmanager.admin.components.security;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.admin.controller.exception.AdminAuthenticationException;
import com.salesmanager.admin.utils.Constants;


@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {
	
    private static final Logger logger = LoggerFactory.getLogger(AdminAuthenticationProvider.class);
	 
    private final static String AUTHORIZATION = "Authorization";
    private final static String LONGDATE_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";

	@Value("${shopizer.api.url}")
	private String backend;
	
	public String refreshAuthenticationToken(Authentication authentication) throws AuthenticationException {
	    

	    @SuppressWarnings("unchecked")
        Map<String, String> details = (Map<String, String>) authentication.getDetails();
	    String token = details.get(Constants.TOKEN);
	  
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(AUTHORIZATION, "Bearer " + token);//set bearer token
        
        

        String refreshResourceUrl
        = backend + "/auth/refresh";
        
        
        
        //Invoke web service
        RestTemplate restTemplate = new RestTemplate();

        
        HttpEntity entity = new HttpEntity(headers);



        ResponseEntity<String> resp = null;

        try {
          
            resp = restTemplate.exchange(
                refreshResourceUrl, HttpMethod.GET, entity, String.class);
            
            

        } catch(HttpClientErrorException e) {
            if(HttpStatus.FORBIDDEN.name().equals(e.getStatusCode().name())) {
                throw new AdminAuthenticationException("Cannot authenticate this client [Forbidden]",e.getStatusCode());
            }
            if(HttpStatus.NOT_FOUND.name().equals(e.getStatusCode().name())) {
                throw new AdminAuthenticationException("Cannot authenticate this client [Not found]",e.getStatusCode());
            }
            logger.error("Error during authentication [" + e.getMessage() + "] [" + e.getStatusCode().name() + "]" );
        }
 
        if(!HttpStatus.OK.equals(resp.getStatusCode())) {
            throw new AdminAuthenticationException("Cannot authenticate this client [ " + resp.getStatusCode().name() + "]");
        }
        
       String result =  resp.getBody();

        details.put(Constants.TOKEN,result);
        
        return result;

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
		ObjectMapper mapper = new ObjectMapper();
		String json = "{\"username\":\"" + name
				+ "\",\"password\":\"" + password + "\"}";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        
        String loginResourceUrl
        = backend + "/private/login";
        
        //Invoke web service
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> resp = null;
        try {
        	resp = restTemplate.postForEntity(loginResourceUrl, entity, String.class);
        } catch(HttpClientErrorException e) {
        	if(HttpStatus.FORBIDDEN.name().equals(e.getStatusCode().name())) {
        		throw new AdminAuthenticationException("Cannot authenticate this client [Forbidden]",e.getStatusCode());
        	}
        	if(HttpStatus.NOT_FOUND.name().equals(e.getStatusCode().name())) {
        		throw new AdminAuthenticationException("Cannot authenticate this client [Not found]",e.getStatusCode());
        	}
        	logger.error("Error during authentication [" + e.getMessage() + "] [" + e.getStatusCode().name() + "]" );
        }
 
        if(!HttpStatus.OK.equals(resp.getStatusCode())) {
        	throw new AdminAuthenticationException("Cannot authenticate this client [ " + resp.getStatusCode().name() + "]");
        }
        
        String body = resp.getBody();
        
		Map<String, Object> map = new HashMap<String, Object>();

		// convert JSON string to Map
		try {
			map = mapper.readValue(body, new TypeReference<Map<String, String>>(){});
		} catch (Exception e) {
			logger.error("Cannot parse login response body " + body, e);
			throw new AdminAuthenticationException("Cannot authenticate this client, response parsing problem [ " + body + "]");
		}
		
		String token = String.valueOf(map.get(Constants.TOKEN));
		
        //get user details (Principal)
		headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set(AUTHORIZATION, "Bearer " + token);//set bearer token
	        
	        
	    entity = new HttpEntity<String>(headers);
	    
	    //User details web service
	    String profileResourceUrl
	        = backend + "/private/users/" + name;
	        
	    //Invoke web service
	    restTemplate = new RestTemplate();

	     resp = restTemplate.exchange(profileResourceUrl, HttpMethod.GET, entity, String.class);

	        
	     if(!HttpStatus.OK.equals(resp.getStatusCode())) {
	        	throw new AdminAuthenticationException("Cannot get profile for this client [ " + resp.getStatusCode().name() + "]");
	     }
	        
	     body = resp.getBody();
	     map = new HashMap<String, Object>();

			// convert JSON string to Map
		 try {
				map = mapper.readValue(body, new TypeReference<Map<String, Object>>(){});
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("Cannot parse get profile response body " + body, e);
				throw new AdminAuthenticationException("Cannot get profile this client, response parsing problem [ " + body + "]");
		  }
			
			//firstName
			//defaultLanguage
			//active
		    //userName
			//groups
		
		List grants = new ArrayList<String>();

		List<Map<String,String>> groups = (List<Map<String,String>>)map.get("groups");
		List<Map<String,String>> permissions = (List<Map<String,String>>)map.get("permissions");
		
		
		//prefix ROLE_ in front of group (spring security obligation)
		for(Map<String,String> g : groups) {
			GrantedAuthority gt = new SimpleGrantedAuthority("ROLE_"+g.get("name"));
			grants.add(gt);
		}
		
		for(Map<String,String> p : permissions) {
			GrantedAuthority gt = new SimpleGrantedAuthority("ROLE_"+p.get("name"));
			grants.add(gt);
		}

		GrantedAuthority gta = new SimpleGrantedAuthority("ROLE_AUTH");
		grants.add(gta);
		
		
		AdminAuthenticationToken auth = new AdminAuthenticationToken(name,password,grants);
		
		String lastAccess = (String)map.get(Constants.User.LAST_ACCESS);
		if(StringUtils.isBlank(lastAccess)) {
			SimpleDateFormat format = new SimpleDateFormat(LONGDATE_FORMAT);
			lastAccess =  format.format(new Date());
		}
		
		Map<String,String> details = new HashMap<String,String>();
		details.put(Constants.TOKEN,token);
		details.put(Constants.User.FIRST_NAME, (String)map.get(Constants.User.FIRST_NAME));
		details.put(Constants.User.LAST_NAME, (String)map.get(Constants.User.LAST_NAME));
		details.put(Constants.User.USER_NAME, (String)map.get(Constants.User.USER_NAME));
		details.put(Constants.User.LAST_ACCESS, lastAccess);
		details.put(Constants.User.ACTIVE, String.valueOf(((Boolean)map.get(Constants.User.ACTIVE)).booleanValue()));
		details.put(Constants.User.DEFAULT_LANGUAGE, (String)map.get(Constants.User.DEFAULT_LANGUAGE));
		details.put(Constants.User.MERCHANT_CODE, (String)map.get(Constants.User.MERCHANT_CODE));
		
		//set locale (language) according to the selection created for that user
		Locale l = new Locale((String)map.get(Constants.User.DEFAULT_LANGUAGE));
		LocaleContextHolder.setLocale(l);
		
		auth.setDetails(details);
		
		logger.debug("Successfull authentication for [" + (String)map.get(Constants.User.USER_NAME) + "]");
			
		return auth;
     
        
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(AdminAuthenticationToken.class);
	}


}
