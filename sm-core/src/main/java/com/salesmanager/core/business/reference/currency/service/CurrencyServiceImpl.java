package com.salesmanager.core.business.reference.currency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.reference.currency.dao.CurrencyDao;
import com.salesmanager.core.business.reference.currency.model.Currency;
import com.salesmanager.core.business.reference.currency.model.Currency_;

@Service("currencyService")
public class CurrencyServiceImpl extends SalesManagerEntityServiceImpl<Long, Currency>
	implements CurrencyService {
	
	@Autowired
	public CurrencyServiceImpl(CurrencyDao currencyDao) {
		super(currencyDao);
	}
	
	@Override
	public Currency getByCode(String code) {
		return getByField(Currency_.code, code);
	}

}
