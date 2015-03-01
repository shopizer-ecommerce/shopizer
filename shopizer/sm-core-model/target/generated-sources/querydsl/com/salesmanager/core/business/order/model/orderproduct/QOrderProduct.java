package com.salesmanager.core.business.order.model.orderproduct;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderProduct is a Querydsl query type for OrderProduct
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderProduct extends EntityPathBase<OrderProduct> {

    private static final long serialVersionUID = 2061280426L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderProduct orderProduct = new QOrderProduct("orderProduct");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final SetPath<OrderProductDownload, QOrderProductDownload> downloads = this.<OrderProductDownload, QOrderProductDownload>createSet("downloads", OrderProductDownload.class, QOrderProductDownload.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final NumberPath<java.math.BigDecimal> oneTimeCharge = createNumber("oneTimeCharge", java.math.BigDecimal.class);

    public final com.salesmanager.core.business.order.model.QOrder order;

    public final SetPath<OrderProductAttribute, QOrderProductAttribute> orderAttributes = this.<OrderProductAttribute, QOrderProductAttribute>createSet("orderAttributes", OrderProductAttribute.class, QOrderProductAttribute.class, PathInits.DIRECT2);

    public final SetPath<OrderProductPrice, QOrderProductPrice> prices = this.<OrderProductPrice, QOrderProductPrice>createSet("prices", OrderProductPrice.class, QOrderProductPrice.class, PathInits.DIRECT2);

    public final StringPath productName = createString("productName");

    public final NumberPath<Integer> productQuantity = createNumber("productQuantity", Integer.class);

    public final StringPath sku = createString("sku");

    public QOrderProduct(String variable) {
        this(OrderProduct.class, forVariable(variable), INITS);
    }

    public QOrderProduct(Path<? extends OrderProduct> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderProduct(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderProduct(PathMetadata<?> metadata, PathInits inits) {
        this(OrderProduct.class, metadata, inits);
    }

    public QOrderProduct(Class<? extends OrderProduct> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new com.salesmanager.core.business.order.model.QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

