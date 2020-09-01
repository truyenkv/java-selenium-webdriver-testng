package webdriver;

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

public class Template {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_JavaExecutor() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		
		//compare domain
		Assert.assertEquals(ExcuteBrowser("return document.domain;"), "live.demoguru99.com");
		
		//compare url
		Assert.assertEquals(ExcuteBrowser("return document.URL;"), "http://live.demoguru99.com/"); 
		sleep(2);
		//Click on Element
		ClickElement(driver.findElement(By.xpath("//a[text()='Mobile']")));
		
		//add samsung to card
		ClickElement(driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button")));
		sleep(1);
		//compare string
		Assert.assertTrue(getTextJS().contains("Samsung Galaxy was added to your shopping cart."));
		
		//open service tab
		ClickElement(driver.findElement(By.xpath("//a[text()='Customer Service']")));
		
		//verify title
		Assert.assertEquals(ExcuteBrowser("return document.title;"), "Customer Service");
		
		srollToEleement(driver.findElement(By.xpath("//input[@type='email']")));
		
	}

	@Test
	public void TC_02_DeleAttribute() {
		driver.get("http://demo.guru99.com/v4/");
	}

	//@Test
	public void TC_03_LoginFormDisplayed() {
		
	}
	
	public void sleep(int i) throws InterruptedException {
		Thread.sleep(i * 1000);
		
	}
	
	public void srollToEleement(WebElement element) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public Object ExcuteBrowser(String command) {
		return jsExecutor.executeScript(command);
	}
	
	public void ClickElement(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public String getTextJS() {
		return (String) jsExecutor.executeScript("return document.documentElement.getInnerText();");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
