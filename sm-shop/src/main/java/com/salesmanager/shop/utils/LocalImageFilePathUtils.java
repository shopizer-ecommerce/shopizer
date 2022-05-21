package com.salesmanager.shop.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.catalog.manufacturer.Manufacturer;




@Component
public class LocalImageFilePathUtils extends AbstractimageFilePath{
	
	private String basePath = Constants.STATIC_URI;
	
	private static final String SCHEME = "http://";
	private String contentUrl = null;

	
	@Autowired
	private ServerConfig serverConfig;

	@Override
	public String getBasePath(MerchantStore store) {
		if(StringUtils.isBlank(contentUrl)) {
			String host = new StringBuilder().append(SCHEME).append(serverConfig.getApplicationHost()).toString();
			return new StringBuilder().append(this.getScheme(store, host)).append(basePath).toString();
		} else {
			return new StringBuilder().append(contentUrl).append(basePath).toString();
		}
	}

	@Override
	public void setBasePath(String context) {
		this.basePath = context;
	}
	
	/**
	 * Builds a static content image file path that can be used by image servlet
	 * utility for getting the physical image
	 * @param store
	 * @param imageName
	 * @return
	 */
	public String buildStaticimageUtils(MerchantStore store, String imageName) {
		StringBuilder imgName = new StringBuilder().append(getBasePath(store)).append(Constants.FILES_URI).append(Constants.SLASH).append(store.getCode()).append("/").append(FileContentType.IMAGE.name()).append("/");
				if(!StringUtils.isBlank(imageName)) {
					imgName.append(imageName);
				}
		return imgName.toString();
				
	}
	
	/**
	 * Builds a static content image file path that can be used by image servlet
	 * utility for getting the physical image by specifying the image type
	 * @param store
	 * @param imageName
	 * @return
	 */
	public String buildStaticimageUtils(MerchantStore store, String type, String imageName) {
		StringBuilder imgName = new StringBuilder().append(getBasePath(store)).append(Constants.FILES_URI).append(Constants.SLASH).append(store.getCode()).append("/").append(type).append("/");
		if(!StringUtils.isBlank(imageName)) {
				imgName.append(imageName);
		}
		return imgName.toString();

	}
	
	/**
	 * Builds a manufacturer image file path that can be used by image servlet
	 * utility for getting the physical image
	 * @param store
	 * @param manufacturer
	 * @param imageName
	 * @return
	 */
	public String buildManufacturerimageUtils(MerchantStore store, Manufacturer manufacturer, String imageName) {
		return new StringBuilder().append(getBasePath(store)).append("/").append(store.getCode()).append("/").
				append(FileContentType.MANUFACTURER.name()).append("/")
				.append(manufacturer.getId()).append("/")
				.append(imageName).toString();
	}
	
	/**
	 * Builds a product image file path that can be used by image servlet
	 * utility for getting the physical image
	 * @param store
	 * @param product
	 * @param imageName
	 * @return
	 */
	public String buildProductimageUtils(MerchantStore store, Product product, String imageName) {
		return new StringBuilder().append(getBasePath(store)).append("/products/").append(store.getCode()).append("/")
				.append(product.getSku()).append("/").append("LARGE").append("/").append(imageName).toString();
	}
	
	/**
	 * Builds a default product image file path that can be used by image servlet
	 * utility for getting the physical image
	 * @param store
	 * @param sku
	 * @param imageName
	 * @return
	 */
	public String buildProductimageUtils(MerchantStore store, String sku, String imageName) {
		return new StringBuilder().append(getBasePath(store)).append("/products/").append(store.getCode()).append("/")
				.append(sku).append("/").append("LARGE").append("/").append(imageName).toString();
	}
	
	/**
	 * Builds a large product image file path that can be used by the image servlet
	 * @param store
	 * @param sku
	 * @param imageName
	 * @return
	 */
	public String buildLargeProductimageUtils(MerchantStore store, String sku, String imageName) {
		return new StringBuilder().append(getBasePath(store)).append("/products/").append(store.getCode()).append("/")
				.append(sku).append("/").append("LARGE").append("/").append(imageName).toString();
	}


	
	/**
	 * Builds a merchant store logo path
	 * @param store
	 * @return
	 */
	public String buildStoreLogoFilePath(MerchantStore store) {
		return new StringBuilder().append(getBasePath(store)).append(Constants.FILES_URI).append(Constants.SLASH).append(store.getCode()).append("/").append(FileContentType.LOGO).append("/")
				.append(store.getStoreLogo()).toString();
	}
	
	/**
	 * Builds product property image url path
	 * @param store
	 * @param imageName
	 * @return
	 */
	public String buildProductPropertyimageUtils(MerchantStore store, String imageName) {
		return new StringBuilder().append(getBasePath(store)).append(Constants.FILES_URI).append(Constants.SLASH).append(store.getCode()).append("/").append(FileContentType.PROPERTY).append("/")
				.append(imageName).toString();
	}

	
	@Override
	public String getContextPath() {
		return super.getProperties().getProperty(CONTEXT_PATH);
	}
	
	private String getScheme(MerchantStore store, String derivedHost) {
		return store.getDomainName() != null ? store.getDomainName():derivedHost;
	}

	@Override
	public void setContentUrlPath(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	



}
