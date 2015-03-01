package com.salesmanager.core.business.common.model.audit;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAuditSection is a Querydsl query type for AuditSection
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QAuditSection extends BeanPath<AuditSection> {

    private static final long serialVersionUID = -24539866L;

    public static final QAuditSection auditSection = new QAuditSection("auditSection");

    public final DateTimePath<java.util.Date> dateCreated = createDateTime("dateCreated", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateModified = createDateTime("dateModified", java.util.Date.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public QAuditSection(String variable) {
        super(AuditSection.class, forVariable(variable));
    }

    public QAuditSection(Path<? extends AuditSection> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuditSection(PathMetadata<?> metadata) {
        super(AuditSection.class, metadata);
    }

}

