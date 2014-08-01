/**
 * 
 */
package com.salesmanager.core.modules.promotions.voucher;


/**
 * <p>Generic Voucher exception indicating an error while generating
 * voucher.
 * </p>
 * @author Umesh Awasthi
 *
 */
public class VoucherGenerationException extends VoucherException{
	
	
	private static final long serialVersionUID = 1L;

	public VoucherGenerationException(Exception e) {
		super(e);
	}
	
	public VoucherGenerationException(String message, Exception e) {
		super(message,e);
	}
	

}
