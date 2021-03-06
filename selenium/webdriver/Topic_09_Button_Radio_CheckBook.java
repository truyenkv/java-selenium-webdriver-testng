package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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
	Alert alert;
	String chromeAuto = System.getProperty("user.dir") + "/autoITScript/authen_chrome.exe";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		javaScript = (JavascriptExecutor) driver;
		// Open url
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// @Test
	public void TC_01_ValidateCurrentUrl() throws InterruptedException {
		// go to fahasha
		driver.get("https://www.fahasa.com/customer/account/create");
		// swtich to Dang nhap
		sleepInSecond(1);
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();

		// Veirfy the Dang Nhap Button
		Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

		sleepInSecond(1);
		// Verify Dang Nhap button is enable
		driver.findElement(By.id("login_username")).sendKeys("truyen@yopmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");

		Assert.assertTrue(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

		// Reload page
		driver.navigate().refresh();

		// delete disable of Login Button
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		jsDeleteDisable("//button[@class='fhs-btn-login']");
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).click();

		// Verify the text
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[text()='Thông tin này không thể để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[text()='Thông tin này không thể để trống']")).isDisplayed());

	}

	// @Test
	public void TC_02_Default_CheckBox_And_Radio_Button() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");

		// click and verify checkbox isSelected
		driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='eq5']")).isSelected());

		// deselect and check
		driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']")).click();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='eq5']")).isSelected());

		// go to new link
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		// click to radio
		driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']")).click();

		// check radio button
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='engine3']")).isSelected());

	}

	// @Test
	public void TC_03_Custome_Radio_CheckBox() throws InterruptedException {
		// got to site
		driver.get("https://material.angular.io/components/radio/examples");
		sleepInSecond(2);
		// click to Radio button and check
		jsClickElement("//div[text()=' Summer ']/preceding-sibling::div/input");
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()=' Summer ']/preceding-sibling::div/input")).isSelected());

		// go to new site
		driver.get("https://material.angular.io/components/checkbox/examples");
		// click to check box
		jsClickElement("//span[text()='Checked']/preceding-sibling::div/input");
		jsClickElement("//span[text()='Indeterminate']/preceding-sibling::div/input");
		sleepInSecond(1);

		// Verify the checkbox isselected()
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::div/input")).isSelected());

		jsClickElement("//span[text()='Checked']/preceding-sibling::div/input");
		jsClickElement("//span[text()='Indeterminate']/preceding-sibling::div/input");
		sleepInSecond(1);
		Assert.assertFalse(driver.findElement(By.xpath("//span[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::div/input")).isSelected());

	}

//	@Test
	public void TC_04_Accept_Alert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// click the button
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(1);
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");

	}

//	@Test
	public void TC_05_Confirm_Alert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// click the button
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(1);
		alert = driver.switchTo().alert();
		alert.dismiss();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");

	}

//	@Test
	public void TC_06_Promop_Alert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// click the button
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(1);
		alert = driver.switchTo().alert();
		alert.sendKeys("Truyen");
		sleepInSecond(2);
		alert.accept();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: Truyen");

	}

	@Test
	public void TC_07_Authenticate() {
		String username = "admin";
		String password = "admin";
		String url = "http://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText(), "Congratulations! You must have the proper credentials.");

	}

	 @Test
	public void TC_08_Authenticate_AutoIt() throws IOException {
		String username = "admin";
		String password = "admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";
		if(driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] {chromeAuto, username, password});
		}
		driver.get(url);

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

	public void jsClickElement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		javaScript.executeScript("arguments[0].click()", element);

	}

}
