package com.salesmanager.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.Before;
import cucumber.api.java.After;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import net.thucydides.core.annotations.Steps;

import com.salesmanager.cucumber.pages.HomePage;
import com.salesmanager.cucumber.steps.BuyerSteps;





public class RegisterCustomerSteps {

    @Steps
    BuyerSteps user;

    //HomePage homePage;
    
    //private WebDriver driver;
	//private String baseUrl;
/*
	@Before("@selenium")
	public void setUp() throws Throwable {
		//System.setProperty("webdriver.gecko.driver", "C:\\selenium\\geckodriver.exe");
        //driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();

        //DesiredCapabilities capability = DesiredCapabilities.firefox();
        //capability.setBrowserName("firefox");
        //driver = new RemoteWebDriver( new URL("http://dockerselenium.azurewebsites.net/wd/hub"), capability);
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //driver.manage().window().setSize(new Dimension(1920,1080));
        
	    //baseUrl = "http://bluebottle.westeurope.cloudapp.azure.com:8080";
	    baseUrl = "https://www.ebay.com/";
	}
*/	
	@Given("^the user is logged out$")
	public void the_user_is_logged_out() throws Throwable {
//	    driver.get(baseUrl + "/shop/customer/logout");
	}

	@When("^the user register with \"(.*)\"$")
	public void the_user_register_with(String name) throws Throwable {
		user.opens_home_page();
		user.register(name);
		
/*	    driver.get(baseUrl + "/shop/customer/registration.html");
	    driver.findElement(By.id("firstName")).clear();
	    driver.findElement(By.id("firstName")).sendKeys(name);
	    driver.findElement(By.id("lastName")).clear();
	    driver.findElement(By.id("lastName")).sendKeys("Test");
	    driver.findElement(By.id("hidden_zones")).clear();
	    driver.findElement(By.id("hidden_zones")).sendKeys("Test");
	    driver.findElement(By.id("userName")).clear();	    
	    driver.findElement(By.id("userName")).sendKeys(java.util.UUID.randomUUID().toString());
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("3bitstest@gmail.com");
	    driver.findElement(By.id("password")).clear();
	    String password=java.util.UUID.randomUUID().toString();
	    driver.findElement(By.id("password")).sendKeys(password);
	    driver.findElement(By.id("passwordAgain")).clear();
	    driver.findElement(By.id("passwordAgain")).sendKeys(password);
	    driver.findElement(By.id("submitRegistration")).click();
	    driver.get(baseUrl + "/shop");
*/	}

	@Then("^the user should receive a greeting with \"([^\"]*)\"$")
	public void the_user_should_receive_a_greeting_with(String name) throws Throwable {
		String username = user.read_greeting();
		//String username = driver.findElement(By.xpath("//*[@id='fat-menu']/a")).getText();
		assertEquals(username, "WELCOME " + name.toUpperCase());
	}
/*
	@After("@selenium")
	public void tearDown(){
		driver.quit();
	}
*/	
}
