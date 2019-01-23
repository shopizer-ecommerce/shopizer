package com.salesmanager.test.shop.integration.store;

import static org.junit.Assert.assertNotNull;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.shop.ReadableMerchantStore;
import com.salesmanager.test.shop.common.ServicesTestSupport;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MerchantStoreApiIntegrationTest extends ServicesTestSupport {
  
  

  @Inject
  private TestRestTemplate testRestTemplate;
  
  /**
   * Test get DEFAULT store
   * @throws Exception
   */
  @Test
  public void getDefaultStore() throws Exception {
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

  
  
}
