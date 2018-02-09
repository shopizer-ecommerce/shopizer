package com.salesmanager.test.shop.controller.product.rest;

import java.nio.charset.Charset;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.salesmanager.shop.model.catalog.SearchProductList;
import com.salesmanager.shop.model.catalog.SearchProductRequest;
import com.salesmanager.shop.model.catalog.manufacturer.PersistableManufacturer;

public class SearchProductAPITest {
	
	private RestTemplate restTemplate;
	
	public HttpHeaders getHeader(){
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
		//MediaType.APPLICATION_JSON //for application/json
		headers.setContentType(mediaType);
		//Basic Authentication
		String authorisation = "admin" + ":" + "password";
		byte[] encodedAuthorisation = Base64.encode(authorisation.getBytes());
		headers.add("Authorization", "Basic " + new String(encodedAuthorisation));
		return headers;
	}
	
	
	//@Test
	@Ignore
	public void testSearch() throws Exception {
		
		SearchProductRequest searchRequest = new SearchProductRequest();
		searchRequest.setCount(15);
		searchRequest.setStart(0);
		searchRequest.setQuery("test");
		
		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(searchRequest);
		
		System.out.println(json);
		
		HttpEntity<String> entity = new HttpEntity<String>(json, getHeader());
		
		restTemplate = new RestTemplate();

		ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/api/v1/search", entity, SearchProductList.class);

		SearchProductList search = (SearchProductList) response.getBody();
		System.out.println("Search count : " + search.getProductCount());
		
	}

}
