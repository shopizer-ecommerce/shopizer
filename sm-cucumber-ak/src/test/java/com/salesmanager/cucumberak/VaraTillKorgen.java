package com.salesmanager.cucumberak;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.ParentPage;

// lägger vara till varukorgen
public class VaraTillKorgen extends ParentPage {
	public static void enVara(WebDriver driver){	
		WebDriverWait w = new WebDriverWait(driver, 50);
		driver.get("http://jenkins2017.westeurope.cloudapp.azure.com:8080/shop/product/Spring-in-Action.html");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250);");
		w.until(ExpectedConditions.presenceOfElementLocated((By.xpath("(//button[@type='button'])[7]"))));
  		driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
  		jse.executeScript("scroll(0, -250);");
	}

	public static void tvaVaror(WebDriver driver)throws InterruptedException{	
		WebDriverWait w = new WebDriverWait(driver, 50);
		driver.get("http://jenkins2017.westeurope.cloudapp.azure.com:8080/shop/product/Spring-in-Action.html");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250);");
		w.until(ExpectedConditions.presenceOfElementLocated((By.xpath("(//button[@type='button'])[7]"))));
  		driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
		Thread.sleep(1000);//!
		driver.get("http://jenkins2017.westeurope.cloudapp.azure.com:8080/shop/product/the-big-switch.html");
		jse.executeScript("scroll(0, 250);");
		w.until(ExpectedConditions.presenceOfElementLocated((By.xpath("(//button[@type='button'])[7]"))));
  		driver.findElement(By.xpath(".//*[@id='input-6']/div/button")).click();
		jse.executeScript("scroll(0, -250);");
		}
	
}
