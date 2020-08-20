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

public class Topic_09_Button_Radio_CheckBook {
	WebDriver driver;
	JavascriptExecutor javaScript;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		javaScript = (JavascriptExecutor) driver;
		//Open url
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_ValidateCurrentUrl() throws InterruptedException {
		//go to fahasha
		driver.get("https://www.fahasa.com/customer/account/create");
		//swtich to Dang nhap
		sleepInSecond(1);
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		
		//Veirfy the Dang Nhap Button
		Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
		
		sleepInSecond(1);
		//Verify Dang Nhap button is enable
		driver.findElement(By.id("login_username")).sendKeys("truyen@yopmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		
		Assert.assertTrue(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());
		
		//Reload page
		driver.navigate().refresh();
		
		//delete disable of Login Button
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		jsDeleteDisable("//button[@class='fhs-btn-login']");
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).click();
		
		//Verify the text
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[text()='Thông tin này không thể để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[text()='Thông tin này không thể để trống']")).isDisplayed());
		
	}

	//@Test
	public void TC_02_ValidatePageTitle() {
		
	}

	//@Test
	public void TC_03_LoginFormDisplayed() {
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(int time) throws InterruptedException {
		Thread.sleep(time * 1000);
	}
	
	public void jsDeleteDisable(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		javaScript.executeScript("arguments[0].removeAttribute('disabled')", element);
		
	}
}
