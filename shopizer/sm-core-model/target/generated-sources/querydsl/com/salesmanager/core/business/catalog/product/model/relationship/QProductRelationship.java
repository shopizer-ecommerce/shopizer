package com.salesmanager.core.business.catalog.product.model.relationship;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductRelationship is a Querydsl query type for ProductRelationship
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductRelationship extends EntityPathBase<ProductRelationship> {

    private static final long serialVersionUID = -1255541879L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductRelationship productRelationship = new QProductRelationship("productRelationship");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final BooleanPath active = createBoolean("active");

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final com.salesmanager.core.business.catalog.product.model.QProduct product;

    public final com.salesmanager.core.business.catalog.product.model.QProduct relatedProduct;

    public final com.salesmanager.core.business.merchant.model.QMerchantStore store;

    public QProductRelationship(String variable) {
        this(ProductRelationship.class, forVariable(variable), INITS);
    }

    public QProductRelationship(Path<? extends ProductRelationship> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductRelationship(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductRelationship(PathMetadata<?> metadata, PathInits inits) {
        this(ProductRelationship.class, metadata, inits);
    }

    public QProductRelationship(Class<? extends ProductRelationship> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.salesmanager.core.business.catalog.product.model.QProduct(forProperty("product"), inits.get("product")) : null;
        this.relatedProduct = inits.isInitialized("relatedProduct") ? new com.salesmanager.core.business.catalog.product.model.QProduct(forProperty("relatedProduct"), inits.get("relatedProduct")) : null;
        this.store = inits.isInitialized("store") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("store"), inits.get("store")) : null;
    }

}

