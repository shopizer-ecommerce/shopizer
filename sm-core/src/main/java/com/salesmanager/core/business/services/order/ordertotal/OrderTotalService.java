package com.salesmanager.core.business.services.order.ordertotal;

import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderSummary;
import com.salesmanager.core.model.order.OrderTotalVariation;
import com.salesmanager.core.model.reference.language.Language;

/**
 * Additional dynamic order total calculation
 * from the rules engine and other modules
 * @author carlsamson
 *
 */
public interface OrderTotalService {
	
	OrderTotalVariation findOrderTotalVariation(final OrderSummary summary, final Customer customer, final MerchantStore store, final Language language) throws Exception;

}
