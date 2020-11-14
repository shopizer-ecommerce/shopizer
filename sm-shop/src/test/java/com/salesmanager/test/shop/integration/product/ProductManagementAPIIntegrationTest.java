package com.salesmanager.test.shop.integration.product;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.CREATED;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionType;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.catalog.category.Category;
import com.salesmanager.shop.model.catalog.category.CategoryDescription;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.manufacturer.Manufacturer;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.PersistableProductPrice;
import com.salesmanager.shop.model.catalog.product.PersistableProductReview;
import com.salesmanager.shop.model.catalog.product.ProductDescription;
import com.salesmanager.shop.model.catalog.product.ProductSpecification;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.RentalOwner;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOption;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription;
import com.salesmanager.test.shop.common.ServicesTestSupport;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ProductManagementAPIIntegrationTest extends ServicesTestSupport {

	private RestTemplate restTemplate;

	private Long testCategoryID;

	private Long testProductID;

	@Test
	public void createProductWithCategory() throws Exception {


		final PersistableCategory newCategory = new PersistableCategory();
		newCategory.setCode("test-cat");
		newCategory.setSortOrder(1);
		newCategory.setVisible(true);
		newCategory.setDepth(4);

		final Category parent = new Category();

		newCategory.setParent(parent);

		final CategoryDescription description = new CategoryDescription();
		description.setLanguage("en");
		description.setName("test-cat");
		description.setFriendlyUrl("test-cat");
		description.setTitle("test-cat");

		final List<CategoryDescription> descriptions = new ArrayList<>();
		descriptions.add(description);

		newCategory.setDescriptions(descriptions);

		final HttpEntity<PersistableCategory> categoryEntity = new HttpEntity<>(newCategory, getHeader());

		final ResponseEntity<PersistableCategory> categoryResponse = testRestTemplate.postForEntity(
				"/api/v1/private/category?store=" + Constants.DEFAULT_STORE, categoryEntity, PersistableCategory.class);
		final PersistableCategory cat = categoryResponse.getBody();
		assertThat(categoryResponse.getStatusCode(), is(CREATED));
		assertNotNull(cat.getId());

		final PersistableProduct product = new PersistableProduct();
		final ArrayList<Category> categories = new ArrayList<>();
		categories.add(cat);
		product.setCategories(categories);
		ProductSpecification specifications = new ProductSpecification();
		specifications.setManufacturer(
				com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer.DEFAULT_MANUFACTURER);
		product.setProductSpecifications(specifications);
		product.setPrice(BigDecimal.TEN);
		product.setSku("123");
		final HttpEntity<PersistableProduct> entity = new HttpEntity<>(product, getHeader());

		final ResponseEntity<PersistableProduct> response = testRestTemplate.postForEntity(
				"/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entity, PersistableProduct.class);
		assertThat(response.getStatusCode(), is(CREATED));
	}

	/**
	 * Creates a ProductReview requires an existing Customer and an existing
	 * Product
	 *
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void createProductReview() throws Exception {

		final PersistableProductReview review = new PersistableProductReview();
		review.setCustomerId(1L);

		review.setProductId(1L);
		review.setLanguage("en");
		review.setRating(2D);// rating is on 5
		review.setDescription(
				"Not as good as expected. From what i understood that was supposed to be premium quality but unfortunately i had to return the item after one week... Verry disapointed !");
		review.setDate("2013-06-06");
		final HttpEntity<PersistableProductReview> entity = new HttpEntity<>(review, getHeader());

		final ResponseEntity<PersistableProductReview> response = testRestTemplate.postForEntity(
				"/api/v1/private/products/1/reviews?store=" + Constants.DEFAULT_STORE, entity,
				PersistableProductReview.class);

		final PersistableProductReview rev = response.getBody();
		assertThat(response.getStatusCode(), is(CREATED));
		assertNotNull(rev.getId());

	}

	/**
	 * Creates a product option value that can be used to create a product
	 * attribute when creating a new product
	 *
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void createOptionValue() throws Exception {

		final ProductOptionValueDescription description = new ProductOptionValueDescription();
		description.setLanguage("en");
		description.setName("Red");

		final List<ProductOptionValueDescription> descriptions = new ArrayList<>();
		descriptions.add(description);

		final PersistableProductOptionValue optionValue = new PersistableProductOptionValue();
		optionValue.setOrder(1);
		optionValue.setCode("colorred");
		optionValue.setDescriptions(descriptions);

		final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		final String json = writer.writeValueAsString(optionValue);

		System.out.println(json);

		/**
		 * { "descriptions" : [ { "name" : "Red", "description" : null,
		 * "friendlyUrl" : null, "keyWords" : null, "highlights" : null,
		 * "metaDescription" : null, "title" : null, "language" : "en", "id" : 0
		 * } ], "order" : 1, "code" : "color-red", "id" : 0 }
		 */

		restTemplate = new RestTemplate();

		final HttpEntity<String> entity = new HttpEntity<>(json, getHeader());

		final ResponseEntity response = restTemplate.postForEntity(
				"http://localhost:8080/sm-shop/services/private/DEFAULT/product/optionValue", entity,
				PersistableProductOptionValue.class);

		final PersistableProductOptionValue opt = (PersistableProductOptionValue) response.getBody();
		System.out.println("New optionValue ID : " + opt.getId());

	}

	/**
	 * Creates a new ProductOption
	 *
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void createOption() throws Exception {

		final ProductOptionDescription description = new ProductOptionDescription();
		description.setLanguage("en");
		description.setName("Color");

		final List<ProductOptionDescription> descriptions = new ArrayList<>();
		descriptions.add(description);

		final PersistableProductOption option = new PersistableProductOption();
		option.setOrder(1);
		option.setCode("color");
		option.setType(ProductOptionType.Select.name());
		option.setDescriptions(descriptions);

		final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		final String json = writer.writeValueAsString(option);

		System.out.println(json);

		/**
		 * { "descriptions" : [ { "name" : "Color", "description" : null,
		 * "friendlyUrl" : null, "keyWords" : null, "highlights" : null,
		 * "metaDescription" : null, "title" : null, "language" : "en", "id" : 0
		 * } ], "type" : SELECT, "order" : 1, "code" : "color", "id" : 0 }
		 */

		restTemplate = new RestTemplate();

		final HttpEntity<String> entity = new HttpEntity<>(json, getHeader());

		final ResponseEntity response = restTemplate.postForEntity(
				"http://localhost:8080/sm-shop/services/private/DEFAULT/product/option", entity,
				PersistableProductOption.class);

		final PersistableProductOption opt = (PersistableProductOption) response.getBody();
		System.out.println("New option ID : " + opt.getId());

	}

	@Test
	@Ignore
	public void getProducts() throws Exception {
		restTemplate = new RestTemplate();

		final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());

		final ResponseEntity<ReadableProduct[]> response = restTemplate.exchange(
				"http://localhost:8080/sm-shop/services/rest/products/DEFAULT/en/" + testCategoryID, HttpMethod.GET,
				httpEntity, ReadableProduct[].class);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception();
		} else {
			System.out.println(response.getBody().length + " Product records found.");
		}
	}

	@Test
	@Ignore
	public void putProduct() throws Exception {
		restTemplate = new RestTemplate();

		// TODO: Put Product

	}

	@Test
	@Ignore
	public void postProduct() throws Exception {
		restTemplate = new RestTemplate();

		final PersistableProduct product = new PersistableProduct();

		final String code = "abcdef";

		final String categoryCode = "ROOT";// root category

		final Category category = new Category();
		category.setCode(categoryCode);
		final List<Category> categories = new ArrayList<>();
		categories.add(category);

		final String manufacturer = "temple";
		final Manufacturer collection = new Manufacturer();
		collection.setCode(manufacturer);

		// core properties

		product.setSku(code);

		// product.setManufacturer(collection); //no manufacturer assigned for
		// now
		// product.setCategories(categories); //no category assigned for now

		product.setSortOrder(0);// set iterator as sort order
		product.setAvailable(true);// force availability
		product.setProductVirtual(false);// force tangible good
		product.setQuantityOrderMinimum(1);// force to 1 minimum when ordering
		product.setProductShipeable(true);// all items are shipeable

		/** images **/
		final String image = "/Users/carlsamson/Documents/csti/IMG_4626.jpg";
		// String image = "C:/personal/em/pictures-misc/IMG_2675.JPG";

		final File imgPath = new File(image);

		// PersistableImage persistableImage = new PersistableImage();

		// persistableImage.setBytes(this.extractBytes(imgPath));
		// persistableImage.setImageName(imgPath.getName());

		// List<PersistableImage> images = new ArrayList<PersistableImage>();
		// images.add(persistableImage);

		// product.setImages(images);

		ProductSpecification specifications = new ProductSpecification();
		specifications.setHeight(new BigDecimal(20));
		specifications.setLength(new BigDecimal(21));
		specifications.setWeight(new BigDecimal(22));
		specifications.setWidth(new BigDecimal(23));

		product.setProductSpecifications(specifications);
		product.setQuantity(5);
		product.setQuantityOrderMaximum(2);

		final PersistableProductPrice productPrice = new PersistableProductPrice();
		productPrice.setDefaultPrice(true);

		productPrice.setOriginalPrice(new BigDecimal(250));
		productPrice.setDiscountedPrice(new BigDecimal(125));

		final List<PersistableProductPrice> productPriceList = new ArrayList<>();
		productPriceList.add(productPrice);

		product.setProductPrices(productPriceList);

		final List<ProductDescription> descriptions = new ArrayList<>();

		// add english description
		ProductDescription description = new ProductDescription();
		description.setLanguage("en");
		description.setTitle("Buddha Head");
		description.setName("Buddha Head");
		description.setDescription("Buddha Head");
		description.setFriendlyUrl("buddha-head");

		// description.setHighlights(record.get("highlights_en"));

		descriptions.add(description);

		// add french description
		description = new ProductDescription();
		description.setLanguage("fr");
		description.setTitle("Tête de Buddha");
		description.setName("Tête de Buddha");
		description.setDescription(description.getName());
		description.setFriendlyUrl("tete-de-buddha");
		//

		descriptions.add(description);

		product.setDescriptions(descriptions);

		// RENTAL
		final RentalOwner owner = new RentalOwner();
		// need to create a customer first
		owner.setId(1L);
		product.setOwner(owner);

		final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		final String json = writer.writeValueAsString(product);

		System.out.println(json);

		final HttpEntity<String> entity = new HttpEntity<>(json, getHeader());

		// post to create category web service
		final ResponseEntity response = restTemplate.postForEntity("http://localhost:8080/api/v1/product", entity,
				PersistableProduct.class);

		final PersistableProduct prod = (PersistableProduct) response.getBody();

		System.out.println("---------------------");

	}

	@Test
	@Ignore
	public void deleteProduct() throws Exception {
		restTemplate = new RestTemplate();

		final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());

		restTemplate.exchange("http://localhost:8080/sm-shop/services/rest/product/DEFAULT/en/" + testCategoryID + "/"
				+ testProductID, HttpMethod.DELETE, httpEntity, ReadableProduct.class);
		System.out.println("Product " + testProductID + " Deleted.");
	}

	/** private helper methods **/
	public byte[] extractBytes(final File imgPath) throws Exception {

		final FileInputStream fis = new FileInputStream(imgPath);

		final BufferedInputStream inputStream = new BufferedInputStream(fis);
		final byte[] fileBytes = new byte[(int) imgPath.length()];
		inputStream.read(fileBytes);
		inputStream.close();

		return fileBytes;

	}

}