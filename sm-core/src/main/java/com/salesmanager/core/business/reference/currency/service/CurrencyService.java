package com.salesmanager.core.business.reference.currency.service;

import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.reference.currency.model.Currency;

public interface CurrencyService extends SalesManagerEntityService<Long, Currency> {

	Currency getByCode(String code);

}
