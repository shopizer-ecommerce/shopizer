package com.salesmanager.core.modules.order;

import java.io.ByteArrayOutputStream;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.business.reference.language.model.Language;

public interface InvoiceModule {
	
	public ByteArrayOutputStream createInvoice(MerchantStore store, Order order, Language language) throws Exception;

}
