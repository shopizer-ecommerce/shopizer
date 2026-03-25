package com.salesmanager.test.catalog;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import com.salesmanager.core.business.services.catalog.product.RecentlyViewedService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.RecentlyViewed;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.test.common.AbstractSalesManagerCoreTestCase;

public class RecentlyViewedServiceTest extends AbstractSalesManagerCoreTestCase {

    @Inject
    private RecentlyViewedService recentlyViewedService;

    private static int skuCounter = 0;

    private Product createTestProduct(MerchantStore store, Language en) throws Exception {
        ProductType generalType = productTypeService.getProductType(ProductType.GENERAL_TYPE);

        Product product = new Product();
        product.setProductHeight(new BigDecimal("4"));
        product.setProductLength(new BigDecimal("3"));
        product.setProductWeight(new BigDecimal("1"));
        product.setProductWidth(new BigDecimal("2"));
        product.setSku("RV-TEST-SKU-" + (++skuCounter));
        product.setType(generalType);
        product.setMerchantStore(store);

        ProductDescription desc = new ProductDescription();
        desc.setName("Recently Viewed Test Product");
        desc.setLanguage(en);
        desc.setProduct(product);
        Set<ProductDescription> descs = new HashSet<>();
        descs.add(desc);
        product.setDescriptions(descs);

        ProductAvailability availability = new ProductAvailability();
        availability.setProductQuantity(10);
        availability.setRegion("*");
        availability.setProduct(product);

        ProductPrice price = new ProductPrice();
        price.setDefaultPrice(true);
        price.setProductPriceAmount(new BigDecimal("19.99"));
        price.setProductAvailability(availability);  // required back-reference
        ProductPriceDescription priceDesc = new ProductPriceDescription();
        priceDesc.setName("Base price");
        priceDesc.setLanguage(en);
        priceDesc.setProductPrice(price);
        Set<ProductPriceDescription> priceDescs = new HashSet<>();
        priceDescs.add(priceDesc);
        price.setDescriptions(priceDescs);
        Set<ProductPrice> prices = new HashSet<>();
        prices.add(price);
        availability.setPrices(prices);

        Set<ProductAvailability> availabilities = new HashSet<>();
        availabilities.add(availability);
        product.setAvailabilities(availabilities);

        productService.create(product);
        return product;
    }

    @Test
    public void testRecordViewForGuest() throws Exception {
        MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
        Language en = languageService.getByCode("en");
        Product product = createTestProduct(store, en);

        String sessionId = "test-session-guest-001";

        // record view as guest
        recentlyViewedService.recordView(product.getId(), null, sessionId);

        List<RecentlyViewed> results = recentlyViewedService.getRecentlyViewed(null, sessionId);
        Assert.assertFalse("Should have at least one recently viewed product", results.isEmpty());
        Assert.assertEquals(product.getId(), results.get(0).getProduct().getId());
    }

    @Test
    public void testRecordViewUpsert() throws Exception {
        MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
        Language en = languageService.getByCode("en");
        Product product = createTestProduct(store, en);

        String sessionId = "test-session-upsert-" + System.currentTimeMillis();

        // record same product twice — should still be 1 row
        recentlyViewedService.recordView(product.getId(), null, sessionId);
        recentlyViewedService.recordView(product.getId(), null, sessionId);

        List<RecentlyViewed> results = recentlyViewedService.getRecentlyViewed(null, sessionId);
        Assert.assertEquals("Upsert should keep only 1 row per session+product", 1, results.size());
    }

    @Test
    public void testTop10Limit() throws Exception {
        MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
        Language en = languageService.getByCode("en");
        String sessionId = "test-session-top10-" + System.currentTimeMillis();

        // create and view 12 distinct products
        for (int i = 0; i < 12; i++) {
            Product p = createTestProduct(store, en);
            recentlyViewedService.recordView(p.getId(), null, sessionId);
        }

        List<RecentlyViewed> results = recentlyViewedService.getRecentlyViewed(null, sessionId);
        Assert.assertTrue("Should return at most 10 recently viewed products", results.size() <= 10);
    }
}
