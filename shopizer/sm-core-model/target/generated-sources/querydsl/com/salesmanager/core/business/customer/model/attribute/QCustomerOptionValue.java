package com.salesmanager.core.business.customer.model.attribute;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomerOptionValue is a Querydsl query type for CustomerOptionValue
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomerOptionValue extends EntityPathBase<CustomerOptionValue> {

    private static final long serialVersionUID = 1685979798L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerOptionValue customerOptionValue = new QCustomerOptionValue("customerOptionValue");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final StringPath code = createString("code");

    public final StringPath customerOptionValueImage = createString("customerOptionValueImage");

    public final SetPath<CustomerOptionValueDescription, QCustomerOptionValueDescription> descriptions = this.<CustomerOptionValueDescription, QCustomerOptionValueDescription>createSet("descriptions", CustomerOptionValueDescription.class, QCustomerOptionValueDescription.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.salesmanager.core.business.merchant.model.QMerchantStore merchantStore;

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

    public QCustomerOptionValue(String variable) {
        this(CustomerOptionValue.class, forVariable(variable), INITS);
    }

    public QCustomerOptionValue(Path<? extends CustomerOptionValue> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerOptionValue(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerOptionValue(PathMetadata<?> metadata, PathInits inits) {
        this(CustomerOptionValue.class, metadata, inits);
    }

    public QCustomerOptionValue(Class<? extends CustomerOptionValue> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.merchantStore = inits.isInitialized("merchantStore") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("merchantStore"), inits.get("merchantStore")) : null;
    }

}

