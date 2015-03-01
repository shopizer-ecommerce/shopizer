package com.salesmanager.core.business.reference.geozone.model;

import com.salesmanager.core.business.reference.country.model.Country;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(GeoZone.class)
public abstract class GeoZone_ {

	public static volatile SingularAttribute<GeoZone, Long> id;
	public static volatile SingularAttribute<GeoZone, String> name;
	public static volatile SingularAttribute<GeoZone, String> code;
	public static volatile ListAttribute<GeoZone, Country> countries;
	public static volatile ListAttribute<GeoZone, GeoZoneDescription> descriptions;

}

