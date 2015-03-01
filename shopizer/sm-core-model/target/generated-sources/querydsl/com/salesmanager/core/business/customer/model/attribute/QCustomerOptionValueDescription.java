package com.salesmanager.core.business.customer.model.attribute;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomerOptionValueDescription is a Querydsl query type for CustomerOptionValueDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomerOptionValueDescription extends EntityPathBase<CustomerOptionValueDescription> {

    private static final long serialVersionUID = 1656934278L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerOptionValueDescription customerOptionValueDescription = new QCustomerOptionValueDescription("customerOptionValueDescription");

    public final com.salesmanager.core.business.common.model.QDescription _super;

    // inherited
    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final QCustomerOptionValue customerOptionValue;

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

    public QCustomerOptionValueDescription(String variable) {
        this(CustomerOptionValueDescription.class, forVariable(variable), INITS);
    }

    public QCustomerOptionValueDescription(Path<? extends CustomerOptionValueDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerOptionValueDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerOptionValueDescription(PathMetadata<?> metadata, PathInits inits) {
        this(CustomerOptionValueDescription.class, metadata, inits);
    }

    public QCustomerOptionValueDescription(Class<? extends CustomerOptionValueDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.salesmanager.core.business.common.model.QDescription(type, metadata, inits);
        this.auditSection = _super.auditSection;
        this.customerOptionValue = inits.isInitialized("customerOptionValue") ? new QCustomerOptionValue(forProperty("customerOptionValue"), inits.get("customerOptionValue")) : null;
        this.description = _super.description;
        this.id = _super.id;
        this.language = _super.language;
        this.name = _super.name;
        this.title = _super.title;
    }

}

