package com.salesmanager.test.shop.controller.product.rest;

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

import com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionType;
import com.salesmanager.web.entity.catalog.manufacturer.ManufacturerDescription;
import com.salesmanager.web.entity.catalog.manufacturer.PersistableManufacturer;
import com.salesmanager.web.entity.catalog.product.PersistableProductReview;
import com.salesmanager.web.entity.catalog.product.ReadableProduct;
import com.salesmanager.web.entity.catalog.product.attribute.PersistableProductOption;
import com.salesmanager.web.entity.catalog.product.attribute.PersistableProductOptionValue;
import com.salesmanager.web.entity.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.web.entity.catalog.product.attribute.ProductOptionValueDescription;

public class ProductManagementAPITest {
	
	private RestTemplate restTemplate;
	
	private Long testCategoryID;
	
	private Long testProductID;

	
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
	
	/**
	 * Creates a Manufacturer reference object that can be used when creating
	 * a product
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void createManufacturer() throws Exception {
		
		ManufacturerDescription description = new ManufacturerDescription();
		description.setLanguage("en");
		description.setName("Tag Heuer");
		description.setFriendlyUrl("tag-watches");
		description.setTitle("Tag Heuer");
		
		List<ManufacturerDescription> descriptions = new ArrayList<ManufacturerDescription>();
		descriptions.add(description);
		
		PersistableManufacturer manufacturer = new PersistableManufacturer();
		manufacturer.setOrder(1);
		manufacturer.setDescriptions(descriptions);
		

		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(manufacturer);
		
		System.out.println(json);
		
/*		{
			  "descriptions" : [ {
			    "name" : "Tag Heuer",
			    "description" : null,
			    "friendlyUrl" : "tag-watches",
			    "keyWords" : null,
			    "highlights" : null,
			    "metaDescription" : null,
			    "title" : "Tag Heuer",
			    "language" : "en",
			    "id" : 0
			  } ],
			  "order" : 1,
			  "id" : 0
			}*/
		
		restTemplate = new RestTemplate();

		
		HttpEntity<String> entity = new HttpEntity<String>(json, getHeader());

		ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/sm-shop/shop/services/private/manufacturer/DEFAULT", entity, PersistableManufacturer.class);

