package com.salesmanager.test.shop.integration.user;

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
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.security.PersistableGroup;
import com.salesmanager.shop.model.user.PersistableUser;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.model.user.UserPassword;
import com.salesmanager.test.shop.common.ServicesTestSupport;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserApiIntegrationTest extends ServicesTestSupport {
  
  private static Long DEFAULT_USER_ID = 1L;
  private static String CREATED_PASSWORD = "Password1";
  private static String NEW_CREATED_PASSWORD = "Password2";
  
  @Inject
  private TestRestTemplate testRestTemplate;
  
  @Test
  public void getUser() throws Exception {
      final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());

      final ResponseEntity<ReadableUser> response = testRestTemplate.exchange(String.format("/api/v1/private/users/" + DEFAULT_USER_ID), HttpMethod.GET,
              httpEntity, ReadableUser.class);
      if (response.getStatusCode() != HttpStatus.OK) {
          throw new Exception(response.toString());
      } else {
          final ReadableUser user = response.getBody();
          assertNotNull(user);
      }
  }
  
  @Test
  public void createUserChangePassword() throws Exception {
 
      PersistableUser newUser = new PersistableUser();
      newUser.setDefaultLanguage("en");
      newUser.setEmailAddress("test@test.com");
      newUser.setFirstName("Test");
      newUser.setLastName("User");
      newUser.setUserName("test@test.com");
      newUser.setPassword(CREATED_PASSWORD);
      
      PersistableGroup g = new PersistableGroup();
      g.setName("ADMIN");
      
      newUser.getGroups().add(g);
      
      final HttpEntity<PersistableUser> persistableUser = new HttpEntity<PersistableUser>(newUser, getHeader());

      ReadableUser user = null;
      final ResponseEntity<ReadableUser> response = testRestTemplate.exchange(String.format("/api/v1/private/user/"), HttpMethod.POST,
          persistableUser, ReadableUser.class);
      if (response.getStatusCode() != HttpStatus.OK) {
          throw new Exception(response.toString());
      } else {
          user = response.getBody();
          assertNotNull(user); 
      }
      
      String oldPassword = CREATED_PASSWORD;
      String newPassword = NEW_CREATED_PASSWORD;
      
      UserPassword userPassword = new UserPassword();
      userPassword.setPassword(oldPassword);
      userPassword.setChangePassword(newPassword);
      
      final HttpEntity<UserPassword> changePasswordEntity = new HttpEntity<UserPassword>(userPassword, getHeader());

      
      final ResponseEntity<Void> changePassword = testRestTemplate.exchange(String.format("/api/v1/private/user/" + user.getId() + "/password"), HttpMethod.PATCH, changePasswordEntity, Void.class);
      if (changePassword.getStatusCode() != HttpStatus.OK) {
          throw new Exception(response.toString());
      } else {
        assertNotNull("Password changed"); 
      }
      
      
  }
  


}
