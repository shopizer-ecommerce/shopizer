package com.salesmanager.test.references;

import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.country.CountryDescription;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.test.configuration.ConfigurationTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ConfigurationTest.class})
@Ignore
public class ReferencesTest {
	

	
	@Inject
	LanguageService languageService;
	
	@Inject
	CountryService countryService;
	
	//@Test
	@Ignore
	public void testReferences() throws ServiceException {
		
		Language en = new Language();
		en.setCode("en");
		en.setSortOrder(0);
		
		languageService.save(en);
		
		Language fr = new Language();
		fr.setCode("fr");
		fr.setSortOrder(0);
		
		languageService.save(fr);
		
		
		List<Language> langs = languageService.getLanguages();
		
		System.out.println("Language size " + langs.size());
		
		Country us = new Country();
		us.setIsoCode("US");
		
		CountryDescription us_en = new CountryDescription();
		us_en.setLanguage(en);
		us_en.setCountry(us);
		us_en.setName("United States");
		
		us.getDescriptions().add(us_en);
		
		CountryDescription us_fr = new CountryDescription();
		us_fr.setLanguage(fr);
		us_fr.setCountry(us);
		us_fr.setName("Etats Unis");
		
		us.getDescriptions().add(us_fr);
		
		countryService.save(us);
		
		Country c = countryService.getByCode("US");
		
		System.out.println(c.getId());
		
		
		
		System.out.println("***********Done**************");
		
		
		
	}

}
