package test.usecases;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import db.services.UserPersistenceService;
import db.services.impl.UserPersistenceServiceImpl;
import domain.user.User;
import test.utils.TestUtils;


public class ViewPagesTestCase {
	WebDriver driver;

	@Test
	public void viewPage() throws Exception { 
		User testUser = TestUtils.generateUser();
		UserPersistenceService userService = UserPersistenceServiceImpl.getInstance();
		userService.create(testUser);
		
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.get("http://localhost:8080/CS6359_Project/login.jsp");
	    WebElement username = driver.findElement(By.name("username"));
	    WebElement password = driver.findElement(By.name("password"));
	    WebElement button = driver.findElement(By.name("submit"));         

	    username.sendKeys(testUser.getUsername());
	    password.sendKeys(testUser.getPassword());
	    button.click();
	    Assert.assertEquals("Home", driver.getTitle());
	    
	    WebElement transactionLink = driver.findElement(By.name("menutransactions"));
	    transactionLink.click();
		Assert.assertEquals("Transactions",driver.getTitle());
		
		WebElement faqLink = driver.findElement(By.name("menufaq"));
		faqLink.click();
		Assert.assertEquals("FAQ",driver.getTitle());
			
		WebElement aboutLink = driver.findElement(By.name("menuabout"));
		aboutLink.click();
		Assert.assertEquals("About",driver.getTitle());
			
		
		WebElement logout = driver.findElement(By.name("menulogout"));
		logout.click();
		Assert.assertEquals("Login",driver.getTitle());
		
	}

	@After 
	public void closePage(){
		driver.quit();
	}
}
