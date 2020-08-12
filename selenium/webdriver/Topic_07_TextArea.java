package webdriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextArea {
	WebDriver driver;
	String email, userID, pass, loginUrl;
	String customerID, customerName, gender, dob, address, city, state, pin, mobileNumber, emailCustomer,
			passwordCustomer;
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
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// check
	}

	@Test
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

	@Test
	public void TC_02_Login_And_Verify_Home_Screen() {
		// go to home screen
		driver.get("http://demo.guru99.com/v4/");
		loginUrl = driver.getCurrentUrl();
		// Login with valid username and password
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(By.name("btnLogin")).click();

		// Verify the item on Home page
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText(),
				"Manger Id : " + userID);
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");

	}

	@Test
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
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),
				customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dateFormat(dob));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				emailCustomer);
	}

	@Test
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
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),
				customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dateFormat(dob));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				emailCustomer);
	}

	// @Test
	public void TC_05_LoginFormDisplayed() {

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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
