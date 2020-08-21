package webdriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_08_TextArea {
	WebDriverWait explicitWait;
	Select select;
	WebDriver driver;
	JavascriptExecutor jsExcute;
	String email, userID, pass, loginUrl;
	String customerID, customerName, gender, dob, address, city, state, pin, mobileNumber, emailCustomer, passwordCustomer;
	By customerNameField = By.name("name");
	By doBField = By.id("dob");
	By addressField = By.name("addr");
	By cityField = By.name("city");
	By stateField = By.name("state");
	By pinField = By.name("pinno");
	By mobileNumField = By.name("telephoneno");
	By emailCusField = By.name("emailid");
	By passCusField = By.name("password");
	By submitBtn = By.name("sub");
	By customerId = By.name("cusid");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);
		jsExcute = (JavascriptExecutor) driver;
		
	}

	//@Test
	public void TC_01_Get_Account() {
		// go to get username account
		email = "truyen" + random() + "@yopmail.com";
		driver.get("http://demo.guru99.com/");

		// Input email and send
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		// store UserID and PASS
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		pass = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	//@Test
	public void TC_02_Login_And_Verify_Home_Screen() {
		// go to home screen
		driver.get("http://demo.guru99.com/v4/");
		loginUrl = driver.getCurrentUrl();
		// Login with valid username and password
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(By.name("btnLogin")).click();

		// Verify the item on Home page
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText(), "Manger Id : " + userID);
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(), "Welcome To Manager's Page of Guru99 Bank");

	}

	//@Test
	public void TC_03_Create_New_Customer() throws ParseException {
		customerName = "Truyen Kieu";
		dob = "08/08/2020";
		address = "123 Nguyen Dinh Chieu\nQuan 1\nHCM";
		city = "City";
		state = "state Quan One";
		pin = "111111";
		gender = "male";
		mobileNumber = "0989988888";
		emailCustomer = "truyenCus" + random() + "@yopmail.com";
		passwordCustomer = "123456";

		// go to login Page
		driver.get(loginUrl);
		// Login with valid username and password
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(By.name("btnLogin")).click();

		// open New Customer screen
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Input data to field
		driver.findElement(customerNameField).sendKeys(customerName);
		driver.findElement(doBField).sendKeys(dob);
		driver.findElement(addressField).sendKeys(address);
		driver.findElement(cityField).sendKeys(city);
		driver.findElement(stateField).sendKeys(state);
		driver.findElement(pinField).sendKeys(pin);
		driver.findElement(mobileNumField).sendKeys(mobileNumber);
		driver.findElement(emailCusField).sendKeys(emailCustomer);
		driver.findElement(passCusField).sendKeys(passwordCustomer);

		// click submit button
		driver.findElement(By.name("sub")).click();

		// Verify the user information
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(customerID);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateFormat(dob));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailCustomer);
	}

	//@Test
	public void TC_04_Edit_Customer() throws ParseException, InterruptedException {

		// go to edit customer screen
		driver.get(loginUrl);
		// Login with valid username and password
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(By.name("btnLogin")).click();

		// open New Customer screen
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		// input the customerID and send
		driver.findElement(customerId).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();

		// verify customer name and address
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(addressField).getText(), address);

		// generate new inform
		address = address + "Edited";
		city = city + "Edited";
		state = state + "Edited";
		pin = String.valueOf(random());
		mobileNumber = "2342" + random();
		// input new value and submit
		driver.findElement(addressField).clear();
		driver.findElement(addressField).sendKeys(address);
		driver.findElement(cityField).clear();
		driver.findElement(cityField).sendKeys(city);
		driver.findElement(stateField).clear();
		driver.findElement(stateField).sendKeys(state);
		driver.findElement(pinField).clear();
		driver.findElement(pinField).sendKeys(pin);
		driver.findElement(mobileNumField).clear();
		driver.findElement(mobileNumField).sendKeys(mobileNumber);
		driver.findElement(submitBtn).click();
		Thread.sleep(2000);

		// Verify the value after editing
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateFormat(dob));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailCustomer);
	}

	//@Test
	public void TC_05_Handle_Dropdown_List() throws InterruptedException {
		// open the site
		driver.get("https://automationfc.github.io/basic-form/index.html");

		select = new Select(driver.findElement(By.id("job1")));

		// check job role is not multiple select
		Assert.assertFalse(select.isMultiple());

		// Select Mobile Testing by selectByVisibleText
		select.selectByVisibleText("Mobile Testing");

		Thread.sleep(2000);
		// check Test is selected is "Mobile Testing"
		System.out.print(select.getFirstSelectedOption().getText());
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");

		// select Manual Test by value
		select.selectByValue("manual");
		Thread.sleep(2000);
		// check Test is selected is "Mobile Testing"
		System.out.print(select.getFirstSelectedOption().getText());
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");

		select.selectByIndex(9);
		Thread.sleep(2000);
		// check Test is selected is "Mobile Testing"
		System.out.print(select.getFirstSelectedOption().getText());
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");

		// Check the size is 10
		List<WebElement> option = select.getOptions();
		Assert.assertEquals(option.size(), 10);

		select = new Select(driver.findElement(By.id("job2")));
		// check job role is multiple select
		Assert.assertTrue(select.isMultiple());
		
		List<String> data = new ArrayList<>();
		//Select multiple item
		select.selectByVisibleText("Automation");
		data.add("Automation");
		select.selectByVisibleText("Mobile");
		data.add("Mobile");
		select.selectByVisibleText("Desktop");
		data.add("Desktop");
		
		//Verify 3 items were added
		int multil = select.getAllSelectedOptions().size();
		Assert.assertEquals(multil, 3);
		
		List<String> nameAdded = new ArrayList<>();
		List<WebElement> optionName = select.getAllSelectedOptions();
		for(WebElement options: optionName) {
			System.out.println(options.getText());
			nameAdded.add(options.getText());
		}
		
		//checck is data = select
		Assert.assertEquals(data, nameAdded);
		
		//de select all
		select.deselectAll();
		
		//Verify no item is select
		Assert.assertEquals(select.getAllSelectedOptions().size(), 0);
		
		
		
	}

	@Test
	public void TC_06_Handler_Customer_Drop() throws InterruptedException {
//		//goto jquery
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		//click to dropdown
		sleepInSecond(2);
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		sleepInSecond(2);
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "1");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='1']")).isDisplayed());
		sleepInSecond(2);
		selectItemInDropDown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		
