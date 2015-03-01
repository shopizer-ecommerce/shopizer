package com.salesmanager.core.business.catalog.product.model.attribute;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductOptionValueDescription is a Querydsl query type for ProductOptionValueDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductOptionValueDescription extends EntityPathBase<ProductOptionValueDescription> {

    private static final long serialVersionUID = 901951909L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductOptionValueDescription productOptionValueDescription = new QProductOptionValueDescription("productOptionValueDescription");

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

    public final QProductOptionValue productOptionValue;

    //inherited
    public final StringPath title;

    public QProductOptionValueDescription(String variable) {
        this(ProductOptionValueDescription.class, forVariable(variable), INITS);
    }

    public QProductOptionValueDescription(Path<? extends ProductOptionValueDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductOptionValueDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductOptionValueDescription(PathMetadata<?> metadata, PathInits inits) {
        this(ProductOptionValueDescription.class, metadata, inits);
    }

    public QProductOptionValueDescription(Class<? extends ProductOptionValueDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.salesmanager.core.business.common.model.QDescription(type, metadata, inits);
        this.auditSection = _super.auditSection;
        this.description = _super.description;
        this.id = _super.id;
        this.language = _super.language;
        this.name = _super.name;
        this.productOptionValue = inits.isInitialized("productOptionValue") ? new QProductOptionValue(forProperty("productOptionValue"), inits.get("productOptionValue")) : null;
        this.title = _super.title;
    }

}

