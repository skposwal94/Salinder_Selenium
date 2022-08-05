package com.Salinder.learningmaven.LearningSelenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Navigation {

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		// basic set up to begin with Selenium
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome driver\\chromedriver.exe");

		// opens the browser
		driver = new ChromeDriver();

		// load URL
//		driver.get("https://www.youtube.com/");

		// maximize the screen
//		driver.manage().window().maximize();

	}

	@Test
	public void test1() {

		driver.get("https://admin-demo.nopcommerce.com/login");
//		driver.get("https://www.amazon.com/");
		
//		driver.navigate().back();
//		driver.navigate().forward();
//		driver.navigate().refresh();
//		
//		driver.navigate().to("https://www.google.com/");
		
//		WebElement element = driver.findElement(By.cssSelector("input#small-searchterms")); //
//		
//		element.sendKeys("xyz");
		
//		List<WebElement> elementList = driver.findElements(By.cssSelector("ul.list > li"));
//		
//		System.out.println("Printing list size "+ elementList.size());
//				
//		for(WebElement e : elementList) {
//			
//			System.out.println(e.getText());
//		}
		
		WebElement element = driver.findElement(By.id("Email")); 
//		element.clear();
//		element.sendKeys("adm123456@gmail.com");
		
		element.getAttribute("value");
		System.out.println("Result is: "+element.getAttribute("value"));
				
		
		

	}

	@AfterMethod
	public void tearDown() {

		// close the browser

//		driver.close();

	}

}
