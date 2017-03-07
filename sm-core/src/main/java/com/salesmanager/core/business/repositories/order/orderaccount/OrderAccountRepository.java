package com.salesmanager.core.business.repositories.order.orderaccount;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.order.orderaccount.OrderAccount;

public interface OrderAccountRepository extends JpaRepository<OrderAccount, Long> {


}
