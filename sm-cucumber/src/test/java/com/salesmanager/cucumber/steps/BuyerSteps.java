package com.salesmanager.cucumber.steps;
import com.salesmanager.cucumber.pages.CurrentPage;
import com.salesmanager.cucumber.pages.Header;
import com.salesmanager.cucumber.pages.HomePage;
import com.salesmanager.cucumber.pages.LogoutPage;
import com.salesmanager.cucumber.pages.RegistrationPage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class BuyerSteps extends ScenarioSteps {
    HomePage homePage;
    RegistrationPage registrationPage;
    CurrentPage currentPage;
    LogoutPage logoutPage;
    Header header;
    

    @Step
    public void opens_home_page() {
        homePage.open();
    }

    @Step
	public void register(String name) {
		registrationPage.register(name);
	}
    
    @Step
	public void submitRegistration() {
		registrationPage.submitRegistration();		
	}

    @Step
    public String read_greeting() {
		return header.readGreeting();
	}
    

    @Step
	public void logout() {
		logoutPage.open();
		
		
	}



}
