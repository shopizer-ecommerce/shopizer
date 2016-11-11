package com.shopizer.test;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopizer.search.services.SearchResponse;
import com.shopizer.search.services.SearchService;


/**
 * curl -X POST http://localhost:9200/keyword_en_default/_search?pretty=true -d '{"query": {"match": {"keyword": {"query": "spr","analyzer": "standard"}}}}'
 * 
 * Get all indexed data
 * curl -XGET 'http://localhost:9200/keyword_en_default/_search?pretty=1' 
 * 
 * Test if an indice exists
 * curl -XHEAD -i 'http://localhost:9200/keyword_en_default'
 * 
 * Get index infos
 * curl -XGET 'http://localhost:9200/keyword_en_default/'
 * 
 * 
 * @author carlsamson
 *
 */

@ContextConfiguration(locations = {
		"classpath:spring/spring-context-test.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
})

@Ignore
public class TestSearchKeywords {
	
	@Inject
	private SearchService searchService;
	
	@Test
	public void testSearchKeywords() throws Exception {
		

		String json="{\"query\": {\"match\" : {\"keyword\" : {\"query\" : \"Étu\",\"analyzer\" : \"standard\"}}}}";

		
		System.out.println(json);

		SearchResponse resp= searchService.searchAutoComplete("keyword_fr_default",json,10);
		
		ObjectMapper mapper = new ObjectMapper();
		String indexData = mapper.writeValueAsString(resp);
		System.out.println(indexData);
		
		
	}

}
