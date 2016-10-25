package com.salesmanager.web.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.utils.CoreConfiguration;
import com.salesmanager.web.constants.ApplicationConstants;

/**
 * Creates a request to reCaptcha 2
 * https://www.google.com/recaptcha/api/siteverify
 * Throws an exception if it can't connect to reCaptcha
 * returns true or false if validation has passed
 * @author carlsamson
 *
 */
@Component
public class CaptchaRequestUtils {
	
	@Inject
	private CoreConfiguration configuration; //for reading public and secret key
	
	private static final String SUCCESS_INDICATOR = "success";
	
	public boolean checkCaptcha(String gRecaptchaResponse) throws Exception {
		
	    HttpClient client = new HttpClient();
	    
	    String url = configuration.getProperty(ApplicationConstants.RECAPTCHA_URL);;

        NameValuePair[] data = {
                new NameValuePair("secret", configuration.getProperty(ApplicationConstants.RECAPTCHA_PRIVATE_KEY)),
                new NameValuePair("response", gRecaptchaResponse)
              };
	    
	    // Create a method instance.
	    PostMethod post = new PostMethod(url);
	    post.setRequestBody(data);
	    
	    boolean checkCaptcha = false;
	    

	    try {
	      // Execute the method.
	      int statusCode = client.executeMethod(post);

	      if (statusCode != HttpStatus.SC_OK) {
	    	throw new Exception("Got an invalid response from reCaptcha " + url + " [" + post.getStatusLine() + "]");
	      }

	      // Read the response body.
	      byte[] responseBody = post.getResponseBody();

	      // Deal with the response.
	      // Use caution: ensure correct character encoding and is not binary data
	      //System.out.println(new String(responseBody));
	      
	      String json = new String(responseBody);
	      
	      Map<String,String> map = new HashMap<String,String>();
	  	  ObjectMapper mapper = new ObjectMapper();
	  	  
	  	  map = mapper.readValue(json, 
			    new TypeReference<HashMap<String,String>>(){});
	  	  
	  	  String successInd = map.get(SUCCESS_INDICATOR);
	  	  
	  	  if(StringUtils.isBlank(successInd)) {
	  		  throw new Exception("Unreadable response from reCaptcha " + json);
	  	  }
	  	  
	  	  Boolean responseBoolean = Boolean.valueOf(successInd);
	  	  
	  	  if(responseBoolean) {
	  		checkCaptcha = true;
	  	  }
	  	  
	  	  return checkCaptcha;

	    } finally {
	      // Release the connection.
	      post.releaseConnection();
	    }  
	  }


}
