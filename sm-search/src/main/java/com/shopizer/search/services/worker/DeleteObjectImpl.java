package com.shopizer.search.services.worker;

import javax.inject.Inject;

import com.shopizer.search.services.impl.SearchDelegate;
import com.shopizer.search.utils.SearchClient;


public class DeleteObjectImpl implements DeleteObjectWorker {
	
	@Inject
	private SearchDelegate searchDelegate;

	public void deleteObject(SearchClient client, String collection, String object, String id, ExecutionContext context) throws Exception {
		
		
		//need to get the original entry
		com.shopizer.search.services.GetResponse r = searchDelegate.getObject(collection, object, id);
		
		if(r!=null) {
			
			if(context==null) {
				context = new ExecutionContext();
			}
			
			
			if(r.getFieldMap()!=null && r.getFieldMap().size()>0) {

				//ObjectMapper mapper = new ObjectMapper();
				//Map indexData = mapper.readValue(r.getResponseAsString(), Map.class);
				
				context.setObject("indexData", r.getFieldMap());
			
			}
			
		}
		
/*		String query = new StringBuilder()
		.append("{\"query\":{\"term\" : {\"_id\" : \"")
		.append(id)
		.append("\" }}}").toString();
		
		SearchRequest r = new SearchRequest();
		r.setCollection(collection);
		r.setJson(query);*/
	
		
		//SearchResponse resp =s.search(r);
		
		//if(resp.getSearchHits()!=null && resp.getSearchHits().size()>0) {
			
			//List<SearchHit> hits = (List<SearchHit>)resp.getSearchHits();
		
			//SearchHit hit = hits.get(0);
			//Map obj = hit.getSource();
			
/*			Map<String,Object> indexData = (Map)context.getObject("indexData");
			if(indexData==null) {
				ObjectMapper mapper = new ObjectMapper();
				indexData = mapper.readValue(json, Map.class);
			}*/
			
			//if(context==null) {
			//	context = new ExecutionContext();
			//}
			
			//context.setObject("indexData", obj);
		
		//}
		
		//SearchServiceImpl search = new SearchServiceImpl(client);
		searchDelegate.delete(collection, object, id);

	}


	public void deleteObject(SearchClient client, String collection, String object, String id)
			throws Exception {
		throw new Exception("Not implemented");
		
	}


}
