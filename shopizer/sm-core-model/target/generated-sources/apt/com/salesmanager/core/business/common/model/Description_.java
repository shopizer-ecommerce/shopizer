package com.salesmanager.core.business.common.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.reference.language.model.Language;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Description.class)
public abstract class Description_ {

	public static volatile SingularAttribute<Description, Long> id;
	public static volatile SingularAttribute<Description, String> title;
	public static volatile SingularAttribute<Description, String> description;
	public static volatile SingularAttribute<Description, String> name;
	public static volatile SingularAttribute<Description, Language> language;
	public static volatile SingularAttribute<Description, AuditSection> auditSection;

}

