package com.salesmanager.core.business.catalog.product.model.image;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductImageDescription is a Querydsl query type for ProductImageDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductImageDescription extends EntityPathBase<ProductImageDescription> {

    private static final long serialVersionUID = -692511131L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductImageDescription productImageDescription = new QProductImageDescription("productImageDescription");

    public final com.salesmanager.core.business.common.model.QDescription _super;

    public final StringPath altTag = createString("altTag");

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

    public final QProductImage productImage;

    //inherited
    public final StringPath title;

    public QProductImageDescription(String variable) {
        this(ProductImageDescription.class, forVariable(variable), INITS);
    }

    public QProductImageDescription(Path<? extends ProductImageDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductImageDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductImageDescription(PathMetadata<?> metadata, PathInits inits) {
        this(ProductImageDescription.class, metadata, inits);
    }

    public QProductImageDescription(Class<? extends ProductImageDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.salesmanager.core.business.common.model.QDescription(type, metadata, inits);
        this.auditSection = _super.auditSection;
        this.description = _super.description;
        this.id = _super.id;
        this.language = _super.language;
        this.name = _super.name;
        this.productImage = inits.isInitialized("productImage") ? new QProductImage(forProperty("productImage"), inits.get("productImage")) : null;
        this.title = _super.title;
    }

}

