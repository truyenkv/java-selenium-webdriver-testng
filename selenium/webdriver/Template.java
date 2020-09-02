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

public class Template {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String source = System.getProperty("user.dir");
	String image1 = "image1.jpg";
	String image2 = "image2.jpg";
	String image3 = "image3.jpg";
	String image_1_Path = source + "/upload/" + image1;
	String image_2_Path = source + "/upload/" + image2;
	String image_3_Path = source + "/upload/" + image3;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	//@Test
	public void TC_01_JavaExecutor() throws InterruptedException {
		
	}

	@Test
	public void TC_02_DeleAttribute() {
		
	}

	//@Test
	public void TC_03_LoginFormDisplayed() {
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
