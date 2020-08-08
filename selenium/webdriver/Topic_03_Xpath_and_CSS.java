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

	//@Test
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

	//@Test
	public void TC_02_Login_With_Invalid_Email() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("1234@1223");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		String emailerror = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(emailerror, "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	//@Test
	public void TC_03_Login_With_Invalid_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("12345");
		driver.findElement(By.id("send2")).click();
		String passlerror = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(passlerror, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	
	//@Test
	public void TC_04_Login_With_Incorrect_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automatio@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();
		String error = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(error, "Invalid login or password.");
	}
	
	//@Test
	public void TC_05_Login_With_Valid_Account() {
		String fullName = "Automation Testing";
		String email = "automation_13@gmail.com";
		
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.name("login[password]")).sendKeys("123123");
		driver.findElement(By.id("send2")).click();
		
		//cach1, tuyet doi
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(),"MY DASHBOARD");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']/h1[text()='My Dashboard']")).isDisplayed());
		
		//cach2, tuong doi
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']/h1[contains(text(), 'My Dashboard')]")).isDisplayed());
		
		//cach2 tuong doi
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']/strong")).getText(), "Hello, "+fullName+"!");
		
		
		//Tuong doi
		String contact = driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']/p")).getText();
		Assert.assertTrue(contact.contains(fullName));
		Assert.assertTrue(contact.contains(email));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']/p[contains(.,'"+fullName+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']/p[contains(.,'"+email+"')]")).isDisplayed());
		
		//logout 
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//li[last()]")).click();
	}

	@Test
	public void TC_06_Register_Account() {
		String firstName = "Truyen";
		String lastName = "Kieu";
		String email = "automation"+ random() +"@gmail.com";
		
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//click to create account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//input firstname
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		
		//input lastname
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		
		//input email
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
		
		//input password
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123123");
		
		//input confirm password
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123123");
		
		//click register
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		//verify message
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText().contains("Thank you for registering with Main Website Store."));
		String verify = driver.findElement(By.xpath("//div[@class='col-1']//p")).getText();
		Assert.assertTrue(verify.contains(firstName+ " "+lastName));
		Assert.assertTrue(verify.contains(email));
		//logout 
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//li[last()]")).click();
		
	}
	
	public int random() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
