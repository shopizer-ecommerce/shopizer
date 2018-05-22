package com.salesmanager.shop.store.model.search;

import org.json.simple.JSONObject;

public class AutoCompleteRequest {
	
	//private String collectionName;
	//private String query;
	//private String filter;
	private String merchantCode;
	private String languageCode;
	
	
	private final static String WILDCARD_QUERY = "wildcard";
	private final static String KEYWORD = "keyword";
	private final static String UNDERSCORE = "_";
	private final static String ALL = "*";
	private final static String TYPE = "type";
	private final static String ANALYZER = "analyzer";
	private final static String STD_ANALYZER = "standard";
	private final static String TYPE_PHRASE = "phrase_prefix";
	private final static String QUERY = "query";
	private final static String MATCH = "match";
	
	public AutoCompleteRequest(String merchantCode, String languageCode) {
		this.merchantCode = merchantCode;
		this.languageCode = languageCode;
	}

	@SuppressWarnings("unchecked")
	public String toJSONString(String query) {

		//{"query": {"match": {"keyword": {"query": "spr","analyzer": "standard"}}}}
		
		
		JSONObject keyword = new JSONObject();
		JSONObject q = new JSONObject();
		JSONObject mq = new JSONObject();
		JSONObject match = new JSONObject();
		
		q.put(QUERY, query);
		//q.put(TYPE, TYPE_PHRASE);
		q.put(ANALYZER, STD_ANALYZER);
		
		keyword.put(KEYWORD, q);
		
		match.put(MATCH, keyword);
		
		mq.put(QUERY, match);
		
		//StringBuilder qValueBuilder = new StringBuilder();
		//qValueBuilder.append(query.toLowerCase()).append(ALL);
		
		//q.put(KEYWORD, qValueBuilder.toString());
		//wildcard.put(WILDCARD_QUERY, q);
		
		
		return mq.toJSONString();
	}
	
	/** keyword_en_default **/
	public String getCollectionName() {
		StringBuilder qBuilder = new StringBuilder();
		qBuilder.append(KEYWORD).append(UNDERSCORE).append(getLanguageCode()).append(UNDERSCORE)
		.append(getMerchantCode());
		
		return qBuilder.toString().toLowerCase();
	}
	
	

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

}
