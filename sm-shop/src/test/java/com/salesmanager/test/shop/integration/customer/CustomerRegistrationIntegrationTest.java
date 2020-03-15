package com.salesmanager.test.shop.integration.customer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.OK;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.model.customer.CustomerGender;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.address.Address;
import com.salesmanager.shop.store.security.AuthenticationRequest;
import com.salesmanager.shop.store.security.AuthenticationResponse;
import com.salesmanager.test.shop.common.ServicesTestSupport;

public class CustomerRegistrationIntegrationTest extends ServicesTestSupport {

    @Test
    public void registerCustomer() {
        
      
        final PersistableCustomer testCustomer = new PersistableCustomer();
        testCustomer.setEmailAddress("customer1@test.com");
        testCustomer.setPassword("clear123");
        testCustomer.setGender(CustomerGender.M.name());
        testCustomer.setLanguage("en");
        final Address billing = new Address();
        billing.setFirstName("customer1");
        billing.setLastName("ccstomer1");
        billing.setCountry("BE");
        testCustomer.setBilling(billing);
        testCustomer.setStoreCode(Constants.DEFAULT_STORE);
        final HttpEntity<PersistableCustomer> entity = new HttpEntity<>(testCustomer, getHeader());

        final ResponseEntity<PersistableCustomer> response = testRestTemplate.postForEntity("/api/v1/customer/register", entity, PersistableCustomer.class);
        assertThat(response.getStatusCode(), is(OK));

        // created customer can login

        final ResponseEntity<AuthenticationResponse> loginResponse = testRestTemplate.postForEntity("/api/v1/customer/login", new HttpEntity<>(new AuthenticationRequest("customer1@test.com", "clear123")),
                AuthenticationResponse.class);
        assertThat(loginResponse.getStatusCode(), is(OK));
        assertNotNull(loginResponse.getBody().getToken());

    }

}