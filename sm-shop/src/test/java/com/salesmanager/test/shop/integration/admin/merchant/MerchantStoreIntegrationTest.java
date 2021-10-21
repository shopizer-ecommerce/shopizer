package com.salesmanager.test.shop.integration.admin.merchant;

import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.application.ShopApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Contains tests targeting {@link com.salesmanager.shop.admin.controller.merchant.MerchantStoreController}
 */
@SpringBootTest(classes = ShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MerchantStoreIntegrationTest {
    private static final String QUERY_PARAM_ID = "id";

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    /**
     * used to retrieve an existing merchant store
     */
    @Autowired
    private MerchantStoreService merchantService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @WithMockUser(roles = {"STORE"})
    public void editStoreInformsAboutMissingID() throws Exception {
        // when endpoint is called without any store ID
        ResultActions resultActions = callEditStoreEndpoint(null);

        // then a message indicating that the required request parameter is missing
        String expectedErrorSubString = String.format("Required request parameter '%s'", QUERY_PARAM_ID);
        resultActions.andExpect(model().attribute("errMsg", containsString(expectedErrorSubString)));
    }

    @Test
    @WithMockUser(roles = {"STORE"})
    public void callEditStoreWithExistingID() throws Exception {
        // when endpoint is called with an existing store ID
        Integer existingID = merchantService.getByCode(MerchantStore.DEFAULT_STORE).getId();
        ResultActions resultActions = callEditStoreEndpoint(existingID.toString());

        // then the correct view is expected without any error
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name(ControllerConstants.Tiles.Store.store))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("store"));
    }

    private ResultActions callEditStoreEndpoint(String queryParam) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/admin/store/editStore.html");
        if (queryParam != null) {
            requestBuilder.queryParam(QUERY_PARAM_ID, queryParam);
        }
        return mvc.perform(requestBuilder);
    }
}
