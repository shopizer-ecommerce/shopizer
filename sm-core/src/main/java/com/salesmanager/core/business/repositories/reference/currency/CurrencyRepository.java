package com.salesmanager.core.business.repositories.reference.currency;

import com.salesmanager.core.model.reference.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency getByCode(String code);
}
