package com.shopizer.search.services.worker;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;




import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopizer.search.services.impl.SearchDelegate;
import com.shopizer.search.utils.FileUtil;
import com.shopizer.search.utils.IndexConfiguration;
import com.shopizer.search.utils.SearchClient;


public class ObjectIndexerImpl implements IndexWorker {
	
	private static boolean init = false;

	@Inject
	private SearchDelegate searchDelegate;
	
	private List<IndexConfiguration> indexConfigurations;

	public List<IndexConfiguration> getIndexConfigurations() {
		return indexConfigurations;
	}

	public void setIndexConfigurations(List<IndexConfiguration> indexConfigurations) {
		this.indexConfigurations = indexConfigurations;
	}
	
	private static Logger log = Logger.getLogger(ObjectIndexerImpl.class);
	
	public synchronized void init(SearchClient client) {
		
		//get the list of configuration
		//get the collection name and index name
		//get the mapping file
		if(init) {
			return;
		}

		init = true;
		if(getIndexConfigurations()!=null && getIndexConfigurations().size()>0) {

				for(Object o : indexConfigurations) {
					
					IndexConfiguration config = (IndexConfiguration)o;
					
					String mappingFile = null;
					String settingsFile = null;
					if(!StringUtils.isBlank(config.getMappingFileName())) {
						mappingFile = config.getMappingFileName();
					}
					if(!StringUtils.isBlank(config.getSettingsFileName())) {
						settingsFile = config.getSettingsFileName();
					}
					
					if(mappingFile!=null || settingsFile!=null) {
						
						String metadata = null;
						String settingsdata = null;
						try {
							
							if(mappingFile!=null) {
								metadata = FileUtil.readFileAsString(mappingFile);
							}
							
							if(settingsFile!=null) {
								settingsdata = FileUtil.readFileAsString(settingsFile);
							}

							if(!StringUtils.isBlank(config.getIndexName())) {
								
								if(!searchDelegate.indexExist(config.getCollectionName())) {
									searchDelegate.createIndice(metadata, settingsdata, config.getCollectionName(), config.getIndexName());
								}
							}
							
						} catch (Exception e) {
							log.error(e);
							log.error("*********************************************");
							log.error(e);
							log.error("*********************************************");
							init=false;
						}
					}
				} 
		} 
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void execute(SearchClient client, String json, String collection, String object, String id, ExecutionContext context)
			throws Exception {

		try {
			

			if(!init) {
				init(client);
			}
			
			//get json object
			Map<String,Object> indexData = (Map<String,Object>)context.getObject("indexData");
			if(indexData==null) {
				ObjectMapper mapper = new ObjectMapper();
				indexData = mapper.readValue(json, Map.class);
			}
			
			if(context==null) {
				context = new ExecutionContext();
			}
			
			context.setObject("indexData", indexData);

			com.shopizer.search.services.GetResponse r = searchDelegate.getObject(collection, object, id);
			if(r!=null) {
				searchDelegate.delete(collection, object, id);
			}
			
			searchDelegate.index(json, collection, object, id);
		
		} catch (Exception e) {
			log.error("Exception while indexing a product, maybe a timing ussue for no shards available",e);
		}
		

	}

}
