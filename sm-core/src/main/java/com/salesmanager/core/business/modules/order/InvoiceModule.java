package com.salesmanager.core.business.modules.order;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.reference.language.Language;

import java.io.ByteArrayOutputStream;


public interface InvoiceModule {

    ByteArrayOutputStream createInvoice(MerchantStore store, Order order, Language language) throws Exception;

}
