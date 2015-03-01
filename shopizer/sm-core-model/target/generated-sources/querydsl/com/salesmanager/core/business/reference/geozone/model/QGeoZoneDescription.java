package com.salesmanager.core.business.reference.geozone.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGeoZoneDescription is a Querydsl query type for GeoZoneDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGeoZoneDescription extends EntityPathBase<GeoZoneDescription> {

    private static final long serialVersionUID = -198060969L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGeoZoneDescription geoZoneDescription = new QGeoZoneDescription("geoZoneDescription");

    public final com.salesmanager.core.business.common.model.QDescription _super;

    // inherited
    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    //inherited
    public final StringPath description;

    public final QGeoZone geoZone;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final com.salesmanager.core.business.reference.language.model.QLanguage language;

    //inherited
    public final StringPath name;

    //inherited
    public final StringPath title;

    public QGeoZoneDescription(String variable) {
        this(GeoZoneDescription.class, forVariable(variable), INITS);
    }

    public QGeoZoneDescription(Path<? extends GeoZoneDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGeoZoneDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGeoZoneDescription(PathMetadata<?> metadata, PathInits inits) {
        this(GeoZoneDescription.class, metadata, inits);
    }

    public QGeoZoneDescription(Class<? extends GeoZoneDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.salesmanager.core.business.common.model.QDescription(type, metadata, inits);
        this.auditSection = _super.auditSection;
        this.description = _super.description;
        this.geoZone = inits.isInitialized("geoZone") ? new QGeoZone(forProperty("geoZone")) : null;
        this.id = _super.id;
        this.language = _super.language;
        this.name = _super.name;
        this.title = _super.title;
    }

}

