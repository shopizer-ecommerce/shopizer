package com.salesmanager.core.business.repositories.reference.language;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.reference.language.Language;

public interface LanguageRepository extends JpaRepository <Language, Integer> {
	
	Language findByCode(String code) throws ServiceException;
	


}
