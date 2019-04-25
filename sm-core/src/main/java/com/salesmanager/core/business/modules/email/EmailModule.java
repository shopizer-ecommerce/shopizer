package com.salesmanager.core.business.modules.email;

public interface EmailModule {
  
  public void send(final Email email) throws Exception;

  public void setEmailConfig(EmailConfig emailConfig);

}
