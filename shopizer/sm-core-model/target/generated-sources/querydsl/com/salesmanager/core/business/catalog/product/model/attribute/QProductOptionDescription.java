package com.salesmanager.core.business.catalog.product.model.attribute;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductOptionDescription is a Querydsl query type for ProductOptionDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductOptionDescription extends EntityPathBase<ProductOptionDescription> {

    private static final long serialVersionUID = -284570654L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductOptionDescription productOptionDescription = new QProductOptionDescription("productOptionDescription");

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

    public final QProductOption productOption;

    public final StringPath productOptionComment = createString("productOptionComment");

    //inherited
    public final StringPath title;

    public QProductOptionDescription(String variable) {
        this(ProductOptionDescription.class, forVariable(variable), INITS);
    }

    public QProductOptionDescription(Path<? extends ProductOptionDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductOptionDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductOptionDescription(PathMetadata<?> metadata, PathInits inits) {
        this(ProductOptionDescription.class, metadata, inits);
    }

    public QProductOptionDescription(Class<? extends ProductOptionDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.salesmanager.core.business.common.model.QDescription(type, metadata, inits);
        this.auditSection = _super.auditSection;
        this.description = _super.description;
        this.id = _super.id;
        this.language = _super.language;
        this.name = _super.name;
        this.productOption = inits.isInitialized("productOption") ? new QProductOption(forProperty("productOption"), inits.get("productOption")) : null;
        this.title = _super.title;
    }

}

