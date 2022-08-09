package com.Salinder.learningmaven.LearningSelenium;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultipleWindowsSwitch {

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();
	}

	@Test
	public void Test() throws InterruptedException {

		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");

		// command emulating control + enter from keyboard for new tab

		String tab = Keys.chord(Keys.CONTROL, Keys.RETURN);

		// opening libraries option on left in new tab
		WebElement loginOption = driver.findElement(By.cssSelector("div.list-group a:nth-of-type(1)"));
		loginOption.sendKeys(tab);  // using tab string to open in new window
		Thread.sleep(2000);

		WebElement foregtPasswordOption = driver.findElement(By.cssSelector("div.list-group a:nth-of-type(3)"));
		foregtPasswordOption.sendKeys(tab); // using tab string to open in new window
		Thread.sleep(2000); 

		String parentWindowID = driver.getWindowHandle();
		System.out.println(parentWindowID);

		Set<String> allWindowHandles = driver.getWindowHandles();

	
		// referring using page title to switch b/w windows open
		for (String s : allWindowHandles) {

			String title;
			title = driver.switchTo().window(s).getTitle();
			System.out.println(title);
			if (title.equals("Forgot Your Password?")) {
				driver.switchTo().window(s);
				Thread.sleep(2000);
			} else if (title.equals("Account Login")) {
				driver.switchTo().window(s);
				Thread.sleep(2000);
			}

		}
		Thread.sleep(2000);
		driver.switchTo().window(parentWindowID);

	}

	@AfterMethod
	public void teardown() throws InterruptedException {

		Thread.sleep(2000);
		driver.quit();

	}

}
