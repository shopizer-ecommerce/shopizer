package com.salesmanager.core.business.repositories.order.orderproduct;

import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {


}
