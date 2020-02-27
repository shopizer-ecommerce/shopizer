package com.salesmanager.test.shop.integration.category;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.catalog.category.Category;
import com.salesmanager.shop.model.catalog.category.CategoryDescription;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategoryList;
import com.salesmanager.shop.model.catalog.manufacturer.PersistableManufacturer;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.ProductSpecification;
import com.salesmanager.test.shop.common.ServicesTestSupport;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CategoryManagementAPIIntegrationTest extends ServicesTestSupport {

    @Autowired
    private TestRestTemplate testRestTemplate;



    /**
     * Read - GET a category by id
     *
     * @throws Exception
     */
    @Test
    public void getCategory() throws Exception {
        final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());

        final ResponseEntity<ReadableCategoryList> response = testRestTemplate.exchange(String.format("/api/v1/category/"), HttpMethod.GET,
                httpEntity, ReadableCategoryList.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception(response.toString());
        } else {
            final List<ReadableCategory> categories = response.getBody().getCategories();
            assertNotNull(categories);
        }
    }

    /**
     * Creates - POST a category for a given store
     *
     * @throws Exception
     */

    @Test
    public void postCategory() throws Exception {

        PersistableCategory newCategory = new PersistableCategory();
        newCategory.setCode("javascript");
        newCategory.setSortOrder(1);
        newCategory.setVisible(true);
        newCategory.setDepth(4);

        Category parent = new Category();

        newCategory.setParent(parent);

        CategoryDescription description = new CategoryDescription();
        description.setLanguage("en");
        description.setName("Javascript");
        description.setFriendlyUrl("javascript");
        description.setTitle("Javascript");

        List<CategoryDescription> descriptions = new ArrayList<>();
        descriptions.add(description);

        newCategory.setDescriptions(descriptions);

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        final String json = writer.writeValueAsString(newCategory);

        HttpEntity<String> entity = new HttpEntity<>(json, getHeader());

        ResponseEntity response = testRestTemplate.postForEntity("/api/v1/private/category", entity, PersistableCategory.class);
        PersistableCategory cat = (PersistableCategory) response.getBody();
        assertThat(response.getStatusCode(), is(CREATED));
        assertNotNull(cat.getId());

    }
    
    @Test
    public void putCategory() throws Exception {

        //create
        PersistableCategory newCategory = new PersistableCategory();
        newCategory.setCode("angular");
        newCategory.setSortOrder(1);
        newCategory.setVisible(true);
        newCategory.setDepth(4);


        CategoryDescription description = new CategoryDescription();
        description.setLanguage("en");
        description.setName("angular");
        description.setFriendlyUrl("angular");
        description.setTitle("angular");

        List<CategoryDescription> descriptions = new ArrayList<>();
        descriptions.add(description);

        newCategory.setDescriptions(descriptions);

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        final String json = writer.writeValueAsString(newCategory);

        final HttpEntity<String> entity = new HttpEntity<>(json, getHeader());
        //create category
        final ResponseEntity response = testRestTemplate.postForEntity("/api/v1/private/category", entity, PersistableCategory.class);
        final PersistableCategory cat = (PersistableCategory) response.getBody();
        assertThat(response.getStatusCode(), is(CREATED));
        assertNotNull(cat.getId());
        
        HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());
        
        final ResponseEntity<ReadableCategory> readableQuery = testRestTemplate.exchange(String.format("/api/v1/category/" +  cat.getId()), HttpMethod.GET,
            httpEntity, ReadableCategory.class);
        
        assertThat(readableQuery.getStatusCode(), is(OK));
        
        ReadableCategory readableCategory = readableQuery.getBody();
        
        newCategory = new PersistableCategory();
        newCategory.setCode("angular");
        newCategory.setVisible(true);
        newCategory.setDepth(4);
        newCategory.setSortOrder(2);
        description = new CategoryDescription();
        description.setLanguage("en");
        description.setName("angular");
        description.setFriendlyUrl("angular");
        description.setTitle("angular");

        descriptions = new ArrayList<>();
        descriptions.add(description);

        newCategory.setDescriptions(descriptions);

        
        HttpEntity<PersistableCategory> requestUpdate = new HttpEntity<>(newCategory, getHeader());
        
        ResponseEntity resp = testRestTemplate.exchange("/api/v1/private/category/" + cat.getId(), HttpMethod.PUT,   requestUpdate, Void.class);
        assertThat(resp.getStatusCode(), is(OK));
        
        //update

    }

    @Test
    public void postComplexCategory() throws Exception {

        /** Dining room **/
        final PersistableCategory dining = new PersistableCategory();
        dining.setCode("diningroom");
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

        List<CategoryDescription> descriptions = new ArrayList<>();
        descriptions.add(endescription);
        descriptions.add(frdescription);

        dining.setDescriptions(descriptions);

        final Category diningParent = new Category();
        diningParent.setCode(dining.getCode());

        /** armoire **/
        final PersistableCategory armoire = new PersistableCategory();
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

        descriptions = new ArrayList<>();
        descriptions.add(endescription);
        descriptions.add(frdescription);

        armoire.setDescriptions(descriptions);
        dining.getChildren().add(armoire);

        /** benches **/
        final PersistableCategory bench = new PersistableCategory();
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

        descriptions = new ArrayList<>();
        descriptions.add(endescription);
        descriptions.add(frdescription);

        bench.setDescriptions(descriptions);
        dining.getChildren().add(bench);

        /** Living room **/
        final PersistableCategory living = new PersistableCategory();
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

        descriptions = new ArrayList<>();
        descriptions.add(endescription);
        descriptions.add(frdescription);

        living.setDescriptions(descriptions);

        /** lounge **/

        final PersistableCategory lounge = new PersistableCategory();
        lounge.setCode("lounge");
        lounge.setSortOrder(3);
        lounge.setVisible(true);

        final Category livingParent = living;
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

        descriptions = new ArrayList<>();
        descriptions.add(endescription);
        descriptions.add(frdescription);

        lounge.setDescriptions(descriptions);
        living.getChildren().add(lounge);

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        final String json = writer.writeValueAsString(dining);

        //System.out.println(json);

        final HttpEntity<String> entity = new HttpEntity<>(json, getHeader());

        final int sizeBefore = testRestTemplate.exchange(String.format("/api/v1/category"), HttpMethod.GET,
                new HttpEntity<>(getHeader()), ReadableCategoryList.class).getBody().getCategories().size();

        final ResponseEntity response = testRestTemplate.postForEntity("/api/v1/private/category", entity, PersistableCategory.class);

        final PersistableCategory cat = (PersistableCategory) response.getBody();
        assertThat(response.getStatusCode(), is(CREATED));
        assertNotNull(cat.getId());



    }

    @Test
    public void deleteCategory() throws Exception {

        final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());

        testRestTemplate.exchange("/services/DEFAULT/category/100", HttpMethod.DELETE, httpEntity, Category.class);
    }

    @Test
    public void manufacturerForItemsInCategory() throws Exception {
      
      ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
      
      //create first manufacturer
      PersistableManufacturer m1 = super.manufacturer("BRAND1");   
      
      String json = writer.writeValueAsString(m1);
      HttpEntity<String> entity = new HttpEntity<>(json, getHeader());

      @SuppressWarnings("rawtypes")
      ResponseEntity response = testRestTemplate.postForEntity("/api/v1/private/manufacturer", entity, PersistableManufacturer.class);
      assertThat(response.getStatusCode(), is(CREATED));

      //create second manufacturer
      PersistableManufacturer m2 = super.manufacturer("BRAND2");
      json = writer.writeValueAsString(m2);
      entity = new HttpEntity<>(json, getHeader());

      response = testRestTemplate.postForEntity("/api/v1/private/manufacturer", entity, PersistableManufacturer.class);
      assertThat(response.getStatusCode(), is(CREATED));
      
      //create category
      PersistableCategory category = super.category("TEST");
      Category cat = new Category();//to be used in product
      cat.setCode("TEST");
      
      json = writer.writeValueAsString(category);
      entity = new HttpEntity<>(json, getHeader());

      @SuppressWarnings("rawtypes")
      ResponseEntity categoryResponse = testRestTemplate.postForEntity("/api/v1/private/category", entity, PersistableCategory.class);
      assertThat(categoryResponse.getStatusCode(), is(CREATED));
      final PersistableCategory persistable = (PersistableCategory) categoryResponse.getBody();
      
      Long id = persistable.getId();
      
      //create first item
      
      PersistableProduct product1 = super.product("PRODUCT1");
      product1.getCategories().add(cat);
      
      
      ProductSpecification specifications = new ProductSpecification();
      specifications.setManufacturer("BRAND1");
      product1.setProductSpecifications(specifications);
      
      json = writer.writeValueAsString(product1);
      entity = new HttpEntity<>(json, getHeader());

      response = testRestTemplate.postForEntity("/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entity, PersistableProduct.class);
      assertThat(response.getStatusCode(), is(CREATED));
            
      //create second item      
      
      PersistableProduct product2 = super.product("PRODUCT2");
      product2.getCategories().add(cat);
      
      
      specifications = new ProductSpecification();
      specifications.setManufacturer("BRAND2");
      product2.setProductSpecifications(specifications);
      
      json = writer.writeValueAsString(product2);
      entity = new HttpEntity<>(json, getHeader());

      response = testRestTemplate.postForEntity("/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entity, PersistableProduct.class);
      assertThat(response.getStatusCode(), is(CREATED));
      
      entity = new HttpEntity<>(getHeader());
            
      //get manufacturers in category
      @SuppressWarnings("rawtypes")
      ResponseEntity<List> manufacturers = testRestTemplate.exchange(String.format("/api/v1/category/" + id + "/manufacturers"), HttpMethod.GET, entity, List.class);  
      assertThat(manufacturers.getStatusCode(), is(OK));
      
      @SuppressWarnings("unchecked")
      List<ReadableManufacturer> manufacturerList = manufacturers.getBody();

      
      //assertFalse(manufacturerList.isEmpty());
      

      
      
    }

}