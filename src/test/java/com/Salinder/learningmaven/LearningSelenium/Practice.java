package com.Salinder.learningmaven.LearningSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Practice {
	
	WebDriver driver;

	@BeforeMethod
	public void setup() {

		// basic set up to begin with Selenium
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome driver\\chromedriver.exe");

		// opens the browser
		driver = new ChromeDriver();

		// load URL
		driver.get("https://www.youtube.com/");

		// maximize the screen
		driver.manage().window().maximize();

	}

	@Test
	public void test1() {

		String titleOfPage = driver.getTitle();
		String url = driver.getCurrentUrl();
//		String pageSource = driver.getPageSource();
//		System.out.println("Page source is\n\n "+ pageSource);
		System.out.println("Title of page is: " + titleOfPage);
		System.out.println("Captured URL is "+url);
//		Assert.assertEquals(titleOfPage, "Sal", "Title does not match");
//		System.out.println("Captured URL is "+url);
		
		WebElement element = driver.findElement(By.cssSelector("div[class=\"style-scope ytd-topbar-menu-button-renderer\"]"));
		System.out.println("Is Web element displayed " + element.isDisplayed());
		System.out.println("Is Web element enabled " + element.isEnabled());

		element.click();
		System.out.println("Is Web element selected " + element.isSelected());

	}

	@AfterMethod
	public void tearDown() {

		// close the browser

//		driver.close();

	}

}
