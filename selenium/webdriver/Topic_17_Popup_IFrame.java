package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Popup_IFrame {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Fixed_Popop() throws InterruptedException {
		driver.get("https://www.zingpoll.com/");
		sleepInSecond(3);
		//click to signIn button
		driver.findElement(By.cssSelector("#Loginform")).click();
		sleepInSecond(3);
		WebElement closeBtn = driver.findElement(By.cssSelector("#Login .close"));
		
		//check popup is displayed
		Assert.assertTrue(closeBtn.isDisplayed());
		sleepInSecond(3);
		
		//close popup
		closeBtn.click();
		sleepInSecond(1);
		//check popup is close
		Assert.assertFalse(closeBtn.isDisplayed());
		
	}

	//@Test
	public void TC_02_Fixed_Popuop() throws InterruptedException {
		driver.get("https://bni.vn/");
		sleepInSecond(3);
		//check popup display
		if(driver.findElement(By.xpath("//strong[text()='WELCOME TO BNI VIETNAM']")).isDisplayed()) {
			driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();
		}
		
	}

//	@Test
	public void TC_03_Randome_Popup() throws InterruptedException {
		driver.get("https://blog.testproject.io/");
		
		sleepInSecond(2);
		driver.findElement(By.cssSelector("#search-2 .search-field")).clear();
		driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("#search-2 .glass")).click();
		sleepInSecond(3);
		//declare title
		List<WebElement> allTitle = driver.findElements(By.cssSelector(".post-on-archive-page .post-title"));
		for(WebElement title: allTitle) {
			String tem = title.getText().trim();
			Assert.assertTrue(tem.contains("Selenium"));
		}
		
	}
	
	@Test
	public void TC_04_iFrame() throws InterruptedException {
		driver.get("https://kyna.vn/");
		
		//Verify fb frame hien thi
		if(driver.findElement(By.xpath("//a[@title='Close']")).isDisplayed()) {
			System.out.println("Co hien thi popup");
			driver.findElement(By.xpath("//a[@title='Close']")).click();
		}
		sleepInSecond(3);
		//switch to frame
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
	 	WebElement like =  driver.findElement(By.cssSelector("div.lfloat div._1drq"));
	 	Assert.assertTrue(like.isDisplayed());
		//Verify the text
	 	Assert.assertEquals(like.getText(), "169K likes");
	 	
	 	driver.switchTo().defaultContent();
	 	
	 	sleepInSecond(3);
	 	driver.switchTo().frame("cs_chat_iframe");
	 	
	 	//check is dispplay
	 	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='button_text']")).isDisplayed());
	 	sleepInSecond(3);
	 	//open chat
	 	driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();
	 	sleepInSecond(1);
	 	
	 	//input
	 	driver.findElement(By.cssSelector("input.input_name")).sendKeys("truyen");
	 	driver.findElement(By.cssSelector("input.input_email")).sendKeys("truyen@yopmail.com");
	 	driver.findElement(By.cssSelector("input.input_phone")).sendKeys("098898899");
	 	driver.findElement(By.cssSelector("input.submit")).click();
	 	
	 	driver.switchTo().defaultContent();
	 	sleepInSecond(2);
	 	driver.findElement(By.id("live-search-bar")).sendKeys("java");
	 	sleepInSecond(1);
	 	driver.findElement(By.className("search-button")).click();
	 	
	 	List<WebElement> title = driver.findElements(By.cssSelector("ul#w0>li.col-xl-4"));
	 	for(WebElement tit: title) {
	 		Assert.assertTrue(tit.getText().trim().contains("Java"));
	 	}
	 	
	}
	
	
	
	public void sleepInSecond(int sec) throws InterruptedException {
		Thread.sleep(sec*1000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
