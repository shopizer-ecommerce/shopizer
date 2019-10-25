package com.salesmanager.test.utils;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.email.Email;
import com.salesmanager.core.business.services.system.EmailService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.test.common.AbstractSalesManagerCoreTestCase;
import com.salesmanager.test.configuration.ConfigurationTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ConfigurationTest.class})
@Ignore
public class SendEmailTest extends AbstractSalesManagerCoreTestCase {
  
  @Inject
  private EmailService emailService;
  
  @Test
  public void sendEmail() throws ServiceException, Exception {
    
      MerchantStore merchant = merchantService.getByCode( MerchantStore.DEFAULT_STORE );
      
      Map<String, String> templateTokens = new HashMap<String,String>();
      templateTokens.put("EMAIL_ADMIN_LABEL", "");
      templateTokens.put("EMAIL_STORE_NAME", "");
      templateTokens.put("EMAIL_FOOTER_COPYRIGHT", "");
      templateTokens.put("EMAIL_DISCLAIMER", "");
      templateTokens.put("EMAIL_SPAM_DISCLAIMER", "");
      templateTokens.put("LOGOPATH", "");

      
      templateTokens.put("EMAIL_CONTACT_NAME", "Test");
      templateTokens.put("EMAIL_CONTACT_EMAIL", "test@gmail.com");
      templateTokens.put("EMAIL_CONTACT_CONTENT", "Hello");

      templateTokens.put("EMAIL_CUSTOMER_CONTACT", "Contact");
      templateTokens.put("EMAIL_CONTACT_NAME_LABEL", "Name");
      templateTokens.put("EMAIL_CONTACT_EMAIL_LABEL", "Email");



      Email email = new Email();
      email.setFrom("Default store");
      email.setFromEmail("test@shopizer.com");
      email.setSubject("Contact");
      email.setTo("test@shopizer.com");
      email.setTemplateName("email_template_contact.ftl");
      email.setTemplateTokens(templateTokens);

      emailService.sendHtmlEmail(merchant, email);

    
  }

}
