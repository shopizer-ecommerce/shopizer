package com.salesmanager.shop.tags;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.utils.FilePathUtils;


public class ProductDescriptionUrlTag extends RequestContextAwareTag {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6319855234657139862L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDescriptionUrlTag.class);



	private ProductDescription productDescription;
	
	@Inject
	private FilePathUtils filePathUtils;

	/**
	 * Created the product url for the store front
	 */
	public int doStartTagInternal() throws JspException {
		try {

			if (filePathUtils==null) {
	            WebApplicationContext wac = getRequestContext().getWebApplicationContext();
	            AutowireCapableBeanFactory factory = wac.getAutowireCapableBeanFactory();
	            factory.autowireBean(this);
	        }

			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
			//*** IF USED FROM ADMIN THE STORE WILL BE NULL, THEN TRY TO USE ADMIN STORE
			if(merchantStore==null) {
				merchantStore = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			}
			
			
			StringBuilder productPath = new StringBuilder();
			
			String baseUrl = filePathUtils.buildStoreUri(merchantStore, request);
			productPath.append(baseUrl);
			
			if(!StringUtils.isBlank(this.getProductDescription().getSeUrl())) {
				productPath.append(Constants.PRODUCT_URI).append("/");
				productPath.append(this.getProductDescription().getSeUrl());
			} else {
				productPath.append(Constants.PRODUCT_ID_URI).append("/");
				productPath.append(this.getProductDescription().getProduct().getSku());
			}
			
			productPath.append(Constants.URL_EXTENSION);
			


			pageContext.getOut().print(productPath.toString());


			
		} catch (Exception ex) {
			LOGGER.error("Error while getting content url", ex);
		}
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}

	public void setProductDescription(ProductDescription productDescription) {
		this.productDescription = productDescription;
	}

	public ProductDescription getProductDescription() {
		return productDescription;
	}


	

}
