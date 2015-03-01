package com.salesmanager.core.business.order.model.orderproduct;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderProductDownload is a Querydsl query type for OrderProductDownload
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderProductDownload extends EntityPathBase<OrderProductDownload> {

    private static final long serialVersionUID = -2074114222L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderProductDownload orderProductDownload = new QOrderProductDownload("orderProductDownload");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final NumberPath<Integer> downloadCount = createNumber("downloadCount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> maxdays = createNumber("maxdays", Integer.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final QOrderProduct orderProduct;

    public final StringPath orderProductFilename = createString("orderProductFilename");

    public QOrderProductDownload(String variable) {
        this(OrderProductDownload.class, forVariable(variable), INITS);
    }

    public QOrderProductDownload(Path<? extends OrderProductDownload> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderProductDownload(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderProductDownload(PathMetadata<?> metadata, PathInits inits) {
        this(OrderProductDownload.class, metadata, inits);
    }

    public QOrderProductDownload(Class<? extends OrderProductDownload> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderProduct = inits.isInitialized("orderProduct") ? new QOrderProduct(forProperty("orderProduct"), inits.get("orderProduct")) : null;
    }

}

