package com.salesmanager.lambdas.test;

import com.amazonaws.services.lambda.runtime.Context;

public class ShopizerTestLambda {
	

	    public String myHandler(Context context) {
	    	
	    	System.out.println("Into my method myHandler");
	    	
	    	String response = "{'statusCode': 200,'headers':{'Access-Control-Allow-Origin':'*'},'body': 'Shopizer Test Lambda'}";
	    	
	        return response;
	    }

}
