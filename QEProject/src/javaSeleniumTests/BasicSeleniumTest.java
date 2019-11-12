package javaSeleniumTests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicSeleniumTest {

	private static WebDriver driver;
	@BeforeClass
	public static void setUp() {
		/***************************/
		//Make sure you know the location of your chrome driver!!!!!!!!
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Student\\Desktop\\chromeDriver\\chromedriver.exe");
				
		driver = new ChromeDriver();
		
		//Sets the webpage that is inside the driver
		driver.get("http://localhost:8080/QEProject/HomeServlet");
	}
	
	//Closes the webpage after completing all tests
	@AfterClass
	public static void shutDown() {
		driver.close();
	}
	
	//Asserts whether the title is the same as the expected
	@Test
	public void testTitle() {
		
		String pageTitle = driver.getTitle();
		
		assertThat(pageTitle,is("Musicians Headquaters"));
		
	}
	
	@Test
	public void testLoginPage() {
		
		WebElement findE = driver.findElement(By.cssSelector("#navbarNavDropdown > ul > li:nth-child(2) > a"));
		
		findE.click();
		
		WebElement findUsernameField = driver.findElement(By.cssSelector("body > form > p:nth-child(1) > input[type=text]"));
		
		findUsernameField.sendKeys("test");
		
		WebElement findPasswordField = driver.findElement(By.cssSelector("body > form > p:nth-child(2) > input[type=text]"));
		
		findPasswordField.sendKeys("test");
		
		WebElement findSubmitB = driver.findElement(By.cssSelector("body > form > input[type=submit]"));
		
		findSubmitB.click();
		
		WebElement invTitle = driver.findElement(By.cssSelector("body > h1"));
		
		assertThat(invTitle.getText(),is("See our stock!"));
		
	}

}