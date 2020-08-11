package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Webdriver_API {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		//Open url
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/");
		//check 
	}

	@Test
	public void TC_01_Verify_URL() {
		//click My ACCOUNT link at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Verify the my account url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		//Click Create An account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Verify the register url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Verify_Title() {
		//click My ACCOUNT link at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Verify the login page title
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		//Click Create An account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();		
		
		//Verify the create account page title
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}

	@Test
	public void TC_03_NavigateFunction() {
		//click My ACCOUNT link at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Click Create An account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();		
		
		//Verify the register url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		
		//Back to Login Page
		driver.navigate().back();
		
		//Verify the my account url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		//Move to forward Register Page
		driver.navigate().forward();
		
		//Verify the create account page title
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
		
	}
	
	@Test
	public void TC_04_Get_Page_SourceCode() {
		//click My ACCOUNT link at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Verify Login page contains text..
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		//Click Create An account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();		
				
		//Verify create an account page contains text 
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
