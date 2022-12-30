package com.salesmanager.test.shop.integration.system;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.salesmanager.core.model.system.optin.OptinType;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.customer.optin.PersistableCustomerOptin;
import com.salesmanager.shop.model.system.PersistableOptin;
import com.salesmanager.test.shop.common.ServicesTestSupport;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class OptinApiIntegrationTest extends ServicesTestSupport {
  
  @Autowired
  private TestRestTemplate testRestTemplate;
  
  
  @Test
  public void createOptin() throws Exception {

      PersistableOptin optin = new PersistableOptin();
      optin.setCode(OptinType.PROMOTIONS.name());
      optin.setOptinType(OptinType.PROMOTIONS.name());
     
      
      final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
      final String json = writer.writeValueAsString(optin);
 
      
      final HttpEntity<String> entity = new HttpEntity<>(json, getHeader());
      final ResponseEntity<PersistableOptin> response = testRestTemplate.postForEntity("/api/v1/private/optin", entity, PersistableOptin.class);

      if (response.getStatusCode() != HttpStatus.OK) {
          throw new Exception(response.toString());
      } else {
          assertTrue(true);
      }
  }
  
  public void createCustomerOptinNewsletter() throws Exception {


    PersistableCustomerOptin customerOptin = new PersistableCustomerOptin();
    customerOptin.setEmail("test@test.com");
    customerOptin.setFirstName("Jack");
    customerOptin.setLastName("John");
    
    final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
    final String json = writer.writeValueAsString(customerOptin);
    System.out.println(json);
    
    final HttpEntity<String> e = new HttpEntity<>(json);
    final ResponseEntity<?> resp = testRestTemplate.postForEntity("/api/v1/newsletter", e, PersistableCustomerOptin.class);

    if (resp.getStatusCode() != HttpStatus.OK) {
      throw new Exception(resp.toString());
    } else {
      assertTrue(true);
    }
    
    
}

}
