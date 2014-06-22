package com.salesmanager.core.business.customer;



/**
 * <p>Exception thrown when system able register customer
 * </p>
 * 
 * @author Umesh Awasthi
 * @since 1.2
 *
 */
public class CustomerRegistrationException extends Exception
{

    private static final long serialVersionUID = 879320340641131133L;
    
    public CustomerRegistrationException(final String msg, final Throwable cause)
    {
        super(msg, cause);
    }

    public CustomerRegistrationException(final String msg)
    {
        super(msg);
    }
    
    public CustomerRegistrationException(Throwable t)
    {
        super(t);
    }

}
