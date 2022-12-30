package com.salesmanager.core.business.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.order.OrderTotal;

public interface OrderTotalRepository extends JpaRepository<OrderTotal, Long> {


}
