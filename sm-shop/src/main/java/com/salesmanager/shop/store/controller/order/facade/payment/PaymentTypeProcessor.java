package com.salesmanager.shop.store.controller.order.facade.payment;

import java.util.Map;

import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.shop.model.order.ShopOrder;

public interface PaymentTypeProcessor {

    Payment processPayment(ShopOrder order, Order modelOrder,
            Payment basePayment, Transaction transaction) throws Exception;
}
