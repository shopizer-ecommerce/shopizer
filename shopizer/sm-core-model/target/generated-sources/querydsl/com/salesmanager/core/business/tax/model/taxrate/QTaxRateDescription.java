package com.salesmanager.core.business.tax.model.taxrate;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTaxRateDescription is a Querydsl query type for TaxRateDescription
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaxRateDescription extends EntityPathBase<TaxRateDescription> {

    private static final long serialVersionUID = 1107151451L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaxRateDescription taxRateDescription = new QTaxRateDescription("taxRateDescription");

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

    public final QTaxRate taxRate;

    //inherited
    public final StringPath title;

    public QTaxRateDescription(String variable) {
        this(TaxRateDescription.class, forVariable(variable), INITS);
    }

    public QTaxRateDescription(Path<? extends TaxRateDescription> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaxRateDescription(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaxRateDescription(PathMetadata<?> metadata, PathInits inits) {
        this(TaxRateDescription.class, metadata, inits);
    }

    public QTaxRateDescription(Class<? extends TaxRateDescription> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.salesmanager.core.business.common.model.QDescription(type, metadata, inits);
        this.auditSection = _super.auditSection;
        this.description = _super.description;
        this.id = _super.id;
        this.language = _super.language;
        this.name = _super.name;
        this.taxRate = inits.isInitialized("taxRate") ? new QTaxRate(forProperty("taxRate"), inits.get("taxRate")) : null;
        this.title = _super.title;
    }

}

