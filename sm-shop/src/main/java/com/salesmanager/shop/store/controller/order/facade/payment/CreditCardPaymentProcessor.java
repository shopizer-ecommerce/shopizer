package com.salesmanager.shop.store.controller.order.facade.payment;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.salesmanager.core.business.utils.CreditCardUtils;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.payment.CreditCard;
import com.salesmanager.core.model.payments.CreditCardPayment;
import com.salesmanager.core.model.payments.CreditCardType;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.shop.model.order.ShopOrder;

public class CreditCardPaymentProcessor implements PaymentTypeProcessor {

    @Override
    public Payment processPayment(ShopOrder order, Order modelOrder,
            Payment basePayment, Transaction transaction) throws Exception {

        CreditCardPayment payment = new CreditCardPayment();
        payment.setCardOwner(order.getPayment().get("creditcard_card_holder"));
        payment.setCredidCardValidationNumber(order.getPayment().get("creditcard_card_cvv"));
        payment.setCreditCardNumber(order.getPayment().get("creditcard_card_number"));
        payment.setExpirationMonth(order.getPayment().get("creditcard_card_expirationmonth"));
        payment.setExpirationYear(order.getPayment().get("creditcard_card_expirationyear"));

        Map<String, String> paymentMetaData = order.getPayment();
        payment.setPaymentMetaData(paymentMetaData);
        payment.setPaymentType(PaymentType.CREDITCARD);
        payment.setAmount(order.getOrderTotalSummary().getTotal());
        payment.setModuleName(order.getPaymentModule());
        payment.setCurrency(modelOrder.getCurrency());

        String cardType = order.getPayment().get("creditcard_card_type");
        CreditCardType creditCardType = null;

        if (CreditCardType.AMEX.name().equalsIgnoreCase(cardType)) {
            creditCardType = CreditCardType.AMEX;
        } else if (CreditCardType.VISA.name().equalsIgnoreCase(cardType)) {
            creditCardType = CreditCardType.VISA;
        } else if (CreditCardType.MASTERCARD.name().equalsIgnoreCase(cardType)) {
            creditCardType = CreditCardType.MASTERCARD;
        } else if (CreditCardType.DINERS.name().equalsIgnoreCase(cardType)) {
            creditCardType = CreditCardType.DINERS;
        } else if (CreditCardType.DISCOVERY.name().equalsIgnoreCase(cardType)) {
            creditCardType = CreditCardType.DISCOVERY;
        }

        payment.setCreditCard(creditCardType);

        if (creditCardType != null) {
            CreditCard cc = new CreditCard();
            cc.setCardType(creditCardType);
            cc.setCcCvv(payment.getCredidCardValidationNumber());
            cc.setCcOwner(payment.getCardOwner());
            cc.setCcExpires(payment.getExpirationMonth() + "-" + payment.getExpirationYear());

            if (!StringUtils.isBlank(cc.getCcNumber())) {
                String maskedNumber = CreditCardUtils
                        .maskCardNumber(order.getPayment().get("creditcard_card_number"));
                cc.setCcNumber(maskedNumber);
                modelOrder.setCreditCard(cc);
            }
        }

        return payment;
    }
}
