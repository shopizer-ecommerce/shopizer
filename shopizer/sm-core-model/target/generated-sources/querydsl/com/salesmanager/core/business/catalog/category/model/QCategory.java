package com.salesmanager.core.business.catalog.category.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = -23277537L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategory category = new QCategory("category");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final ListPath<Category, QCategory> categories = this.<Category, QCategory>createList("categories", Category.class, QCategory.class, PathInits.DIRECT2);

    public final StringPath categoryImage = createString("categoryImage");

    public final BooleanPath categoryStatus = createBoolean("categoryStatus");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> depth = createNumber("depth", Integer.class);

    public final ListPath<CategoryDescription, QCategoryDescription> descriptions = this.<CategoryDescription, QCategoryDescription>createList("descriptions", CategoryDescription.class, QCategoryDescription.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lineage = createString("lineage");

    public final com.salesmanager.core.business.merchant.model.QMerchantStore merchantStore;

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final QCategory parent;

    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

    public final BooleanPath visible = createBoolean("visible");

    public QCategory(String variable) {
        this(Category.class, forVariable(variable), INITS);
    }

    public QCategory(Path<? extends Category> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCategory(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCategory(PathMetadata<?> metadata, PathInits inits) {
        this(Category.class, metadata, inits);
    }

    public QCategory(Class<? extends Category> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auditSection = inits.isInitialized("auditSection") ? new com.salesmanager.core.business.common.model.audit.QAuditSection(forProperty("auditSection")) : null;
        this.merchantStore = inits.isInitialized("merchantStore") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("merchantStore"), inits.get("merchantStore")) : null;
        this.parent = inits.isInitialized("parent") ? new QCategory(forProperty("parent"), inits.get("parent")) : null;
    }

}

