package com.salesmanager.test.shop.common;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.CREATED;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.catalog.manufacturer.ManufacturerDescription;
import com.salesmanager.shop.model.catalog.manufacturer.PersistableManufacturer;
import com.salesmanager.shop.model.shop.ReadableMerchantStore;
import com.salesmanager.shop.populator.customer.ReadableCustomerList;
import com.salesmanager.shop.store.security.AuthenticationRequest;
import com.salesmanager.shop.store.security.AuthenticationResponse;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ServicesTestSupport {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    protected HttpHeaders getHeader() {
        return getHeader("admin", "password");
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

    public PersistableManufacturer createManufacturer() {
        final ManufacturerDescription description = new ManufacturerDescription();
        description.setLanguage("en");
        description.setName("Tag Heuer");
        description.setFriendlyUrl("tag-watches");
        description.setTitle("Tag Heuer");
        final List<ManufacturerDescription> descriptions = new ArrayList<>();
        descriptions.add(description);

        final PersistableManufacturer manufacturer = new PersistableManufacturer();
        manufacturer.setOrder(1);
        manufacturer.setCode("MNF");
        manufacturer.setDescriptions(descriptions);

        final HttpEntity<PersistableManufacturer> entity = new HttpEntity<>(manufacturer, getHeader());
        final ResponseEntity response = testRestTemplate.postForEntity("/api/v1/private/manufacturers?store=" + Constants.DEFAULT_STORE, entity, PersistableManufacturer.class);
        final PersistableManufacturer manuf = (PersistableManufacturer) response.getBody();
        assertThat(response.getStatusCode(), is(CREATED));
        assertNotNull(manuf.getId());
        return manuf;

    }

}