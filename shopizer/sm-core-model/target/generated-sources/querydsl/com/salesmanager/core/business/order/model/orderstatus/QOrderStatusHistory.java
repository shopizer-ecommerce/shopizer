package com.salesmanager.core.business.order.model.orderstatus;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderStatusHistory is a Querydsl query type for OrderStatusHistory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderStatusHistory extends EntityPathBase<OrderStatusHistory> {

    private static final long serialVersionUID = 1594945862L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderStatusHistory orderStatusHistory = new QOrderStatusHistory("orderStatusHistory");

    public final StringPath comments = createString("comments");

    public final NumberPath<Integer> customerNotified = createNumber("customerNotified", Integer.class);

    public final DateTimePath<java.util.Date> dateAdded = createDateTime("dateAdded", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.salesmanager.core.business.order.model.QOrder order;

    public final EnumPath<OrderStatus> status = createEnum("status", OrderStatus.class);

    public QOrderStatusHistory(String variable) {
        this(OrderStatusHistory.class, forVariable(variable), INITS);
    }

    public QOrderStatusHistory(Path<? extends OrderStatusHistory> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderStatusHistory(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderStatusHistory(PathMetadata<?> metadata, PathInits inits) {
        this(OrderStatusHistory.class, metadata, inits);
    }

    public QOrderStatusHistory(Class<? extends OrderStatusHistory> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new com.salesmanager.core.business.order.model.QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

