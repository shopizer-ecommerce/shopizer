package com.shopizer.search.services.worker;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.inject.Inject;



import org.apache.commons.collections4.CollectionUtils;

import com.shopizer.search.services.SearchHit;
import com.shopizer.search.services.SearchRequest;
import com.shopizer.search.services.SearchResponse;
import com.shopizer.search.services.impl.SearchDelegate;
import com.shopizer.search.utils.CustomIndexConfiguration;
import com.shopizer.search.utils.DynamicIndexNameUtil;
import com.shopizer.search.utils.SearchClient;


public class DeleteKeywordsImpl implements DeleteObjectWorker {
	
	

	private List<CustomIndexConfiguration> indexConfigurations = null;
	@Inject
	private SearchDelegate searchDelegate;

	public void deleteObject(SearchClient client,String collection, String object, String id, ExecutionContext context) throws Exception {

		@SuppressWarnings("rawtypes")
		Map indexData = (Map)context.getObject("indexData");
		
		if(indexData!=null) {
			
			
			if(!CollectionUtils.isEmpty(this.getIndexConfigurations())) {
		
				for(CustomIndexConfiguration indexConfiguration : this.getIndexConfigurations()) {

					
					
					//build a search string for retrieving the internal id of the keyword
					String query = new StringBuilder()
						.append("{\"query\":{\"term\" : {\"_id_\" : \"")
						.append(id)
						.append("\" }}}").toString();
					
					SearchRequest sr = new SearchRequest();
					sr.addCollection(indexConfiguration.getCollectionName());
					sr.setJson(query);

					SearchResponse r = searchDelegate.search(sr);
					if(r!=null && !CollectionUtils.isEmpty(r.getIds())) {
						//Collection<String> ids = r.getIds();
						List<String> ids = new ArrayList<String>();
						Collection<SearchHit> hits = r.getSearchHits();
						for(SearchHit hit : hits) {
							ids.add(hit.getInternalId());
						}
						searchDelegate.bulkDeleteIndex(ids, indexConfiguration.getCollectionName(), indexConfiguration.getIndexName());
					}
		
				}
				
			}
			
		}
	}
	

	public void deleteObject(SearchClient client,String collection, String object, String id) throws Exception {

		if(searchDelegate.indexExist(collection)) {
		
			String query = new StringBuilder()
			.append("{\"query\":{\"term\" : {\"_id_\" : \"")
			.append(id)
			.append("\" }}}").toString();
		
			SearchRequest sr = new SearchRequest();
			sr.addCollection(collection);
			sr.setJson(query);
			
			
			SearchResponse r = searchDelegate.search(sr);
			if(r!=null) {
				Collection<String> ids = r.getIds();
			
				searchDelegate.bulkDeleteIndex(ids, collection, object);
			}
		
		}
		
		
	}

	public List<CustomIndexConfiguration> getIndexConfigurations() {
		return indexConfigurations;
	}

	public void setIndexConfigurations(List<CustomIndexConfiguration> indexConfigurations) {
		this.indexConfigurations = indexConfigurations;
	}



}
