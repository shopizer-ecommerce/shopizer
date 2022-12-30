package com.salesmanager.core.business.repositories.reference.currency;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.reference.currency.Currency;

public interface CurrencyRepository extends JpaRepository <Currency, Long> {

	
	Currency getByCode(String code);
}
