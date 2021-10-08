package com.salesmanager.test.shop.integration.admin.products;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.ProductRepository;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.model.catalog.product.Product;
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

import static com.salesmanager.core.model.merchant.MerchantStore.DEFAULT_STORE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Contains tests targeting {@link com.salesmanager.shop.admin.controller.products.ProductKeywordsController}
 */
@SpringBootTest(classes = ShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ProductKeywordsIntegrationTest {

    private static final String QUERY_PARAM_ID = "id";

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    /**
     * used for storing a test product
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * used to retrieve an existing merchant (which is a mandatory field for product)
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
    @WithMockUser(roles = {"PRODUCTS"})
    public void displayKeywordsReturnsHttp400ForMissingID() throws Exception {
        // when endpoint is called without any ID
        ResultActions resultActions = callDisplayKeywordsEndpoint(null);

        // then HTTP status 400 is expected
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = {"PRODUCTS"})
    public void displayKeywordsReturnsHttp400ForInvalidID() throws Exception {
        // when endpoint is called with an invalid ID
        String queryParam = "notANumber";
        ResultActions resultActions = callDisplayKeywordsEndpoint(queryParam);

        // then HTTP status 400 is expected
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = {"PRODUCTS"})
    public void displayKeywordsForExistingID() throws Exception {
        // given a product in DB
        int givenProductID = 1;
        Product existingProduct = createMinimalProduct(givenProductID);
        productRepository.save(existingProduct);

        // when endpoint is called with an existing product ID
        String queryParam = "" + givenProductID;
        ResultActions resultActions = callDisplayKeywordsEndpoint(queryParam);

        // then the correct view is expected without any error
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name(ControllerConstants.Tiles.Product.productKeywords))
                .andExpect(model().hasNoErrors());
    }

    private ResultActions callDisplayKeywordsEndpoint(String queryParam) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/admin/products/product/keywords.html");
        if (queryParam != null) {
            requestBuilder.queryParam(QUERY_PARAM_ID, queryParam);
        }
        return mvc.perform(requestBuilder);
    }

    private Product createMinimalProduct(int id) throws ServiceException {
        Product product = new Product();
        product.setId((long) id);
        product.setMerchantStore(merchantService.getByCode(DEFAULT_STORE));
        return product;
    }
}
