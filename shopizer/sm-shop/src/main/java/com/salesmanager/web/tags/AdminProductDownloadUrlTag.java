package com.salesmanager.web.tags;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.catalog.product.model.file.DigitalProduct;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.FilePathUtils;

public class AdminProductDownloadUrlTag extends TagSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6319855234657139862L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminProductDownloadUrlTag.class);

	private DigitalProduct digitalProduct;




	public DigitalProduct getDigitalProduct() {
		return digitalProduct;
	}

	public void setDigitalProduct(DigitalProduct digitalProduct) {
		this.digitalProduct = digitalProduct;
	}

	public int doStartTag() throws JspException {
		try {


			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			HttpSession session = request.getSession();

			StringBuilder filePath = new StringBuilder();
			
			//TODO domain from merchant, else from global config, else from property (localhost)
			
			// example -> "/files/{storeCode}/{fileName}.{extension}"
			

			@SuppressWarnings("unchecked")
			Map<String,String> configurations = (Map<String, String>)session.getAttribute(Constants.STORE_CONFIGURATION);
			String scheme = Constants.HTTP_SCHEME;
			if(configurations!=null) {
				scheme = (String)configurations.get("scheme");
			}
			

			
			filePath.append(scheme).append("://")
			.append(merchantStore.getDomainName())
			//.append("/")
			.append(request.getContextPath());
			
			filePath
				.append(FilePathUtils.buildAdminDownloadProductFilePath(merchantStore, digitalProduct)).toString();

			

			pageContext.getOut().print(filePath.toString());


			
		} catch (Exception ex) {
			LOGGER.error("Error while getting content url", ex);
		}
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}





	

}
