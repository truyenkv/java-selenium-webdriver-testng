package webdriver;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_15_16_Wait {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait wait;
	String source = System.getProperty("user.dir");
	String image1 = "image1.jpg";
	String image1Path = source + "/upload/" + image1;
	FluentWait <WebElement> fluenWait;
	FluentWait <WebDriver> fluenWaitDriver;
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);

	}

	// @Test
	public void TC_01_StaticWait() throws InterruptedException {

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		// click the start button
		sleepInSecond(4);
		driver.findElement(By.cssSelector("#start>button")).click();
		sleepInSecond(6);
		Assert.assertTrue(driver.findElement(By.cssSelector("#finish")).isDisplayed());

	}

	// @Test
	public void TC_02_Implicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		Time();
		implicit_Wait_6s();
		driver.findElement(By.cssSelector("#start>button")).click();
		implicit_Wait_6s();
		Assert.assertTrue(driver.findElement(By.cssSelector("#finish")).isDisplayed());
		Time();

	}

	// wait the loading icon - invisibility
	// @Test
	public void TC_03_Explicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		Time();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#start>button")));
		driver.findElement(By.cssSelector("#start>button")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		Assert.assertTrue(driver.findElement(By.cssSelector("#finish")).isDisplayed());
		Time();

	}

	// wait the hello text - visibility
	// @Test
	public void TC_04_Explicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		Time();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#start>button")));
		driver.findElement(By.cssSelector("#start>button")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("finish")));
		Assert.assertTrue(driver.findElement(By.id("finish")).isDisplayed());
		Time();

	}

	// wait calendar
	//@Test
	public void TC_05_Explicit_Wait() throws InterruptedException {
		Time();
		driver.get("https://gofile.io/uploadFiles");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Time();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("dropZoneBtnSelect")));
		// select file
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image1Path);
		// wait until upload button display
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("uploadFiles-btnUpload")));
		driver.findElement(By.id("uploadFiles-btnUpload")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("uploadFiles-uploadRowResult-qrcode")));
		Assert.assertEquals(driver.findElement(By.cssSelector(".alert-success>span")).getText(), "Your files have been successfully uploaded");
		Time();
		// open download link
		driver.findElement(By.id("uploadFiles-uploadRowResult-downloadLink")).click();
	}


	// wait upload
	// @Test
	public void TC_06_Explicit_Wait() throws InterruptedException {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		Time();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("calendarContainer")));
		Assert.assertEquals(driver.findElement(By.cssSelector(".RadAjaxPanel>span")).getText(), "No Selected Dates to display.");
		// click 8
		driver.findElement(By.xpath("//td[@title='Tuesday, September 08, 2020']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		Assert.assertEquals(driver.findElement(By.cssSelector(".RadAjaxPanel>span")).getText(), "Tuesday, September 8, 2020");
		Time();
	}

	//Fluent wait with WebElement 
	//@Test
	public void TC_07_FluentWait() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("javascript_countdown_time")));
		WebElement countTime = driver.findElement(By.id("javascript_countdown_time"));
		
		//fluent wait
		fluenWait = new FluentWait<WebElement>(countTime);
		fluenWait.withTimeout(20, TimeUnit.SECONDS)
			.pollingEvery(100, TimeUnit.MICROSECONDS)
			.ignoring(NoSuchElementException.class)
			.until(new Function<WebElement, Boolean>(){
				public Boolean apply(WebElement element) {
					boolean flag = element.getText().endsWith("00");
					System.out.print(element.getText());
					return flag;
				}
			});
	}
	
	//Fluent wait with webelement
	@Test
	public void TC_08_FluentWait() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#start>button")));
		driver.findElement(By.cssSelector("#start>button")).click();
		Time();
		fluenWaitDriver = new FluentWait<WebDriver>(driver);
		fluenWaitDriver.withTimeout(20, TimeUnit.SECONDS)
			.pollingEvery(100, TimeUnit.MICROSECONDS)
			.ignoring(NoSuchElementException.class);
		
		WebElement Hello = (WebElement) fluenWaitDriver.until(new Function<WebDriver, WebElement>(){

			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.cssSelector("#finish"));
			}
			
		});
		Assert.assertEquals(Hello.getText(), "Hello World!");
		Time();
		
	}

	

	public void sleepInSecond(int i) throws InterruptedException {
		Thread.sleep(i * 1000);
	}

	public void implicit_Wait_6s() {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	public void Time() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
