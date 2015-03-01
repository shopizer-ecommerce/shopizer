package com.salesmanager.core.business.customer.model.attribute;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomerOption is a Querydsl query type for CustomerOption
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomerOption extends EntityPathBase<CustomerOption> {

    private static final long serialVersionUID = 2093688731L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerOption customerOption = new QCustomerOption("customerOption");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final BooleanPath active = createBoolean("active");

    public final StringPath code = createString("code");

    public final StringPath customerOptionType = createString("customerOptionType");

    public final SetPath<CustomerOptionDescription, QCustomerOptionDescription> descriptions = this.<CustomerOptionDescription, QCustomerOptionDescription>createSet("descriptions", CustomerOptionDescription.class, QCustomerOptionDescription.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.salesmanager.core.business.merchant.model.QMerchantStore merchantStore;

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final BooleanPath publicOption = createBoolean("publicOption");

    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

    public QCustomerOption(String variable) {
        this(CustomerOption.class, forVariable(variable), INITS);
    }

    public QCustomerOption(Path<? extends CustomerOption> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerOption(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerOption(PathMetadata<?> metadata, PathInits inits) {
        this(CustomerOption.class, metadata, inits);
    }

    public QCustomerOption(Class<? extends CustomerOption> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.merchantStore = inits.isInitialized("merchantStore") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("merchantStore"), inits.get("merchantStore")) : null;
    }

}

