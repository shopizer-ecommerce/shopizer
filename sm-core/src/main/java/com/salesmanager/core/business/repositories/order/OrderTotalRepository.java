package com.salesmanager.core.business.repositories.order;

import com.salesmanager.core.model.order.OrderTotal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTotalRepository extends JpaRepository<OrderTotal, Long> {


}
