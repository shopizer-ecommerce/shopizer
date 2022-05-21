package com.salesmanager.shop.utils;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.merchant.MerchantStore;

public interface ImageFilePath {
	
	/**
	 * Context path configured in shopizer-properties.xml
	 * @return
	 */
	public String getContextPath();
	
	
	public String getBasePath(MerchantStore store);

	/**
	 * Builds a static content image file path that can be used by image servlet
	 * utility for getting the physical image
	 * @param store
	 * @param imageName
	 * @return
	 */
	public String buildStaticImageUtils(MerchantStore store, String imageName);
	
	/**
	 * Builds a static content image file path that can be used by image servlet
	 * utility for getting the physical image by specifying the image type
	 * @param store
	 * @param imageName
	 * @return
	 */
	public String buildStaticImageUtils(MerchantStore store, String type, String imageName);
	
	/**
	 * Builds a manufacturer image file path that can be used by image servlet
	 * utility for getting the physical image
	 * @param store
	 * @param manufacturer
	 * @param imageName
	 * @return
	 */
	public String buildManufacturerImageUtils(MerchantStore store, Manufacturer manufacturer, String imageName);
	
	/**
	 * Builds a product image file path that can be used by image servlet
	 * utility for getting the physical image
	 * @param store
	 * @param product
	 * @param imageName
	 * @return
	 */
	public String buildProductImageUtils(MerchantStore store, Product product, String imageName);
	
	/**
	 * Builds a default product image file path that can be used by image servlet
	 * utility for getting the physical image
	 * @param store
	 * @param sku
	 * @param imageName
	 * @return
	 */
	public String buildProductImageUtils(MerchantStore store, String sku, String imageName);
	
	/**
	 * Builds a large product image file path that can be used by the image servlet
	 * @param store
	 * @param sku
	 * @param imageName
	 * @return
	 */
	public String buildLargeProductImageUtils(MerchantStore store, String sku, String imageName);


	
	/**
	 * Builds a merchant store logo path
	 * @param store
	 * @return
	 */
	public String buildStoreLogoFilePath(MerchantStore store);
	
	/**
	 * Builds product property image url path
	 * @param store
	 * @param imageName
	 * @return
	 */
	public String buildProductPropertyImageUtils(MerchantStore store, String imageName);
	
	/**
	 * A custom file type image handler
	 * @param store
	 * @param imageName
	 * @param type
	 * @return
	 */
	String buildCustomTypeImageUtils(MerchantStore store, String imageName, FileContentType type);
	
	
	/**
	 * Builds static file path
	 * @param store
	 * @param fileName
	 * @return
	 */
	public String buildStaticContentFilePath(MerchantStore store, String fileName);


}
