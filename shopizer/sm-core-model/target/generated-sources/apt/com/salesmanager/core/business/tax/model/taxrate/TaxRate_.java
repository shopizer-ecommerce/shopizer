package com.salesmanager.core.business.tax.model.taxrate;

import com.salesmanager.core.business.common.model.audit.AuditSection;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.core.business.tax.model.taxclass.TaxClass;
import java.math.BigDecimal;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TaxRate.class)
public abstract class TaxRate_ {

	public static volatile SingularAttribute<TaxRate, MerchantStore> merchantStore;
	public static volatile SingularAttribute<TaxRate, BigDecimal> taxRate;
	public static volatile ListAttribute<TaxRate, TaxRate> taxRates;
	public static volatile SingularAttribute<TaxRate, TaxRate> parent;
	public static volatile SingularAttribute<TaxRate, String> code;
	public static volatile ListAttribute<TaxRate, TaxRateDescription> descriptions;
	public static volatile SingularAttribute<TaxRate, Country> country;
	public static volatile SingularAttribute<TaxRate, Long> id;
	public static volatile SingularAttribute<TaxRate, Boolean> piggyback;
	public static volatile SingularAttribute<TaxRate, TaxClass> taxClass;
	public static volatile SingularAttribute<TaxRate, Integer> taxPriority;
	public static volatile SingularAttribute<TaxRate, String> stateProvince;
	public static volatile SingularAttribute<TaxRate, AuditSection> auditSection;
	public static volatile SingularAttribute<TaxRate, Zone> zone;

}

