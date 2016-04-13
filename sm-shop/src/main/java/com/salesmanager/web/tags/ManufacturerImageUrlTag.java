package com.salesmanager.web.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.FilePathUtils;
import com.salesmanager.web.utils.ImageFilePath;

public class ManufacturerImageUrlTag extends RequestContextAwareTag {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6319855234657139862L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerImageUrlTag.class);


	private String imageName;
	private String imageType;
	private Manufacturer manufacturer;
	
	@Autowired
	private FilePathUtils filePathUtils;


	@Autowired
	@Qualifier("img")
	private ImageFilePath imageUtils;

	public int doStartTagInternal() throws JspException {
		try {
			
			if (filePathUtils==null || imageUtils==null) {
	            WebApplicationContext wac = getRequestContext().getWebApplicationContext();
	            AutowireCapableBeanFactory factory = wac.getAutowireCapableBeanFactory();
	            factory.autowireBean(this);
	        }


			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			
			MerchantStore merchantStore = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			StringBuilder imagePath = new StringBuilder();
			
			String baseUrl = filePathUtils.buildStoreUri(merchantStore, request);
			imagePath.append(baseUrl);
			
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

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	

}
