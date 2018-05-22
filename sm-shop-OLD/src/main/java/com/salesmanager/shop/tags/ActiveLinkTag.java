package com.salesmanager.shop.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.shop.constants.Constants;


public class ActiveLinkTag extends TagSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ActiveLinkTag.class);

	private final static String ACTIVE = "active";
	
	private String linkCode = null;
	private String activeReturnCode = null;
	private String inactiveReturnCode = null;
		

	public int doStartTag() throws JspException {
		try {



			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();

			String requestLinkCode = (String)request.getAttribute(Constants.LINK_CODE);
			
			if(StringUtils.isBlank(requestLinkCode)) {
				if(!StringUtils.isBlank(inactiveReturnCode)) {
					pageContext.getOut().print(inactiveReturnCode);
				} else {
					pageContext.getOut().print("");
				}
			} else {
				if(requestLinkCode.equalsIgnoreCase(linkCode)) {
					if(!StringUtils.isBlank(activeReturnCode)) {
						pageContext.getOut().print(activeReturnCode);
					} else {
						pageContext.getOut().print(ACTIVE);
					}
				} else {
					if(!StringUtils.isBlank(inactiveReturnCode)) {
						pageContext.getOut().print(inactiveReturnCode);
					} else {
						pageContext.getOut().print("");
					}
				}
			}


			
		} catch (Exception ex) {
			LOGGER.error("Error while creating active link", ex);
		}
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}

	public String getLinkCode() {
		return linkCode;
	}

	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}

	public String getActiveReturnCode() {
		return activeReturnCode;
	}

	public void setActiveReturnCode(String activeReturnCode) {
		this.activeReturnCode = activeReturnCode;
	}








	

}
