package com.salesmanager.core.business.reference.language.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Language.class)
public abstract class Language_ {

	public static volatile SingularAttribute<Language, Integer> id;
	public static volatile ListAttribute<Language, MerchantStore> storesDefaultLanguage;
	public static volatile SingularAttribute<Language, Integer> sortOrder;
	public static volatile SingularAttribute<Language, String> code;
	public static volatile ListAttribute<Language, MerchantStore> stores;
	public static volatile SingularAttribute<Language, AuditSection> auditSection;

}

