package com.salesmanager.core.business.catalog.product.model.manufacturer;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Manufacturer.class)
public abstract class Manufacturer_ {

	public static volatile SingularAttribute<Manufacturer, Long> id;
	public static volatile SingularAttribute<Manufacturer, MerchantStore> merchantStore;
	public static volatile SingularAttribute<Manufacturer, Integer> order;
	public static volatile SingularAttribute<Manufacturer, String> image;
	public static volatile SetAttribute<Manufacturer, ManufacturerDescription> descriptions;
	public static volatile SingularAttribute<Manufacturer, AuditSection> auditSection;

}

