package com.salesmanager.core.business.reference.country.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.country.model.QCountry;
import com.salesmanager.core.business.reference.country.model.QCountryDescription;
import com.salesmanager.core.business.reference.language.model.Language;

@Repository("countryDao")
public class CountryDaoImpl extends SalesManagerEntityDaoImpl<Integer, Country> implements CountryDao {

	
	@Override
	public List<Country> listByLanguage(Language language) {
		QCountry qCountry = QCountry.country;
		QCountryDescription qDescription = QCountryDescription.countryDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCountry)
			.leftJoin(qCountry.descriptions, qDescription).fetch()
			.where(qDescription.language.id.eq(language.getId()))
			.orderBy(qDescription.name.asc());
		

		
		List<Country> countries = query.list(qCountry);
		return countries;
	}
}
