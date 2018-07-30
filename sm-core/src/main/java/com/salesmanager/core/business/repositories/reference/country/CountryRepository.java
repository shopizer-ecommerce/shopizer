package com.salesmanager.core.business.repositories.reference.country;

import com.salesmanager.core.model.reference.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("select c from Country c left join fetch c.descriptions cd where c.isoCode=?1")
    Country findByIsoCode(String code);


    @Query("select c from Country c left join fetch c.descriptions cd where cd.language.id=?1")
    List<Country> listByLanguage(Integer id);

}
