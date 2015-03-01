package com.salesmanager.core.business.tax.model.taxrate;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTaxRate is a Querydsl query type for TaxRate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaxRate extends EntityPathBase<TaxRate> {

    private static final long serialVersionUID = -927761695L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaxRate taxRate1 = new QTaxRate("taxRate1");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final com.salesmanager.core.business.common.model.audit.QAuditSection auditSection;

    public final StringPath code = createString("code");

    public final com.salesmanager.core.business.reference.country.model.QCountry country;

    public final ListPath<TaxRateDescription, QTaxRateDescription> descriptions = this.<TaxRateDescription, QTaxRateDescription>createList("descriptions", TaxRateDescription.class, QTaxRateDescription.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.salesmanager.core.business.merchant.model.QMerchantStore merchantStore;

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final QTaxRate parent;

    public final BooleanPath piggyback = createBoolean("piggyback");

    public final StringPath stateProvince = createString("stateProvince");

    public final com.salesmanager.core.business.tax.model.taxclass.QTaxClass taxClass;

    public final NumberPath<Integer> taxPriority = createNumber("taxPriority", Integer.class);

    public final NumberPath<java.math.BigDecimal> taxRate = createNumber("taxRate", java.math.BigDecimal.class);

    public final ListPath<TaxRate, QTaxRate> taxRates = this.<TaxRate, QTaxRate>createList("taxRates", TaxRate.class, QTaxRate.class, PathInits.DIRECT2);

    public final com.salesmanager.core.business.reference.zone.model.QZone zone;

    public QTaxRate(String variable) {
        this(TaxRate.class, forVariable(variable), INITS);
    }

    public QTaxRate(Path<? extends TaxRate> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaxRate(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaxRate(PathMetadata<?> metadata, PathInits inits) {
        this(TaxRate.class, metadata, inits);
    }

    public QTaxRate(Class<? extends TaxRate> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auditSection = inits.isInitialized("auditSection") ? new com.salesmanager.core.business.common.model.audit.QAuditSection(forProperty("auditSection")) : null;
        this.country = inits.isInitialized("country") ? new com.salesmanager.core.business.reference.country.model.QCountry(forProperty("country"), inits.get("country")) : null;
        this.merchantStore = inits.isInitialized("merchantStore") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("merchantStore"), inits.get("merchantStore")) : null;
        this.parent = inits.isInitialized("parent") ? new QTaxRate(forProperty("parent"), inits.get("parent")) : null;
        this.taxClass = inits.isInitialized("taxClass") ? new com.salesmanager.core.business.tax.model.taxclass.QTaxClass(forProperty("taxClass"), inits.get("taxClass")) : null;
        this.zone = inits.isInitialized("zone") ? new com.salesmanager.core.business.reference.zone.model.QZone(forProperty("zone"), inits.get("zone")) : null;
    }

}

