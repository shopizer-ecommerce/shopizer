package com.salesmanager.web.utils;

import org.apache.commons.lang3.StringUtils;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.content.model.FileContentType;
import com.salesmanager.core.business.merchant.model.MerchantStore;



public abstract class AbstractimageFilePath implements ImageFilePath {

	public abstract String getBasePath();

	public abstract void setBasePath(String basePath);
	
	/**
	 * Builds a static content image file path that can be used by image servlet
	 * utility for getting the physical image
	 * @param store
	 * @param imageName
	 * @return
	 */
	public String buildStaticimageUtils(MerchantStore store, String imageName) {
		StringBuilder imgName = new StringBuilder().append(getBasePath()).append("/").append(store.getCode()).append("/").append(FileContentType.IMAGE.name()).append("/");
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
		StringBuilder imgName = new StringBuilder().append(getBasePath()).append("/").append(store.getCode()).append("/").append(type).append("/");
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
		return new StringBuilder().append(getBasePath()).append("/").append(store.getCode()).append("/").
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
		return new StringBuilder().append(getBasePath()).append("/").append(store.getCode()).append("/").append(FileContentType.PRODUCT.name()).append("/")
				.append(product.getSku()).append("/").append(imageName).toString();
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
		return new StringBuilder().append(getBasePath()).append("/").append(store.getCode()).append("/").append(FileContentType.PRODUCT.name()).append("/")
				.append(sku).append("/").append(imageName).toString();
	}
	
	/**
	 * Builds a large product image file path that can be used by the image servlet
	 * @param store
	 * @param sku
	 * @param imageName
	 * @return
	 */
	public String buildLargeProductimageUtils(MerchantStore store, String sku, String imageName) {
		return new StringBuilder().append(getBasePath()).append("/").append(store.getCode()).append("/").append(FileContentType.PRODUCTLG.name()).append("/")
				.append(sku).append("/").append(imageName).toString();
	}


	
	/**
	 * Builds a merchant store logo path
	 * @param store
	 * @return
	 */
	public String buildStoreLogoFilePath(MerchantStore store) {
		return new StringBuilder().append(getBasePath()).append("/").append(store.getCode()).append("/").append(FileContentType.LOGO).append("/")
				.append(store.getStoreLogo()).toString();
	}
	
	/**
	 * Builds product property image url path
	 * @param store
	 * @param imageName
	 * @return
	 */
	public String buildProductPropertyimageUtils(MerchantStore store, String imageName) {
		return new StringBuilder().append(getBasePath()).append("/").append(store.getCode()).append("/").append(FileContentType.PROPERTY).append("/")
				.append(imageName).toString();
	}
	

	
	


}
