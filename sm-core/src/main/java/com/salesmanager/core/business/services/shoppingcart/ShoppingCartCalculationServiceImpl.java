/**
 *
 */
package com.salesmanager.core.business.services.shoppingcart;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.services.order.OrderServiceImpl;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderTotalSummary;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;

/**
 * <p>
 * Implementation class responsible for calculating state of shopping cart. This
 * class will take care of calculating price of each line items of shopping cart
 * as well any discount including sub-total and total amount.
 * </p>
 *
 * @author Umesh Awasthi
 * @version 1.2
 */
@Service("shoppingCartCalculationService")
public class ShoppingCartCalculationServiceImpl implements ShoppingCartCalculationService {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Inject
	private ShoppingCartService shoppingCartService;

	@Inject
	private OrderService orderService;

	/**
	 * <p>
	 * Method used to recalculate state of shopping cart every time any change
	 * has been made to underlying {@link ShoppingCart} object in DB.
	 * </p>
	 * Following operations will be performed by this method.
	 *
	 * <li>Calculate price for each {@link ShoppingCartItem} and update it.</li>
	 * <p>
	 * This method is backbone method for all price calculation related to
	 * shopping cart.
	 * </p>
	 *
	 * @see OrderServiceImpl
	 *
	 * @param cartModel
	 * @param customer
	 * @param store
	 * @param language
	 * @throws ServiceException
	 */
	@Override
	public OrderTotalSummary calculate(final ShoppingCart cartModel, final Customer customer, final MerchantStore store,
			final Language language) throws ServiceException {

		Validate.notNull(cartModel, "cart cannot be null");
		Validate.notNull(cartModel.getLineItems(), "Cart should have line items.");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(customer, "Customer cannot be null");
		OrderTotalSummary orderTotalSummary = orderService.calculateShoppingCartTotal(cartModel, customer, store,
				language);
		updateCartModel(cartModel);
		return orderTotalSummary;

	}

	/**
	 * <p>
	 * Method used to recalculate state of shopping cart every time any change
	 * has been made to underlying {@link ShoppingCart} object in DB.
	 * </p>
	 * Following operations will be performed by this method.
	 *
	 * <li>Calculate price for each {@link ShoppingCartItem} and update it.</li>
	 * <p>
	 * This method is backbone method for all price calculation related to
	 * shopping cart.
	 * </p>
	 *
	 * @see OrderServiceImpl
	 *
	 * @param cartModel
	 * @param store
	 * @param language
	 * @throws ServiceException
	 */
	@Override
	public OrderTotalSummary calculate(final ShoppingCart cartModel, final MerchantStore store, final Language language)
			throws ServiceException {

		Validate.notNull(cartModel, "cart cannot be null");
		Validate.notNull(cartModel.getLineItems(), "Cart should have line items.");
		Validate.notNull(store, "MerchantStore cannot be null");
		OrderTotalSummary orderTotalSummary = orderService.calculateShoppingCartTotal(cartModel, store, language);
		updateCartModel(cartModel);
		return orderTotalSummary;

	}

	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}

	private void updateCartModel(final ShoppingCart cartModel) throws ServiceException {
		shoppingCartService.saveOrUpdate(cartModel);
	}

}
