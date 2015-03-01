package com.salesmanager.core.business.reference.zone.model;

import com.salesmanager.core.business.reference.country.model.Country;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Zone.class)
public abstract class Zone_ {

	public static volatile SingularAttribute<Zone, Long> id;
	public static volatile SingularAttribute<Zone, String> code;
	public static volatile ListAttribute<Zone, ZoneDescription> descriptions;
	public static volatile SingularAttribute<Zone, Country> country;

}

