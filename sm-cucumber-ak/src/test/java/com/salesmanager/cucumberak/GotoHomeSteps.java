
package com.salesmanager.cucumberak;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GotoHomeSteps{
	private WebDriver driver;
	private String baseUrl;
	
	@Before("@selenium1")
	public void setUp() throws Throwable {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		driver = new RemoteWebDriver( new URL("http://dockerselenium.azurewebsites.net/wd/hub"), capability);
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920,1080));
		baseUrl = "http://jenkins2017.westeurope.cloudapp.azure.com:8080";
		System.out.println("GoToHome before");
	}
		
	@Given("^I am on the productpage$")
	public void productsPage() throws Throwable{
		System.out.println("GoToHome given");
		WebDriverWait w = new WebDriverWait(driver, 30);
		driver.get(baseUrl + "/shop/product/the-big-switch.html");
	    Thread.sleep(3000);//! 
		w.until(ExpectedConditions.elementToBeClickable(By.linkText("Home")));
	}
	
	@When("^click on the house symbol$")
	public void homeByHouse() throws Throwable { 
		System.out.println("GoToHome when");
		driver.findElement(By.linkText("Default store")).click();
        Thread.sleep(3000);
	}
	
	@Then("^I will be redirected to startpage$")
	public void assertHomeByHouse() throws Throwable {
		System.out.println("Test 1, sidan som visades: " + driver.getTitle());
		assertEquals("Shopizer Demo - Default store",  driver.getTitle());
		//driver.quit();
	}
	@After("@selenium1")
	public void tearDown(){
		driver.quit();
	}
}
	
