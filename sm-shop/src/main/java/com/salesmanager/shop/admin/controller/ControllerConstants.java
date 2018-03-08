/**
 * 
 */
package com.salesmanager.shop.admin.controller;

/**
 * Interface contain constant for Controller.These constant will be used throughout
 * sm-shop to  providing constant values to various Controllers being used in the
 * application.
 * @author Umesh A
 *
 */
public interface ControllerConstants
{

    interface Tiles{
    	
    	final String adminDashboard="admin-dashboard";
        interface ContentImages{
            final String addContentImages="admin-contentImages-add";
            final String contentImages="admin-content-images";
            final String fileBrowser="admin-content-filebrowser";
 
        }
        
        interface ContentFiles{
            final String addContentFiles="admin-content-files-add";
            final String contentFiles="admin-content-files";

 
        }
        
        interface Content{
            final String contentPages="admin-content-pages";
            final String contentPagesDetails="admin-content-pages-details";

        }
        
        interface Customer{
            final String optionsList="admin-customer-options-list";
            final String optionDetails="admin-customer-options-details";
            final String optionsValuesList="admin-customer-options-values-list";
            final String optionsValueDetails="admin-customer-options-values-details";
            final String optionsSet="admin-customer-options-set";

        }
        
        interface Product{
            final String productReviews="catalogue-product-reviews";
            final String productPrices="admin-products-prices";
            final String productPrice="admin-products-price";
            final String relatedItems="admin-products-related";
            final String digitalProduct="admin-products-digital";
            final String productImages="admin-products-images";
            final String productImagesUrl="admin-products-images-url";
            final String productKeywords="admin-products-keywords";
            final String customGroups="admin-products-groups";
            final String customGroupsDetails="admin-products-groups-details";
            final String manufacturerList="admin-products-manufacturer";
            final String manufacturerDetails="admin-products-manufacturer-details";
        }
        
        interface User{
            final String profile="admin-user-profile";
            final String users="admin-users";
            final String password="admin-user-password";
        }
        
        interface Store{
            final String stores="admin-stores";
        }


        interface Shipping{
            final String shippingMethod="shipping-method";
            final String shippingMethods="shipping-methods";
            final String shippingOptions="shipping-options";
            final String shippingPackaging="shipping-packaging";
            final String customShippingWeightBased="admin-shipping-custom";
        }
        
        interface Payment{
        	final String paymentMethods="payment-methods";
        	final String paymentMethod="payment-method";
        }
        
        interface Order{
            final String orders="admin-orders";
            final String ordersEdit="admin-orders-edit";
            final String ordersTransactions="admin-orders-transactions";
        }
        
        interface Configuration{
            final String accounts="config-accounts";
            final String email="config-email";
            final String cache="admin-cache";
            final String system="config-system";
        }
        
        interface Tax{
            final String taxClasses="tax-classes";
            final String taxClass="tax-class";
            final String taxConfiguration="tax-configuration";
            final String taxRates="tax-rates";
            final String taxRate="tax-rate";
        }
        
    }
}
