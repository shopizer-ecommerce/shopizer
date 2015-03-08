package com.salesmanager.test.shop.controller.category.rest;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import com.salesmanager.web.entity.catalog.category.Category;
import com.salesmanager.web.entity.catalog.category.CategoryDescription;
import com.salesmanager.web.entity.catalog.category.PersistableCategory;
import com.salesmanager.web.entity.catalog.category.ReadableCategory;

public class CategoryManagementAPITest {
	
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
	 * Read - GET a category by id
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void getCategory() throws Exception {
		restTemplate = new RestTemplate();
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(getHeader());
		
		ResponseEntity<ReadableCategory> response = restTemplate.exchange("http://localhost:8080/sm-shop/services/public/DEFAULT/category/100?lang=en", HttpMethod.GET, httpEntity, ReadableCategory.class);
		
		if(response.getStatusCode() != HttpStatus.OK){
			throw new Exception();
		}else{
			System.out.println(response.getBody() + " Category record found.");
		}
	}
	
	

	/**
	 * Creates - POST a category for a given store
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void postCategory() throws Exception {
		restTemplate = new RestTemplate();
		
		PersistableCategory newCategory = new PersistableCategory();
		newCategory.setCode("javascript");
		newCategory.setSortOrder(1);
		newCategory.setVisible(true);
		
		Category parent = new Category();
		parent.setId(4L);
		
		newCategory.setParent(parent);
		
		CategoryDescription description = new CategoryDescription();
		description.setLanguage("en");
		description.setName("Javascript");
		description.setFriendlyUrl("javascript");
		description.setTitle("Javascript");
		
		List<CategoryDescription> descriptions = new ArrayList<CategoryDescription>();
		descriptions.add(description);
		
		newCategory.setDescriptions(descriptions);
		
		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(newCategory);
		
		System.out.println(json);
		
		
		HttpEntity<String> entity = new HttpEntity<String>(json, getHeader());

		ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/sm-shop/services/private/DEFAULT/category", entity, PersistableCategory.class);

		PersistableCategory cat = (PersistableCategory) response.getBody();
		System.out.println("New Category ID : " + cat.getId());
	}
	
	@Test
	@Ignore
	public void deleteCategory() throws Exception {
		restTemplate = new RestTemplate();
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(getHeader());
		
		restTemplate.exchange("http://localhost:8080/sm-shop/services/DEFAULT/category/100", HttpMethod.DELETE, httpEntity, Category.class);
		System.out.println("Category id 100 Deleted.");
	}

	
}
