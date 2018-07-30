package com.salesmanager.core.business.repositories.shipping;

import com.salesmanager.core.model.shipping.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShippingQuoteRepository extends JpaRepository<Quote, Long> {

    @Query("select q from Quote as q where q.orderId = ?1")
    List<Quote> findByOrder(Long order);

}
