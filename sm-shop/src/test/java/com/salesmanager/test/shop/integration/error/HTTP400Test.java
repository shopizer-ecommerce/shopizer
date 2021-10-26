package com.salesmanager.test.shop.integration.error;

import com.salesmanager.shop.application.ShopApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class HTTP400Test {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    /**
     *  test java.lang.IllegalArgumentException in ShopErrorController should return bad request:400
     * @throws Exception
     */
    @Test
    public void testShopErrorController() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/shop");
        requestBuilder.queryParam("locale", "372.92268390754566");
        ResultActions resultActions =  mvc.perform(requestBuilder);
        // then HTTP status 400 is expected
        resultActions.andExpect(status().isBadRequest());
    }

    /**
     *  test java.lang.IllegalArgumentException in AdminErrorController should return bad request:400
     * @throws Exception
     */
    @Test
    public void testAdminErrorController() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/admin");
        requestBuilder.queryParam("locale", "372.92268390754566");
        ResultActions resultActions =  mvc.perform(requestBuilder);
        // then HTTP status 400 is expected
        resultActions.andExpect(status().isBadRequest());
    }

    /**
     *  test java.lang.IllegalArgumentException in ImagesController should return bad request:400
     * @throws Exception
     */
    @Test
    public void test400InImageController() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/static/products/OpenBSD/someday/.biLevel");
        requestBuilder.queryParam("locale", "372.92268390754566");
        ResultActions resultActions =  mvc.perform(requestBuilder);
        // then HTTP status 400 is expected
        resultActions.andExpect(status().isBadRequest());
    }

    /**
     *  test MissingServletRequestParameterException in ProductKeywordsController should return bad request:400
     * @throws Exception
     */
    @Test
    public void test400InProductKeywordsController() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/admin/products/product/keywords.html");
        ResultActions resultActions =  mvc.perform(requestBuilder);
        // then HTTP status 400 is expected
        resultActions.andExpect(status().isBadRequest());
    }


}
