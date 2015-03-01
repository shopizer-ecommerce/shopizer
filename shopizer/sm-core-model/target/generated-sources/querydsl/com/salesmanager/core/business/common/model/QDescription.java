package com.salesmanager.core.business.common.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDescription is a Querydsl query type for Description
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QDescription extends EntityPathBase<Description> {

    private static final long serialVersionUID = -340390733L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDescription description1 = new QDescription("description1");

    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.salesmanager.core.business.reference.language.model.QLanguage language;

    public final StringPath name = createString("name");

    public final StringPath title = createString("title");

    public QDescription(String variable) {
        this(Description.class, forVariable(variable), INITS);
    }

    public QDescription(Path<? extends Description> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDescription(PathMetadata<?> metadata, PathInits inits) {
        this(Description.class, metadata, inits);
    }

    public QDescription(Class<? extends Description> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auditSection = inits.isInitialized("auditSection") ? new com.salesmanager.core.business.common.model.audit.QAuditSection(forProperty("auditSection")) : null;
        this.language = inits.isInitialized("language") ? new com.salesmanager.core.business.reference.language.model.QLanguage(forProperty("language"), inits.get("language")) : null;
    }

}

