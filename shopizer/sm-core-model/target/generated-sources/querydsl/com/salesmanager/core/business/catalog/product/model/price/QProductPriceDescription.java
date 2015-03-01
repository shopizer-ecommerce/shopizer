package com.salesmanager.core.business.catalog.product.model.price;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductPriceDescription is a Querydsl query type for ProductPriceDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductPriceDescription extends EntityPathBase<ProductPriceDescription> {

    private static final long serialVersionUID = -1337388123L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductPriceDescription productPriceDescription = new QProductPriceDescription("productPriceDescription");

    public final com.salesmanager.core.business.common.model.QDescription _super;

    // inherited
    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    //inherited
    public final StringPath description;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final com.salesmanager.core.business.reference.language.model.QLanguage language;

    //inherited
    public final StringPath name;

    public final QProductPrice productPrice;

    //inherited
    public final StringPath title;

    public QProductPriceDescription(String variable) {
        this(ProductPriceDescription.class, forVariable(variable), INITS);
    }

    public QProductPriceDescription(Path<? extends ProductPriceDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductPriceDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductPriceDescription(PathMetadata<?> metadata, PathInits inits) {
        this(ProductPriceDescription.class, metadata, inits);
    }

    public QProductPriceDescription(Class<? extends ProductPriceDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.salesmanager.core.business.common.model.QDescription(type, metadata, inits);
        this.auditSection = _super.auditSection;
        this.description = _super.description;
        this.id = _super.id;
        this.language = _super.language;
        this.name = _super.name;
        this.productPrice = inits.isInitialized("productPrice") ? new QProductPrice(forProperty("productPrice"), inits.get("productPrice")) : null;
        this.title = _super.title;
    }

}

