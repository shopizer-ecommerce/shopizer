package com.salesmanager.core.business.catalog.product.model.manufacturer;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QManufacturer is a Querydsl query type for Manufacturer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QManufacturer extends EntityPathBase<Manufacturer> {

    private static final long serialVersionUID = -1279425962L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QManufacturer manufacturer = new QManufacturer("manufacturer");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final SetPath<ManufacturerDescription, QManufacturerDescription> descriptions = this.<ManufacturerDescription, QManufacturerDescription>createSet("descriptions", ManufacturerDescription.class, QManufacturerDescription.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final com.salesmanager.core.business.merchant.model.QMerchantStore merchantStore;

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final NumberPath<Integer> order = createNumber("order", Integer.class);

    public QManufacturer(String variable) {
        this(Manufacturer.class, forVariable(variable), INITS);
    }

    public QManufacturer(Path<? extends Manufacturer> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QManufacturer(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QManufacturer(PathMetadata<?> metadata, PathInits inits) {
        this(Manufacturer.class, metadata, inits);
    }

    public QManufacturer(Class<? extends Manufacturer> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auditSection = inits.isInitialized("auditSection") ? new com.salesmanager.core.business.common.model.audit.QAuditSection(forProperty("auditSection")) : null;
        this.merchantStore = inits.isInitialized("merchantStore") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("merchantStore"), inits.get("merchantStore")) : null;
    }

}

