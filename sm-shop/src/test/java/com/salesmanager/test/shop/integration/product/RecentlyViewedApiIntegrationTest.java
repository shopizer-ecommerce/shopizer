package com.salesmanager.test.shop.integration.product;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableRecentlyViewedProduct;
import com.salesmanager.test.shop.common.ServicesTestSupport;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RecentlyViewedApiIntegrationTest extends ServicesTestSupport {

    private static final String SESSION_ID = "test-integration-session-rv-001";

    private HttpHeaders guestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Session-Id", SESSION_ID);
        return headers;
    }

    @Test
    public void testRecordViewReturns204() {
        // create a product to view
        ReadableProduct product = sampleProduct("rv-test-product-" + System.currentTimeMillis());
        assertNotNull(product);

        // POST /api/v1/products/{id}/view — guest (no auth, just session header)
        HttpEntity<Void> request = new HttpEntity<>(guestHeaders());
        ResponseEntity<Void> response = testRestTemplate.exchange(
                "/api/v1/products/" + product.getId() + "/view",
                HttpMethod.POST, request, Void.class);

        assertThat(response.getStatusCode(), is(NO_CONTENT));
    }

    @Test
    public void testGetRecentlyViewedReturnsListAfterView() {
        ReadableProduct product = sampleProduct("rv-test-get-" + System.currentTimeMillis());
        assertNotNull(product);

        // record the view
        HttpEntity<Void> postRequest = new HttpEntity<>(guestHeaders());
        testRestTemplate.exchange(
                "/api/v1/products/" + product.getId() + "/view",
                HttpMethod.POST, postRequest, Void.class);

        // GET /api/v1/customer/recently-viewed
        HttpEntity<Void> getRequest = new HttpEntity<>(guestHeaders());
        ResponseEntity<List<ReadableRecentlyViewedProduct>> response = testRestTemplate.exchange(
                "/api/v1/customer/recently-viewed",
                HttpMethod.GET, getRequest,
                new ParameterizedTypeReference<List<ReadableRecentlyViewedProduct>>() {});

        assertThat(response.getStatusCode(), is(OK));
        assertNotNull(response.getBody());
        // at least the product we just viewed should be in the list
        boolean found = response.getBody().stream()
                .anyMatch(p -> product.getId().equals(p.getId()));
        org.junit.Assert.assertTrue("Viewed product should appear in recently-viewed list", found);
    }

    @Test
    public void testGetRecentlyViewedWithNoViewsReturnsEmptyList() {
        // fresh session that has never viewed anything
        HttpHeaders freshHeaders = new HttpHeaders();
        freshHeaders.set("X-Session-Id", "empty-session-" + System.currentTimeMillis());

        HttpEntity<Void> request = new HttpEntity<>(freshHeaders);
        ResponseEntity<List<ReadableRecentlyViewedProduct>> response = testRestTemplate.exchange(
                "/api/v1/customer/recently-viewed",
                HttpMethod.GET, request,
                new ParameterizedTypeReference<List<ReadableRecentlyViewedProduct>>() {});

        assertThat(response.getStatusCode(), is(OK));
        assertNotNull(response.getBody());
        assertThat(response.getBody().size(), is(0));
    }

    @Test
    public void testMostViewedRequiresAdminAuth() {
        // unauthenticated request should be rejected (401/403)
        ResponseEntity<String> response = testRestTemplate.exchange(
                "/api/v1/private/analytics/most-viewed",
                HttpMethod.GET, HttpEntity.EMPTY, String.class);

        org.junit.Assert.assertTrue(
                "Most-viewed endpoint should require auth",
                response.getStatusCode().value() == 401 || response.getStatusCode().value() == 403);
    }

    @Test
    public void testMostViewedWithAdminAuth() {
        HttpEntity<Void> request = new HttpEntity<>(getHeader()); // admin token
        ResponseEntity<List<ReadableRecentlyViewedProduct>> response = testRestTemplate.exchange(
                "/api/v1/private/analytics/most-viewed",
                HttpMethod.GET, request,
                new ParameterizedTypeReference<List<ReadableRecentlyViewedProduct>>() {});

        assertThat(response.getStatusCode(), is(OK));
        assertNotNull(response.getBody());
    }
}
