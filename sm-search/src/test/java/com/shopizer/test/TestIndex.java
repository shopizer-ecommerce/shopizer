package com.shopizer.test;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.shopizer.search.services.SearchService;


@ContextConfiguration(locations = {
		"classpath:spring/spring-context-test.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class
})

/**
 * Test if the server is running
 * curl -X GET 'http://localhost:9200'
 * 
 * Test if an indice exists
 * curl -XHEAD -i 'http://localhost:9200/product_en_default'
 * 
 * Get index infos
 * curl -XGET 'http://localhost:9200/product_en_default/'
 * 
 * Get all indexed data
 * curl -XGET 'http://localhost:9200/product_en_default/_search?pretty=1' 
 * 
 * @author carlsamson
 *
 */
@Ignore
public class TestIndex {
	
	@Inject
	private SearchService searchService;
	
	@Test
	public void testIndex() throws Exception {
		
		//String jsonData = "{\"id\":\"1\",\"name\":\"Sac de plage en tissu recyclé M4858\",\"price\":\"45.99\",\"categories\":[\"sac\",\"accessoires\"],\"store\":\"default\",\"availability\":\"*\",\"available\":\"true\",\"lang\":\"fr\",\"description\":\"Sac de plage vintage en tissu et cuir recyclés\"}";
		//String jsonData = "{\"id\":\"2\",\"name\":\"Étui pour IPAD\",\"price\":\"45.99\",\"categories\":[\"accessoires\"],\"store\":\"default\",\"availability\":\"*\",\"available\":\"true\",\"lang\":\"fr\",\"description\":\"i-pad et apple\"}";
		
		
		//String jsonData = "{\"id\":\"5\",\"name\":\"Tête de bufle en os sculptée\",\"price\":\"145.99\",\"categories\":[\"accessoires\"],\"manufacturer\":[\"vintage\"],\"store\":\"default\",\"availability\":\"*\",\"available\":\"true\",\"lang\":\"fr\",\"description\":\"os sculpté\"}";
		String jsonData = "{\"id\":\"6\",\"name\":\"Tête de cheval en os sculptée\",\"price\":\"195.99\",\"categories\":[\"accessoires\"],\"manufacturer\":[\"horse\"],\"store\":\"default\",\"availability\":\"*\",\"available\":\"true\",\"lang\":\"fr\",\"description\":\"oeuvre os sculpté\"}";
		
		
		//String jsonData = "{\"id\":\"3\",\"name\":\"Coussin en tissu\",\"price\":\"75.99\",\"categories\":[\"accessoires\"],\"store\":\"default\",\"availability\":\"*\",\"available\":\"true\",\"lang\":\"fr\",\"description\":\"Coussin de tissu\"}";
		//String jsonData = "{\"id\":\"4\",\"name\":\"Sac en cuir\",\"price\":\"99.99\",\"categories\":[\"accessoires\"],\"store\":\"default\",\"availability\":\"*\",\"available\":\"true\",\"lang\":\"fr\",\"description\":\"Sac en cuir\"}";
		//String jsonData = "{\"id\":\"1\",\"name\":\"Spring in action\",\"price\":\"23.99\",\"categories\":[\"book\",\"technology\"],\"store\":\"default\",\"availability\":\"*\",\"available\":\"true\",\"lang\":\"en\",\"description\":\"Best spring book, covers Spring MVC and Spring security\", \"tags\":[\"Spring\",\"Security\",\"Spring MVC\",\"Web\"]}";
		//String jsonData = "{\"id\":\"2\",\"name\":\"Citizen tech model silver watch\",\"price\":\"453.99\",\"categories\":[\"Men watches\"],\"store\":\"default\",\"availability\":\"*\",\"available\":\"true\",\"lang\":\"en\",\"description\":\"Silver watch 2012 model\", \"tags\":[\"Silver watch\",\"Liquidation\"]}";

		
		//searchService.index(jsonData, "product_en_default", "product_en");
		searchService.index(jsonData, "product_fr_default", "product_fr");
		
		System.out.println("Done !");
		
	}

}
