package com.salesmanager.core.business.content.dao;

import java.util.List;

import com.salesmanager.core.business.content.model.Content;
import com.salesmanager.core.business.content.model.ContentDescription;
import com.salesmanager.core.business.content.model.ContentType;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

public interface ContentDao extends SalesManagerEntityDao<Long, Content> {

	List<Content> listByType(ContentType contentType, MerchantStore store,
			Language language) throws ServiceException;

	List<Content> listByType(List<ContentType> contentType, MerchantStore store,
			Language language) throws ServiceException;

	Content getByCode(String code, MerchantStore store)
			throws ServiceException;

	Content getByCode(String code, MerchantStore store, Language language)
			throws ServiceException;

	List<Content> listByType(List<ContentType> contentType, MerchantStore store)
			throws ServiceException;

	List<Content> listByType(ContentType contentType, MerchantStore store)
			throws ServiceException;

	/**
	 * List ContentDescription objects. Removes non visible content
	 * @param contentType
	 * @param store
	 * @param language
	 * @return
	 * @throws ServiceException
	 */
	List<ContentDescription> listNameByType(List<ContentType> contentType,
			MerchantStore store, Language language) throws ServiceException;

	Content getByLanguage(Long id, Language language) throws ServiceException;

	ContentDescription getBySeUrl(MerchantStore store, String seUrl);



}
