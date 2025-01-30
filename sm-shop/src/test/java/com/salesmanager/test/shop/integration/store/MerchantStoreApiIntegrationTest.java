package com.salesmanager.test.shop.integration.store;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.amazonaws.services.kms.model.NotFoundException;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.api.v1.store.MerchantStoreApi;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.store.controller.user.facade.UserFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.security.Principal;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
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

    @InjectMocks
    private MerchantStoreApi controller; // Update controller name to MerchantStoreApi
    @Mock
    private UserFacade userFacade; // Mocked dependency
    @Mock
    private StoreFacade storeFacade;

    @Inject
    private TestRestTemplate testRestTemplate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
  
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
  public void testCreateStore() throws Exception {
      
      
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

      ResponseEntity<Void> response = testRestTemplate.exchange(String.format("/api/v1/private/store/"), HttpMethod.POST, httpEntity, Void.class);

      assertThat(response.getStatusCode(), is(HttpStatus.OK));

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

  /**
   *  Test case to verify the functionality true statement whether the store exist.
   * Added by Luv Patel with increased coverage
   * */
    @Test
    public void testExistsOnStoreExists() {
        String code = "sampleCode";
        when(storeFacade.existByCode(code)).thenReturn(true);

        // Making a call to the method to be tested
        ResponseEntity<EntityExists> response = controller.exists(code);

        // Verifying the response
        assertTrue(response.getBody().isExists());
    }

    /**
     * Test case to check the functionality of 'exists' when provided with wrong store code.
     * Added By Luv Pate;
     * */
    @Test
    public void testExistsOnStoreDoesNotExist() {
        String code = "notPresentCode";
        when(storeFacade.existByCode(code)).thenReturn(false);

        // Making a call to the method to be tested
        ResponseEntity<EntityExists> response = controller.exists(code);

        // Verifying the response
        assertFalse(response.getBody().isExists());
    }

    /**
     * Test case to check the functionality of StoreFull method, when the authenticated user is NULL
     * Added by Luv Patel with increased branch coverage
     */
    @Test
    public void testStoreFullOnAuthenticatedUserIsNull() {
        // Setting up mock behavior
        when(userFacade.authenticatedUser()).thenReturn(null);
        // Create a Language object
        Language language = new Language();
        // Verifying whether the storeFull method throws an UnauthorizedException
        assertThrows(UnauthorizedException.class, () -> {
            controller.storeFull("code", language);
        });
    }

    /**
     * Test case to check the functionality of StoreFull method, when the user is not in Authenticated Group
     * Added by Luv Patel with increased branch coverage
     */
    @Test
    public void testStoreFullOnUserNotInAuthorizedGroup() {
        // Set up mock behavior
        when(userFacade.authenticatedUser()).thenReturn("authenticatedUser");
        doThrow(UnauthorizedException.class).when(userFacade).authorizedGroup(eq("authenticatedUser"), anyList());

        // Create a Language object
        Language language = new Language();

        // Verify that the storeFull method throws an UnauthorizedException
        assertThrows(UnauthorizedException.class, () -> {
            controller.storeFull("code", language);
        });
    }


//
}
