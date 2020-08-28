package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction {
	WebDriver driver;
	Actions action;
	WebElement element;
	JavascriptExecutor jsExcutor;
	String root = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		action = new Actions(driver);
		jsExcutor = (JavascriptExecutor) driver;
	}

//	@Test
	public void TC_01_Hover_Element() throws InterruptedException {
		driver.get("http://www.myntra.com/");
		element = driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"));
		action.moveToElement(element).perform();
		sleepInSecond(2);
		
		//click Home & Bathc
		driver.findElement(By.xpath("//a[@href='/kids-home-bath']")).click();
		//Verify tranform successful
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());
		
		
		
	}

	//@Test
	public void TC_02_ClickAndHove() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> allItem = driver.findElements(By.xpath("//li"));
		
		//click li[1] move to li[4]
		action.clickAndHold(allItem.get(4)).moveToElement(allItem.get(7)).release().perform();
		sleepInSecond(2);
		
		//verify select = 4
		List<WebElement> selected = driver.findElements(By.cssSelector(".ui-selected"));
		Assert.assertEquals(selected.size(), 5);
		
		
	}

	//@Test
	public void TC_03_Select_Randome() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> allItem = driver.findElements(By.xpath("//li"));
		
		//click randome 3 6 9 11
		action.keyDown(Keys.CONTROL).perform();
		action.click(allItem.get(3)).click(allItem.get(6)).click(allItem.get(9)).click(allItem.get(11)).perform();
		sleepInSecond(2);
		action.keyUp(Keys.CONTROL).perform();
		
		List<WebElement> selected = driver.findElements(By.cssSelector(".ui-selected"));
		List<String> selectedText = new ArrayList<String>();
		for(WebElement ele: selected) {
			selectedText.add(ele.getText());
		}
		System.out.println(selectedText);
		Assert.assertTrue(selectedText.contains("4"));
		Assert.assertTrue(selectedText.contains("7"));
		Assert.assertTrue(selectedText.contains("10"));
		Assert.assertTrue(selectedText.contains("12"));
		
		
	}
//	@Test
	public void TC_04_Doule_Click() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
	}
	//@Test
	public void TC_04_RightClick() throws InterruptedException {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		//right click on element
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();;
		sleepInSecond(2);
		
		//hover move on quit'
		element = driver.findElement(By.cssSelector(".context-menu-icon-quit"));
		action.moveToElement(element).perform();
		sleepInSecond(2);
		
		//verify quick visible.hove
		String getAtt = element.getAttribute("class");
		Assert.assertTrue(getAtt.contains("context-menu-visible"));
		Assert.assertTrue(getAtt.contains("context-menu-hover"));
		element.click();
		
	}
	//@Test
	public void TC_05_DrapAndDrop() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droptarget"));
		
		sleepInSecond(2);
		//drap and drop
		action.dragAndDrop(source, target).perform();
		
		sleepInSecond(2);
		//verify text
		Assert.assertEquals(target.getText(), "You did great!");
		
		
		
	}
	@Test
	public void TC_06_DrapAndDrop_HTML5() throws IOException, InterruptedException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		sleepInSecond(2);
		String Source = "#column-a";
		String Target = "#column-b";
		String javascriptPath = root + "/JavaScript/drag_and_drop_helper.js";
		String javascript = readFile(javascriptPath);
		javascript = javascript + "$(\"" + Source + "\").simulateDragDrop({ dropTarget: \"" + Target + "\"});";
		jsExcutor.executeScript(javascript);
		
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		
		
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
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
