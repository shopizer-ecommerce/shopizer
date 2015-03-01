package com.salesmanager.core.business.reference.currency.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCurrency is a Querydsl query type for Currency
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCurrency extends EntityPathBase<Currency> {

    private static final long serialVersionUID = -457284431L;

    public static final QCurrency currency1 = new QCurrency("currency1");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final StringPath code = createString("code");

    public final SimplePath<java.util.Currency> currency = createSimple("currency", java.util.Currency.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final BooleanPath supported = createBoolean("supported");

    public QCurrency(String variable) {
        super(Currency.class, forVariable(variable));
    }

    public QCurrency(Path<? extends Currency> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCurrency(PathMetadata<?> metadata) {
        super(Currency.class, metadata);
    }

}

