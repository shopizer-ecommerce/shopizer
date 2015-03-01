package com.salesmanager.core.business.reference.currency.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Currency.class)
public abstract class Currency_ {

	public static volatile SingularAttribute<Currency, Long> id;
	public static volatile SingularAttribute<Currency, String> name;
	public static volatile SingularAttribute<Currency, Boolean> supported;
	public static volatile SingularAttribute<Currency, String> code;
	public static volatile SingularAttribute<Currency, java.util.Currency> currency;

}

