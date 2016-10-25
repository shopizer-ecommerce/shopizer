package com.salesmanager.core.business.services.reference.currency;

import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.reference.currency.Currency;

public interface CurrencyService extends SalesManagerEntityService<Long, Currency> {

	Currency getByCode(String code);

}
