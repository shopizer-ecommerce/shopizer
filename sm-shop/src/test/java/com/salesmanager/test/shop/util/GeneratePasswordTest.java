package com.salesmanager.test.shop.util;

import javax.inject.Inject;
import javax.inject.Named;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.test.shop.common.ServicesTestSupport;


/**
 * This utility is for password encryption
 * @author carlsamson
 *
 */
@Ignore
@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class GeneratePasswordTest extends ServicesTestSupport {
  

  @Inject
  @Named("passwordEncoder")
  private PasswordEncoder passwordEncoder;
  
  @Test
  public void createPassword() throws Exception {
 

      String password ="password";
      String encoded = passwordEncoder.encode(password);
      System.out.println(encoded);
  }
  


}
