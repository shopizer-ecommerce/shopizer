package com.salesmanager.core.business.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.salesmanager.core.modules.integration.shipping.model.ShippingQuoteModule;
import com.shopizer.modules.shipping.canadapost.CanadaPostQuoteModule;

/**
 * Contains injection of external shopizer starter modules
 * @author carlsamson
 * Upcoming Way
 *
 */
@Configuration
public class ModulesConfiguration {
	
	
	/**
	 * Goes along with
	 * shipping-canadapost-spring-boot-starter
	 */
    //@Autowired
    //private CanadaPostQuoteModule canadaPostQuoteModule;
    
	//@Bean
	//public ShippingQuoteModule canadapost() {
	//	return canadaPostQuoteModule;
	//}

}
