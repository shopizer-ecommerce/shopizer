package com.salesmanager.test.shop.controller.store.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.shop.model.shop.ContactForm;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Ignore
public class ContactAPITest {
	
	private RestTemplate restTemplate;


	private HttpHeaders getHeader(){
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
		//MediaType.APPLICATION_JSON //for application/json
		headers.setContentType(mediaType);
		return headers;
	}
	
	/**
	 * Contact us email
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void contactUs() throws Exception {
		restTemplate = new RestTemplate();
		
		
		ContactForm contact = new ContactForm();
		contact.setComment("A few good words for you!");
		contact.setEmail("me@test.com");
		contact.setName("Johny Depp");
		contact.setSubject("Hello ny friend");
		
		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(contact);
		
		System.out.println(json);
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(json, getHeader());
		
		ResponseEntity<AjaxResponse> response = restTemplate.exchange("http://localhost:8080/sm-shop/services/public/DEFAULT/contact", HttpMethod.POST, httpEntity, AjaxResponse.class);
		
		if(response.getStatusCode() != HttpStatus.OK){
			throw new Exception();
		}else{
			System.out.println(response.getBody() + " Success sending contact");
		}
	}
	
		
}
