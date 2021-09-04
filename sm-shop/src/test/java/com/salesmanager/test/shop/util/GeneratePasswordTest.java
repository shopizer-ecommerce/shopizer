package com.salesmanager.test.shop.util;

import com.salesmanager.shop.admin.model.userpassword.UserReset;
import org.junit.Assert;
import javax.inject.Inject;
import javax.inject.Named;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.test.shop.common.ServicesTestSupport;


import static com.salesmanager.shop.store.controller.customer.facade.CustomerFacadeImpl.USERNAME_LENGTH;


/**
 * This utility is for password encryption
 * @author carlsamson
 *
 */
@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class GeneratePasswordTest extends ServicesTestSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratePasswordTest.class);

  @Inject
  @Named("passwordEncoder")
  private PasswordEncoder passwordEncoder;
  
  @Test
  public void createPassword() throws Exception {
 

      String password ="password";
      String encoded = passwordEncoder.encode(password);
      LOGGER.info(encoded);
      System.out.println(encoded);
      //To comply with sonarlint rule java:S2699
      Assert.assertNotNull(encoded);
  }

    @Test
    public void generateNick() {
        long start = System.currentTimeMillis();
        LOGGER.info("Starting random generation: {}", start);
        String userName = UserReset.generateRandomString(USERNAME_LENGTH);
        LOGGER.info(userName);
        LOGGER.info("End random generation, elapsed milliseconds: {}", System.currentTimeMillis() - start);
        Assert.assertNotNull(userName);
    }


}
