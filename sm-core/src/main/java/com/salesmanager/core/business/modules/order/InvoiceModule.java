package com.salesmanager.core.business.modules.order;

import java.io.ByteArrayOutputStream;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.reference.language.Language;


public interface InvoiceModule {
	
	public ByteArrayOutputStream createInvoice(MerchantStore store, Order order, Language language) throws Exception;

}
