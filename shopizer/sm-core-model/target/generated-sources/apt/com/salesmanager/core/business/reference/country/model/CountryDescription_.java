package com.salesmanager.core.business.reference.country.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CountryDescription.class)
public abstract class CountryDescription_ extends com.salesmanager.core.business.common.model.Description_ {

	public static volatile SingularAttribute<CountryDescription, Country> country;

}

