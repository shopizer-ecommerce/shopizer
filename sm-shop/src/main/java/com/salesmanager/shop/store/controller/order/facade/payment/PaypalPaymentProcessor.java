package com.salesmanager.shop.store.controller.order.facade.payment;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.shop.model.order.ShopOrder;

public class PaypalPaymentProcessor implements PaymentTypeProcessor {

    @Override
    public Payment processPayment(ShopOrder order, Order modelOrder,
            Payment basePayment, Transaction transaction) throws Exception {

        if (transaction == null) {
            throw new ServiceException("payment.error");
        }

        com.salesmanager.core.model.payments.PaypalPayment payment =
                new com.salesmanager.core.model.payments.PaypalPayment();

        payment.setPayerId(transaction.getTransactionDetails().get("PAYERID"));
        payment.setPaymentToken(transaction.getTransactionDetails().get("TOKEN"));

        return payment;
    }
}
