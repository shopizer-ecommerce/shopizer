package com.salesmanager.core.business.services.tax;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.tax.taxclass.TaxClass;
import com.salesmanager.core.model.tax.taxrate.TaxRate;

public interface TaxRateService extends SalesManagerEntityService<Long, TaxRate> {

	public List<TaxRate> listByStore(MerchantStore store) throws ServiceException;

	List<TaxRate> listByCountryZoneAndTaxClass(Country country, Zone zone,
			TaxClass taxClass, MerchantStore store, Language language)
			throws ServiceException;

	List<TaxRate> listByCountryStateProvinceAndTaxClass(Country country,
			String stateProvince, TaxClass taxClass, MerchantStore store,
			Language language) throws ServiceException;

	 TaxRate getByCode(String code, MerchantStore store)
			throws ServiceException;
	 
	 TaxRate getById(Long id, MerchantStore store)
				throws ServiceException;

	List<TaxRate> listByStore(MerchantStore store, Language language)
			throws ServiceException;

	TaxRate saveOrUpdate(TaxRate taxRate) throws ServiceException;
	
	

}
