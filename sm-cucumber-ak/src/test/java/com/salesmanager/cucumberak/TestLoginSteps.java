package com.salesmanager.cucumberak;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.Before;
import cucumber.api.java.After;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.ParentPage;

public class TestLoginSteps{
	private WebDriver driver;
	private String baseUrl;
	
	@Before("@selenium2")
	public void setUp() throws Throwable {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		driver = new RemoteWebDriver( new URL("http://dockerselenium.azurewebsites.net/wd/hub"), capability);
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920,1080));
		baseUrl = "http://jenkins2017.westeurope.cloudapp.azure.com:8080";
		System.out.println("Login before");
	}

	@Given("^the user is logged out$")
	public void the_user_is_logged_out() throws Throwable {
		System.out.println("login given");
	    driver.get(baseUrl + "/shop/customer/logout");
	    Thread.sleep(3000);//! 
	}	
	
	@When("^the user enters username \"(.*)\"$")
	public void inlogg(String namn) throws InterruptedException {
		System.out.println("login when");
		  driver.get("http://jenkins2017.westeurope.cloudapp.azure.com:8080/shop/");
		  Thread.sleep(3000);//! 
	      driver.findElement(By.cssSelector("#customerAccount > button.btn.dropdown-toggle")).click();
	      driver.findElement(By.id("signin_userName")).clear();
	      driver.findElement(By.id("signin_userName")).sendKeys(namn);
	      driver.findElement(By.id("signin_password")).clear();
	      driver.findElement(By.id("signin_password")).sendKeys("elsapi");
	      driver.findElement(By.id("login-button")).click();
   	      Thread.sleep(3000);//! 
		}
	
	@Then("^the user should recive a greeting message with \"([^\"]*)\"$")
	public void loginTest(String message)throws InterruptedException{ 
 		WebDriverWait w = new WebDriverWait(driver, 50);
	    //Thread.sleep(1000);//! 
	    w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@id='customerAccount']/button")));
	    WebElement statusLogin= driver.findElement(By.xpath(".//*[@id='customerAccount']/button"));
	    assertEquals(message,  statusLogin.getText());
	    //assertEquals("WELCOME 'ANNA'",  statusLogin.getText());
	    System.out.println("Test 2, inloggning: "+ message +  statusLogin.getText());
	    //driver.quit();
	}


	@After("@selenium2")
	public void tearDown(){
		driver.quit();
	}
}
