package com.salesmanager.core.business.order.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -1579690742L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final com.salesmanager.core.business.generic.model.QSalesManagerEntity _super = new com.salesmanager.core.business.generic.model.QSalesManagerEntity(this);

    public final com.salesmanager.core.business.common.model.QBilling billing;

    public final EnumPath<OrderChannel> channel = createEnum("channel", OrderChannel.class);

    public final com.salesmanager.core.business.order.model.payment.QCreditCard creditCard;

    public final com.salesmanager.core.business.reference.currency.model.QCurrency currency;

    public final NumberPath<java.math.BigDecimal> currencyValue = createNumber("currencyValue", java.math.BigDecimal.class);

    public final StringPath customerEmailAddress = createString("customerEmailAddress");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final DatePath<java.util.Date> datePurchased = createDate("datePurchased", java.util.Date.class);

    public final com.salesmanager.core.business.common.model.QDelivery delivery;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ipAddress = createString("ipAddress");

    public final DateTimePath<java.util.Date> lastModified = createDateTime("lastModified", java.util.Date.class);

    public final SimplePath<java.util.Locale> locale = createSimple("locale", java.util.Locale.class);

    public final com.salesmanager.core.business.merchant.model.QMerchantStore merchant;

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final DateTimePath<java.util.Date> orderDateFinished = createDateTime("orderDateFinished", java.util.Date.class);

    public final SetPath<com.salesmanager.core.business.order.model.orderstatus.OrderStatusHistory, com.salesmanager.core.business.order.model.orderstatus.QOrderStatusHistory> orderHistory = this.<com.salesmanager.core.business.order.model.orderstatus.OrderStatusHistory, com.salesmanager.core.business.order.model.orderstatus.QOrderStatusHistory>createSet("orderHistory", com.salesmanager.core.business.order.model.orderstatus.OrderStatusHistory.class, com.salesmanager.core.business.order.model.orderstatus.QOrderStatusHistory.class, PathInits.DIRECT2);

    public final SetPath<com.salesmanager.core.business.order.model.orderproduct.OrderProduct, com.salesmanager.core.business.order.model.orderproduct.QOrderProduct> orderProducts = this.<com.salesmanager.core.business.order.model.orderproduct.OrderProduct, com.salesmanager.core.business.order.model.orderproduct.QOrderProduct>createSet("orderProducts", com.salesmanager.core.business.order.model.orderproduct.OrderProduct.class, com.salesmanager.core.business.order.model.orderproduct.QOrderProduct.class, PathInits.DIRECT2);

    public final SetPath<OrderTotal, QOrderTotal> orderTotal = this.<OrderTotal, QOrderTotal>createSet("orderTotal", OrderTotal.class, QOrderTotal.class, PathInits.DIRECT2);

    public final EnumPath<OrderType> orderType = createEnum("orderType", OrderType.class);

    public final StringPath paymentModuleCode = createString("paymentModuleCode");

    public final EnumPath<com.salesmanager.core.business.payments.model.PaymentType> paymentType = createEnum("paymentType", com.salesmanager.core.business.payments.model.PaymentType.class);

    public final StringPath shippingModuleCode = createString("shippingModuleCode");

    public final EnumPath<com.salesmanager.core.business.order.model.orderstatus.OrderStatus> status = createEnum("status", com.salesmanager.core.business.order.model.orderstatus.OrderStatus.class);

    public final NumberPath<java.math.BigDecimal> total = createNumber("total", java.math.BigDecimal.class);

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrder(PathMetadata<?> metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.billing = inits.isInitialized("billing") ? new com.salesmanager.core.business.common.model.QBilling(forProperty("billing"), inits.get("billing")) : null;
        this.creditCard = inits.isInitialized("creditCard") ? new com.salesmanager.core.business.order.model.payment.QCreditCard(forProperty("creditCard")) : null;
        this.currency = inits.isInitialized("currency") ? new com.salesmanager.core.business.reference.currency.model.QCurrency(forProperty("currency")) : null;
        this.delivery = inits.isInitialized("delivery") ? new com.salesmanager.core.business.common.model.QDelivery(forProperty("delivery"), inits.get("delivery")) : null;
        this.merchant = inits.isInitialized("merchant") ? new com.salesmanager.core.business.merchant.model.QMerchantStore(forProperty("merchant"), inits.get("merchant")) : null;
    }

}

