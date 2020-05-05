package com.salesmanager.core.business.modules.cart;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.salesmanager.core.business.modules.common.IndexEntityProcessor;
import com.salesmanager.core.business.modules.order.IndexOrderProcessor;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;

@Component
public class IndexShoppingCartProcessor extends IndexEntityProcessor implements ShoppingCartProcessor {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexOrderProcessor.class);

	@Override
	public void process(String event, Object entity, MerchantStore store) {
		this.process(event, entity, new Customer(), store);

	}

	@Async
	@Override
	public void process(String event, Object entity, Customer customer, MerchantStore store) {
		
		ShoppingCart cart = (ShoppingCart)entity;
		try {
			RestHighLevelClient client = client();

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);			
			Mapping m = new Mapping("cart", event, cart, customer);
			String json = mapper.writeValueAsString(m);
			
			String indexName = new StringBuilder().append(INDEX_NAME).append(store.getCode().toLowerCase()).toString();

	        
	        IndexRequest indexRequest = new IndexRequest(indexName);
	        indexRequest.id(String.valueOf(cart.getId()));
	        indexRequest.source(json, XContentType.JSON);

		    IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
		    
		    if(response.getResult() != DocWriteResponse.Result.CREATED && response.getResult() != DocWriteResponse.Result.UPDATED) {
		    	LOGGER.error(
		          "An error occured while indexing an shopping cart document " + cart.getId() + " " + response.getResult().name());
		    }

			client.close();
		} catch(Exception e) {
			LOGGER.error("Cannot index cart [" + cart.getId() + "] ", e);
		}

	}


}
