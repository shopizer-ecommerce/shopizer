package com.salesmanager.core.business.generic.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSalesManagerEntity is a Querydsl query type for SalesManagerEntity
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QSalesManagerEntity extends BeanPath<SalesManagerEntity<? extends Comparable<?>, ?>> {

    private static final long serialVersionUID = 433632735L;

    public static final QSalesManagerEntity salesManagerEntity = new QSalesManagerEntity("salesManagerEntity");

    public final ComparablePath<Comparable<?>> id = createComparable("id", Comparable.class);

    public final BooleanPath new$ = createBoolean("new");

    @SuppressWarnings("all")
    public QSalesManagerEntity(String variable) {
        super((Class)SalesManagerEntity.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QSalesManagerEntity(Path<? extends SalesManagerEntity> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    @SuppressWarnings("all")
    public QSalesManagerEntity(PathMetadata<?> metadata) {
        super((Class)SalesManagerEntity.class, metadata);
    }

}

