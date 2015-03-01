package com.salesmanager.core.business.catalog.product.model.manufacturer;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QManufacturerDescription is a Querydsl query type for ManufacturerDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QManufacturerDescription extends EntityPathBase<ManufacturerDescription> {

    private static final long serialVersionUID = 1282013126L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QManufacturerDescription manufacturerDescription = new QManufacturerDescription("manufacturerDescription");

    public final com.salesmanager.core.business.common.model.QDescription _super;

    // inherited
    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final DateTimePath<java.util.Date> dateLastClick = createDateTime("dateLastClick", java.util.Date.class);

    //inherited
    public final StringPath description;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final com.salesmanager.core.business.reference.language.model.QLanguage language;

    public final QManufacturer manufacturer;

    //inherited
    public final StringPath name;

    //inherited
    public final StringPath title;

    public final StringPath url = createString("url");

    public final NumberPath<Integer> urlClicked = createNumber("urlClicked", Integer.class);

    public QManufacturerDescription(String variable) {
        this(ManufacturerDescription.class, forVariable(variable), INITS);
    }

    public QManufacturerDescription(Path<? extends ManufacturerDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QManufacturerDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QManufacturerDescription(PathMetadata<?> metadata, PathInits inits) {
        this(ManufacturerDescription.class, metadata, inits);
    }

    public QManufacturerDescription(Class<? extends ManufacturerDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.salesmanager.core.business.common.model.QDescription(type, metadata, inits);
        this.auditSection = _super.auditSection;
        this.description = _super.description;
        this.id = _super.id;
        this.language = _super.language;
        this.manufacturer = inits.isInitialized("manufacturer") ? new QManufacturer(forProperty("manufacturer"), inits.get("manufacturer")) : null;
        this.name = _super.name;
        this.title = _super.title;
    }

}

