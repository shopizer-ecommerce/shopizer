package com.salesmanager.core.business.catalog.product.model.attribute;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductOptionValue is a Querydsl query type for ProductOptionValue
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductOptionValue extends EntityPathBase<ProductOptionValue> {

    private static final long serialVersionUID = 1136016855L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductOptionValue productOptionValue = new QProductOptionValue("productOptionValue");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final StringPath code = createString("code");

    public final SetPath<ProductOptionValueDescription, QProductOptionValueDescription> descriptions = this.<ProductOptionValueDescription, QProductOptionValueDescription>createSet("descriptions", ProductOptionValueDescription.class, QProductOptionValueDescription.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.salesmanager.core.business.merchant.model.QMerchantStore merchantStore;

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final BooleanPath productOptionDisplayOnly = createBoolean("productOptionDisplayOnly");

    public final StringPath productOptionValueImage = createString("productOptionValueImage");

    public final NumberPath<Integer> productOptionValueSortOrder = createNumber("productOptionValueSortOrder", Integer.class);

    public QProductOptionValue(String variable) {
        this(ProductOptionValue.class, forVariable(variable), INITS);
    }

    public QProductOptionValue(Path<? extends ProductOptionValue> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductOptionValue(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductOptionValue(PathMetadata<?> metadata, PathInits inits) {
        this(ProductOptionValue.class, metadata, inits);
    }

    public QProductOptionValue(Class<? extends ProductOptionValue> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.merchantStore = inits.isInitialized("merchantStore") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("merchantStore"), inits.get("merchantStore")) : null;
    }

}

