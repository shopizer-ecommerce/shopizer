package com.salesmanager.test.shop.common;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.catalog.category.Category;
import com.salesmanager.shop.model.catalog.category.CategoryDescription;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.manufacturer.ManufacturerDescription;
import com.salesmanager.shop.model.catalog.manufacturer.PersistableManufacturer;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.ProductDescription;
import com.salesmanager.shop.model.catalog.product.ProductSpecification;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.shoppingcart.PersistableShoppingCartItem;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;
import com.salesmanager.shop.model.store.ReadableMerchantStore;
import com.salesmanager.shop.populator.customer.ReadableCustomerList;
import com.salesmanager.shop.store.security.AuthenticationRequest;
import com.salesmanager.shop.store.security.AuthenticationResponse;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ServicesTestSupport {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    protected HttpHeaders getHeader() {
        return getHeader("admin@shopizer.com", "password");
    }

    protected HttpHeaders getHeader(final String userName, final String password) {
        final ResponseEntity<AuthenticationResponse> response = testRestTemplate.postForEntity("/api/v1/private/login", new HttpEntity<>(new AuthenticationRequest(userName, password)),
                AuthenticationResponse.class);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.add("Authorization", "Bearer " + response.getBody().getToken());
        return headers;
    }

    public ReadableMerchantStore fetchStore() {
        final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());
        return testRestTemplate.exchange(String.format("/api/v1/store/%s", Constants.DEFAULT_STORE), HttpMethod.GET,
                httpEntity, ReadableMerchantStore.class).getBody();

    }

    public ReadableCustomerList fetchCustomers() {
        final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());
        return testRestTemplate.exchange("/api/v1/private/customers", HttpMethod.GET,
                httpEntity, ReadableCustomerList.class).getBody();

    }

    protected PersistableManufacturer manufacturer(String code) {

      PersistableManufacturer m = new PersistableManufacturer();
      m.setCode(code);
      m.setOrder(0);

      ManufacturerDescription desc = new ManufacturerDescription();
      desc.setLanguage("en");
      desc.setName(code);

      m.getDescriptions().add(desc);

      return m;


    }

    protected PersistableCategory category(String code) {

      PersistableCategory newCategory = new PersistableCategory();
      newCategory.setCode(code);
      newCategory.setSortOrder(1);
      newCategory.setVisible(true);
      newCategory.setDepth(1);


      CategoryDescription description = new CategoryDescription();
      description.setLanguage("en");
      description.setName(code);


      List<CategoryDescription> descriptions = new ArrayList<>();
      descriptions.add(description);

      newCategory.setDescriptions(descriptions);

      return newCategory;


    }

    protected PersistableProduct product(String code) {


      PersistableProduct product = new PersistableProduct();

      product.setPrice(BigDecimal.TEN);
      product.setSku(code);

      ProductDescription description = new ProductDescription();
      description.setName(code);
      description.setLanguage("en");

      product.getDescriptions().add(description);

      return product;

    }

    protected ReadableProduct sampleProduct(String code) {

        final PersistableCategory newCategory = new PersistableCategory();
        newCategory.setCode(code);
        newCategory.setSortOrder(1);
        newCategory.setVisible(true);
        newCategory.setDepth(4);

        final Category parent = new Category();

        newCategory.setParent(parent);

        final CategoryDescription description = new CategoryDescription();
        description.setLanguage("en");
        description.setName("test-cat");
        description.setFriendlyUrl("test-cat");
        description.setTitle("test-cat");

        final List<CategoryDescription> descriptions = new ArrayList<>();
        descriptions.add(description);

        newCategory.setDescriptions(descriptions);

        final HttpEntity<PersistableCategory> categoryEntity = new HttpEntity<>(newCategory, getHeader());

        final ResponseEntity<PersistableCategory> categoryResponse = testRestTemplate.postForEntity("/api/v1/private/category?store=" + Constants.DEFAULT_STORE, categoryEntity,
                PersistableCategory.class);
        final PersistableCategory cat = categoryResponse.getBody();
        assertThat(categoryResponse.getStatusCode(), is(CREATED));
        assertNotNull(cat.getId());

        final PersistableProduct product = new PersistableProduct();
        final ArrayList<Category> categories = new ArrayList<>();
        categories.add(cat);
        product.setCategories(categories);
        ProductSpecification specifications = new ProductSpecification();
        specifications.setManufacturer(com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer.DEFAULT_MANUFACTURER);
        product.setProductSpecifications(specifications);
        product.setAvailable(true);
        product.setPrice(BigDecimal.TEN);
        product.setSku(code);
        product.setQuantity(100);
        ProductDescription productDescription = new ProductDescription();
        productDescription.setDescription("TEST");
        productDescription.setName("TestName");
        productDescription.setLanguage("en");
        product.getDescriptions().add(productDescription);


        final HttpEntity<PersistableProduct> entity = new HttpEntity<>(product, getHeader());

        final ResponseEntity<PersistableProduct> response = testRestTemplate.postForEntity("/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entity, PersistableProduct.class);
        assertThat(response.getStatusCode(), is(CREATED));

        final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());

        String apiUrl = "/api/v1/products/" + response.getBody().getId();

        ResponseEntity<ReadableProduct> readableProduct = testRestTemplate.exchange(apiUrl, HttpMethod.GET, httpEntity, ReadableProduct.class);
        assertThat(readableProduct.getStatusCode(), is(OK));

        return readableProduct.getBody();
    }

    protected ReadableShoppingCart sampleCart() {


    	ReadableProduct product = sampleProduct("sampleCart");
    	assertNotNull(product);

        PersistableShoppingCartItem cartItem = new PersistableShoppingCartItem();
        cartItem.setProduct(product.getId());
        cartItem.setQuantity(1);

        final HttpEntity<PersistableShoppingCartItem> cartEntity = new HttpEntity<>(cartItem, getHeader());
        final ResponseEntity<ReadableShoppingCart> response = testRestTemplate.postForEntity(String.format("/api/v1/cart/"), cartEntity, ReadableShoppingCart.class);

        assertNotNull(response);
        assertThat(response.getStatusCode(), is(CREATED));

    	return response.getBody();
    }


}
