package com.salesmanager.core.business.order.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderTotal is a Querydsl query type for OrderTotal
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderTotal extends EntityPathBase<OrderTotal> {

    private static final long serialVersionUID = 2057940058L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderTotal orderTotal = new QOrderTotal("orderTotal");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath module = createString("module");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final QOrder order;

    public final StringPath orderTotalCode = createString("orderTotalCode");

    public final EnumPath<OrderTotalType> orderTotalType = createEnum("orderTotalType", OrderTotalType.class);

    public final EnumPath<OrderValueType> orderValueType = createEnum("orderValueType", OrderValueType.class);

    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

    public final StringPath text = createString("text");

    public final StringPath title = createString("title");

    public final NumberPath<java.math.BigDecimal> value = createNumber("value", java.math.BigDecimal.class);

    public QOrderTotal(String variable) {
        this(OrderTotal.class, forVariable(variable), INITS);
    }

    public QOrderTotal(Path<? extends OrderTotal> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderTotal(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderTotal(PathMetadata<?> metadata, PathInits inits) {
        this(OrderTotal.class, metadata, inits);
    }

    public QOrderTotal(Class<? extends OrderTotal> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

