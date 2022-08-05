package com.Salinder.learningmaven.LearningSelenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverInfo;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstSeleniumTest {

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
		Assert.assertEquals(titleOfPage, "Sal", "Title does not match");
//		System.out.println("Captured URL is "+url);

	}

	@AfterMethod
	public void tearDown() {

		// close the browser

		driver.close();

	}

}
