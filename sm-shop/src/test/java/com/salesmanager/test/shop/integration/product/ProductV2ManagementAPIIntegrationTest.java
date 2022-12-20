package com.salesmanager.test.shop.integration.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpStatus.CREATED;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.catalog.category.Category;
import com.salesmanager.shop.model.catalog.category.CategoryDescription;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOption;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription;
import com.salesmanager.shop.model.catalog.product.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.product.ProductSpecification;
import com.salesmanager.shop.model.catalog.product.variation.PersistableProductVariation;
import com.salesmanager.test.shop.common.ServicesTestSupport;


@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ProductV2ManagementAPIIntegrationTest extends ServicesTestSupport {
	
	
	@Test
	public void createProductWithCategory() throws Exception {


		/**
		 * Create a category for product association
		 */
		final PersistableCategory newCategory = new PersistableCategory();
		newCategory.setCode("test-catv2");
		newCategory.setSortOrder(1);
		newCategory.setVisible(true);
		newCategory.setDepth(4);

		final Category parent = new Category();

		newCategory.setParent(parent);

		final CategoryDescription description = new CategoryDescription();
		description.setLanguage("en");
		description.setName("test-catv2");
		description.setFriendlyUrl("test-catv2");
		description.setTitle("test-catv2");

		final List<CategoryDescription> descriptions = new ArrayList<>();
		descriptions.add(description);

		newCategory.setDescriptions(descriptions);

		final HttpEntity<PersistableCategory> categoryEntity = new HttpEntity<>(newCategory, getHeader());

		final ResponseEntity<PersistableCategory> categoryResponse = testRestTemplate.postForEntity(
				"/api/v1/private/category?store=" + Constants.DEFAULT_STORE, categoryEntity, PersistableCategory.class);
		final PersistableCategory cat = categoryResponse.getBody();
		assertTrue(categoryResponse.getStatusCode()== CREATED);
		assertNotNull(cat.getId());

		final PersistableProduct product = super.product("123");
		final ArrayList<Category> categories = new ArrayList<>();
		categories.add(cat);
		product.setCategories(categories);
		ProductSpecification specifications = new ProductSpecification();
		specifications.setManufacturer(
				com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer.DEFAULT_MANUFACTURER);
		product.setProductSpecifications(specifications);
		product.setPrice(BigDecimal.TEN);
		
		final HttpEntity<PersistableProduct> productEntity = new HttpEntity<>(product, getHeader());
		final ResponseEntity<PersistableProduct> response = testRestTemplate.postForEntity(
				"/api/v2/private/product?store=" + Constants.DEFAULT_STORE, productEntity, PersistableProduct.class);
		assertTrue(response.getStatusCode()== CREATED);
		
		//create options
		PersistableProductOption color = new PersistableProductOption();
		color.setCode("color");
		ProductOptionDescription colorEn = new ProductOptionDescription();
		colorEn.setName("Color");
		colorEn.setLanguage("en");
		color.getDescriptions().add(colorEn);
		
		final HttpEntity<PersistableProductOption> colorEntity = new HttpEntity<>(color, getHeader());
		final ResponseEntity<PersistableProductOption> colorResponse = testRestTemplate.postForEntity(
				"/api/v1/private/product/option?store=" + Constants.DEFAULT_STORE, colorEntity, PersistableProductOption.class);
		assertTrue(colorResponse.getStatusCode()== CREATED);
		System.out.println(colorResponse.getBody().getId());
		
		
		PersistableProductOption size = new PersistableProductOption();
		size.setCode("size");
		ProductOptionDescription sizeEn = new ProductOptionDescription();
		sizeEn.setName("Size");
		sizeEn.setLanguage("en");
		size.getDescriptions().add(sizeEn);
		
		final HttpEntity<PersistableProductOption> sizeEntity = new HttpEntity<>(size, getHeader());
		final ResponseEntity<PersistableProductOption> sizeResponse = testRestTemplate.postForEntity(
				"/api/v1/private/product/option?store=" + Constants.DEFAULT_STORE, sizeEntity, PersistableProductOption.class);
		assertTrue(sizeResponse.getStatusCode()== CREATED);
		System.out.println(colorResponse.getBody().getId());
		
		//opions values
		PersistableProductOptionValue white = new PersistableProductOptionValue();
		white.setCode("white");
		ProductOptionValueDescription whiteEn = new ProductOptionValueDescription();
		whiteEn.setName("White");
		whiteEn.setLanguage("en");
		white.getDescriptions().add(whiteEn);
		
		final HttpEntity<PersistableProductOptionValue> whiteEntity = new HttpEntity<>(white, getHeader());
		final ResponseEntity<PersistableProductOptionValue> whiteResponse = testRestTemplate.postForEntity(
				"/api/v1/private/product/option?store=" + Constants.DEFAULT_STORE, whiteEntity, PersistableProductOptionValue.class);
		assertTrue(whiteResponse.getStatusCode()== CREATED);
		System.out.println(whiteResponse.getBody().getId());
		
		PersistableProductOptionValue medium = new PersistableProductOptionValue();
		medium.setCode("medium");
		ProductOptionValueDescription mediumEn = new ProductOptionValueDescription();
		mediumEn.setName("Medium");
		mediumEn.setLanguage("en");
		white.getDescriptions().add(mediumEn);
		
		//create variantions
		PersistableProductVariation whiteVariation = new PersistableProductVariation();
		PersistableProductVariation mediumVariation = new PersistableProductVariation();
		// toto
		//create variants
	}

}
