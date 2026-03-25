package com.salesmanager.core.business.services.catalog.product;

import java.util.List;

import com.salesmanager.core.model.catalog.product.RecentlyViewed;

public interface RecentlyViewedService {

    void recordView(Long productId, Long customerId, String sessionId);

    List<RecentlyViewed> getRecentlyViewed(Long customerId, String sessionId);

    List<Object[]> getMostViewed(org.springframework.data.domain.Pageable pageable);
}
