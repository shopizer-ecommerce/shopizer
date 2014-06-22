package com.salesmanager.core.business.catalog.product.service.review;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.salesmanager.core.business.catalog.product.dao.review.ProductReviewDao;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.review.ProductReview;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.reference.language.model.Language;

@Service("productReviewService")
public class ProductReviewServiceImpl extends
		SalesManagerEntityServiceImpl<Long, ProductReview> implements
		ProductReviewService {


	private ProductReviewDao productReviewDao;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	public ProductReviewServiceImpl(
			ProductReviewDao productReviewDao) {
			super(productReviewDao);
			this.productReviewDao = productReviewDao;
	}

	@Override
	public List<ProductReview> getByCustomer(Customer customer) {
		return productReviewDao.getByCustomer(customer);
	}

	@Override
	public List<ProductReview> getByProduct(Product product) {
		return productReviewDao.getByProduct(product);
	}
	
	@Override
	public ProductReview getByProductAndCustomer(Long productId, Long customerId) {
		return productReviewDao.getByProductAndCustomer(productId, customerId);
	}
	
	@Override
	public List<ProductReview> getByProduct(Product product, Language language) {
		return productReviewDao.getByProduct(product, language);
	}
	
	public void create(ProductReview review) throws ServiceException {
		
		//adjust score
		
		//refresh product
		Product product = productService.getById(review.getProduct().getId());
		
		//ajust product rating
		Integer count = 0;
		if(product.getProductReviewCount()!=null) {
			count = product.getProductReviewCount();
		}
				
		
		

		BigDecimal averageRating = product.getProductReviewAvg();
		if(averageRating==null) {
			averageRating = new BigDecimal(0);
		}
		//get reviews

		
		BigDecimal totalRating = averageRating.multiply(new BigDecimal(count));
		totalRating = totalRating.add(new BigDecimal(review.getReviewRating()));
		
		count = count + 1;
		double avg = totalRating.doubleValue() / count.intValue();
		
		product.setProductReviewAvg(new BigDecimal(avg));
		product.setProductReviewCount(count);
		super.create(review);
		
		productService.update(product);
		
	}


}
