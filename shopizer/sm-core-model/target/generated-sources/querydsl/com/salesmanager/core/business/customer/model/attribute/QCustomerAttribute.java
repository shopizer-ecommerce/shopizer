package com.salesmanager.core.business.customer.model.attribute;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomerAttribute is a Querydsl query type for CustomerAttribute
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomerAttribute extends EntityPathBase<CustomerAttribute> {

    private static final long serialVersionUID = -266341962L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerAttribute customerAttribute = new QCustomerAttribute("customerAttribute");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final com.salesmanager.core.business.customer.model.QCustomer customer;

    public final QCustomerOption customerOption;

    public final QCustomerOptionValue customerOptionValue;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath textValue = createString("textValue");

    public QCustomerAttribute(String variable) {
        this(CustomerAttribute.class, forVariable(variable), INITS);
    }

    public QCustomerAttribute(Path<? extends CustomerAttribute> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerAttribute(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerAttribute(PathMetadata<?> metadata, PathInits inits) {
        this(CustomerAttribute.class, metadata, inits);
    }

    public QCustomerAttribute(Class<? extends CustomerAttribute> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new com.salesmanager.core.business.customer.model.QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.customerOption = inits.isInitialized("customerOption") ? new QCustomerOption(forProperty("customerOption"), inits.get("customerOption")) : null;
        this.customerOptionValue = inits.isInitialized("customerOptionValue") ? new QCustomerOptionValue(forProperty("customerOptionValue"), inits.get("customerOptionValue")) : null;
    }

}

