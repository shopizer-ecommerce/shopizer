package com.salesmanager.core.business.shoppingcart.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QShoppingCartItem is a Querydsl query type for ShoppingCartItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QShoppingCartItem extends EntityPathBase<ShoppingCartItem> {

    private static final long serialVersionUID = -1924733561L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShoppingCartItem shoppingCartItem = new QShoppingCartItem("shoppingCartItem");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final SetPath<ShoppingCartAttributeItem, QShoppingCartAttributeItem> attributes = this.<ShoppingCartAttributeItem, QShoppingCartAttributeItem>createSet("attributes", ShoppingCartAttributeItem.class, QShoppingCartAttributeItem.class, PathInits.DIRECT2);

    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final QShoppingCart shoppingCart;

    public QShoppingCartItem(String variable) {
        this(ShoppingCartItem.class, forVariable(variable), INITS);
    }

    public QShoppingCartItem(Path<? extends ShoppingCartItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShoppingCartItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShoppingCartItem(PathMetadata<?> metadata, PathInits inits) {
        this(ShoppingCartItem.class, metadata, inits);
    }

    public QShoppingCartItem(Class<? extends ShoppingCartItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auditSection = inits.isInitialized("auditSection") ? new com.salesmanager.core.business.common.model.audit.QAuditSection(forProperty("auditSection")) : null;
        this.shoppingCart = inits.isInitialized("shoppingCart") ? new QShoppingCart(forProperty("shoppingCart"), inits.get("shoppingCart")) : null;
    }

}

