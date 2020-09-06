package webdriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_16_Wait {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	// @Test
	public void TC_02_StaticWait() throws InterruptedException {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		// click the start button
		sleepInSecond(4);
		driver.findElement(By.cssSelector("#start>button")).click();
		sleepInSecond(6);
		Assert.assertTrue(driver.findElement(By.cssSelector("#finish")).isDisplayed());

	}

	@Test
	public void TC_02_Implicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		Time();
		implicit_Wait_6s();
		driver.findElement(By.cssSelector("#start>button")).click();
		implicit_Wait_6s();
		Assert.assertTrue(driver.findElement(By.cssSelector("#finish")).isDisplayed());
		Time();

	}

	// @Test
	public void TC_03_LoginFormDisplayed() {

	}

	public void sleepInSecond(int i) throws InterruptedException {
		Thread.sleep(i * 1000);
	}

	public void implicit_Wait_6s() {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	public void Time() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
