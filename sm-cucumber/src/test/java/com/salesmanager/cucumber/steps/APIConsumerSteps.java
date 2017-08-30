package com.salesmanager.cucumber.steps;

import com.salesmanager.cucumber.api.CustomerRESTAPI;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;


public class APIConsumerSteps  extends ScenarioSteps {

	
    @Step
	public int postCustomer(String json) throws Throwable {
		CustomerRESTAPI api=new CustomerRESTAPI();
		return api.postCustomer(json);
	}

}
