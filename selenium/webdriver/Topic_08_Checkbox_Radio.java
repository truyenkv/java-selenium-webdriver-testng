package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	// Checkbox
	By firstCheckbox = By.xpath("//input[@value='Anemia']");
	By secondCheckbox = By.xpath("//input[@value='Asthma']");
	By thirdCheckbox = By.xpath("//input[@value='Arthritis']");
	By allChechboxes=By.xpath("//input[@type='checkbox']");

	// Radio
	By firtRadio = By.xpath("//input[@value='3-4 days']");
	By secondRadio = By.xpath("//input[@value='I have a strict diet']");

	//@Test
	public void TC_01_Button() throws InterruptedException {
		driver.get("https://www.fahasa.com/customer/account/create");
		// Navigate to Login Tab
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		WebElement loginButton = driver.findElement(By.cssSelector(".fhs-btn-login"));

		// Verify login to Button is Disable
		boolean status = loginButton.isEnabled();
		System.out.println("Login Status= " + status);
		Assert.assertFalse(status);

		// Input to email and password
		driver.findElement(By.cssSelector("#login_username")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("123456");
		sleepInSecond(2);

		// Verify login to Button is Enable
		status = loginButton.isEnabled();
		System.out.println("Login Status= " + status);
		Assert.assertTrue(status);
		// Click to Login Button
		loginButton.click();

		String errorMessage = driver.findElement(By.cssSelector(".fhs-login-msg")).getText();
		Assert.assertEquals(errorMessage, "Số điện thoại/Email hoặc Mật khẩu sai!");
		loginButton.click();
		/*
		 * driver.navigate().refresh(); sleepInSecond(2); driver.findElement(By.cssSelector("li.popup-login-tab-login")).click(); loginButton = driver.findElement(By.cssSelector(".fhs-btn-login"));
		 * sleepInSecond(5);
		 * 
		 * // Verify login to Button is Disable status = loginButton.isEnabled(); System.out.println("Login Status= " + status); Assert.assertFalse(loginButton.isEnabled());
		 * removeDisableAttribute(loginButton); sleepInSecond(2); loginButton.click(); sleepInSecond(2);
		 * Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_username']/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");
		 * Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_password']/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");
		 */	}

	@Test
	public void TC_02_Default_Radio_Checkbox() throws InterruptedException {
		driver.get("https://automationfc.github.io/multiple-fields/");

		// Verify 3 checkbox + 2 radio dau tien la deselected
		Assert.assertFalse(driver.findElement(firstCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(secondCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(thirdCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(firtRadio).isSelected());
		Assert.assertFalse(driver.findElement(secondRadio).isSelected());

		// Click to 3 first checkbox + 2 radio
		driver.findElement(firstCheckbox).click();
		driver.findElement(secondCheckbox).click();
		driver.findElement(thirdCheckbox).click();
		driver.findElement(firtRadio).click();
		driver.findElement(secondRadio).click();
		sleepInSecond(5);
		// Verify 3 checkbox + 2 radio dau tien la selected
		Assert.assertTrue(driver.findElement(firstCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(secondCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(thirdCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(firtRadio).isSelected());
		Assert.assertTrue(driver.findElement(secondRadio).isSelected());
		
		driver.navigate().refresh();
		
		//Click to all Checkbox
		List<WebElement>Checkboxes=driver.findElements(allChechboxes);
		//Select (Chechbox/radio)
		for(WebElement checkbox:Checkboxes) {
			checkbox.click();
			Thread.sleep(2000);
			Assert.assertTrue(checkbox.isSelected());
		}
		//Verify selected
		//for(WebElement checkbox:Checkboxes)
		//{
		//	Assert.assertTrue(checkbox.isSelected());
			
		//}
		//Deselected
		for(WebElement checkbox:Checkboxes) {
			checkbox.click();
			Thread.sleep(500);
		}
		//Verify deselected
		for(WebElement checkbox:Checkboxes) {
			Assert.assertFalse(checkbox.isSelected());
			
		}
		
	}

	//@Test
	public void TC_03_Custom_Radio_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkedCheckbox = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::div/input");
		// The div-->Click
		clickByJavascript(driver.findElement(checkedCheckbox));
		sleepInSecond(5);
		// Verify Checked checkbox is selected
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickByJavascript(WebElement element) {
		jsExecutor.executeScript("arguments[0].click()", element);

	}
	 public void removeDisableAttribute(WebElement element) {
		   jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
		  
	  }
	@AfterClass
	public void afterClass() {
		driver.quit();

	}

}
