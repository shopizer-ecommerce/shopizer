package com.salesmanager.admin.components.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.admin.controller.exception.AdminAuthenticationException;
import com.salesmanager.admin.utils.Constants;


@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {
	
    private static final Logger logger = LoggerFactory.getLogger(AdminAuthenticationProvider.class);
	 
    private final static String AUTHORIZATION = "Authorization";

	@Value("${shopizer.api.url}")
	private String backend;
	
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

        ResponseEntity<String> resp
          = restTemplate.postForEntity(loginResourceUrl, entity, String.class);
 
        
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
			
			//first name
			//last name
			//email
			//language
			//groups
		
		 List grants = new ArrayList<String>();

		List<Map<String,String>> groups = (List<Map<String,String>>)map.get("groups");
		
		
		//TODO add prefix ROLE_ in front of group
		for(Map<String,String> g : groups) {
			GrantedAuthority gt = new SimpleGrantedAuthority("ROLE_"+g.get("name"));
			grants.add(gt);
		}
		
		GrantedAuthority gta = new SimpleGrantedAuthority("ROLE_AUTH");
		grants.add(gta);
		
		
		AdminAuthenticationToken auth = new AdminAuthenticationToken(name,password,grants);
		
		
		Map<String,String> details = new HashMap<String,String>();
		details.put(Constants.TOKEN,token);
		
		auth.setDetails(details);
			
		return auth;
     
        
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(AdminAuthenticationToken.class);
	}


}
