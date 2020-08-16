package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_locator_selenium {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		//Open url
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		//check 
	}

	@Test
	public void TC_01_() {
		//ID
		driver.findElement(By.id("")).sendKeys("");
		
		//Class
		driver.findElement(By.className("")).sendKeys("");
		
		//Name
		driver.findElement(By.name("")).click();
		
		//linkText
		driver.findElement(By.linkText("")).click();
		
		//partial LinkText
		driver.findElement(By.partialLinkText(""));
		
		//Tag Name
		driver.findElements(By.tagName("")).size();
		
		//CSS
		driver.findElement(By.cssSelector("")).click();
		
		//xpaht
		driver.findElement(By.xpath("")).click();
	}

	public void TC_02_() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
