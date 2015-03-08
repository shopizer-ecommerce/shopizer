package com.salesmanager.web.tags;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.web.constants.Constants;

public class ProductDescriptionUrlTag extends TagSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6319855234657139862L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDescriptionUrlTag.class);



	private ProductDescription productDescription;

	/**
	 * Created the product url for the store front
	 */
	public int doStartTag() throws JspException {
		try {

			//http://www.domainname.com:8080/shop/product/product-name.html
			//or
			//http://www.domainname.com:8080/shop/productid/sku.html
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			HttpSession session = request.getSession();

			StringBuilder productPath = new StringBuilder();

			@SuppressWarnings("unchecked")
			Map<String,String> configurations = (Map<String, String>)session.getAttribute(Constants.STORE_CONFIGURATION);
			String scheme = Constants.HTTP_SCHEME;
			if(configurations!=null) {
				scheme = (String)configurations.get("scheme");
			}
			

			
			productPath.append(scheme).append("://")
			.append(merchantStore.getDomainName())
			.append(request.getContextPath());
			
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
