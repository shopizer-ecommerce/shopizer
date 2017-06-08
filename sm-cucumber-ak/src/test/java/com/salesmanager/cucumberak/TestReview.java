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

public class TestReview {
	private WebDriver driver;
	private String baseUrl;

	@Before("@selenium10")
	public void setUp() throws Throwable {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		driver = new RemoteWebDriver( new URL("http://dockerselenium.azurewebsites.net/wd/hub"), capability);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920,1080));
		baseUrl = "http://jenkins2017.westeurope.cloudapp.azure.com:8080";
		System.out.println("Labb4_10 WriteReview, Before" );
	}

	@Given("^Select product \"([^\"]*)\"$")
	public void select_product(String arg1) throws Throwable {
		  driver.get(baseUrl + "/shop/customer/logout");
		  driver.get(baseUrl + "/shop/");
		  Thread.sleep(2000);//! 
	      driver.findElement(By.cssSelector("#customerAccount > button.btn.dropdown-toggle")).click();
	      driver.findElement(By.id("signin_userName")).clear();
	      driver.findElement(By.id("signin_userName")).sendKeys("annapanna");
	      driver.findElement(By.id("signin_password")).clear();
	      driver.findElement(By.id("signin_password")).sendKeys("elsapi");
	      driver.findElement(By.id("login-button")).click();
	      Thread.sleep(3000);//! 
	      driver.findElement(By.xpath("//div[@id='pageContainer']/div[2]/div[3]/div[2]/div/div[2]/div/div/a/img")).click();
	      System.out.println("Labb4_10 WriteReview, Given" );
	}

	@When("^I write rewview \"([^\"]*)\"$")
	public void i_write_rewview(String arg1) throws Throwable {
		WebDriverWait w = new WebDriverWait(driver, 30);
		driver.findElement(By.linkText("Customer review(s)")).click();
	    w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("reviewButton")));
	    driver.findElement(By.id("reviewButton")).click();
	    driver.findElement(By.id("description")).clear();
	    driver.findElement(By.id("description")).sendKeys("Bra bok");
	    driver.findElement(By.cssSelector("#review > button.btn")).click(); 
	    System.out.println("Labb4_10 WriteReview, When" );
	}

	@Then("^Check writed review$")
	public void check_writed_review() throws Throwable {
	    WebElement reviewBook = driver.findElement(By.xpath(" .//*[@id='h2tab3']/p"));
	    assertEquals("Bra bok", reviewBook.getText());
		System.out.println("Labb4_10 WriteReview, Then" );
	}
	
	@After("@selenium10")
	public void tearDown(){
		driver.quit();
	}
	
	
/*	
	public static void reviewTest(WebDriver driver) throws InterruptedException{
		WebDriverWait w = new WebDriverWait(driver, 30);
		Login.startLogin(driver);
	      Thread.sleep(1000);//!
	      driver.findElement(By.xpath("//div[@id='pageContainer']/div[2]/div[3]/div[2]/div/div[2]/div/div/a/img")).click();
	      driver.findElement(By.linkText("Customer review(s)")).click();
	      w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("reviewButton")));
	      driver.findElement(By.id("reviewButton")).click();
	      driver.findElement(By.id("description")).clear();
	      driver.findElement(By.id("description")).sendKeys("Bra bok");
	      driver.findElement(By.cssSelector("#review > button.btn")).click(); 
	      WebElement reviewBook = driver.findElement(By.xpath(" .//*[@id='h2tab3']/p"));
	  	  assertEquals("Bra bok", reviewBook.getText());
	      System.out.println("Test 10, review");
	      driver.quit();
	 }
*/
}
