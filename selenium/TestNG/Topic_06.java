package TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_06 {
	WebDriver driver;
	@Parameters("browser")
	@BeforeClass(alwaysRun= true)
	public void beforeClass(String brownser) {
		if(brownser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
			driver = new ChromeDriver();
		}
		else if(brownser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("hay nhap tring duyet");
		}
		
		// Open url
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	 @Test(groups="run", priority=3)
	public void TC_01_Verify_URL() {
		driver.get("http://live.demoguru99.com/");
		// click My ACCOUNT link at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Verify the my account url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		// Click Create An account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Verify the register url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	 @Test( priority=2)
	public void TC_02_Verify_Title() {
		driver.get("http://live.demoguru99.com/");
		// click My ACCOUNT link at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Verify the login page title
		Assert.assertEquals(driver.getTitle(), "Customer Login");

		// Click Create An account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Verify the create account page title
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	 @Test(groups="run",  priority=1)
	public void TC_03_NavigateFunction() {
		driver.get("http://live.demoguru99.com/");
		// click My ACCOUNT link at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Click Create An account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Verify the register url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

		// Back to Login Page
		driver.navigate().back();

		// Verify the my account url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		// Move to forward Register Page
		driver.navigate().forward();

		// Verify the create account page title
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	 @Test
	public void TC_04_Get_Page_SourceCode() {
		driver.get("http://live.demoguru99.com/");
		// click My ACCOUNT link at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Verify Login page contains text..
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

		// Click Create An account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Verify create an account page contains text
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}

	 @Test
	public void TC_05_Verify_Elemenets_isDisplay() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Verify Email is display?
		if (driver.findElement(By.cssSelector("#mail")).isDisplayed()) {
			driver.findElement(By.cssSelector("#mail")).sendKeys("Automation Testing");
		}

		// Verify Education is display?
		if (driver.findElement(By.cssSelector("#edu")).isDisplayed()) {
			driver.findElement(By.cssSelector("#edu")).sendKeys("Automation Testing");
		}

		// Verify Age under 18 is display?
		if (driver.findElement(By.cssSelector("#over_18")).isDisplayed()) {
			driver.findElement(By.cssSelector("#over_18")).click();
		}

	}

	 @Test( priority=7)
	public void TC_06_Verify_Elemenets_isEnable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Verify Email is enbale?
		if (driver.findElement(By.cssSelector("#mail")).isEnabled()) {
			System.out.println("Email is Enable");
		} else {
			System.out.println("Email is Disable");
		}

		// Verify Education is display?
		if (driver.findElement(By.cssSelector("#edu")).isEnabled()) {
			System.out.println("Education is Enable");
		} else {
			System.out.println("Education is Disable");
		}

		// Verify Age under 18 is display?
		if (driver.findElement(By.cssSelector("#over_18")).isEnabled()) {
			System.out.println("Age is Enable");
		} else {
			System.out.println("Age is Disable");
		}

		// Verify Job Role 1
		if (driver.findElement(By.name("user_job1")).isEnabled()) {
			System.out.println("Job Role 1 is Enable");
		} else {
			System.out.println("Job Role 1 is Disable");
		}

		// Verify Job Role 2
		if (driver.findElement(By.name("user_job2")).isEnabled()) {
			System.out.println("Job Role 2 is Enable");
		} else {
			System.out.println("Job Role 2 is Disable");
		}

		// Verify the Insterests enable?
		if (driver.findElement(By.id("development")).isEnabled()) {
			System.out.println("Development is Enable");
		} else {
			System.out.println("Development is Disable");
		}

		// Verify the Slider 1 enable?
		if (driver.findElement(By.name("slider-1")).isEnabled()) {
			System.out.println("Slider 1 is Enable");
		} else {
			System.out.println("Slider 2 is Disable");
		}

		// Verify the Slider 2 enable?
		if (driver.findElement(By.id("slider-2")).isEnabled()) {
			System.out.println("Slider 2 is Enable");
		} else {
			System.out.println("Slider 2 is Disable");
		}

		// Verify the Password disable?
		if (driver.findElement(By.id("password")).isEnabled()) {
			System.out.println("Password is Enable");
		} else {
			System.out.println("Password is Disable");
		}

		// Verify the Age disable?
		if (driver.findElement(By.id("radio-disabled")).isEnabled()) {
			System.out.println("Age is Enable");
		} else {
			System.out.println("Age is Disable");
		}

	}

	 @Test( priority=5)
	public void TC_07_Verify_Elemenets_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Click age under 18
		driver.findElement(By.cssSelector("#under_18")).click();

		// Click Interests - Devlopment
		driver.findElement(By.id("development")).click();

		// Verify the under 18 is Selected?
		Assert.assertTrue(driver.findElement(By.cssSelector("#under_18")).isSelected());

		// Verify the Insterests is Selected?
		Assert.assertTrue(driver.findElement(By.id("development")).isSelected());

		// unselect the Interes-Development
		driver.findElement(By.id("development")).click();

		// Verify the Development is unselect
		Assert.assertFalse(driver.findElement(By.id("development")).isSelected());

	}

	@Test ( priority=4)
	public void TC_08_Register_MailChimp() throws InterruptedException {
		driver.get("https://login.mailchimp.com/signup/");
		// Input Text to username
		driver.findElement(By.id("email")).sendKeys("kieuvantruyen@yopmail.com");
		// Input Text to Username
		driver.findElement(By.id("new_username")).sendKeys("truyen");
		sendKeyPassword("1");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplay("//li[@class='number-char completed']"));
		Assert.assertFalse(isElementDiable("//button[@id='create-account']"));
		sendKeyPassword("t");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplay("//li[@class='lowercase-char completed']"));
		Assert.assertFalse(isElementDiable("//button[@id='create-account']"));
		sendKeyPassword("T");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplay("//li[@class='uppercase-char completed']"));
		Assert.assertFalse(isElementDiable("//button[@id='create-account']"));
		sendKeyPassword("#");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplay("//li[@class='special-char completed']"));
		Assert.assertFalse(isElementDiable("//button[@id='create-account']"));
		driver.findElement(By.id("new_password")).clear();
		sendKeyPassword("12345678");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplay("//li[@class='8-char completed']"));
		Assert.assertFalse(isElementDiable("//button[@id='create-account']"));
		driver.findElement(By.name("marketing_newsletter")).click();
		Assert.assertTrue(driver.findElement(By.name("marketing_newsletter")).isSelected());;
		
	}

	public void sendKeyPassword(String key) {
		driver.findElement(By.id("new_password")).sendKeys(key);
	}

	public boolean isElementDiable(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isEnabled()) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isElementDisplay(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	@AfterClass(alwaysRun= true)
	public void afterClass() {
		driver.quit();
	}

}
