package com.salesmanager.core.business.system.service;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.modules.email.Email;
import com.salesmanager.core.modules.email.EmailConfig;


public interface EmailService {

	public void sendHtmlEmail(MerchantStore store, Email email) throws ServiceException, Exception;
	
	public EmailConfig getEmailConfiguration(MerchantStore store) throws ServiceException;
	
	public void saveEmailConfiguration(EmailConfig emailConfig, MerchantStore store) throws ServiceException;
	
}
