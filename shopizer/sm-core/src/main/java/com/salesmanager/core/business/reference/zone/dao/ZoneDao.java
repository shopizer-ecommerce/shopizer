package com.salesmanager.core.business.reference.zone.dao;

import java.util.List;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.zone.model.Zone;


public interface ZoneDao extends SalesManagerEntityDao<Long,Zone> {

	List<Zone> listByLanguageAndCountry(Country country, Language language);

	List<Zone> listByLanguage(Language language);

}
