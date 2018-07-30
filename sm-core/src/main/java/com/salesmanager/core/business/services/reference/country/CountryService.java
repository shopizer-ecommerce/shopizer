package com.salesmanager.core.business.services.reference.country;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.country.CountryDescription;
import com.salesmanager.core.model.reference.language.Language;

import java.util.List;
import java.util.Map;

public interface CountryService extends SalesManagerEntityService<Integer, Country> {

    Country getByCode(String code) throws ServiceException;

    void addCountryDescription(Country country, CountryDescription description) throws ServiceException;

    List<Country> getCountries(Language language) throws ServiceException;

    Map<String, Country> getCountriesMap(Language language)
            throws ServiceException;

    List<Country> getCountries(List<String> isoCodes, Language language)
            throws ServiceException;
}
