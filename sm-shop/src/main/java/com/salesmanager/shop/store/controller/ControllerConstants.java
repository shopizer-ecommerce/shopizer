/**
 * 
 */
package com.salesmanager.shop.store.controller;

/**
 * Interface contain constant for Controller.These constant will be used throughout
 * sm-shop to  providing constant values to various Controllers being used in the
 * application.
 * @author Umesh A
 *
 */
public interface ControllerConstants
{

    final static String REDIRECT="redirect:";
    
    interface Tiles{
        interface ShoppingCart{
            final static String MAINSHOPPINGCART_STRING="maincart";
        }
        
        interface Category{
            final static String CATEGORY_STRING="category";
        }
        
        interface Product{
            final static String PRODUCT_STRING="product";
        }
        
        interface Items{
            final static String ITEMS_MANUFACTURE_STRING="items.manufacturer";
        }
        
        interface Customer{
            final static String CUSTOMER_STRING="customer";
            final static String CUSTOMERLOGON_STRING="customerLogon";
            final static String REVIEW_STRING="review";
            final static String REGISTER_STRING="register";
            final static String CHANGEPASSWORD_STRING="customerPassword";
            final static String CUSTOMERORDERS_STRING="customerOrders";
            final static String CUSTOMERORDER_STRING="customerOrder";
            final static String BILLING_STRING="customerAddress";
            final static String EDITADDRESS_STRING="editCustomerAddress";
        }
        
        interface Content{
            final static String CONTENT_STRING="content";
            final static String CONTACTUS_STRING="contactus";
        }
        
        interface Pages{
            final static String NOTFOUND_STRING="404";
            final static String TIMEOUT_STRING="timeout";
        }
        
        interface Merchant{
            final static String contactUs="contactus";
        }
        
        interface Checkout{
            final static String CHECKOUT_STRING="checkout";
            final static String CONFIRMATION_STRING="confirmation";
        }
        
        interface Search{
            final static String SEARCH_STRING="search";
        }
        
        interface Error {
        	final static String ACCESSDENIED_STRING = "accessDenied";
        	final static String ERROR_STRING = "error";
        }
        

        
    }

    interface Views
    {
        interface Controllers
        {
            interface Registration
            {
                String registrationPageString = "shop/customer/registration.html";
            }
        }
    }
}
