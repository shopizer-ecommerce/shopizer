package com.salesmanager.core.business.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.salesmanager.core.modules.integration.payment.model.PaymentModule;
import com.salesmanager.core.modules.integration.shipping.model.ShippingQuoteModule;

/**
 * Contains injection of external shopizer starter modules
 * @author carlsamson
 * New Way - out of xml config and using spring boot starters
 *
 */
@Configuration
public class ModulesConfiguration {
	
	
	/**
	 * Goes along with
	 * shipping-canadapost-spring-boot-starter
	 */
    @Autowired
    private ShippingQuoteModule canadapost;
    
    
    /**
     * All living modules exposed here
     */
    @Autowired
    private List<PaymentModule> liveModules;


}
