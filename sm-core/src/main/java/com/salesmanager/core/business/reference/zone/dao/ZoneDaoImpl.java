package com.salesmanager.core.business.reference.zone.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.zone.model.QZone;
import com.salesmanager.core.business.reference.zone.model.QZoneDescription;
import com.salesmanager.core.business.reference.zone.model.Zone;

@Repository("zoneDao")
public class ZoneDaoImpl extends SalesManagerEntityDaoImpl<Long, Zone> implements ZoneDao {
	
	@Override
	public List<Zone> listByLanguageAndCountry(Country country, Language language) {
		QZone qZone = QZone.zone;
		QZoneDescription qDescription = QZoneDescription.zoneDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qZone)
			.leftJoin(qZone.descriptions, qDescription).fetch()
			.where(qDescription.language.id.eq(language.getId())
					.and(qZone.country.isoCode.eq(country.getIsoCode())))
					.orderBy(qDescription.name.asc());
		

		
		List<Zone> zones = query.list(qZone);
		return zones;
	}
	
	@Override
	public List<Zone> listByLanguage(Language language) {
		QZone qZone = QZone.zone;
		QZoneDescription qDescription = QZoneDescription.zoneDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qZone)
			.leftJoin(qZone.descriptions, qDescription).fetch()
			.where(qDescription.language.id.eq(language.getId()))
					.orderBy(qDescription.name.asc());

		List<Zone> zones = query.list(qZone);
		return zones;
	}

}
