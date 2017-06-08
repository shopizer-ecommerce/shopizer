package com.salesmanager.cucumberak;

import org.openqa.selenium.WebDriver;

import pages.ParentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestLogOut {
	private WebDriver driver;
	private String baseUrl;

	@Before("@selenium7")
	public void setUp() throws Throwable {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		driver = new RemoteWebDriver( new URL("http://dockerselenium.azurewebsites.net/wd/hub"), capability);
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920,1080));
		baseUrl = "http://jenkins2017.westeurope.cloudapp.azure.com:8080";
		System.out.println("Labb4_7 LogOut, Before" );
	}

	@Given("^user logged in as \"([^\"]*)\"$")
	public void user_loggd_in(String arg1) throws Throwable {
		  driver.get(baseUrl + "/shop/customer/logout");
		  driver.get(baseUrl + "/shop/");
		  Thread.sleep(2000);//! 
	      driver.findElement(By.cssSelector("#customerAccount > button.btn.dropdown-toggle")).click();
	      driver.findElement(By.id("signin_userName")).clear();
	      driver.findElement(By.id("signin_userName")).sendKeys(arg1);
	      driver.findElement(By.id("signin_password")).clear();
	      driver.findElement(By.id("signin_password")).sendKeys("elsapi");
	      driver.findElement(By.id("login-button")).click();
 	      Thread.sleep(3000);//! 
 	      System.out.println("Labb4_7 LogOut, Given" );
	}

	@When("^I logging out$")
	public void i_logging_out() throws Throwable {
		Thread.sleep(1000);//!
		driver.findElement(By.cssSelector("#customerAccount > button.btn.dropdown-toggle")).click();
		Thread.sleep(1000);//!
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);//!
		System.out.println("Labb4_7 LogOut, When" );
	}

	@Then("^user is not logged in$")
	public void user_is_not_logged_in() throws Throwable {
		Thread.sleep(1000);//!
		WebElement statusLogin= driver.findElement(By.id("customerAccount"));
		assertEquals("SIGNIN",  statusLogin.getText());
		System.out.println("Labb4_7 LogOut, Then" );
	}

	@After("@selenium7")
	public void tearDown(){
		driver.quit();
	}

	
/*	
	public static void logOutTest(WebDriver driver)throws InterruptedException{
		Thread.sleep(1000);//!
		driver.findElement(By.cssSelector("#customerAccount > button.btn.dropdown-toggle")).click();
		Thread.sleep(1000);//!
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);//!
		WebElement statusLogin= driver.findElement(By.id("customerAccount"));
		assertEquals("SIGNIN",  statusLogin.getText());
		System.out.println("Test 7, logga ut: " + statusLogin.getText());
		driver.quit();
		}
*/
}
	