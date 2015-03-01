package com.salesmanager.core.business.common.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBilling is a Querydsl query type for Billing
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QBilling extends BeanPath<Billing> {

    private static final long serialVersionUID = 420191922L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBilling billing = new QBilling("billing");

    public final StringPath address = createString("address");

    public final StringPath city = createString("city");

    public final StringPath company = createString("company");

    public final com.salesmanager.core.business.reference.country.model.QCountry country;

    public final StringPath firstName = createString("firstName");

    public final StringPath lastName = createString("lastName");

    public final StringPath postalCode = createString("postalCode");

    public final StringPath state = createString("state");

    public final StringPath telephone = createString("telephone");

    public final com.salesmanager.core.business.reference.zone.model.QZone zone;

    public QBilling(String variable) {
        this(Billing.class, forVariable(variable), INITS);
    }

    public QBilling(Path<? extends Billing> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBilling(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBilling(PathMetadata<?> metadata, PathInits inits) {
        this(Billing.class, metadata, inits);
    }

    public QBilling(Class<? extends Billing> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new com.salesmanager.core.business.reference.country.model.QCountry(forProperty("country"), inits.get("country")) : null;
        this.zone = inits.isInitialized("zone") ? new com.salesmanager.core.business.reference.zone.model.QZone(forProperty("zone"), inits.get("zone")) : null;
    }

}