		PersistableManufacturer manuf = (PersistableManufacturer) response.getBody();
		System.out.println("New Manufacturer ID : " + manuf.getId());
		
		
	}
	
	/**
	 * Creates a ProductReview
	 * requires an existing Customer and an existing Product
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void createProductReview() throws Exception {
		
		//requires an existing product
		//requires an existing customer
		PersistableProductReview review = new PersistableProductReview();
		review.setCustomerId(1L);
		review.setProductId(1L);
		review.setLanguage("en");
		review.setRating(2D);//rating is on 5
		review.setDescription("Not as good as expected. From what i understood that was supposed to be premium quality but unfortunately i had to return the item after one week... Verry disapointed !");
		review.setDate("2013-06-06");

		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(review);
		
		System.out.println(json);
		
		/**
		 * {
  			"description" : "Excellent product !",
  			"productId" : 1,
  			"rating" : 4.5,
  			"customerId" : 1,
  			"date" : "2013-06-06",
  			"language" : "en"
			}
		 */

		
		restTemplate = new RestTemplate();

		
		HttpEntity<String> entity = new HttpEntity<String>(json, getHeader());

		ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/sm-shop/shop/services/private/product/review/DEFAULT", entity, PersistableProductReview.class);

		PersistableProductReview rev = (PersistableProductReview) response.getBody();
		System.out.println("New ProductReview ID : " + rev.getId());
		
		
	}
	
	/**
	 * Creates a product option value that can be used to create a product attribute
	 * when creating a new product
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void createOptionValue() throws Exception {
		
		ProductOptionValueDescription description = new ProductOptionValueDescription();
		description.setLanguage("en");
		description.setName("Red");
		
		List<ProductOptionValueDescription> descriptions = new ArrayList<ProductOptionValueDescription>();
		descriptions.add(description);
		
		PersistableProductOptionValue optionValue = new PersistableProductOptionValue();
		optionValue.setOrder(1);
		optionValue.setCode("colorred");
		optionValue.setDescriptions(descriptions);
		

		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(optionValue);
		
		System.out.println(json);
		
		/**
		 * {
			  "descriptions" : [ {
			    "name" : "Red",
			    "description" : null,
			    "friendlyUrl" : null,
			    "keyWords" : null,
			    "highlights" : null,
			    "metaDescription" : null,
			    "title" : null,
			    "language" : "en",
			    "id" : 0
			  } ],
			  "order" : 1,
			  "code" : "color-red",
			  "id" : 0
			}
		 */

		restTemplate = new RestTemplate();

		HttpEntity<String> entity = new HttpEntity<String>(json, getHeader());

		ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/sm-shop/shop/services/private/product/optionValue/DEFAULT", entity, PersistableProductOptionValue.class);

		PersistableProductOptionValue opt = (PersistableProductOptionValue) response.getBody();
		System.out.println("New optionValue ID : " + opt.getId());

	}
	
	/**
	 * Creates a new ProductOption
	 * @throws Exception
	 */
	@Test
    @Ignore
	public void createOption() throws Exception {
		
		ProductOptionDescription description = new ProductOptionDescription();
		description.setLanguage("en");
		description.setName("Color");
		
		List<ProductOptionDescription> descriptions = new ArrayList<ProductOptionDescription>();
		descriptions.add(description);
		
		PersistableProductOption option = new PersistableProductOption();
		option.setOrder(1);
		option.setCode("color");
		option.setType(ProductOptionType.Select.name());
		option.setDescriptions(descriptions);
		

		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(option);
		
		System.out.println(json);
		
		/**
		 * {
			  "descriptions" : [ {
			    "name" : "Color",
			    "description" : null,
			    "friendlyUrl" : null,
			    "keyWords" : null,
			    "highlights" : null,
			    "metaDescription" : null,
			    "title" : null,
			    "language" : "en",
			    "id" : 0
			  } ],
			  "type" : SELECT,
			  "order" : 1,
			  "code" : "color",
			  "id" : 0
			}
		 */

		restTemplate = new RestTemplate();

		HttpEntity<String> entity = new HttpEntity<String>(json, getHeader());

		ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/sm-shop/shop/services/private/product/option/DEFAULT", entity, PersistableProductOption.class);

		PersistableProductOption opt = (PersistableProductOption) response.getBody();
		System.out.println("New option ID : " + opt.getId());

	}
		
	
	@Test
	@Ignore
	public void getProducts() throws Exception {
		restTemplate = new RestTemplate();
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(getHeader());
		
		ResponseEntity<ReadableProduct[]> response = restTemplate.exchange("http://localhost:8080/sm-shop/shop/services/rest/products/DEFAULT/en/"+testCategoryID, HttpMethod.GET, httpEntity, ReadableProduct[].class);
		
		if(response.getStatusCode() != HttpStatus.OK){
			throw new Exception();
		}else{
			System.out.println(response.getBody().length + " Product records found.");
		}
	}
	
	@Test
	@Ignore
	public void putProduct() throws Exception {
		restTemplate = new RestTemplate();
		
		//TODO: Put Product
		
	}
	
	@Test
	@Ignore
	public void postProduct() throws Exception {
		restTemplate = new RestTemplate();
		
		//TODO: Post Product

		
	}
	
	@Test
	@Ignore
	public void deleteProduct() throws Exception {
		restTemplate = new RestTemplate();
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(getHeader());
		
		restTemplate.exchange("http://localhost:8080/sm-shop/shop/services/rest/products/DEFAULT/en/"+testCategoryID+"/"+testProductID, HttpMethod.DELETE, httpEntity, ReadableProduct.class);
		System.out.println("Product "+testProductID+" Deleted.");
	}
	
}
