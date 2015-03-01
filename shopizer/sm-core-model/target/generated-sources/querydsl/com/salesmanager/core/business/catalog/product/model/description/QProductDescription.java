package com.salesmanager.core.business.catalog.product.model.description;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductDescription is a Querydsl query type for ProductDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductDescription extends EntityPathBase<ProductDescription> {

    private static final long serialVersionUID = 1339100279L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductDescription productDescription = new QProductDescription("productDescription");

    public final com.salesmanager.core.business.common.model.QDescription _super;

    // inherited
    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    //inherited
    public final StringPath description;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final com.salesmanager.core.business.reference.language.model.QLanguage language;

    public final StringPath metatagDescription = createString("metatagDescription");

    public final StringPath metatagKeywords = createString("metatagKeywords");

    public final StringPath metatagTitle = createString("metatagTitle");

    //inherited
    public final StringPath name;

    public final com.salesmanager.core.business.catalog.product.model.QProduct product;

    public final StringPath productExternalDl = createString("productExternalDl");

    public final StringPath productHighlight = createString("productHighlight");

    public final StringPath seUrl = createString("seUrl");

    //inherited
    public final StringPath title;

    public QProductDescription(String variable) {
        this(ProductDescription.class, forVariable(variable), INITS);
    }

    public QProductDescription(Path<? extends ProductDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductDescription(PathMetadata<?> metadata, PathInits inits) {
        this(ProductDescription.class, metadata, inits);
    }

    public QProductDescription(Class<? extends ProductDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.salesmanager.core.business.common.model.QDescription(type, metadata, inits);
        this.auditSection = _super.auditSection;
        this.description = _super.description;
        this.id = _super.id;
        this.language = _super.language;
        this.name = _super.name;
        this.product = inits.isInitialized("product") ? new com.salesmanager.core.business.catalog.product.model.QProduct(forProperty("product"), inits.get("product")) : null;
        this.title = _super.title;
    }

}

