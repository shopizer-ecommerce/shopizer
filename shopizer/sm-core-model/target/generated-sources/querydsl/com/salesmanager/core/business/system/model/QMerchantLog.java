package com.salesmanager.core.business.system.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMerchantLog is a Querydsl query type for MerchantLog
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMerchantLog extends EntityPathBase<MerchantLog> {

    private static final long serialVersionUID = -1072204713L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMerchantLog merchantLog = new QMerchantLog("merchantLog");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath log = createString("log");

    public final StringPath module = createString("module");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final com.salesmanager.core.business.merchant.model.QMerchantStore store;

    public QMerchantLog(String variable) {
        this(MerchantLog.class, forVariable(variable), INITS);
    }

    public QMerchantLog(Path<? extends MerchantLog> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMerchantLog(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMerchantLog(PathMetadata<?> metadata, PathInits inits) {
        this(MerchantLog.class, metadata, inits);
    }

    public QMerchantLog(Class<? extends MerchantLog> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("store"), inits.get("store")) : null;
    }

}