//		//go to Angular
		driver.get("https://bit.ly/2UV2vYi");
		sleepInSecond(2);
		selectItemInDropDown("//*[@id='games']//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']/li", "Basketball");
		Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Basketball");
		sleepInSecond(2);
		selectItemInDropDown("//*[@id='games']//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']/li", "Golf");
		Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Golf");
		
//		//goto ReactJS
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		sleepInSecond(2);
		selectItemInDropDown("//i[@class='dropdown icon']", "//span[@class='text']", "Jenny Hess");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Jenny Hess']")).isDisplayed());
		selectItemInDropDown("//i[@class='dropdown icon']", "//span[@class='text']", "Christian");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Christian']")).isDisplayed());
		selectItemInDropDown("//i[@class='dropdown icon']", "//span[@class='text']", "Elliot Fu");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Elliot Fu']")).isDisplayed());
		
		
		//goto edit editable
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		sendKeyToDrop("//div[@id='default-place']/input", "Audi");
		Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "Audi");
		
		//go multiple select
		driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
		
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		String[] month = {"January", "April", "May", "June", "July"};
		selectMultipleElement("//div[@id='example']/div/div[2]//button","//div[@id='example']/div/div[2]//ul//li//input/following-sibling::span",month);
		//div[@id='example']/div/div[2]//button[@class='ms-choice']
		checkItemSelected(month);
		
	}
	
	public void selectMultipleElement(String parentLocator, String allElementXpath, String []listSelect) throws InterruptedException {
		driver.findElement(By.xpath(parentLocator)).click();
		sleepInSecond(1);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allElementXpath)));
		List<WebElement> allElements = driver.findElements(By.xpath(allElementXpath));
		System.out.println("Number of element: "+ allElements.size());
		
		//Duyet tat ca cac phan tu den khi thoa dieu kien
		for(WebElement item: allElements) {
			for(String select: listSelect) {
				if(item.getText().equals(select)) {
					//scroll to view element
					jsExcute.executeScript("arguments[0].scrollIntoView(true)", item);
					sleepInSecond(1);
					//thuc hien click
					jsExcute.executeScript("arguments[0].click()", item);
					sleepInSecond(1);
				}
				List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']"));
				System.out.print("List item selected: "+ itemSelected.size());
				if(listSelect.length == itemSelected.size()) {
					break;
				}
			}
		}
	}
	
	public boolean checkItemSelected(String [] itemSelectedText) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']"));
		int numberItemSelect = itemSelected.size();
		String allItemSelected = driver.findElement(By.xpath("//div[@id='example']/div/div[2]//button/span")).getText();
		if(numberItemSelect <= 3 && numberItemSelect > 0) {
			for(String item: itemSelectedText) {
				if(allItemSelected.contains(item)){
					break;
				}
			}
			return true;
		}
		else {
			return driver.findElement(By.xpath("//div[@id='example']/div/div[2]//button/span[text()='"+ numberItemSelect +" of 12 selected']")).isDisplayed();
		}
		
		
		
	}
	
	public void sendKeyToDrop(String locator, String value) throws InterruptedException {
		driver.findElement(By.xpath(locator)).clear();
		sleepInSecond(1);
		driver.findElement(By.xpath(locator)).sendKeys(value);
		sleepInSecond(1);
		driver.findElement(By.xpath(locator)).sendKeys(Keys.TAB);
		sleepInSecond(1);
	}
	
	public void selectItemInDropDown(String parentLocator, String itemLocator, String expectedItem) throws InterruptedException {
//		1. click on parent tab to load all item
		driver.findElement(By.xpath(parentLocator)).click();
		sleepInSecond(2);
//		2. Wait until all item is display
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));
		
//		3. Take all items to list
		List<WebElement> allItem = driver.findElements(By.xpath(itemLocator));
		
//		4. Select the item and click
		for(WebElement item: allItem) {
			String actual = item.getText();
			if(actual.equals(expectedItem)) {
				
				//wait and scroll to element
				sleepInSecond(1);
				jsExcute.executeScript("arguments[0].scrollIntoView(true)", item);
				
				item.click();
				sleepInSecond(1);
				break; 
			}
		}
	}
	
	public void sleepInSecond(int time) throws InterruptedException {
		Thread.sleep(time*1000);
	}
	
	public int random() {
		Random rand = new Random();
		return rand.nextInt(900000);

	}

	// Cover date to String
	public String dateFormat(String date) throws ParseException {
		SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
		Date datetime = input.parse(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(datetime);
	}
	
	//get hidden text
	public String getHiddenText(String locator) {
		return (String) jsExcute.executeScript("return document.querySelector(\""+locator+ "\").textContent");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
