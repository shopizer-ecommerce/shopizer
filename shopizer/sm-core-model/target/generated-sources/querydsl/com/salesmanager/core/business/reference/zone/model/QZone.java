package com.salesmanager.core.business.reference.zone.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QZone is a Querydsl query type for Zone
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QZone extends EntityPathBase<Zone> {

    private static final long serialVersionUID = -1948445487L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QZone zone = new QZone("zone");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final StringPath code = createString("code");

    public final com.salesmanager.core.business.reference.country.model.QCountry country;

    public final ListPath<ZoneDescription, QZoneDescription> descriptions = this.<ZoneDescription, QZoneDescription>createList("descriptions", ZoneDescription.class, QZoneDescription.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public QZone(String variable) {
        this(Zone.class, forVariable(variable), INITS);
    }

    public QZone(Path<? extends Zone> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QZone(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QZone(PathMetadata<?> metadata, PathInits inits) {
        this(Zone.class, metadata, inits);
    }

    public QZone(Class<? extends Zone> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new com.salesmanager.core.business.reference.country.model.QCountry(forProperty("country"), inits.get("country")) : null;
    }

}

