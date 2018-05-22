package com.salesmanager.shop.store.controller.product.facade;

import java.util.List;

import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.manufacturer.PersistableManufacturer;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.PersistableProductReview;
import com.salesmanager.shop.model.catalog.product.ProductPriceEntity;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;
import com.salesmanager.shop.model.catalog.product.ReadableProductReview;

public interface ProductFacade {
	
	PersistableProduct saveProduct(MerchantStore store, PersistableProduct product, Language language) throws Exception;
	
	/**
	 * Get a Product by id and store
	 * @param store
	 * @param id
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProduct getProduct(MerchantStore store, Long id, Language language) throws Exception;
	
	
	/**
	 * Reads a product by code
	 * @param store
	 * @param uniqueCode
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProduct getProductByCode(MerchantStore store, String uniqueCode, Language language) throws Exception;
	
	/**
	 * Get a product by sku and store
	 * @param store
	 * @param sku
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProduct getProduct(MerchantStore store,String sku, Language language) throws Exception;
	
	/**
	 * Sets a new price to an existing product
	 * @param product
	 * @param price
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProduct updateProductPrice(ReadableProduct product, ProductPriceEntity price, Language language) throws Exception;

	/**
	 * Sets a new price to an existing product
	 * @param product
	 * @param quantity
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProduct updateProductQuantity(ReadableProduct product, int quantity, Language language) throws Exception;

	/**
	 * Deletes a product for a given product id
	 * @param product
	 * @throws Exception
	 */
	void deleteProduct(Product product) throws Exception;
	
	
	/**
	 * Filters a list of product based on criteria
	 * @param store
	 * @param language
	 * @param criterias
	 * @return
	 * @throws Exception
	 */
	ReadableProductList getProductListsByCriterias(MerchantStore store, Language language, ProductCriteria criterias) throws Exception;
	
	
	/**
	 * Adds a product to a category
	 * @param category
	 * @param product
	 * @return
	 * @throws Exception
	 */
	ReadableProduct addProductToCategory(Category category, Product product, Language language) throws Exception;
	
	/**
	 * Removes item from a category
	 * @param category
	 * @param product
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableProduct removeProductFromCategory(Category category, Product product, Language language) throws Exception;

	
	/**
	 * Saves or updates a Product review
	 * @param review
	 * @param language
	 * @throws Exception
	 */
	void saveOrUpdateReview(PersistableProductReview review, MerchantStore store, Language language) throws Exception;

	/**
	 * Deletes a product review
	 * @param review
	 * @param store
	 * @param language
	 * @throws Exception
	 */
	void deleteReview(ProductReview review, MerchantStore store, Language language) throws Exception;

	/**
	 * Get reviews for a given product
	 * @param product
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	List<ReadableProductReview> getProductReviews(Product product, MerchantStore store, Language language) throws Exception;
	
	/**
	 * Creates or saves a manufacturer
	 * @param manufacturer
	 * @param store
	 * @param language
	 * @throws Exception
	 */
	void saveOrUpdateManufacturer(PersistableManufacturer manufacturer, MerchantStore store, Language language) throws Exception;
	
	/**
	 * Deletes a manufacturer
	 * @param manufacturer
	 * @param store
	 * @param language
	 * @throws Exception
	 */
	void deleteManufacturer(Manufacturer manufacturer, MerchantStore store, Language language) throws Exception;
	
	/**
	 * Get a Manufacturer by id
	 * @param id
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	ReadableManufacturer getManufacturer(Long id, MerchantStore store, Language language) throws Exception;
	
	/**
	 * Get all Manufacturer
	 * @param store
	 * @param language
	 * @return
	 * @throws Exception
	 */
	List<ReadableManufacturer> getAllManufacturers(MerchantStore store, Language language) throws Exception;
}
