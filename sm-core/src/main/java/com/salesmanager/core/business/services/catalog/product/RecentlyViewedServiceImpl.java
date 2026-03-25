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
        Optional<RecentlyViewed> existing = customerId != null
                ? recentlyViewedRepository.findByCustomerIdAndProductId(customerId, productId)
                : recentlyViewedRepository.findBySessionIdAndProductId(sessionId, productId);

        RecentlyViewed rv = existing.orElseGet(() -> {
            RecentlyViewed n = new RecentlyViewed();
            Product product = productRepository.getOne(productId);
            n.setProduct(product);
            n.setCustomerId(customerId);
            n.setSessionId(sessionId);
            return n;
        });
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
