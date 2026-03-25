package com.salesmanager.shop.store.api.v1.product;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableRecentlyViewedProduct;
import com.salesmanager.shop.store.controller.product.facade.RecentlyViewedFacade;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1")
public class RecentlyViewedApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecentlyViewedApi.class);

    @Inject
    private RecentlyViewedFacade recentlyViewedFacade;

    @Inject
    private CustomerService customerService;

    private Long resolveCustomerId(HttpServletRequest request) {
        try {
            if (request.getUserPrincipal() != null) {
                String userName = request.getUserPrincipal().getName();
                Customer customer = customerService.getByNick(userName);
                if (customer != null) {
                    return customer.getId();
                }
            }
        } catch (Exception e) {
            LOGGER.debug("Could not resolve customer from principal", e);
        }
        return null;
    }

    @PostMapping("/products/{id}/view")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
        @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
    })
    public void recordView(
            @PathVariable Long id,
            @RequestHeader(value = "X-Session-Id", required = false) String sessionId,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            recentlyViewedFacade.recordView(id, resolveCustomerId(request), sessionId);
        } catch (Exception e) {
            LOGGER.error("Error recording product view for product id " + id, e);
            try {
                response.sendError(503, "Error recording product view");
            } catch (Exception ignore) {}
        }
    }

    @GetMapping({"/customer/recently-viewed", "/auth/customer/recently-viewed"})
    @ApiImplicitParams({
        @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
        @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
    })
    public List<ReadableRecentlyViewedProduct> getRecentlyViewed(
            @RequestHeader(value = "X-Session-Id", required = false) String sessionId,
            @ApiIgnore MerchantStore merchantStore,
            @ApiIgnore Language language,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            return recentlyViewedFacade.getRecentlyViewed(
                    resolveCustomerId(request), sessionId, merchantStore, language);
        } catch (Exception e) {
            LOGGER.error("Error fetching recently viewed products", e);
            try {
                response.sendError(503, "Error fetching recently viewed products");
            } catch (Exception ignore) {}
            return null;
        }
    }

    @GetMapping("/private/analytics/most-viewed")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
        @ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en")
    })
    public List<ReadableRecentlyViewedProduct> mostViewed(
            @ApiIgnore MerchantStore merchantStore,
            @ApiIgnore Language language,
            HttpServletResponse response) {
        try {
            return recentlyViewedFacade.getMostViewed(PageRequest.of(0, 20), merchantStore, language);
        } catch (Exception e) {
            LOGGER.error("Error fetching most viewed products", e);
            try {
                response.sendError(503, "Error fetching most viewed products");
            } catch (Exception ignore) {}
            return null;
        }
    }
}
