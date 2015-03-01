package com.salesmanager.core.business.catalog.product.model.attribute;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductAttribute is a Querydsl query type for ProductAttribute
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductAttribute extends EntityPathBase<ProductAttribute> {

    private static final long serialVersionUID = 117442871L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductAttribute productAttribute = new QProductAttribute("productAttribute");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final BooleanPath attributeDefault = createBoolean("attributeDefault");

    public final BooleanPath attributeDiscounted = createBoolean("attributeDiscounted");

    public final BooleanPath attributeDisplayOnly = createBoolean("attributeDisplayOnly");

    public final BooleanPath attributeRequired = createBoolean("attributeRequired");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final com.salesmanager.core.business.catalog.product.model.QProduct product;

    public final BooleanPath productAttributeIsFree = createBoolean("productAttributeIsFree");

    public final NumberPath<java.math.BigDecimal> productAttributePrice = createNumber("productAttributePrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> productAttributeWeight = createNumber("productAttributeWeight", java.math.BigDecimal.class);

    public final QProductOption productOption;

    public final NumberPath<Integer> productOptionSortOrder = createNumber("productOptionSortOrder", Integer.class);

    public final QProductOptionValue productOptionValue;

    public QProductAttribute(String variable) {
        this(ProductAttribute.class, forVariable(variable), INITS);
    }

    public QProductAttribute(Path<? extends ProductAttribute> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductAttribute(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductAttribute(PathMetadata<?> metadata, PathInits inits) {
        this(ProductAttribute.class, metadata, inits);
    }

    public QProductAttribute(Class<? extends ProductAttribute> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.salesmanager.core.business.catalog.product.model.QProduct(forProperty("product"), inits.get("product")) : null;
        this.productOption = inits.isInitialized("productOption") ? new QProductOption(forProperty("productOption"), inits.get("productOption")) : null;
        this.productOptionValue = inits.isInitialized("productOptionValue") ? new QProductOptionValue(forProperty("productOptionValue"), inits.get("productOptionValue")) : null;
    }

}

