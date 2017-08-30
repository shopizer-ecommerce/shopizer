package com.salesmanager.cucumber.api;

import com.salesmanager.shop.model.customer.Customer;
import com.salesmanager.shop.model.customer.CustomerEntity;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.Charset;

public class CustomerRESTAPI {
	
	private RestTemplate restTemplate;
	
	public HttpHeaders getHeader(){
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
		headers.setContentType(mediaType);
		//Basic Authentication
		String authorisation = "admin" + ":" + "password";
		byte[] encodedAuthorisation = Base64.encode(authorisation.getBytes());
		String authorisationstring = "Basic " + new String(encodedAuthorisation);
		headers.add("Authorization", authorisationstring);
		return headers;
	}
	
	public int postCustomer(String json) throws Exception {
		restTemplate = new RestTemplate();
		
		HttpEntity<String> entity = new HttpEntity<String>(json, getHeader());
		ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/services/private/DEFAULT/customer", entity, CustomerEntity.class);
		Customer cust = (Customer) response.getBody();
		
		//System.out.println("New Customer ID : " + cust.getId());
		//testCustmerID = cust.getId();
		
		return response.getStatusCode().value();
	}
	

	
}