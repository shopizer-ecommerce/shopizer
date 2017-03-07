package com.salesmanager.test.shop.controller.system.rest;

import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import org.junit.Ignore;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Ignore
public class SystemAPITest {
	
	private RestTemplate restTemplate;


	private HttpHeaders getHeader(){
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
	
	/**
	 * Contact us email
	 * @throws Exception
	 */
	//@Test
	@Ignore
	public void createIntegrationModule() throws Exception {
		restTemplate = new RestTemplate();
		
		String json = 
				/**
	"{"
	+	"\"module\":\"PAYMENT\","
	+	"\"code\":\"stripe\","
	+	"\"type\":\"stripe\","
	+	"\"version\":\"1.0\","
	+	"\"regions\":[\"US\",\"CA\",\"GB\",\"AU\",\"FI\",\"DK\",\"IE\",\"NO\",\"SE\"],"
	+	"\"image\":\"stripe.png\","
	+	"\"configuration\":[{\"env\":\"TEST\",\"scheme\":\"https\",\"host\":\"www.stripe.com\",\"port\":\"443\",\"uri\":\"/\"},{\"env\":\"PROD\",\"scheme\":\"https\",\"host\":\"www.stripe.com\",\"port\":\"443\",\"uri\":\"/\"}]"
	+"}";
	**/
		
		
		"{"
		+	"\"module\":\"SHIPPING\","
		+	"\"code\":\"priceByDistance\","
		+	"\"version\":\"1.0\","
		+	"\"regions\":[\"*\"]"
		+"}";
		
		

		System.out.println(json);
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(json, getHeader());
		
		ResponseEntity<AjaxResponse> response = restTemplate.exchange("http://localhost:8080/sm-shop/services/private/system/module", HttpMethod.POST, httpEntity, AjaxResponse.class);
		
		if(response.getStatusCode() != HttpStatus.OK){
			throw new Exception();
		}else{
			System.out.println(response.getBody() + " Success creating module");
		}
	}
	
		
}
