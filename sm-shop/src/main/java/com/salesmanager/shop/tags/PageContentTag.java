package com.salesmanager.shop.tags;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.content.Content;
import com.salesmanager.core.model.content.ContentDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;


public class PageContentTag extends RequestContextAwareTag  {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PageContentTag.class);


	@Inject
	private ContentService contentService;
	
	private String contentCode;
	
	
	

	public String getContentCode() {
		return contentCode;
	}


	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
	}


	@Override
	protected int doStartTagInternal() throws Exception {
		if (contentService == null || contentService==null) {
			LOGGER.debug("Autowiring contentService");
            WebApplicationContext wac = getRequestContext().getWebApplicationContext();
            AutowireCapableBeanFactory factory = wac.getAutowireCapableBeanFactory();
            factory.autowireBean(this);
        }
		
		HttpServletRequest request = (HttpServletRequest) pageContext
		.getRequest();
		
		Language language = (Language)request.getAttribute(Constants.LANGUAGE);

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);

		Content content = contentService.getByCode(contentCode, store, language);
		
		String pageContent = "";
		if(content!=null) {
			ContentDescription description = content.getDescription();
			if(description != null) {
				pageContent = description.getDescription();
			}
		}
		
		
		pageContext.getOut().print(pageContent);
		
		return SKIP_BODY;

	}


	public int doEndTag() {
		return EVAL_PAGE;
	}


	

}
