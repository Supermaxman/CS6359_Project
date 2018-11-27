package test.usecases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import db.services.PaintingPersistenceService;
import db.services.UserPersistenceService;
import db.services.impl.PaintingPersistenceServiceImpl;
import db.services.impl.UserPersistenceServiceImpl;
import domain.product.Painting;
import domain.user.User;
import test.utils.TestUtils;

public class RemoveProductTestCase {
	
	WebDriver driver;

	@Test
	public void removeProduct() throws Exception { 
		User testUser = TestUtils.generateUser();
		Painting painting = TestUtils.generatePainting();
		UserPersistenceService userService = UserPersistenceServiceImpl.getInstance();
		userService.create(testUser);
		PaintingPersistenceService paintService = PaintingPersistenceServiceImpl.getInstance();
		paintService.create(painting, testUser.getInventory().getInvnId());
		
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
	    
	    WebElement invLink = driver.findElement(By.partialLinkText("Inventory"));
		invLink.click();
		Assert.assertEquals("Inventory",driver.getTitle());
		
		WebElement editLink = driver.findElement(By.name("EditDetails"));
		editLink.click();
		Assert.assertEquals("Edit Product Details",driver.getTitle());
		
		
		WebElement deleteButton = driver.findElement(By.name("removeproduct1"));
		deleteButton.click();
		
		Assert.assertEquals("Inventory",driver.getTitle());
		
		WebElement logout = driver.findElement(By.name("menulogout"));
		logout.click();
		Assert.assertEquals("Login",driver.getTitle());
		
	}

	@After 
	public void closePage(){
		driver.quit();
	}

}
