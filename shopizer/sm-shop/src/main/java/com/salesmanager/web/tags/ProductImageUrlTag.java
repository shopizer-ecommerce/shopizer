package com.salesmanager.web.tags;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.ImageFilePathUtils;

public class ProductImageUrlTag extends TagSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6319855234657139862L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductImageUrlTag.class);


	private String imageName;
	private String imageType;
	private Product product;


	public int doStartTag() throws JspException {
		try {


			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			HttpSession session = request.getSession();

			StringBuilder imagePath = new StringBuilder();
			
			//TODO domain from merchant, else from global config, else from property (localhost)
			
			// example -> /static/1/PRODUCT/120/product1.jpg
			
			//@SuppressWarnings("unchecked")
			//Map<String,String> configurations = (Map<String, String>)session.getAttribute("STORECONFIGURATION");
			//String scheme = (String)configurations.get("scheme");
			
			//if(StringUtils.isBlank(scheme)) {
			//	scheme = "http";
			//}
			
			@SuppressWarnings("unchecked")
			Map<String,String> configurations = (Map<String, String>)session.getAttribute(Constants.STORE_CONFIGURATION);
			String scheme = Constants.HTTP_SCHEME;
			if(configurations!=null) {
				scheme = (String)configurations.get("scheme");
			}
			

			
			imagePath.append(scheme).append("://")
			.append(merchantStore.getDomainName())
			.append("/")
			.append(request.getContextPath());
			
			imagePath
			
			//.append(scheme).append("://").append(merchantStore.getDomainName())
				//.append(Constants.STATIC_URI)
				.append("/").append(ImageFilePathUtils.buildProductImageFilePath(merchantStore, product, this.getImageName())).toString();

			

			pageContext.getOut().print(imagePath.toString());


			
		} catch (Exception ex) {
			LOGGER.error("Error while getting content url", ex);
		}
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImageType() {
		return imageType;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}




	

}
