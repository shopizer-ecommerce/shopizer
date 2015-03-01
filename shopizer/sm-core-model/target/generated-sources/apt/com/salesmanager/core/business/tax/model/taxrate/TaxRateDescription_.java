package com.salesmanager.core.business.tax.model.taxrate;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TaxRateDescription.class)
public abstract class TaxRateDescription_ extends com.salesmanager.core.business.common.model.Description_ {

	public static volatile SingularAttribute<TaxRateDescription, TaxRate> taxRate;

}

