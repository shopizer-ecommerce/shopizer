package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class ParentPage {
	protected static WebDriver driver;
	
	// anropa wait istället för att ha kod på flera ställen
	/*
	protected WebElement getElementByXPath(String xpath){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath)));
		//("//*[contains(text(), 'Add to cart')]"));
		
	}
	*/
	//@Before
	public static void setup(){
		System.setProperty("webdriver.gecko.driver", "C:\\Program\\Gecko\\geckodriver.exe");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver = new FirefoxDriver();
	}
}
