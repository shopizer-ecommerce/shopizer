package com.salesmanager.shop.store.api.v1.customer;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.customer.review.CustomerReviewService;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.review.CustomerReview;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.customer.PersistableCustomerReview;
import com.salesmanager.shop.model.customer.ReadableCustomerReview;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;

@Controller
@RequestMapping("/api/v1")
public class CustomerReviewApi {
	
	@Inject
	private CustomerFacade customerFacade;
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;

	@Inject
	private CustomerService customerService;
	
	@Inject
	private CustomerReviewService customerReviewService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerReviewApi.class);
	
	
	/**
	 * Reviews made for a given customer
	 * @param id
	 * @param review
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/private/customers/{id}/reviews", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public PersistableCustomerReview create(@PathVariable final Long id, @Valid @RequestBody PersistableCustomerReview review, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
			
			
			//rating already exist
			CustomerReview customerReview = customerReviewService.getByReviewerAndReviewed(review.getCustomerId(), id);
			if(customerReview!=null) {
				response.sendError(500, "A review already exist for this customer and product");
				return null;
			}
			
			//rating maximum 5
			if(review.getRating()>Constants.MAX_REVIEW_RATING_SCORE) {
				response.sendError(503, "Maximum rating score is " + Constants.MAX_REVIEW_RATING_SCORE);
				return null;
			}
			
			review.setReviewedCustomer(id);
			
			customerFacade.saveOrUpdateCustomerReview(review, merchantStore, language);

			return review;
			
		} catch (Exception e) {
			LOGGER.error("Error while saving product review",e);
			try {
				response.sendError(503, "Error while saving product review" + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
	}
	
	

	@RequestMapping( value="/customers/{id}/reviews", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ReadableCustomerReview> getAll(@PathVariable final Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
			
			//customer exist
			Customer reviewed = customerService.getById(id);

			if(reviewed == null) {
				response.sendError(404, "Customer id " + id + " does not exists");
				return null;
			}

			List<ReadableCustomerReview> reviews = customerFacade.getAllCustomerReviewsByReviewed(reviewed, merchantStore, language);
			
			return reviews;
			
		} catch (Exception e) {
			LOGGER.error("Error while getting customer reviews",e);
			try {
				response.sendError(503, "Error while getting customer reviews" + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
	}

	@RequestMapping( value="/private/customers/{id}/reviews/{reviewid}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public PersistableCustomerReview update(@PathVariable final Long id, @PathVariable final Long reviewId, @Valid @RequestBody PersistableCustomerReview review, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
			
			CustomerReview customerReview = customerReviewService.getById(reviewId);
			if(customerReview==null) {
				response.sendError(404, "Customer review with id " + reviewId + " does not exist");
				return null;
			}
			
			if(customerReview.getReviewedCustomer().getId().longValue() != id.longValue()) {
				response.sendError(404, "Customer review with id " + reviewId + " does not exist for this customer");
				return null;
			}
			
			//rating maximum 5
			if(review.getRating()>Constants.MAX_REVIEW_RATING_SCORE) {
				response.sendError(503, "Maximum rating score is " + Constants.MAX_REVIEW_RATING_SCORE);
				return null;
			}
			
			review.setReviewedCustomer(id);
			
			customerFacade.saveOrUpdateCustomerReview(review, merchantStore, language);

			return review;
			
		} catch (Exception e) {
			LOGGER.error("Error while updating customer review",e);
			try {
				response.sendError(503, "Error while updating customer review" + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
	}
	
	@RequestMapping( value="/private/customers/{id}/reviews/{reviewid}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable final Long id, @PathVariable final Long reviewId, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		try {
			
			MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
			Language language = languageUtils.getRESTLanguage(request, merchantStore);	
			
			CustomerReview customerReview = customerReviewService.getById(reviewId);
			if(customerReview==null) {
				response.sendError(404, "Customer review with id " + reviewId + " does not exist");
				return;
			}
			
			if(customerReview.getReviewedCustomer().getId().longValue() != id.longValue()) {
				response.sendError(404, "Customer review with id " + reviewId + " does not exist for this customer");
				return;
			}
			
			


			customerFacade.deleteCustomerReview(customerReview, merchantStore, language);


			
		} catch (Exception e) {
			LOGGER.error("Error while deleting customer review",e);
			try {
				response.sendError(503, "Error while deleting customer review" + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return;
		}
	}
}
