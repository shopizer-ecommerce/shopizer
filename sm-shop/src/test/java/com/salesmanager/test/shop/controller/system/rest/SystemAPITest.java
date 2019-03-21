package com.salesmanager.test.shop.controller.system.rest;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.junit.Ignore;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;

@Ignore
public class SystemAPITest {
	
	private RestTemplate restTemplate;


	private HttpHeaders getHeader(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);//IMPORTANT TO HAVE THIS
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
				
	"{"
	+	"\"module\":\"PAYMENT\","
	+	"\"code\":\"braintree\","
	+	"\"type\":\"creditcard\","
	+	"\"version\":\"1.0\","
	+	"\"regions\":[\"US\",\"CA\",\"GB\",\"AU\",\"FI\",\"DK\",\"IE\",\"NO\",\"SE\",\"AL\",\"AD\",\"AT\",\"BY\",\"BE\",\"BG\",\"HY\",\"CY\",\"CZ\",\"FR\",\"GR\",\"IS\",\"IE\",\"IM\",\"IT\",\"PL\",\"LU\",\"CH\",\"RS\",\"SG\",\"MY\",\"HK\",\"NZ\"],"
	+	"\"image\":\"braintree.jpg\","
	+	"\"configuration\":[{\"env\":\"TEST\",\"scheme\":\"https\",\"host\":\"NOT_REQUIRED\",\"port\":\"NOT_REQUIRED\",\"uri\":\"/\"},{\"env\":\"PROD\",\"scheme\":\"https\",\"host\":\"NOT_REQUIRED\",\"port\":\"NOT_REQUIRED\",\"uri\":\"/\"}]"
	+"}";

		
		/**
		"{"
		+	"\"module\":\"SHIPPING\","
		+	"\"code\":\"priceByDistance\","
		+	"\"version\":\"1.0\","
		+	"\"regions\":[\"*\"]"
		+"}";
		**/
		

		System.out.println(json);
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		//messageConverters.add(new FormHttpMessageConverter());
		//messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		
		restTemplate.setMessageConverters(messageConverters);
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(json, getHeader());
		
		ResponseEntity<AjaxResponse> response = restTemplate.exchange("http://localhost:8080/services/private/system/module", HttpMethod.POST, httpEntity, AjaxResponse.class);
		
		if(response.getStatusCode() != HttpStatus.OK){
			throw new Exception();
		}else{
			System.out.println(response.getBody() + " Success creating module");
		}
	}
	
		
}
