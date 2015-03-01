package com.salesmanager.core.business.common.model;

import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.zone.model.Zone;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Billing.class)
public abstract class Billing_ {

	public static volatile SingularAttribute<Billing, String> lastName;
	public static volatile SingularAttribute<Billing, String> postalCode;
	public static volatile SingularAttribute<Billing, String> address;
	public static volatile SingularAttribute<Billing, String> company;
	public static volatile SingularAttribute<Billing, String> state;
	public static volatile SingularAttribute<Billing, String> firstName;
	public static volatile SingularAttribute<Billing, String> telephone;
	public static volatile SingularAttribute<Billing, Zone> zone;
	public static volatile SingularAttribute<Billing, Country> country;
	public static volatile SingularAttribute<Billing, String> city;

}

