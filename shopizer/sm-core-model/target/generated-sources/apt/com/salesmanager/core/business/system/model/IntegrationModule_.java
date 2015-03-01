package com.salesmanager.core.business.system.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(IntegrationModule.class)
public abstract class IntegrationModule_ {

	public static volatile SingularAttribute<IntegrationModule, Long> id;
	public static volatile SingularAttribute<IntegrationModule, Boolean> customModule;
	public static volatile SingularAttribute<IntegrationModule, String> module;
	public static volatile SingularAttribute<IntegrationModule, String> configDetails;
	public static volatile SingularAttribute<IntegrationModule, String> image;
	public static volatile SingularAttribute<IntegrationModule, String> code;
	public static volatile SingularAttribute<IntegrationModule, String> type;
	public static volatile SingularAttribute<IntegrationModule, String> configuration;
	public static volatile SingularAttribute<IntegrationModule, String> regions;
	public static volatile SingularAttribute<IntegrationModule, AuditSection> auditSection;

}

