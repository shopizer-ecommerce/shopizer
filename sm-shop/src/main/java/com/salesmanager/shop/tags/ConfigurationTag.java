package com.salesmanager.shop.tags;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.salesmanager.core.business.utils.CoreConfiguration;


public class ConfigurationTag extends RequestContextAwareTag {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationTag.class);

	
	@Inject
	private CoreConfiguration coreConfiguration;
	
	private String configurationCode;


	public int doStartTagInternal() throws JspException {
		try {
			
			if (coreConfiguration==null) {
	            WebApplicationContext wac = getRequestContext().getWebApplicationContext();
	            AutowireCapableBeanFactory factory = wac.getAutowireCapableBeanFactory();
	            factory.autowireBean(this);
	        }

			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();


			pageContext.getOut().print(coreConfiguration.getProperty(this.getConfigurationCode(), "property " + getConfigurationCode() + " not found"));


			
		} catch (Exception ex) {
			LOGGER.error("Error while getting content url", ex);
		}
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}

	public String getConfigurationCode() {
		return configurationCode;
	}

	public void setConfigurationCode(String configurationCode) {
		this.configurationCode = configurationCode;
	}









	

}
