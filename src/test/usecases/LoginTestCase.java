package test.usecases;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;

import db.services.impl.UserPersistenceServiceImpl;
import domain.user.User;
import test.utils.TestUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginTestCase 
{
	private WebDriver driver;
	
	@Before
	public void init() throws Exception {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.get("http://localhost:8080/CS6359_Project/login.jsp");
	}
	
	@Test
	public void login() throws Exception { 
		User testUser = TestUtils.generateUser();
		UserPersistenceServiceImpl.getInstance().create(testUser);
	    driver.findElement(By.name("username")).sendKeys(testUser.getUsername());
	    driver.findElement(By.name("password")).sendKeys(testUser.getPassword());
	    driver.findElement(By.name("submit")).click();
	    Assert.assertEquals("Home", driver.getTitle());		
	}
	

	@Test
	public void loginDeactive() throws Exception { 
		User testUser = TestUtils.generateUser();
		testUser.setActive(false);
		UserPersistenceServiceImpl.getInstance().create(testUser);
	    driver.findElement(By.name("username")).sendKeys(testUser.getUsername());
	    driver.findElement(By.name("password")).sendKeys(testUser.getPassword());
	    driver.findElement(By.name("submit")).click();
	    Assert.assertEquals("Login", driver.getTitle());		
	}
	
	
	@After 
	public void closePage(){
		driver.quit();
	}
}