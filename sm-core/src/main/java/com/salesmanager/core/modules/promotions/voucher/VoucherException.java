/**
 * 
 */
package com.salesmanager.core.modules.promotions.voucher;

import com.salesmanager.core.business.generic.exception.ServiceException;

/**
 * <p>Generic Voucher exception indicating underlying system error in
 * vouchers.
 * voucher.
 * </p>
 * @author Umesh Awasthi
 *
 */
public class VoucherException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public VoucherException(Exception e) {
		super(e);
	}
	
	public VoucherException(String message, Exception e) {
		super(message,e);
	}

}
