package com.salesmanager.core.business.shoppingcart.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QShoppingCartAttributeItem is a Querydsl query type for ShoppingCartAttributeItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QShoppingCartAttributeItem extends EntityPathBase<ShoppingCartAttributeItem> {

    private static final long serialVersionUID = 507839451L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShoppingCartAttributeItem shoppingCartAttributeItem = new QShoppingCartAttributeItem("shoppingCartAttributeItem");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final NumberPath<Long> productAttributeId = createNumber("productAttributeId", Long.class);

    public final QShoppingCartItem shoppingCartItem;

    public QShoppingCartAttributeItem(String variable) {
        this(ShoppingCartAttributeItem.class, forVariable(variable), INITS);
    }

    public QShoppingCartAttributeItem(Path<? extends ShoppingCartAttributeItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShoppingCartAttributeItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QShoppingCartAttributeItem(PathMetadata<?> metadata, PathInits inits) {
        this(ShoppingCartAttributeItem.class, metadata, inits);
    }

    public QShoppingCartAttributeItem(Class<? extends ShoppingCartAttributeItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auditSection = inits.isInitialized("auditSection") ? new com.salesmanager.core.business.common.model.audit.QAuditSection(forProperty("auditSection")) : null;
        this.shoppingCartItem = inits.isInitialized("shoppingCartItem") ? new QShoppingCartItem(forProperty("shoppingCartItem"), inits.get("shoppingCartItem")) : null;
    }

}

