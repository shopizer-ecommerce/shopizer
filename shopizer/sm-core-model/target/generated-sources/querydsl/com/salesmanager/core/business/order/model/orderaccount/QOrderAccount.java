package com.salesmanager.core.business.order.model.orderaccount;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderAccount is a Querydsl query type for OrderAccount
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderAccount extends EntityPathBase<OrderAccount> {

    private static final long serialVersionUID = -997287574L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderAccount orderAccount = new QOrderAccount("orderAccount");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final com.salesmanager.core.business.order.model.QOrder order;

    public final NumberPath<Integer> orderAccountBillDay = createNumber("orderAccountBillDay", Integer.class);

    public final DatePath<java.util.Date> orderAccountEndDate = createDate("orderAccountEndDate", java.util.Date.class);

    public final SetPath<OrderAccountProduct, QOrderAccountProduct> orderAccountProducts = this.<OrderAccountProduct, QOrderAccountProduct>createSet("orderAccountProducts", OrderAccountProduct.class, QOrderAccountProduct.class, PathInits.DIRECT2);

    public final DatePath<java.util.Date> orderAccountStartDate = createDate("orderAccountStartDate", java.util.Date.class);

    public QOrderAccount(String variable) {
        this(OrderAccount.class, forVariable(variable), INITS);
    }

    public QOrderAccount(Path<? extends OrderAccount> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderAccount(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderAccount(PathMetadata<?> metadata, PathInits inits) {
        this(OrderAccount.class, metadata, inits);
    }

    public QOrderAccount(Class<? extends OrderAccount> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new com.salesmanager.core.business.order.model.QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

