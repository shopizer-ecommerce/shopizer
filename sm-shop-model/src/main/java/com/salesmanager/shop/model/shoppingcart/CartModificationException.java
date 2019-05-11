/**
 * 
 */
package com.salesmanager.shop.model.shoppingcart;

/**
 * @author Umesh A
 *
 */
public class CartModificationException extends Exception{
	
	
	private static final long serialVersionUID = 679173596061770958L;

	public CartModificationException(final String msg, final Throwable cause)
	  {
	      super(msg, cause);
	  }

	  public CartModificationException(final String msg)
	  {
	      super(msg);
	  }
	  
	  public CartModificationException(Throwable t)
	  {
	      super(t);
	  }
	  

}
