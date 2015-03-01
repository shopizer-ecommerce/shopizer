package com.salesmanager.core.business.user.model;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, Date> loginTime;
	public static volatile SingularAttribute<User, MerchantStore> merchantStore;
	public static volatile SingularAttribute<User, String> adminName;
	public static volatile SingularAttribute<User, Language> defaultLanguage;
	public static volatile SingularAttribute<User, String> answer1;
	public static volatile SingularAttribute<User, String> question1;
	public static volatile SingularAttribute<User, String> answer3;
	public static volatile SingularAttribute<User, String> question2;
	public static volatile SingularAttribute<User, String> answer2;
	public static volatile SingularAttribute<User, String> adminEmail;
	public static volatile SingularAttribute<User, String> question3;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> adminPassword;
	public static volatile SingularAttribute<User, Boolean> active;
	public static volatile SingularAttribute<User, Date> lastAccess;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, AuditSection> auditSection;
	public static volatile ListAttribute<User, Group> groups;

}

