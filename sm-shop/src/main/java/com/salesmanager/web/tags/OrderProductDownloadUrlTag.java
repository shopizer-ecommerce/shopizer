package com.salesmanager.web.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.entity.order.ReadableOrderProductDownload;
import com.salesmanager.web.utils.FilePathUtils;

public class OrderProductDownloadUrlTag extends TagSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6319855234657139862L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderProductDownloadUrlTag.class);


	private ReadableOrderProductDownload productDownload;
	
	private Long orderId;




	public int doStartTag() throws JspException {
		try {


			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);

			StringBuilder filePath = new StringBuilder();
			
			filePath.append(FilePathUtils.buildStoreUri(merchantStore,request));
			
			filePath
				.append(FilePathUtils.buildOrderDownloadProductFilePath(merchantStore, this.getProductDownload(), this.getOrderId())).toString();

			

			pageContext.getOut().print(filePath.toString());


			
		} catch (Exception ex) {
			LOGGER.error("Error while getting order product download url", ex);
		}
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}



	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public ReadableOrderProductDownload getProductDownload() {
		return productDownload;
	}

	public void setProductDownload(ReadableOrderProductDownload productDownload) {
		this.productDownload = productDownload;
	}





	

}
