package webdriver;

import java.util.List;
import java.util.Set;
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

public class Topic_11_12_Popup_IFrame_Window {
	WebDriver driver;
	JavascriptExecutor jsExcutor;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		jsExcutor = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC_01_Fixed_Popop() throws InterruptedException {
		driver.get("https://www.zingpoll.com/");
		sleepInSecond(3);
		// click to signIn button
		driver.findElement(By.cssSelector("#Loginform")).click();
		sleepInSecond(3);
		WebElement closeBtn = driver.findElement(By.cssSelector("#Login .close"));

		// check popup is displayed
		Assert.assertTrue(closeBtn.isDisplayed());
		sleepInSecond(3);

		// close popup
		closeBtn.click();
		sleepInSecond(1);
		// check popup is close
		Assert.assertFalse(closeBtn.isDisplayed());

	}

	// @Test
	public void TC_02_Fixed_Popuop() throws InterruptedException {
		driver.get("https://bni.vn/");
		sleepInSecond(3);
		// check popup display
		if (driver.findElement(By.xpath("//strong[text()='WELCOME TO BNI VIETNAM']")).isDisplayed()) {
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
		// declare title
		List<WebElement> allTitle = driver.findElements(By.cssSelector(".post-on-archive-page .post-title"));
		for (WebElement title : allTitle) {
			String tem = title.getText().trim();
			Assert.assertTrue(tem.contains("Selenium"));
		}

	}

//	@Test
	public void TC_04_iFrame() throws InterruptedException {
		driver.get("https://kyna.vn/");

		// Verify fb frame hien thi
		if (driver.findElement(By.xpath("//a[@title='Close']")).isDisplayed()) {
			System.out.println("Co hien thi popup");
			driver.findElement(By.xpath("//a[@title='Close']")).click();
		}
		sleepInSecond(3);
		// switch to frame
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		WebElement like = driver.findElement(By.cssSelector("div.lfloat div._1drq"));
		Assert.assertTrue(like.isDisplayed());
		// Verify the text
		Assert.assertEquals(like.getText(), "169K likes");

		driver.switchTo().defaultContent();

		sleepInSecond(3);
		driver.switchTo().frame("cs_chat_iframe");

		// check is dispplay
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='button_text']")).isDisplayed());
		sleepInSecond(3);
		// open chat
		driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();
		sleepInSecond(1);

		// input
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
		for (WebElement tit : title) {
			Assert.assertTrue(tit.getText().trim().contains("Java"));
		}
	}

	// @Test
	public void TC_05_Window_Tab() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// click to google and switch to new tab
		String pageCurrent = driver.getWindowHandle();
		System.out.print(pageCurrent);
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		SwithTabById(pageCurrent);
		sleepInSecond(1);

		// check the title is google
		Assert.assertEquals(driver.getTitle(), "Google");

		// Swith
		String child = driver.getWindowHandle();
		sleepInSecond(1);
		SwithTabById(child);

		// click on FB
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

		// check the new titl facebook
		sleepInSecond(1);
		SwithTabByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");

		// switch back parent
		driver.switchTo().window(pageCurrent);
		sleepInSecond(1);

		// click tiki
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();

		// check title of tiki
		SwithTabByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");

		// close all windown except parent
		CloseAllTabExcept(pageCurrent);

		// check comback windown parent with title
		driver.switchTo().window(pageCurrent);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
	}

	//@Test
	public void TC_06_Window_Tab() throws InterruptedException {
		driver.get("https://kyna.vn/");
		// Verify fb frame hien thi
		if (driver.findElement(By.xpath("//a[@title='Close']")).isDisplayed()) {
			System.out.println("Co hien thi popup");
			driver.findElement(By.xpath("//a[@title='Close']")).click();
		}
		sleepInSecond(3);
		// click all element in the footer
		// facebook
		String parent = driver.getWindowHandle();
		clickByJS("//div[@class='social']/a[1]");
		sleepInSecond(1);
		SwithTabByTitle("Kyna.vn - Trang chủ | Facebook");
		sleepInSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
		driver.switchTo().window(parent);
		sleepInSecond(2);
		
		// youtube
		clickByJS("//div[@class='social']/a[2]");
		sleepInSecond(1);
		SwithTabByTitle("(68) Kyna.vn - YouTube");
		sleepInSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");
		driver.switchTo().window(parent);
		sleepInSecond(2);
		
		// zalo
		clickByJS("//div[@class='social']/a[3]");
		sleepInSecond(1);
		SwithTabByTitle("Kyna.vn");
		sleepInSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), "https://zalo.me/1985686830006307471");
		driver.switchTo().window(parent);
		sleepInSecond(2);
		
		CloseAllTabExcept(parent);
	
	}
	
	@Test
	public void TC_07_Windows_Final() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php/");
		//click to Mobile 
		driver.findElement(By.cssSelector("li.level0.first")).click();
		//add sony to card 
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/ul/li[2]")).click();
		//verify test successful
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg>ul>li>span")).getText(), "The product Sony Xperia has been added to comparison list.");
		sleepInSecond(2);
		//driver.findElement(By.cssSelector("li.level0.first")).click();
		//add samsung to card 
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/ul/li[2]")).click();
		//verify test successful
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg>ul>li>span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		sleepInSecond(2);
		//click compare
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(1);
		//switch to new tab
		String current = driver.getWindowHandle();
		SwithTabById(current);
		//Check title
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		//cloase tab
		driver.close();
		driver.switchTo().window(current);
		sleepInSecond(1);
		//clear all
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInSecond(1);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg>ul>li>span")).getText(), "The comparison list was cleared.");

		
	}

	public void clickByJS(String locator) {
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
	}

	public void SwithTabByTitle(String title) {
		Set<String> allId = driver.getWindowHandles();

		for (String id : allId) {
			driver.switchTo().window(id);
			if (driver.getTitle().equals(title)) {
				break;
			}
		}
	}

	public void SwithTabById(String idTab) {
		// get all tab id
		Set<String> allIdTab = driver.getWindowHandles();

		// check idTab in allIdTab
		for (String id : allIdTab) {
			if (!id.equals(idTab)) {
				// switch vao cai do
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void CloseAllTabExcept(String id) throws InterruptedException {
		Set<String> allId = driver.getWindowHandles();
		for (String idd : allId) {
			if (!idd.equals(id)) {
				driver.switchTo().window(idd);
				driver.close();
				sleepInSecond(1);
			}
		}
	}

	public void sleepInSecond(int sec) throws InterruptedException {
		Thread.sleep(sec * 1000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
