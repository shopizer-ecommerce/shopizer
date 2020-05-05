package com.salesmanager.shop.store.controller.customer;

import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.review.ProductReviewService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.catalog.product.PersistableProductReview;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductReview;
import com.salesmanager.shop.populator.catalog.PersistableProductReviewPopulator;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.populator.catalog.ReadableProductReviewPopulator;
import com.salesmanager.shop.store.controller.AbstractController;
import com.salesmanager.shop.store.controller.ControllerConstants;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;
import com.salesmanager.shop.utils.LabelUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Entry point for logged in customers
 * 
 * @author Carl Samson
 *
 */
@Controller
@RequestMapping(Constants.SHOP_URI + "/customer")
public class CustomerProductReviewController extends AbstractController {

	@Inject
	private ProductService productService;

	@Inject
	private LanguageService languageService;

	@Inject
	private PricingService pricingService;

	@Inject
	private ProductReviewService productReviewService;

	@Inject
	private CustomerService customerService;

	@Inject
	private CustomerFacade customerFacade;

	@Inject
	private LabelUtils messages;

	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@PreAuthorize("hasRole('AUTH_CUSTOMER')")
	@RequestMapping(value = "/review.html", method = RequestMethod.GET)
	public String displayProductReview(@RequestParam Long productId, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		MerchantStore store = getSessionAttribute(Constants.MERCHANT_STORE, request);
		Language language = super.getLanguage(request);

		// get product
		Product product = productService.getById(productId);
		if (product == null) {
			return "redirect:" + Constants.SHOP_URI;
		}

		if (product.getMerchantStore().getId().intValue() != store.getId().intValue()) {
			return "redirect:" + Constants.SHOP_URI;
		}

		// create readable product
		ReadableProduct readableProduct = new ReadableProduct();
		ReadableProductPopulator readableProductPopulator = new ReadableProductPopulator();
		readableProductPopulator.setPricingService(pricingService);
		readableProductPopulator.setimageUtils(imageUtils);
		readableProductPopulator.populate(product, readableProduct, store, language);
		model.addAttribute("product", readableProduct);

		Customer customer = customerFacade.getCustomerByUserName(request.getRemoteUser(), store);

		List<ProductReview> reviews = productReviewService.getByProduct(product, language);
		for (ProductReview r : reviews) {
			if (r.getCustomer().getId().longValue() == customer.getId().longValue()) {

				ReadableProductReviewPopulator reviewPopulator = new ReadableProductReviewPopulator();
				ReadableProductReview rev = new ReadableProductReview();
				reviewPopulator.populate(r, rev, store, language);

				model.addAttribute("customerReview", rev);
				break;
			}
		}

		ProductReview review = new ProductReview();
		review.setCustomer(customer);
		review.setProduct(product);

		ReadableProductReview productReview = new ReadableProductReview();
		ReadableProductReviewPopulator reviewPopulator = new ReadableProductReviewPopulator();
		reviewPopulator.populate(review, productReview, store, language);

		model.addAttribute("review", productReview);
		model.addAttribute("reviews", reviews);

		/** template **/
		StringBuilder template = new StringBuilder().append(ControllerConstants.Tiles.Customer.review).append(".")
				.append(store.getStoreTemplate());

		return template.toString();

	}

	@PreAuthorize("hasRole('AUTH_CUSTOMER')")
	@RequestMapping(value = "/review/submit.html", method = RequestMethod.POST)
	public String submitProductReview(@ModelAttribute("review") PersistableProductReview review,
			BindingResult bindingResult, Model model, HttpServletRequest request, HttpServletResponse response,
			Locale locale) throws Exception {

		MerchantStore store = getSessionAttribute(Constants.MERCHANT_STORE, request);
		Language language = getLanguage(request);

		Customer customer = customerFacade.getCustomerByUserName(request.getRemoteUser(), store);

		if (customer == null) {
			return "redirect:" + Constants.SHOP_URI;
		}

		Product product = productService.getById(review.getProductId());
		if (product == null) {
			return "redirect:" + Constants.SHOP_URI;
		}

		if (StringUtils.isBlank(review.getDescription())) {
			FieldError error = new FieldError("description", "description",
					messages.getMessage("NotEmpty.review.description", locale));
			bindingResult.addError(error);
		}

		if (review.getRating() == null) {
			FieldError error = new FieldError("rating", "rating",
					messages.getMessage("NotEmpty.review.rating", locale, "Product rating is required"));
			bindingResult.addError(error);
		}

		review.setDescription(StringEscapeUtils.escapeHtml4(review.getDescription()));

		ReadableProduct readableProduct = new ReadableProduct();
		ReadableProductPopulator readableProductPopulator = new ReadableProductPopulator();
		readableProductPopulator.setPricingService(pricingService);
		readableProductPopulator.setimageUtils(imageUtils);
		readableProductPopulator.populate(product, readableProduct, store, language);
		model.addAttribute("product", readableProduct);

		/** template **/
		StringBuilder template = new StringBuilder().append(ControllerConstants.Tiles.Customer.review).append(".")
				.append(store.getStoreTemplate());

		if (bindingResult.hasErrors()) {

			return template.toString();

		}

		// check if customer has already evaluated the product
		List<ProductReview> reviews = productReviewService.getByProduct(product);

		for (ProductReview r : reviews) {
			if (r.getCustomer().getId().longValue() == customer.getId().longValue()) {
				ReadableProductReviewPopulator reviewPopulator = new ReadableProductReviewPopulator();
				ReadableProductReview rev = new ReadableProductReview();
				reviewPopulator.populate(r, rev, store, language);

				model.addAttribute("customerReview", rev);
				return template.toString();
			}
		}

		PersistableProductReviewPopulator populator = new PersistableProductReviewPopulator();
		populator.setCustomerService(customerService);
		populator.setLanguageService(languageService);
		populator.setProductService(productService);

		review.setDate(DateUtil.formatDate(new Date()));
		review.setCustomerId(customer.getId());

		ProductReview productReview = populator.populate(review, store, language);
		productReviewService.create(productReview);

		model.addAttribute("review", review);
		model.addAttribute("success", "success");

		ReadableProductReviewPopulator reviewPopulator = new ReadableProductReviewPopulator();
		ReadableProductReview rev = new ReadableProductReview();
		reviewPopulator.populate(productReview, rev, store, language);

		model.addAttribute("customerReview", rev);

		return template.toString();

	}

}
