/**
 * 
 */
package com.salesmanager.core.business.promotions.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * <p>Model class representing Serial Vouchers in Shopizer, this model class
 * is responsible for defining  attributes available to serial vouchers. 
 * </p>
 * <p>
 * A serial voucher can be redeemed only once.
 * </p>
 * @author Umesh Awasthi
 *
 */
@Entity
@DiscriminatorValue("SERIAL_VOUCHERS")
public class SerialVouchers extends Vouchers {

	private static final long serialVersionUID = 1L;
	
	@Column(name="VOUCHERS_CODE")
	private String code;
	
	@Column(name="REDEEMED")
	private boolean redeemed;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isRedeemed() {
		return redeemed;
	}

	public void setRedeemed(boolean redeemed) {
		this.redeemed = redeemed;
	}
	
	
	

}
