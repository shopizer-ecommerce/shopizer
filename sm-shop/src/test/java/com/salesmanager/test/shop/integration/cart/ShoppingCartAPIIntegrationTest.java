package com.salesmanager.test.shop.integration.cart;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.test.shop.common.ServicesTestSupport;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ShoppingCartAPIIntegrationTest extends ServicesTestSupport {

    @Autowired
    private TestRestTemplate testRestTemplate;



    /**
     * Read - GET a category by id
     *
     * @throws Exception
     */
    @Test
    public void addToCart() throws Exception {
      
        assertNotNull("TOBECOMPLETED");
/*        final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());

        final ResponseEntity<List> response = testRestTemplate.exchange(String.format("/api/v1/category/"), HttpMethod.GET,
                httpEntity, List.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception(response.toString());
        } else {
            final List<ReadableCategory> categories = response.getBody();
            assertNotNull(categories);
        }*/
    }


}