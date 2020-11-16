package com.salesmanager.test.shop.integration.tax;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.entity.Entity;
import com.salesmanager.shop.model.entity.EntityExists;
import com.salesmanager.shop.model.tax.PersistableTaxClass;
import com.salesmanager.test.shop.common.ServicesTestSupport;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TaxClassIntegrationTest extends ServicesTestSupport {
	
	
    @Test
    public void manageTaxClass() throws Exception {
    	
    	//create tax class
    	PersistableTaxClass taxClass = new PersistableTaxClass();
    	taxClass.setCode("TESTTX");
    	taxClass.setName("Test tax class");
    	
        final HttpEntity<PersistableTaxClass> taxClassEntity = new HttpEntity<>(taxClass, getHeader());
        final ResponseEntity<Entity> response = testRestTemplate.postForEntity(String.format("/api/v1/private/tax/class/"), taxClassEntity, Entity.class);
        
        Entity e = response.getBody();
        
        assertNotNull(e.getId());
        assertTrue(e.getId() > 0);
        
        
        final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());
        
        //tax class exists
        final ResponseEntity<EntityExists> exists = testRestTemplate.exchange(String.format("/api/v1/private/tax/class/unique?code=" + taxClass.getCode()), HttpMethod.GET,
                httpEntity, EntityExists.class);

        assertTrue(exists.getBody().isExists());


        /**
        //list 1 taxClass
        @SuppressWarnings("rawtypes")
		final ResponseEntity<ReadableEntityList> listOfTaxClasses = testRestTemplate.exchange(String.format("/private/tax/class"), HttpMethod.GET,
                httpEntity, ReadableEntityList.class);
        
        assertTrue(listOfTaxClasses.getBody().getRecordsTotal() == 1);
        **/
        
        
        

    	
    }

}
