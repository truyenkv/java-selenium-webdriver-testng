package webdriver;

import java.util.List;
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

public class Topic_14_UploadFile {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	String source = System.getProperty("user.dir");
	String image1 = "image1.jpg";
	String image2 = "image2.jpg";
	String image3 = "image3.jpg";
	String image1Path = source + "/upload/" + image1;
	String image2Path = source + "/upload/" + image2;
	String image3Path = source + "/upload/" + image3;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Upload_File_By_SendKey() throws InterruptedException {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		// sendkey to field
		String imagelist = image1Path + "\n" + image2Path + "\n" + image3Path;
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imagelist);
		sleepInSecond(5);
		// check the imageurl is display before upload
		Assert.assertTrue(checkimageUrl(image1));
		Assert.assertTrue(checkimageUrl(image2));
		Assert.assertTrue(checkimageUrl(image3));
		
		// Collect the Start button
		List<WebElement> StartButtonList = driver.findElements(By.cssSelector("td>.start"));
		for (WebElement startBtn : StartButtonList) {
			sleepInSecond(1);
			startBtn.click();
		}
		// Check the image after uploading 
	
		sleepInSecond(2);
		Assert.assertTrue(checkImageJScript(driver.findElement(By.xpath("//img[contains(@src,'" + image1 + "')]"))));
		Assert.assertTrue(checkImageJScript(driver.findElement(By.xpath("//img[contains(@src,'" + image2 + "')]"))));
		Assert.assertTrue(checkImageJScript(driver.findElement(By.xpath("//img[contains(@src,'" + image3 + "')]"))));

	}

	// @Test
	public void TC_02_DeleAttribute() {

	}

	// @Test
	public void TC_03_LoginFormDisplayed() {

	}

	public void sleepInSecond(int i) throws InterruptedException {
		Thread.sleep(i * 1000);
	}

	public boolean checkImageJScript(WebElement image) {
		jsExecutor = (JavascriptExecutor) driver;
		return (Boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", image);
	}

	public boolean checkimageUrl(String imageName) {
		return driver.findElement(By.xpath("//p[text()='" + imageName + "']")).isDisplayed();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
