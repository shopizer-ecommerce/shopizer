package com.salesmanager.core.business.repositories.reference.country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.reference.country.Country;


public interface CountryRepository extends JpaRepository <Country, Integer> {
	
	@Query("select c from Country c left join fetch c.descriptions cd where c.isoCode=?1")
	Country findByIsoCode(String code);
	

	@Query("select c from Country c left join fetch c.descriptions cd where cd.language.id=?1")
	List<Country> listByLanguage(Integer id);

}
