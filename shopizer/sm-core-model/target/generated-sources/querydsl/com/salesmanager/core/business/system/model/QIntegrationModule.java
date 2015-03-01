package com.salesmanager.core.business.system.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QIntegrationModule is a Querydsl query type for IntegrationModule
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIntegrationModule extends EntityPathBase<IntegrationModule> {

    private static final long serialVersionUID = 1329789211L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIntegrationModule integrationModule = new QIntegrationModule("integrationModule");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final StringPath code = createString("code");

    public final StringPath configDetails = createString("configDetails");

    public final StringPath configuration = createString("configuration");

    public final BooleanPath customModule = createBoolean("customModule");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath module = createString("module");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath regions = createString("regions");

    public final StringPath type = createString("type");

    public QIntegrationModule(String variable) {
        this(IntegrationModule.class, forVariable(variable), INITS);
    }

    public QIntegrationModule(Path<? extends IntegrationModule> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIntegrationModule(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIntegrationModule(PathMetadata<?> metadata, PathInits inits) {
        this(IntegrationModule.class, metadata, inits);
    }

    public QIntegrationModule(Class<? extends IntegrationModule> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auditSection = inits.isInitialized("auditSection") ? new com.salesmanager.core.business.common.model.audit.QAuditSection(forProperty("auditSection")) : null;
    }

}

