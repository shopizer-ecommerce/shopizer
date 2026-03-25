package com.salesmanager.core.business.services.catalog.product;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salesmanager.core.business.repositories.catalog.product.ProductRepository;
import com.salesmanager.core.business.repositories.catalog.product.RecentlyViewedRepository;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.RecentlyViewed;

@Service("recentlyViewedService")
public class RecentlyViewedServiceImpl implements RecentlyViewedService {

    @Inject
    private RecentlyViewedRepository recentlyViewedRepository;

    @Inject
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void recordView(Long productId, Long customerId, String sessionId) {
        List<RecentlyViewed> existing = customerId != null
                ? recentlyViewedRepository.findTop1ByCustomerIdAndProductIdOrderByViewedAtDesc(customerId, productId)
                : recentlyViewedRepository.findTop1BySessionIdAndProductIdOrderByViewedAtDesc(sessionId, productId);

        RecentlyViewed rv = existing.isEmpty() ? new RecentlyViewed() : existing.get(0);
        if (rv.getId() == null) {
            Product product = productRepository.getOne(productId);
            rv.setProduct(product);
            rv.setCustomerId(customerId);
            rv.setSessionId(sessionId);
        }
        rv.setViewedAt(new Date());
        recentlyViewedRepository.save(rv);
    }

    @Override
    public List<RecentlyViewed> getRecentlyViewed(Long customerId, String sessionId) {
        return customerId != null
                ? recentlyViewedRepository.findTop10ByCustomerIdOrderByViewedAtDesc(customerId)
                : recentlyViewedRepository.findTop10BySessionIdOrderByViewedAtDesc(sessionId);
    }

    @Override
    public List<Object[]> getMostViewed(Pageable pageable) {
        return recentlyViewedRepository.findMostViewed(pageable);
    }
}
