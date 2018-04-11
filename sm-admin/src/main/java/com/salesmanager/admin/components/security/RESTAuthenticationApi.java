package com.salesmanager.admin.components.security;

import java.security.Principal;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("authenticationApi")
public class RESTAuthenticationApi {
	
		public ResponseEntity<Principal> authenticate(String username, String pass) {
			HttpEntity<String> entity = new HttpEntity<String>("", createHeaders());
			
			//create body
			
			//exchange

			//User authentication end point
			//return restTemplate.exchange(authenticationUri, HttpMethod.GET, entity, Principal.class);
		
			return null;

		}
		
	
	
		HttpHeaders createHeaders() {
		    HttpHeaders acceptHeaders = new HttpHeaders() {
		        {
		            set(com.google.common.net.HttpHeaders.ACCEPT, 
		                MediaType.APPLICATION_JSON.toString());
		        }
		    };
/*		    String authorization = username + ":" + password;
		    String basic = new String(Base64.encodeBase64
		        (authorization.getBytes(Charset.forName("US-ASCII"))));
		    acceptHeaders.set("Authorization", "Basic " + basic);*/
		 
		    return acceptHeaders;
		}

}
