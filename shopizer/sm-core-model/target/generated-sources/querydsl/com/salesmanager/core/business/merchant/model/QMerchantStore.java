package com.salesmanager.core.business.merchant.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMerchantStore is a Querydsl query type for MerchantStore
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMerchantStore extends EntityPathBase<MerchantStore> {

    private static final long serialVersionUID = 1651865229L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMerchantStore merchantStore = new QMerchantStore("merchantStore");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final StringPath code = createString("code");

    public final StringPath continueshoppingurl = createString("continueshoppingurl");

    public final com.salesmanager.core.business.reference.country.model.QCountry country;

    public final com.salesmanager.core.business.reference.currency.model.QCurrency currency;

    public final BooleanPath currencyFormatNational = createBoolean("currencyFormatNational");

    public final com.salesmanager.core.business.reference.language.model.QLanguage defaultLanguage;

    public final StringPath domainName = createString("domainName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DatePath<java.util.Date> inBusinessSince = createDate("inBusinessSince", java.util.Date.class);

    public final StringPath invoiceTemplate = createString("invoiceTemplate");

    public final ListPath<com.salesmanager.core.business.reference.language.model.Language, com.salesmanager.core.business.reference.language.model.QLanguage> languages = this.<com.salesmanager.core.business.reference.language.model.Language, com.salesmanager.core.business.reference.language.model.QLanguage>createList("languages", com.salesmanager.core.business.reference.language.model.Language.class, com.salesmanager.core.business.reference.language.model.QLanguage.class, PathInits.DIRECT2);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath seizeunitcode = createString("seizeunitcode");

    public final StringPath storeaddress = createString("storeaddress");

    public final StringPath storecity = createString("storecity");

    public final StringPath storeEmailAddress = createString("storeEmailAddress");

    public final StringPath storeLogo = createString("storeLogo");

    public final StringPath storename = createString("storename");

    public final StringPath storephone = createString("storephone");

    public final StringPath storepostalcode = createString("storepostalcode");

    public final StringPath storestateprovince = createString("storestateprovince");

    public final StringPath storeTemplate = createString("storeTemplate");

    public final BooleanPath useCache = createBoolean("useCache");

    public final StringPath weightunitcode = createString("weightunitcode");

    public final com.salesmanager.core.business.reference.zone.model.QZone zone;

    public QMerchantStore(String variable) {
        this(MerchantStore.class, forVariable(variable), INITS);
    }

    public QMerchantStore(Path<? extends MerchantStore> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMerchantStore(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMerchantStore(PathMetadata<?> metadata, PathInits inits) {
        this(MerchantStore.class, metadata, inits);
    }

    public QMerchantStore(Class<? extends MerchantStore> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new com.salesmanager.core.business.reference.country.model.QCountry(forProperty("country"), inits.get("country")) : null;
        this.currency = inits.isInitialized("currency") ? new com.salesmanager.core.business.reference.currency.model.QCurrency(forProperty("currency")) : null;
        this.defaultLanguage = inits.isInitialized("defaultLanguage") ? new com.salesmanager.core.business.reference.language.model.QLanguage(forProperty("defaultLanguage"), inits.get("defaultLanguage")) : null;
        this.zone = inits.isInitialized("zone") ? new com.salesmanager.core.business.reference.zone.model.QZone(forProperty("zone"), inits.get("zone")) : null;
    }

}

