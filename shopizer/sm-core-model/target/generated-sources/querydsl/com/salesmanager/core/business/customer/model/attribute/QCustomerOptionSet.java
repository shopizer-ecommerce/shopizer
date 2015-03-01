package com.salesmanager.core.business.customer.model.attribute;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomerOptionSet is a Querydsl query type for CustomerOptionSet
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomerOptionSet extends EntityPathBase<CustomerOptionSet> {

    private static final long serialVersionUID = 1565995719L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerOptionSet customerOptionSet = new QCustomerOptionSet("customerOptionSet");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final QCustomerOption customerOption;

    public final QCustomerOptionValue customerOptionValue;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

    public QCustomerOptionSet(String variable) {
        this(CustomerOptionSet.class, forVariable(variable), INITS);
    }

    public QCustomerOptionSet(Path<? extends CustomerOptionSet> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerOptionSet(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerOptionSet(PathMetadata<?> metadata, PathInits inits) {
        this(CustomerOptionSet.class, metadata, inits);
    }

    public QCustomerOptionSet(Class<? extends CustomerOptionSet> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customerOption = inits.isInitialized("customerOption") ? new QCustomerOption(forProperty("customerOption"), inits.get("customerOption")) : null;
        this.customerOptionValue = inits.isInitialized("customerOptionValue") ? new QCustomerOptionValue(forProperty("customerOptionValue"), inits.get("customerOptionValue")) : null;
    }

}

