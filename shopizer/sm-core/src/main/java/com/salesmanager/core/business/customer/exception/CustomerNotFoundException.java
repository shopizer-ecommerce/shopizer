package com.salesmanager.core.business.customer.exception;

public class CustomerNotFoundException extends Exception
{

   private static final long serialVersionUID = 132801185016247023L;

    public CustomerNotFoundException(final String msg, final Throwable cause)
    {
        super(msg, cause);
    }

    public CustomerNotFoundException(final String msg)
    {
        super(msg);
    }
    
    public CustomerNotFoundException(Throwable t)
    {
        super(t);
    }
}
