package com.salesmanager.test.shop.integration.store;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import java.util.Arrays;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.references.PersistableAddress;
import com.salesmanager.shop.model.store.PersistableMerchantStore;
import com.salesmanager.shop.model.store.ReadableMerchantStore;
import com.salesmanager.test.shop.common.ServicesTestSupport;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MerchantStoreApiIntegrationTest extends ServicesTestSupport {
  
  private static final String TEST_STORE_CODE = "test";
  private static final String CURRENCY = "CAD";
  private static final String DEFAULT_LANGUAGE = "en";

  @Inject
  private TestRestTemplate testRestTemplate;
  
  /**
   * Test get DEFAULT store
   * @throws Exception
   */
  @Test
  public void testGetDefaultStore() throws Exception {
      final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());

      final ResponseEntity<ReadableMerchantStore> response = testRestTemplate.exchange(String.format("/api/v1/store/" + MerchantStore.DEFAULT_STORE), HttpMethod.GET,
              httpEntity, ReadableMerchantStore.class);
      if (response.getStatusCode() != HttpStatus.OK) {
          throw new Exception(response.toString());
      } else {
          final ReadableMerchantStore store = response.getBody();
          assertNotNull(store);
      }
  }
  
  /**
   * Create a new store then delete it
   * @throws Exception
   */
  @Test
  public void testCreateStoreAndDelete() throws Exception {
      
      
      PersistableAddress address = new PersistableAddress();
      address.setAddress("121212 simple address");
      address.setPostalCode("12345");
      address.setCountry("US");
      address.setCity("FT LD");
      address.setStateProvince("FL");

      PersistableMerchantStore createdStore = new PersistableMerchantStore();
      createdStore.setCode(TEST_STORE_CODE);
      createdStore.setCurrency(CURRENCY);
      createdStore.setDefaultLanguage(DEFAULT_LANGUAGE);
      createdStore.setEmail("test@test.com");
      createdStore.setName(TEST_STORE_CODE);
      createdStore.setPhone("444-555-6666");
      createdStore.setSupportedLanguages(Arrays.asList(DEFAULT_LANGUAGE));
      createdStore.setAddress(address);
      
      final HttpEntity<PersistableMerchantStore> httpEntity = new HttpEntity<PersistableMerchantStore>(createdStore, getHeader());

      ResponseEntity<ReadableMerchantStore> response = testRestTemplate.exchange(String.format("/api/v1/private/store/"), HttpMethod.POST, httpEntity, ReadableMerchantStore.class);

      if (response.getStatusCode() != HttpStatus.OK) {
          throw new Exception(response.toString());
      } else {
          final ReadableMerchantStore store = response.getBody();
          assertNotNull(store);
      }

      //delete store
      ResponseEntity<Void> deleteResponse = testRestTemplate.exchange(String.format("/api/v1/private/store/" + TEST_STORE_CODE), HttpMethod.DELETE, httpEntity, Void.class);

      assertThat(deleteResponse.getStatusCode(), is(HttpStatus.OK));

  }
  
  
  @Test
  public void testAddAndDeleteStoreLogo() {
      LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
      parameters.add("file", new org.springframework.core.io.ClassPathResource("image.jpg"));

      HttpHeaders headers = getHeader();
      headers.setContentType(MediaType.MULTIPART_FORM_DATA);

      HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(parameters, headers);

      ResponseEntity<Void> createResponse = testRestTemplate.exchange(String.format("/api/v1/private/store/" + MerchantStore.DEFAULT_STORE + "/marketing/logo"), HttpMethod.POST, entity, Void.class);

      // Expect Created
      assertThat(createResponse.getStatusCode(), is(HttpStatus.CREATED));
      
      // now remove logo
      HttpEntity<Void> deleteRequest = new HttpEntity<Void>(getHeader());
      
      ResponseEntity<Void> deleteResponse = testRestTemplate.exchange(String.format("/api/v1/private/store/" + MerchantStore.DEFAULT_STORE + "/marketing/logo"), HttpMethod.DELETE, deleteRequest, Void.class);

      // Expect Ok
      assertThat(deleteResponse.getStatusCode(), is(HttpStatus.OK));

  }

  
  
}
