package com.salesmanager.core.business.services.reference.currency;

import com.salesmanager.core.business.repositories.reference.currency.CurrencyRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.reference.currency.Currency;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("currencyService")
public class CurrencyServiceImpl extends SalesManagerEntityServiceImpl<Long, Currency>
        implements CurrencyService {

    private CurrencyRepository currencyRepository;

    @Inject
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        super(currencyRepository);
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency getByCode(String code) {
        return currencyRepository.getByCode(code);
    }

}
