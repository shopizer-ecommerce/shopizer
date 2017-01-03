package com.salesmanager.core.business.order.service.ordertotal;

import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.OrderSummary;
import com.salesmanager.core.business.order.model.OrderTotalVariation;
import com.salesmanager.core.business.reference.language.model.Language;

/**
 * Additional dynamic order total calculation
 * from the rules engine and other modules
 * @author carlsamson
 *
 */
public interface OrderTotalService {
	
	OrderTotalVariation findOrderTotalVariation(final OrderSummary summary, final Customer customer, final MerchantStore store, final Language language) throws Exception;

}
