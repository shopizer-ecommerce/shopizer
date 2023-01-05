package com.salesmanager.shop.utils;

import org.springframework.stereotype.Component;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.constants.Constants;

/**
 * To be used when using an external web server for managing images
 * 	<beans:bean id="img" class="com.salesmanager.shop.utils.LocalImageFilePathUtils">
		<beans:property name="basePath" value="/static" />
	</beans:bean>
 * @author c.samson
 *
 */
@Component
public class ImageFilePathUtils extends AbstractimageFilePath{
	
	private String basePath = Constants.STATIC_URI;
	private String contentUrl = null;

	@Override
	public String getBasePath(MerchantStore store) {
		return basePath;
	}

	@Override
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	@Override
	public String getContextPath() {
		return super.getProperties().getProperty(CONTEXT_PATH);
	}

	@Override
	public void setContentUrlPath(String contentUrl) {
		this.contentUrl = contentUrl;
		
	}



	
}
