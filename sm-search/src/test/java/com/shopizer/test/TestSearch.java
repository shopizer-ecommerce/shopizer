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
import com.shopizer.search.services.SearchRequest;
import com.shopizer.search.services.SearchResponse;
import com.shopizer.search.services.SearchService;

/**
 * search api
 * 
 * Get all indexed data
 * curl -XGET 'http://localhost:9200/product_en_default/_search?pretty=1' 
 * 
 * Search
 * curl -XGET 'http://localhost:9200/product_en_default/_search' -d '{"query":{"query_string":{"fields" : ["name^5", "description", "tags"], "query" : "*spr*", "use_dis_max" : true }},"facets" : { "categories" : { "terms" : {"field" : "categories"}}}}'
 * 
 * 
 * 
 * GET product_fr_default/_search
	{
	  
	    "query":{
	  
					  "multi_match" : {
					    "query":      "bufle",
					    "type":       "best_fields",
					    "fields":     [ "name^10", "description^1" ]
					  }
	
	    },
	    "aggregations": {
	      "categories": {
	         "terms": {"field": "categories"}
	      },
	      "manufacturer": {
	         "terms": {"field": "manufacturer"}
	      }
   		}
	}
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
public class TestSearch {
	
	@Inject
	private SearchService searchService;
	
	@Test
	public void testSearch() throws Exception {
		
		String facets = "\"aggregations\": {\"categories\": {\"terms\": {\"field\": \"categories\"}},\"manufacturer\": {\"terms\": {\"field\": \"manufacturer\"}}}";
		//String facets = "\"facets\" : { \"categories\" : { \"terms\" : {\"field\" : \"categories\"}}}";
		
		//String q ="{\"query\":{\"query_string\":{\"fields\" : [\"name^5\", \"description\", \"tags\"], \"query\" : \"*spr*\", \"use_dis_max\" : true }}";

		String q ="{\"query\":{\"query_string\":{\"fields\" : [\"name^5\", \"description\", \"tags\"], \"query\" : \"bufle\", \"use_dis_max\" : true }}";

		
		String query = q + "," + facets + "}";
		//String query = q + "}";
		
		System.out.println(query);

		//String json ="{\"query\":{\"filtered\":{\"query\":{\"text\":{\"_all\":\"beach\"}},\"filter\":{\"numeric_range\":{\"age\":{\"from\":\"22\",\"to\":\"45\",\"include_lower\":true,\"include_upper\":true}}}}},\"highlight\":{\"fields\":{\"description\":{}}},\"facets\":{\"tags\":{\"terms\":{\"field\":\"tags\"}}}}";

		SearchRequest request = new SearchRequest();
		request.addCollection("product_fr_default");
		request.setJson(query);
		request.setSize(20);
		request.setStart(0);
		


		SearchResponse resp= searchService.search(request);
		
		ObjectMapper mapper = new ObjectMapper();
		String indexData = mapper.writeValueAsString(resp);
		System.out.println(indexData);
		
		
	}

}
