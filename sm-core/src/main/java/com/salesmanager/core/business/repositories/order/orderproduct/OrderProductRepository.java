package com.salesmanager.core.business.repositories.order.orderproduct;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.order.orderproduct.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {


}
