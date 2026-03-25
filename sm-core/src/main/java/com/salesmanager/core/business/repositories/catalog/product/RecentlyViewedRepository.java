package com.salesmanager.core.business.repositories.catalog.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.RecentlyViewed;

public interface RecentlyViewedRepository extends JpaRepository<RecentlyViewed, Long> {

    List<RecentlyViewed> findTop10ByCustomerIdOrderByViewedAtDesc(Long customerId);

    List<RecentlyViewed> findTop10BySessionIdOrderByViewedAtDesc(String sessionId);

    Optional<RecentlyViewed> findByCustomerIdAndProductId(Long customerId, Long productId);

    Optional<RecentlyViewed> findBySessionIdAndProductId(String sessionId, Long productId);

    @Query("select rv.product.id, count(rv) as views from RecentlyViewed rv group by rv.product.id order by views desc")
    List<Object[]> findMostViewed(org.springframework.data.domain.Pageable pageable);
}
