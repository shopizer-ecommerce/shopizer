package com.salesmanager.core.business.order.model.filehistory;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFileHistory is a Querydsl query type for FileHistory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFileHistory extends EntityPathBase<FileHistory> {

    private static final long serialVersionUID = 1489594590L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFileHistory fileHistory = new QFileHistory("fileHistory");

    public final DateTimePath<java.util.Date> accountedDate = createDateTime("accountedDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateAdded = createDateTime("dateAdded", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateDeleted = createDateTime("dateDeleted", java.util.Date.class);

    public final NumberPath<Integer> downloadCount = createNumber("downloadCount", Integer.class);

    public final NumberPath<Long> fileId = createNumber("fileId", Long.class);

    public final NumberPath<Integer> filesize = createNumber("filesize", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.salesmanager.core.business.merchant.model.QMerchantStore store;

    public QFileHistory(String variable) {
        this(FileHistory.class, forVariable(variable), INITS);
    }

    public QFileHistory(Path<? extends FileHistory> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFileHistory(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFileHistory(PathMetadata<?> metadata, PathInits inits) {
        this(FileHistory.class, metadata, inits);
    }

    public QFileHistory(Class<? extends FileHistory> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("store"), inits.get("store")) : null;
    }

}

