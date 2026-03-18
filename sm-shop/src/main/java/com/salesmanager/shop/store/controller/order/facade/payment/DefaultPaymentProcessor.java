package com.salesmanager.shop.store.controller.order.facade.payment;

import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.shop.model.order.ShopOrder;

public class DefaultPaymentProcessor implements PaymentTypeProcessor {

    @Override
    public Payment processPayment(ShopOrder order, Order modelOrder,
            Payment basePayment, Transaction transaction) {
        return basePayment;
    }
}
