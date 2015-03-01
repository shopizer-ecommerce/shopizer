package com.salesmanager.core.business.order.model.orderproduct;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderProductDownload.class)
public abstract class OrderProductDownload_ {

	public static volatile SingularAttribute<OrderProductDownload, Long> id;
	public static volatile SingularAttribute<OrderProductDownload, OrderProduct> orderProduct;
	public static volatile SingularAttribute<OrderProductDownload, Integer> downloadCount;
	public static volatile SingularAttribute<OrderProductDownload, String> orderProductFilename;
	public static volatile SingularAttribute<OrderProductDownload, Integer> maxdays;

}

