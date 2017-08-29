package com.salesmanager.cucumber;

import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.junit.Assert.assertEquals;

import com.salesmanager.cucumber.api.CustomerRESTAPI;
import com.salesmanager.cucumber.steps.APIConsumerSteps;

import cucumber.api.java.en.Then;
//import cucumber.api.java.Before;

public class CustomerAPISteps {
	
	@Steps
	APIConsumerSteps apiConsumer;
	
	//CustomerRESTAPI api;
	int status;
			
	
	@When("^I make a POST request on /services/private/\"([^\"]*)\"/customer with '([^']*)'$")
	public void i_make_a_POST_request_on_services_private_customer_with_json(String storeid, String json) throws Throwable {
	    status=apiConsumer.postCustomer(json);
	    		//api.postCustomer(json);
	}

	@Then("^I should receive response \"([0-9]*)\"$")
	public void i_should_receive_response(int response) throws Throwable {
		assertEquals(response, status);
	}
}