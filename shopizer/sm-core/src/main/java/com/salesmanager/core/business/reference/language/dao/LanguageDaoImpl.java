package com.salesmanager.core.business.reference.language.dao;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.reference.language.model.Language;

@Repository("languageDao")
public class LanguageDaoImpl extends SalesManagerEntityDaoImpl<Integer, Language> implements LanguageDao {
}
