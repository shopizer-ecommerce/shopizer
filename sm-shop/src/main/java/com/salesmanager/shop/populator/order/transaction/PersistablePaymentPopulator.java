package com.salesmanager.shop.populator.order.transaction;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.PaymentType;
import com.salesmanager.core.model.payments.TransactionType;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.order.transaction.PersistablePayment;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;


public class PersistablePaymentPopulator extends
    AbstractDataPopulator<PersistablePayment, Payment> {

  PricingService pricingService;

  @Override
  public Payment populate(PersistablePayment source, Payment target, MerchantStore store,
      Language language) throws ServiceException {
    Validate.notNull(source, "PersistablePayment cannot be null");
    Validate.notNull(pricingService, "pricingService must be set");

    if (target == null) {
      target = new Payment();
    }

    target.setAmount(pricingService.getAmount(source.getAmount()));
    target.setModuleName(source.getPaymentModule());
    target.setPaymentType(PaymentType.valueOf(source.getPaymentType()));
    target.setTransactionType(TransactionType.valueOf(source.getTransactionType()));

    Map<String, String> metadata = new HashMap<String, String>();
    metadata.put("paymentToken", source.getPaymentToken());
    target.setPaymentMetaData(metadata);

    return target;
  }

  @Override
  protected Payment createTarget() {
    // TODO Auto-generated method stub
    return null;
  }

  public PricingService getPricingService() {
    return pricingService;
  }

  public void setPricingService(PricingService pricingService) {
    this.pricingService = pricingService;
  }
}
