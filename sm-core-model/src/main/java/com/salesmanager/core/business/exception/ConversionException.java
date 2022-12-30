/**
 * 
 */
package com.salesmanager.core.business.exception;

/**
 * @author Umesh A
 *
 */
public class ConversionException extends Exception
{
  private static final long serialVersionUID = 687400310032876603L;
  
  public ConversionException(final String msg, final Throwable cause)
  {
      super(msg, cause);
  }

  public ConversionException(final String msg)
  {
      super(msg);
  }
  
  public ConversionException(Throwable t)
  {
      super(t);
  }
  
  

}
