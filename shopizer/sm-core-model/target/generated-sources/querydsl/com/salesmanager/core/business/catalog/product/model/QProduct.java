package com.salesmanager.core.business.catalog.product.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1574270519L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final SetPath<com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute, com.salesmanager.core.business.catalog.product.model.attribute.QProductAttribute> attributes = this.<com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute, com.salesmanager.core.business.catalog.product.model.attribute.QProductAttribute>createSet("attributes", com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute.class, com.salesmanager.core.business.catalog.product.model.attribute.QProductAttribute.class, PathInits.DIRECT2);

    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final SetPath<com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability, com.salesmanager.core.business.catalog.product.model.availability.QProductAvailability> availabilities = this.<com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability, com.salesmanager.core.business.catalog.product.model.availability.QProductAvailability>createSet("availabilities", com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability.class, com.salesmanager.core.business.catalog.product.model.availability.QProductAvailability.class, PathInits.DIRECT2);

    public final BooleanPath available = createBoolean("available");

    public final SetPath<com.salesmanager.core.business.catalog.category.model.Category, com.salesmanager.core.business.catalog.category.model.QCategory> categories = this.<com.salesmanager.core.business.catalog.category.model.Category, com.salesmanager.core.business.catalog.category.model.QCategory>createSet("categories", com.salesmanager.core.business.catalog.category.model.Category.class, com.salesmanager.core.business.catalog.category.model.QCategory.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> dateAvailable = createDateTime("dateAvailable", java.util.Date.class);

    public final SetPath<com.salesmanager.core.business.catalog.product.model.description.ProductDescription, com.salesmanager.core.business.catalog.product.model.description.QProductDescription> descriptions = this.<com.salesmanager.core.business.catalog.product.model.description.ProductDescription, com.salesmanager.core.business.catalog.product.model.description.QProductDescription>createSet("descriptions", com.salesmanager.core.business.catalog.product.model.description.ProductDescription.class, com.salesmanager.core.business.catalog.product.model.description.QProductDescription.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<com.salesmanager.core.business.catalog.product.model.image.ProductImage, com.salesmanager.core.business.catalog.product.model.image.QProductImage> images = this.<com.salesmanager.core.business.catalog.product.model.image.ProductImage, com.salesmanager.core.business.catalog.product.model.image.QProductImage>createSet("images", com.salesmanager.core.business.catalog.product.model.image.ProductImage.class, com.salesmanager.core.business.catalog.product.model.image.QProductImage.class, PathInits.DIRECT2);

    public final com.salesmanager.core.business.catalog.product.model.manufacturer.QManufacturer manufacturer;

    public final com.salesmanager.core.business.merchant.model.QMerchantStore merchantStore;

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final NumberPath<java.math.BigDecimal> productHeight = createNumber("productHeight", java.math.BigDecimal.class);

    public final BooleanPath productIsFree = createBoolean("productIsFree");

    public final NumberPath<java.math.BigDecimal> productLength = createNumber("productLength", java.math.BigDecimal.class);

    public final NumberPath<Integer> productOrdered = createNumber("productOrdered", Integer.class);

    public final NumberPath<java.math.BigDecimal> productReviewAvg = createNumber("productReviewAvg", java.math.BigDecimal.class);

    public final NumberPath<Integer> productReviewCount = createNumber("productReviewCount", Integer.class);

    public final BooleanPath productShipeable = createBoolean("productShipeable");

    public final BooleanPath productVirtual = createBoolean("productVirtual");

    public final NumberPath<java.math.BigDecimal> productWeight = createNumber("productWeight", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> productWidth = createNumber("productWidth", java.math.BigDecimal.class);

    public final SetPath<com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationship, com.salesmanager.core.business.catalog.product.model.relationship.QProductRelationship> relationships = this.<com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationship, com.salesmanager.core.business.catalog.product.model.relationship.QProductRelationship>createSet("relationships", com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationship.class, com.salesmanager.core.business.catalog.product.model.relationship.QProductRelationship.class, PathInits.DIRECT2);

    public final StringPath sku = createString("sku");

    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

    public final com.salesmanager.core.business.tax.model.taxclass.QTaxClass taxClass;

    public final com.salesmanager.core.business.catalog.product.model.type.QProductType type;

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProduct(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProduct(PathMetadata<?> metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auditSection = inits.isInitialized("auditSection") ? new com.salesmanager.core.business.common.model.audit.QAuditSection(forProperty("auditSection")) : null;
        this.manufacturer = inits.isInitialized("manufacturer") ? new com.salesmanager.core.business.catalog.product.model.manufacturer.QManufacturer(forProperty("manufacturer"), inits.get("manufacturer")) : null;
        this.merchantStore = inits.isInitialized("merchantStore") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("merchantStore"), inits.get("merchantStore")) : null;
        this.taxClass = inits.isInitialized("taxClass") ? new com.salesmanager.core.business.tax.model.taxclass.QTaxClass(forProperty("taxClass"), inits.get("taxClass")) : null;
        this.type = inits.isInitialized("type") ? new com.salesmanager.core.business.catalog.product.model.type.QProductType(forProperty("type"), inits.get("type")) : null;
    }

}

