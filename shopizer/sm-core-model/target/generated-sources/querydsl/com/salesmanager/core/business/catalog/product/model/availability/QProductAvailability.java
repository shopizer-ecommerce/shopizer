package com.salesmanager.core.business.catalog.product.model.availability;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductAvailability is a Querydsl query type for ProductAvailability
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductAvailability extends EntityPathBase<ProductAvailability> {

    private static final long serialVersionUID = 2019902031L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductAvailability productAvailability = new QProductAvailability("productAvailability");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final SetPath<com.salesmanager.core.business.catalog.product.model.price.ProductPrice, com.salesmanager.core.business.catalog.product.model.price.QProductPrice> prices = this.<com.salesmanager.core.business.catalog.product.model.price.ProductPrice, com.salesmanager.core.business.catalog.product.model.price.QProductPrice>createSet("prices", com.salesmanager.core.business.catalog.product.model.price.ProductPrice.class, com.salesmanager.core.business.catalog.product.model.price.QProductPrice.class, PathInits.DIRECT2);

    public final com.salesmanager.core.business.catalog.product.model.QProduct product;

    public final DatePath<java.util.Date> productDateAvailable = createDate("productDateAvailable", java.util.Date.class);

    public final BooleanPath productIsAlwaysFreeShipping = createBoolean("productIsAlwaysFreeShipping");

    public final NumberPath<Integer> productQuantity = createNumber("productQuantity", Integer.class);

    public final NumberPath<Integer> productQuantityOrderMax = createNumber("productQuantityOrderMax", Integer.class);

    public final NumberPath<Integer> productQuantityOrderMin = createNumber("productQuantityOrderMin", Integer.class);

    public final BooleanPath productStatus = createBoolean("productStatus");

    public final StringPath region = createString("region");

    public final StringPath regionVariant = createString("regionVariant");

    public QProductAvailability(String variable) {
        this(ProductAvailability.class, forVariable(variable), INITS);
    }

    public QProductAvailability(Path<? extends ProductAvailability> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductAvailability(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductAvailability(PathMetadata<?> metadata, PathInits inits) {
        this(ProductAvailability.class, metadata, inits);
    }

    public QProductAvailability(Class<? extends ProductAvailability> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.salesmanager.core.business.catalog.product.model.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

