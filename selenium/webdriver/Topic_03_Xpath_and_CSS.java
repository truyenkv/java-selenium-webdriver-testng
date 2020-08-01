package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_and_CSS {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		//Open url
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//check 
	}

	@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		String emailerror = driver.findElement(By.id("advice-required-entry-email")).getText();
		String passlerror = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(emailerror, "This is a required field.");
		Assert.assertEquals(passlerror, "This is a required field.");
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("1234@1223");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		String emailerror = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(emailerror, "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	@Test
	public void TC_03_Login_With_Invalid_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("12345");
		driver.findElement(By.id("send2")).click();
		String passlerror = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(passlerror, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	
	@Test
	public void TC_02_Login_With_Incorrect_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automatio@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();
		String error = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(error, "Invalid login or password.");
	}
		

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
