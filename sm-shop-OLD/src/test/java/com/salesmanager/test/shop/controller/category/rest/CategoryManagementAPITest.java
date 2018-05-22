package com.salesmanager.test.shop.controller.category.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.salesmanager.shop.model.catalog.category.Category;
import com.salesmanager.shop.model.catalog.category.CategoryDescription;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Ignore
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
	public void postComplexCategory() throws Exception {
		restTemplate = new RestTemplate();
		
		
		/** Dining room **/
		PersistableCategory dining = new PersistableCategory();
		dining.setCode("dining room");
		dining.setSortOrder(0);
		dining.setVisible(true);
		

		CategoryDescription endescription = new CategoryDescription();
		endescription.setLanguage("en");
		endescription.setName("Dining room");
		endescription.setFriendlyUrl("dining-room");
		endescription.setTitle("Dining room");
			
		
		CategoryDescription frdescription = new CategoryDescription();
		frdescription.setLanguage("fr");
		frdescription.setName("Salle à manger");
		frdescription.setFriendlyUrl("salle-a-manger");
		frdescription.setTitle("Salle à manger");
		
		List<CategoryDescription> descriptions = new ArrayList<CategoryDescription>();
		descriptions.add(endescription);
		descriptions.add(frdescription);
		
		dining.setDescriptions(descriptions);
		
		
		Category diningParent = new Category();
		diningParent.setCode(dining.getCode());
		
		/** armoire **/
		PersistableCategory armoire = new PersistableCategory();
		armoire.setCode("armoire");
		armoire.setSortOrder(1);
		armoire.setVisible(true);
		

		
		armoire.setParent(diningParent);

		endescription = new CategoryDescription();
		endescription.setLanguage("en");
		endescription.setName("Armoires");
		endescription.setFriendlyUrl("armoires");
		endescription.setTitle("Armoires");
		
		frdescription = new CategoryDescription();
		frdescription.setLanguage("fr");
		frdescription.setName("Armoire");
		frdescription.setFriendlyUrl("armoires");
		frdescription.setTitle("Armoires");
		
		descriptions = new ArrayList<CategoryDescription>();
		descriptions.add(endescription);
		descriptions.add(frdescription);
		
		armoire.setDescriptions(descriptions);
		dining.getChildren().add(armoire);
		
		
		/** benches **/
		PersistableCategory bench = new PersistableCategory();
		bench.setCode("bench");
		bench.setSortOrder(4);
		bench.setVisible(true);
		
		
		bench.setParent(diningParent);

		endescription = new CategoryDescription();
		endescription.setLanguage("en");
		endescription.setName("Benches");
		endescription.setFriendlyUrl("benches");
		endescription.setTitle("Benches");
		
		frdescription = new CategoryDescription();
		frdescription.setLanguage("fr");
		frdescription.setName("Bancs");
		frdescription.setFriendlyUrl("bancs");
		frdescription.setTitle("Bancs");
		
		descriptions = new ArrayList<CategoryDescription>();
		descriptions.add(endescription);
		descriptions.add(frdescription);
		
		bench.setDescriptions(descriptions);
		dining.getChildren().add(bench);
		

		
		/** Living room **/
		PersistableCategory living = new PersistableCategory();
		living.setCode("livingroom");
		living.setSortOrder(2);
		living.setVisible(true);
		

		endescription = new CategoryDescription();
		endescription.setLanguage("en");
		endescription.setName("Living room");
		endescription.setFriendlyUrl("living-room");
		endescription.setTitle("Living room");
			
		
		frdescription = new CategoryDescription();
		frdescription.setLanguage("fr");
		frdescription.setName("Salon");
		frdescription.setFriendlyUrl("salon");
		frdescription.setTitle("Salon");
		
		descriptions = new ArrayList<CategoryDescription>();
		descriptions.add(endescription);
		descriptions.add(frdescription);
		
		living.setDescriptions(descriptions);
		
		/** lounge **/
		
		PersistableCategory lounge = new PersistableCategory();
		lounge.setCode("lounge");
		lounge.setSortOrder(3);
		lounge.setVisible(true);
		
		Category livingParent = (Category)living;
		lounge.setParent(livingParent);

		endescription = new CategoryDescription();
		endescription.setLanguage("en");
		endescription.setName("Lounge");
		endescription.setFriendlyUrl("lounge");
		endescription.setTitle("Lounge");
		
		frdescription = new CategoryDescription();
		frdescription.setLanguage("fr");
		frdescription.setName("Divan");
		frdescription.setFriendlyUrl("divan");
		frdescription.setTitle("Divan");
		
		descriptions = new ArrayList<CategoryDescription>();
		descriptions.add(endescription);
		descriptions.add(frdescription);
		
		lounge.setDescriptions(descriptions);
		living.getChildren().add(lounge);
		
		
		
		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(dining);
		
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
