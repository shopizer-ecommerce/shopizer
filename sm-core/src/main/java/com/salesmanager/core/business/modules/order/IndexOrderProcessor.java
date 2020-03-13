package com.salesmanager.core.business.modules.order;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;

/*
 
 This order processor indexes Order to elasticSearch
 This is will result as a time based event searchable from Kibana
  
 Saves orders in orders-<STORE_CODE> index
  
 For this we need elasticsearch host and port from properties file
  
 Before starting Shopizer start elasticsearch and insert new pipeline for setting timestamp
 
	PUT /_ingest/pipeline/timestamp
	{
		"description": "Creates a timestamp when a document is initially indexed",
		"processors": [{
			"set": {
				"field": "_source.timestamp",
				"value": "{{_ingest.timestamp}}"
			}
		}]
	}
	
	//add index wildcard orders followed with asterisk
	PUT orders*\/_settings
	{
	  "index.default_pipeline": "timestamp"
	}
 
 @author carlsamson

 */
@Component("indexOrderProcessor")
public class IndexOrderProcessor implements OrderProcessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexOrderProcessor.class);
	
	private static final String INDEX_NAME = "orders-";
	
	@Value("${elasticsearch.server.host}")
	private List<String> hosts;
	
	@Value("${elasticsearch.server.protocole}")
	private String protocol;
	
	@Value("${elasticsearch.server.port}")
	private int port;

	@Override
	public void processOrder(Order order, Customer customer, MerchantStore store) {
		process(order, customer, store);
	}
	
	/**
	 * TODO migrate to
	 * ExecutorService threadpool = Executors.newCachedThreadPool();
	 * @param order
	 * @param customer
	 * @param store
	 */
	@Async
	private void process(Order order, Customer customer, MerchantStore store)  {
		try {
			RestHighLevelClient client = client();
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);			
			OrderMapping m = new OrderMapping(order, customer);
			String json = mapper.writeValueAsString(m);
			
			String indexName = new StringBuilder().append(INDEX_NAME).append(store.getCode()).toString();

	        
	        IndexRequest indexRequest = new IndexRequest(indexName);
	        indexRequest.id(String.valueOf(order.getId()));
	        indexRequest.source(json, XContentType.JSON);

		    IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
		    
		    if(response.getResult() != DocWriteResponse.Result.CREATED && response.getResult() != DocWriteResponse.Result.UPDATED) {
		    	LOGGER.error(
		          "An error occured while indexing an order document " + order.getId() + " " + response.getResult().name());
		    }

			client.close();
		} catch(Exception e) {
			LOGGER.error("Cannot IndexOrder [" + order.getId() + "] ", e);
		}

	}
	
	private RestHighLevelClient client() throws Exception {
		
		List<HttpHost> nodes = hosts.stream().map(m -> new HttpHost(m, port, protocol)).collect(Collectors.toList());
		RestClientBuilder builder = RestClient.builder(nodes.toArray(new HttpHost[nodes.size()]));
		RestHighLevelClient client = new RestHighLevelClient(builder);
		
		return client;

	}
	
	class OrderMapping {
		private Order order;
		private Customer customer;
		OrderMapping(Order order, Customer customer) {
			this.order = order;
			this.customer = customer;
		}
		public Order getOrder() {
			return order;
		}
		public Customer getCustomer() {
			return customer;
		}

	}
	

}
