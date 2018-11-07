package com.salesmanager.test.shop.controller.customer.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.salesmanager.shop.model.customer.Customer;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.ReadableCustomer;
import com.salesmanager.shop.model.customer.address.Address;
import com.salesmanager.shop.model.customer.attribute.CustomerOptionDescription;
import com.salesmanager.shop.model.customer.attribute.CustomerOptionValueDescription;
import com.salesmanager.shop.model.customer.attribute.PersistableCustomerOption;
import com.salesmanager.shop.model.customer.attribute.PersistableCustomerOptionValue;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Ignore
public class CustomerRESTControllerTest {
	
	private RestTemplate restTemplate;
	
	private Long testCustmerID;
	

	
	public HttpHeaders getHeader(){
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
		headers.setContentType(mediaType);
		//Basic Authentication
		String authorisation = "admin" + ":" + "password";
		byte[] encodedAuthorisation = Base64.encode(authorisation.getBytes());
		headers.add("Authorization", "Basic " + new String(encodedAuthorisation));
		return headers;
	}
	
	@Test
	@Ignore
	public void postCustomerOptionValue() throws Exception {
		restTemplate = new RestTemplate();

		PersistableCustomerOptionValue optionValue = new PersistableCustomerOptionValue();
		optionValue.setCode("yes");
		optionValue.setOrder(0);
		
		CustomerOptionValueDescription description = new CustomerOptionValueDescription();
		description.setLanguage("en");
		description.setName("Yes");
		
		List<CustomerOptionValueDescription> descriptions = new ArrayList<CustomerOptionValueDescription>();
		descriptions.add(description);
		
		optionValue.setDescriptions(descriptions);
		
		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(optionValue);
		
		System.out.println(json);
		

		HttpEntity<String> entity = new HttpEntity<String>(json, getHeader());

		ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/sm-shop/services/private/DEFAULT/customer/optionValue", entity, PersistableCustomerOptionValue.class);

		PersistableCustomerOptionValue optVal = (PersistableCustomerOptionValue) response.getBody();
		System.out.println("New Option value ID : " + optVal .getId());

	}
	
	@Test
	@Ignore
	public void postCustomerOption() throws Exception {
		restTemplate = new RestTemplate();

		PersistableCustomerOption option = new PersistableCustomerOption();
		option.setCode("subscribetonewsletter");
		option.setOrder(0);

		
		CustomerOptionDescription description = new CustomerOptionDescription();
		description.setLanguage("en");
		description.setName("Subscribe to newsletter?");
		
		List<CustomerOptionDescription> descriptions = new ArrayList<CustomerOptionDescription>();
		descriptions.add(description);
		
		option.setDescriptions(descriptions);
		
		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(option);
		
		System.out.println(json);
		

		HttpEntity<String> entity = new HttpEntity<String>(json, getHeader());

		ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/sm-shop/services/private/DEFAULT/customer/option", entity, PersistableCustomerOption.class);

		PersistableCustomerOption opt = (PersistableCustomerOption) response.getBody();
		System.out.println("New Option ID : " + opt .getId());

	}
	

	@Test
	@Ignore
	public void getCustomers() throws Exception {
		
		
		//get customers
		restTemplate = new RestTemplate();
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(getHeader());
		
		ResponseEntity<ReadableCustomer[]> response = restTemplate.exchange("http://localhost:8080/sm-shop/services/private/DEFAULT/customer", HttpMethod.GET, httpEntity, ReadableCustomer[].class);
		
		if(response.getStatusCode() != HttpStatus.OK){
			throw new Exception();
		}else{
			System.out.println(response.getBody().length + " Customer records found.");
		}
	}

	@Test
	@Ignore
	public void postCustomer() throws Exception {
		restTemplate = new RestTemplate();
		
		
		PersistableCustomer customer = new PersistableCustomer();
		customer.setEmailAddress("carl@csticonsulting.com");
		customer.setGender("M");
		customer.setLanguage("en");
		//customer.setEncodedPassword("5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8");
		customer.setClearPassword("password");
		customer.setUserName("testuser");
		
		Address address = new Address();
		address.setAddress("123 my street");
		address.setCity("Boucherville");
		address.setPostalCode("H2H 2H2");
		address.setFirstName("Johny");
		address.setLastName("BGood");
		address.setCountry("CA");
		address.setZone("QC");
		
		customer.setBilling(address);
		
		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(customer);

		HttpEntity<String> entity = new HttpEntity<String>(json, getHeader());

		ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/sm-shop/services/private/DEFAULT/customer", entity, PersistableCustomer.class);

		Customer cust = (Customer) response.getBody();
		System.out.println("New Customer ID : " + cust.getId());
		testCustmerID = cust.getId();
	}
	
	@Test
	@Ignore
	public void deleteCustomer() throws Exception {
		restTemplate = new RestTemplate();
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(getHeader());
		
		restTemplate.exchange("http://localhost:8080/sm-shop/services/private/DEFAULT/customer/"+testCustmerID, HttpMethod.DELETE, httpEntity, Customer.class);
		System.out.println("Customer "+testCustmerID+" Deleted.");
	}
	
}
