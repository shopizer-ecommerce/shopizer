package com.salesmanager.test.shop.integration;

import com.salesmanager.shop.application.ShopApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ShopApplication.class)
class GeneralIntegrationTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    /**
     * Ensures, that we should not get different HTTP status codes back among unknown locales.
     *
     * @param givenLocale the value for 'locale' query parameter
     */
    @ParameterizedTest
    @ValueSource(strings = {"yy", "123", "--", "-."})
    void sameHttpStatusForAllUnknownLocales(String givenLocale) throws Exception {
        // when endpoint is called with "locale" parameter
        MockHttpServletRequestBuilder requestBuilder = get("/shop");
        requestBuilder.queryParam("locale", givenLocale);

        ResultActions resultActions = mvc.perform(requestBuilder);

        // then no error is returned
        resultActions.andExpect(status().isOk());
    }
}
