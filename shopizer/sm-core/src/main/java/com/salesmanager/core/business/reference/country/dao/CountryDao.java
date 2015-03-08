package com.salesmanager.core.business.reference.country.dao;

import java.util.List;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.language.model.Language;

public interface CountryDao extends SalesManagerEntityDao<Integer,Country> {

	public List<Country> listByLanguage(Language language);
}
