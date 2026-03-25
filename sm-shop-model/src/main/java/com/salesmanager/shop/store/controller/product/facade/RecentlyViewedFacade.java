package com.salesmanager.shop.store.controller.product.facade;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableRecentlyViewedProduct;

public interface RecentlyViewedFacade {

    void recordView(Long productId, Long customerId, String sessionId);

    List<ReadableRecentlyViewedProduct> getRecentlyViewed(Long customerId, String sessionId,
            MerchantStore store, Language language);

    List<ReadableRecentlyViewedProduct> getMostViewed(Pageable pageable,
            MerchantStore store, Language language);
}
