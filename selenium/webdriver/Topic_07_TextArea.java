package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextArea {
	WebDriver driver;
	String email, userID, pass;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//check 
	}

	@Test
	public void TC_01_Get_Account() {
		//go to get username account
		email = "truyen" + random() +"yopmail.com";
		driver.get("http://demo.guru99.com/");
		
		//Input email and send
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		
		//store UserID and PASS
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		pass = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
	}

	@Test
	public void TC_02_Login_And_Verify_Home_Screen() {
		//go to home screen
		driver.get("http://demo.guru99.com/v4/");
		
		//Login with valid username and password
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(By.name("btnLogin")).click();
		
		//Verify the item on Home page
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText(), "Manger Id : "+userID);
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		
	}

	//@Test
	public void TC_03_LoginFormDisplayed() {
		
	}
	
	public int random() {
		Random rand = new Random();
		return rand.nextInt(100000);
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
