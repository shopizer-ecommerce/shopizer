package com.salesmanager.core.business.reference.geozone.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGeoZone is a Querydsl query type for GeoZone
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGeoZone extends EntityPathBase<GeoZone> {

    private static final long serialVersionUID = 52549221L;

    public static final QGeoZone geoZone = new QGeoZone("geoZone");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final StringPath code = createString("code");

    public final ListPath<com.salesmanager.core.business.reference.country.model.Country, com.salesmanager.core.business.reference.country.model.QCountry> countries = this.<com.salesmanager.core.business.reference.country.model.Country, com.salesmanager.core.business.reference.country.model.QCountry>createList("countries", com.salesmanager.core.business.reference.country.model.Country.class, com.salesmanager.core.business.reference.country.model.QCountry.class, PathInits.DIRECT2);

    public final ListPath<GeoZoneDescription, QGeoZoneDescription> descriptions = this.<GeoZoneDescription, QGeoZoneDescription>createList("descriptions", GeoZoneDescription.class, QGeoZoneDescription.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public QGeoZone(String variable) {
        super(GeoZone.class, forVariable(variable));
    }

    public QGeoZone(Path<? extends GeoZone> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeoZone(PathMetadata<?> metadata) {
        super(GeoZone.class, metadata);
    }

}

