package com.salesmanager.core.business.catalog.product.model.review;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductReviewDescription is a Querydsl query type for ProductReviewDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductReviewDescription extends EntityPathBase<ProductReviewDescription> {

    private static final long serialVersionUID = 655068083L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductReviewDescription productReviewDescription = new QProductReviewDescription("productReviewDescription");

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

    public final QProductReview productReview;

    //inherited
    public final StringPath title;

    public QProductReviewDescription(String variable) {
        this(ProductReviewDescription.class, forVariable(variable), INITS);
    }

    public QProductReviewDescription(Path<? extends ProductReviewDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductReviewDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductReviewDescription(PathMetadata<?> metadata, PathInits inits) {
        this(ProductReviewDescription.class, metadata, inits);
    }

    public QProductReviewDescription(Class<? extends ProductReviewDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.salesmanager.core.business.common.model.QDescription(type, metadata, inits);
        this.auditSection = _super.auditSection;
        this.description = _super.description;
        this.id = _super.id;
        this.language = _super.language;
        this.name = _super.name;
        this.productReview = inits.isInitialized("productReview") ? new QProductReview(forProperty("productReview"), inits.get("productReview")) : null;
        this.title = _super.title;
    }

}

