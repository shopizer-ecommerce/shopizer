package com.salesmanager.core.business.merchant.model;

import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.currency.model.Currency;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.zone.model.Zone;
import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MerchantStore.class)
public abstract class MerchantStore_ {

	public static volatile SingularAttribute<MerchantStore, Boolean> currencyFormatNational;
	public static volatile SingularAttribute<MerchantStore, String> invoiceTemplate;
	public static volatile SingularAttribute<MerchantStore, String> storeaddress;
	public static volatile SingularAttribute<MerchantStore, Language> defaultLanguage;
	public static volatile SingularAttribute<MerchantStore, String> domainName;
	public static volatile SingularAttribute<MerchantStore, String> code;
	public static volatile SingularAttribute<MerchantStore, String> storeLogo;
	public static volatile SingularAttribute<MerchantStore, String> storepostalcode;
	public static volatile SingularAttribute<MerchantStore, Currency> currency;
	public static volatile SingularAttribute<MerchantStore, String> storeTemplate;
	public static volatile SingularAttribute<MerchantStore, Country> country;
	public static volatile SingularAttribute<MerchantStore, String> seizeunitcode;
	public static volatile SingularAttribute<MerchantStore, Integer> id;
	public static volatile ListAttribute<MerchantStore, Language> languages;
	public static volatile SingularAttribute<MerchantStore, String> storestateprovince;
	public static volatile SingularAttribute<MerchantStore, String> storeEmailAddress;
	public static volatile SingularAttribute<MerchantStore, Boolean> useCache;
	public static volatile SingularAttribute<MerchantStore, String> weightunitcode;
	public static volatile SingularAttribute<MerchantStore, String> storename;
	public static volatile SingularAttribute<MerchantStore, String> storecity;
	public static volatile SingularAttribute<MerchantStore, String> storephone;
	public static volatile SingularAttribute<MerchantStore, Zone> zone;
	public static volatile SingularAttribute<MerchantStore, Date> inBusinessSince;
	public static volatile SingularAttribute<MerchantStore, String> continueshoppingurl;

}

