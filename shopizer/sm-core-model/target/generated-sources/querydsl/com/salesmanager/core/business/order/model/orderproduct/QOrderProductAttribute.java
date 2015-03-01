package com.salesmanager.core.business.order.model.orderproduct;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderProductAttribute is a Querydsl query type for OrderProductAttribute
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderProductAttribute extends EntityPathBase<OrderProductAttribute> {

    private static final long serialVersionUID = -1172650734L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderProductAttribute orderProductAttribute = new QOrderProductAttribute("orderProductAttribute");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QOrderProduct orderProduct;

    public final BooleanPath productAttributeIsFree = createBoolean("productAttributeIsFree");

    public final StringPath productAttributeName = createString("productAttributeName");

    public final NumberPath<java.math.BigDecimal> productAttributePrice = createNumber("productAttributePrice", java.math.BigDecimal.class);

    public final StringPath productAttributeValueName = createString("productAttributeValueName");

    public final NumberPath<java.math.BigDecimal> productAttributeWeight = createNumber("productAttributeWeight", java.math.BigDecimal.class);

    public final NumberPath<Long> productOptionId = createNumber("productOptionId", Long.class);

    public final NumberPath<Long> productOptionValueId = createNumber("productOptionValueId", Long.class);

    public QOrderProductAttribute(String variable) {
        this(OrderProductAttribute.class, forVariable(variable), INITS);
    }

    public QOrderProductAttribute(Path<? extends OrderProductAttribute> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderProductAttribute(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderProductAttribute(PathMetadata<?> metadata, PathInits inits) {
        this(OrderProductAttribute.class, metadata, inits);
    }

    public QOrderProductAttribute(Class<? extends OrderProductAttribute> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderProduct = inits.isInitialized("orderProduct") ? new QOrderProduct(forProperty("orderProduct"), inits.get("orderProduct")) : null;
    }

}

