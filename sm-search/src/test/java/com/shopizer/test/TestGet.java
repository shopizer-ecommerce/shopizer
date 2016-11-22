package com.shopizer.test;

import java.util.Map;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.util.Assert;

import com.shopizer.search.services.GetResponse;
import com.shopizer.search.services.SearchService;


@ContextConfiguration(locations = {
		"classpath:spring/spring-context-test.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class
})

/**
 * Get a specific id
 * curl -XGET 'http://localhost:9200/product_en_default/product_en/1'
 * 
 * @author carlsamson
 *
 */
//@Ignore
public class TestGet {
	
	@Inject
	private SearchService searchService;
	
	@Test
	public void testGet() throws Exception {
		
		/** requires to index at least a product first **/
		GetResponse response = searchService.getObject("product_en_default", "product_en", "1");

		Assert.notNull(response);
		
		
		Map<String, Object> fieldMap = response.getFieldMap();
		
		Assert.notEmpty(fieldMap);
		
		for(String key : fieldMap.keySet()) {
			Object o = fieldMap.get(key);
			System.out.println(key + " - " + o + " -- " + o.getClass());
		}
		
		System.out.println("Done !");
		
	}

}
