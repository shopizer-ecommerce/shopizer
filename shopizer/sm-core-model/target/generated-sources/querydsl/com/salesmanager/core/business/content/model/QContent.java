package com.salesmanager.core.business.content.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QContent is a Querydsl query type for Content
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QContent extends EntityPathBase<Content> {

    private static final long serialVersionUID = 916126944L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContent content = new QContent("content");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final StringPath code = createString("code");

    public final EnumPath<ContentPosition> contentPosition = createEnum("contentPosition", ContentPosition.class);

    public final EnumPath<ContentType> contentType = createEnum("contentType", ContentType.class);

    public final ListPath<ContentDescription, QContentDescription> descriptions = this.<ContentDescription, QContentDescription>createList("descriptions", ContentDescription.class, QContentDescription.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.salesmanager.core.business.merchant.model.QMerchantStore merchantStore;

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

    public final BooleanPath visible = createBoolean("visible");

    public QContent(String variable) {
        this(Content.class, forVariable(variable), INITS);
    }

    public QContent(Path<? extends Content> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QContent(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QContent(PathMetadata<?> metadata, PathInits inits) {
        this(Content.class, metadata, inits);
    }

    public QContent(Class<? extends Content> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auditSection = inits.isInitialized("auditSection") ? new com.salesmanager.core.business.common.model.audit.QAuditSection(forProperty("auditSection")) : null;
        this.merchantStore = inits.isInitialized("merchantStore") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("merchantStore"), inits.get("merchantStore")) : null;
    }

}

