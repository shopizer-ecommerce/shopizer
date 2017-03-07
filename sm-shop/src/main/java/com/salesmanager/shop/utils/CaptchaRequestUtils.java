package com.salesmanager.shop.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.business.utils.CoreConfiguration;
import com.salesmanager.shop.constants.ApplicationConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		HttpClient client = HttpClientBuilder.create().build();
	    
	    String url = configuration.getProperty(ApplicationConstants.RECAPTCHA_URL);;

        List<NameValuePair> data = new ArrayList<NameValuePair>();
        data.add(new BasicNameValuePair("secret",  configuration.getProperty(ApplicationConstants.RECAPTCHA_PRIVATE_KEY)));
        data.add(new BasicNameValuePair("response",  gRecaptchaResponse));

    /* NameValuePair[] data = {
                new NameValuePair("secret", configuration.getProperty(ApplicationConstants.RECAPTCHA_PRIVATE_KEY)),
                new NameValuePair("response", gRecaptchaResponse)
              };*/
	    
	    // Create a method instance.
        HttpPost post = new HttpPost(url);
	    post.setEntity(new UrlEncodedFormEntity(data,StandardCharsets.UTF_8));
	    
	    boolean checkCaptcha = false;
	    

	    try {
	      // Execute the method.
            HttpResponse httpResponse = client.execute(post);
            int statusCode = httpResponse.getStatusLine().getStatusCode();

	      if (statusCode != HttpStatus.SC_OK) {
	    	throw new Exception("Got an invalid response from reCaptcha " + url + " [" + httpResponse.getStatusLine() + "]");
	      }

	      // Read the response body.
            HttpEntity entity = httpResponse.getEntity();
            byte[] responseBody =EntityUtils.toByteArray(entity);


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
