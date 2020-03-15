package com.salesmanager.core.business.modules.email;


public interface HtmlEmailSender {
	
	public void send(final Email email) throws Exception;

	public void setEmailConfig(EmailConfig emailConfig);

}
