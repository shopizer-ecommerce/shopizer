package com.salesmanager.core.business.customer.model.attribute;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomerOptionDescription is a Querydsl query type for CustomerOptionDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomerOptionDescription extends EntityPathBase<CustomerOptionDescription> {

    private static final long serialVersionUID = -608909983L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerOptionDescription customerOptionDescription = new QCustomerOptionDescription("customerOptionDescription");

    public final com.salesmanager.core.business.common.model.QDescription _super;

    // inherited
    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final QCustomerOption customerOption;

    public final StringPath customerOptionComment = createString("customerOptionComment");

    //inherited
    public final StringPath description;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final com.salesmanager.core.business.reference.language.model.QLanguage language;

    //inherited
    public final StringPath name;

    //inherited
    public final StringPath title;

    public QCustomerOptionDescription(String variable) {
        this(CustomerOptionDescription.class, forVariable(variable), INITS);
    }

    public QCustomerOptionDescription(Path<? extends CustomerOptionDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerOptionDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerOptionDescription(PathMetadata<?> metadata, PathInits inits) {
        this(CustomerOptionDescription.class, metadata, inits);
    }

    public QCustomerOptionDescription(Class<? extends CustomerOptionDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.salesmanager.core.business.common.model.QDescription(type, metadata, inits);
        this.auditSection = _super.auditSection;
        this.customerOption = inits.isInitialized("customerOption") ? new QCustomerOption(forProperty("customerOption"), inits.get("customerOption")) : null;
        this.description = _super.description;
        this.id = _super.id;
        this.language = _super.language;
        this.name = _super.name;
        this.title = _super.title;
    }

}

