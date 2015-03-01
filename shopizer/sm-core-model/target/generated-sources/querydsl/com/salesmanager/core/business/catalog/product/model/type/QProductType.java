package com.salesmanager.core.business.catalog.product.model.type;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductType is a Querydsl query type for ProductType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductType extends EntityPathBase<ProductType> {

    private static final long serialVersionUID = 1478405005L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductType productType = new QProductType("productType");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final BooleanPath allowAddToCart = createBoolean("allowAddToCart");

    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public QProductType(String variable) {
        this(ProductType.class, forVariable(variable), INITS);
    }

    public QProductType(Path<? extends ProductType> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductType(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductType(PathMetadata<?> metadata, PathInits inits) {
        this(ProductType.class, metadata, inits);
    }

    public QProductType(Class<? extends ProductType> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auditSection = inits.isInitialized("auditSection") ? new com.salesmanager.core.business.common.model.audit.QAuditSection(forProperty("auditSection")) : null;
    }

}

