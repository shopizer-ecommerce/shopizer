package com.salesmanager.web.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.salesmanager.core.business.catalog.product.model.file.DigitalProduct;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.entity.order.ReadableOrderProductDownload;

public class FilePathUtils {
	
	
	private final static String CUSTOMER_ACCESS_LINK = Constants.SHOP_URI + "/customer/dashboard.html";
	private final static String DOWNLOADS = "/downloads/";
	
	
	/**
	 * Builds a static content content file path that can be used by image servlet
	 * utility for getting the physical image
	 * @param store
	 * @param imageName
	 * @return
	 */
	public static String buildStaticFilePath(MerchantStore store, String imageName) {
		return new StringBuilder().append(Constants.FILES_URI).append(Constants.SLASH).append(store.getCode()).append(Constants.SLASH).append(imageName).toString();
	}
	
	public static String buildAdminDownloadProductFilePath(MerchantStore store, DigitalProduct digitalProduct) {
		return new StringBuilder().append(Constants.ADMIN_URI).append(Constants.FILES_URI).append(DOWNLOADS).append(store.getCode()).append(Constants.SLASH).append(digitalProduct.getProductFileName()).toString();
	}
	
	public static String buildOrderDownloadProductFilePath(MerchantStore store, ReadableOrderProductDownload digitalProduct, Long orderId) {
		return new StringBuilder().append(Constants.SHOP_URI).append(Constants.ORDER_DOWNLOAD_URI).append(Constants.SLASH).append(orderId).append(Constants.SLASH).append(digitalProduct.getId()).append(Constants.URL_EXTENSION).toString();
	}
	
	/**
	 * Builds http://<domain name>/<context path>
	 * @param store
	 * @param request
	 * @return
	 */
	public static String buildStoreUri(MerchantStore store, HttpServletRequest request) {
		StringBuilder resourcePath = new StringBuilder();
		HttpSession session= request.getSession();
		@SuppressWarnings("unchecked")
		Map<String,String> configurations = (Map<String, String>)session.getAttribute(Constants.STORE_CONFIGURATION);
		String scheme = Constants.HTTP_SCHEME;
		if(configurations!=null) {
			scheme = (String)configurations.get("scheme");
		}
		
		String domainName = store.getDomainName();
		if(StringUtils.isBlank(domainName)) {
			domainName = Constants.DEFAULT_DOMAIN_NAME;
		}
		
		resourcePath.append(scheme).append("://")
		.append(domainName)
		.append(request.getContextPath());
		
		return resourcePath.toString();
		
	}
	
	public static String buildStoreUri(MerchantStore store, String contextPath) {
		StringBuilder resourcePath = new StringBuilder();
		String scheme = Constants.HTTP_SCHEME;
		
		String domainName = store.getDomainName();
		if(StringUtils.isBlank(domainName)) {
			domainName = Constants.DEFAULT_DOMAIN_NAME;
		}
		
		resourcePath.append(scheme).append("://")
		.append(domainName)
		.append(contextPath);
		
		return resourcePath.toString();
		
	}
	
	
	/**
	 * Access to the customer section
	 * @param store
	 * @param request
	 * @return
	 */
	public static String buildCustomerUri(MerchantStore store,  String contextPath) {
/*		StringBuilder resourcePath = new StringBuilder();
		//@SuppressWarnings("unchecked")
		//Map<String,String> configurations = (Map<String, String>)session.getAttribute(Constants.STORE_CONFIGURATION);
		String scheme = Constants.HTTP_SCHEME;
		//if(configurations!=null) {
		//	scheme = (String)configurations.get("scheme");
		//}
		
		String domainName = store.getDomainName();
		if(StringUtils.isBlank(domainName)) {
			domainName = Constants.DEFAULT_DOMAIN_NAME;
		}
		
		resourcePath.append(scheme).append("://")
		.append(domainName)
		.append(contextPath)
		.append(CUSTOMER_ACCESS_LINK);
		
		return resourcePath.toString();*/
		
		StringBuilder resourcePath = new StringBuilder();
		String scheme = Constants.HTTP_SCHEME;
		
		String domainName = store.getDomainName();
		if(StringUtils.isBlank(domainName)) {
			domainName = Constants.DEFAULT_DOMAIN_NAME;
		}
		
		resourcePath.append(scheme).append("://")
		.append(domainName)
		.append(contextPath);
		
		return resourcePath.toString();
		
	}
	
	public static String buildAdminUri(MerchantStore store, HttpServletRequest request) {
		StringBuilder resourcePath = new StringBuilder();
		HttpSession session= request.getSession();
		@SuppressWarnings("unchecked")
		Map<String,String> configurations = (Map<String, String>)session.getAttribute(Constants.STORE_CONFIGURATION);
		String scheme = Constants.HTTP_SCHEME;
		if(configurations!=null) {
			scheme = (String)configurations.get("scheme");
		}
		
		String domainName = store.getDomainName();
		if(StringUtils.isBlank(domainName)) {
			domainName = Constants.DEFAULT_DOMAIN_NAME;
		}
		
		resourcePath.append(scheme).append("://")
		.append(domainName)
		.append(request.getContextPath())
		.append(Constants.ADMIN_URI);
		
		return resourcePath.toString();
		
	}
	
	public static String buildCategoryUrl(MerchantStore store, String contextPath, String url) {
		StringBuilder resourcePath = new StringBuilder();
		resourcePath.append(buildStoreUri(store, contextPath))
	
			.append(Constants.SHOP_URI)
			
			.append(Constants.CATEGORY_URI)
			.append(Constants.SLASH)
			.append(url)
			.append(Constants.URL_EXTENSION);

		return resourcePath.toString();
		
	}
	
	public static String buildProductUrl(MerchantStore store, String contextPath, String url) {
		StringBuilder resourcePath = new StringBuilder();
		resourcePath.append(buildStoreUri(store, contextPath))
			.append(Constants.SHOP_URI)
			.append(Constants.PRODUCT_URI)
			.append(Constants.SLASH)
			.append(url)
			.append(Constants.URL_EXTENSION);

		return resourcePath.toString();
		
	}
	

}
