package com.salesmanager.core.business.system.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SystemConfiguration.class)
public abstract class SystemConfiguration_ {

	public static volatile SingularAttribute<SystemConfiguration, Long> id;
	public static volatile SingularAttribute<SystemConfiguration, String> value;
	public static volatile SingularAttribute<SystemConfiguration, AuditSection> auditSection;
	public static volatile SingularAttribute<SystemConfiguration, String> key;

}

