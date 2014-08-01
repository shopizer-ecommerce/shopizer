/**
 * 
 */
package com.salesmanager.core.modules.promotions.voucher;

import com.salesmanager.core.business.shoppingcart.model.ShoppingCart;

/**
 * <p>Entry point for Voucher Service.Voucher Implementation will be provided 
 * by a separate module.
 * VoucherModule will work as a hook between sm-core and voucher implementation.
 * </p>
 * <p>
 * Voucher implementation will be provided under new module, this will work as a 
 * bridge to use platform
 * </p>
 * @author Umesh Awasthi
 *
 */
public interface VoucherModule {

	/**
	 * <p>Method used to generate voucher code which can be used in store for promotions.
	 * Voucher implementor is responsible for providing logic to generate voucher code.</p>
	 * 
	 * <p>Any future custom Voucher implementation should provide definition to this method.
	 * prefix and suffix are both optional and provide a way to append prefix or suffix of choice to
	 * system generated voucher code
	 * </p>
	 * 
	 * @param prefix prefix to be used with voucher code if any
	 * @param suffix suffix to be used with voucher if any 
	 * @param voucherCode generated voucher code 
	 * @throws VoucherGenerationException @{@link VoucherGenerationException}
	 */
	public String generateVoucher(final String prefix, final String suffix) throws VoucherGenerationException;
	
	
	/**
	 * <p>Method responsible for applying voucher for given {@link ShoppingCart}.
	 * Customer will provide voucher code for a given order and method will perform
	 * following actions
	 * 
	 * <li>Check if voucher is valid or not </li>
	 * <li> Apply voucher on given shopping cart </li>
	 * <li> re-calculate Shopping cart final price </li>
	 * <li> calculate discounted value and store it with shopping cart </li>
	 * </p>
	 * 
	 * @param voucherCode voucher code applied by customer
	 * @param shoppingCart shopping cart on which voucher is being applied
	 * @return flag indicating if voucher is applied successfully
	 * @throws VoucherException @{@link VoucherException}
	 */
	public boolean redeemVoucher(final String voucherCode, final ShoppingCart shoppingCart) throws VoucherException;
	
	/**
	 * <p>Method responsible for removing applied voucher from given Shopping cart.
	 * In case customer want to remove voucher from the shopping cart, they 
	 * can do so by removing applied voucher from the shopping cart
	 * Method is responsible to perform following operation
	 * 
	 * <li>Check if Shopping cart have a valid voucher</li>
	 * <li>remove voucher from shopping cart </li>
	 * <li>recalculate price of shopping cart </li>
	 * </p>
	 *  
	 * @param shoppingCart
	 * @return
	 * @throws VoucherException
	 */
	public boolean releaseVoucher(final ShoppingCart shoppingCart) throws VoucherException;
	
	
	/**
	 * Method responsible to determine if given voucher is valid or not.
	 * A voucher is valid if
	 * <li>If it is not expired </li>
	 * <li> for serial voucher, it is not used by customer </li>
	 * </p>
	 * 
	 * @param voucherCode voucherCode whose validation to be determined
	 * @return boolean flag indicating if its a valid code or not
	 * @throws VoucherException @{@link VoucherException}
	 * 
	 */
	public boolean isValidVoucher(final String voucherCode) throws VoucherException;
}
