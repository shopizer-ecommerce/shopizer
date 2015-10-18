package com.salesmanager.core.business.tax.dao.taxrate;

import java.util.List;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.zone.model.Zone;
import com.salesmanager.core.business.tax.model.taxclass.TaxClass;
import com.salesmanager.core.business.tax.model.taxrate.TaxRate;

public interface TaxRateDao  extends SalesManagerEntityDao<Long, TaxRate> {

	List<TaxRate> listByStore(MerchantStore store);

	List<TaxRate> listByCountryZoneAndTaxClass(Country country, Zone zone,
			TaxClass taxClass, MerchantStore store, Language language);

	List<TaxRate> listByCountryStateProvinceAndTaxClass(Country country,
			String stateProvince, TaxClass taxClass, MerchantStore store,
			Language language);

	TaxRate getByCode(String code, MerchantStore store);

	List<TaxRate> listByStore(MerchantStore store, Language language);

}
