package com.Salinder.learningmaven.LearningSelenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DropDown {

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

	}

	@Test
	public void test1() throws InterruptedException {
		
		driver.get("https://www.google.com/");
		
		driver.findElement(By.cssSelector("input.gLFyf ")).sendKeys("selenium");
		Thread.sleep(3000);

		List<WebElement> autoSearch = driver.findElements(By.cssSelector("div.mkHrUc  div.OBMEnb  li div.wM6W7d span"));
		
//		Select country = new Select(countryList);
		
//		country.selectByVisibleText("Denmark");
//		country.selectByIndex(8);
//		country.selectByVisibleText("Canada");
//		
//		List<WebElement> listOfCountries = country.getOptions();
//		 
//		System.out.println(listOfCountries);
//		
//		for(WebElement w : listOfCountries) {
//			System.out.println(w.getText());
//		}
		System.out.println("Auto fill options "+autoSearch.size());
		
		for (WebElement element: autoSearch) {
			System.out.println(element.getText());
		}
		
	}

	@AfterMethod
	public void tearDown() {

		// close the browser

//		driver.close();

	}

}
