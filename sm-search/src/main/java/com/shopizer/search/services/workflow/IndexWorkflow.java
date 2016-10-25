package com.shopizer.search.services.workflow;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopizer.search.services.worker.ExecutionContext;
import com.shopizer.search.services.worker.IndexWorker;


@Component
public class IndexWorkflow extends Workflow {
	
	private static Logger log = Logger.getLogger(IndexWorkflow.class);
	
	private List indexWorkflow;
	
	public List getIndexWorkflow() {
		return indexWorkflow;
	}

	public void setIndexWorkflow(List indexWorkflow) {
		this.indexWorkflow = indexWorkflow;
	}
	
	public void index(String json, String collection, String object) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> indexData = mapper.readValue(json, Map.class);
		
		//get id
		String _id = (String)indexData.get("id");
		if(StringUtils.isBlank(_id)) {
			log.warn("No id exist for object " + json + " will create a generic one");
			UUID uid = UUID.randomUUID();
			_id = uid.toString();
			indexData.put("id", _id);
			json = mapper.writeValueAsString(indexData);
		}
		ExecutionContext context = new ExecutionContext();
		context.setObject("indexData", indexData);
		
		if(indexWorkflow!=null) {
			for(Object o : indexWorkflow) {
				IndexWorker iw = (IndexWorker)o;
				iw.execute(this.getSearchClient(),json, collection, object, _id, context);
			}
		}
	}

}
