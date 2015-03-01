package com.salesmanager.core.business.content.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QContentDescription is a Querydsl query type for ContentDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QContentDescription extends EntityPathBase<ContentDescription> {

    private static final long serialVersionUID = 469721852L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContentDescription contentDescription = new QContentDescription("contentDescription");

    public final com.salesmanager.core.business.common.model.QDescription _super;

    // inherited
    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final QContent content;

    //inherited
    public final StringPath description;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final com.salesmanager.core.business.reference.language.model.QLanguage language;

    public final StringPath metatagDescription = createString("metatagDescription");

    public final StringPath metatagKeywords = createString("metatagKeywords");

    public final StringPath metatagTitle = createString("metatagTitle");

    //inherited
    public final StringPath name;

    public final StringPath seUrl = createString("seUrl");

    //inherited
    public final StringPath title;

    public QContentDescription(String variable) {
        this(ContentDescription.class, forVariable(variable), INITS);
    }

    public QContentDescription(Path<? extends ContentDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QContentDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QContentDescription(PathMetadata<?> metadata, PathInits inits) {
        this(ContentDescription.class, metadata, inits);
    }

    public QContentDescription(Class<? extends ContentDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.salesmanager.core.business.common.model.QDescription(type, metadata, inits);
        this.auditSection = _super.auditSection;
        this.content = inits.isInitialized("content") ? new QContent(forProperty("content"), inits.get("content")) : null;
        this.description = _super.description;
        this.id = _super.id;
        this.language = _super.language;
        this.name = _super.name;
        this.title = _super.title;
    }

}

