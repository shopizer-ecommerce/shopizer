package com.salesmanager.core.business.system.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MerchantConfiguration.class)
public abstract class MerchantConfiguration_ {

	public static volatile SingularAttribute<MerchantConfiguration, Long> id;
	public static volatile SingularAttribute<MerchantConfiguration, MerchantStore> merchantStore;
	public static volatile SingularAttribute<MerchantConfiguration, String> value;
	public static volatile SingularAttribute<MerchantConfiguration, MerchantConfigurationType> merchantConfigurationType;
	public static volatile SingularAttribute<MerchantConfiguration, String> key;
	public static volatile SingularAttribute<MerchantConfiguration, AuditSection> auditSection;

}

