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

public class CheckoutTestCase {
	WebDriver driver;

	@Test
	public void checkout() throws Exception { 
		User testUser = TestUtils.generateUser();
		Painting painting = TestUtils.generatePainting();
		UserPersistenceService userService = new UserPersistenceServiceImpl();
		userService.create(testUser);
		PaintingPersistenceService paintService = new PaintingPersistenceServiceImpl();
		paintService.create(painting, testUser.getInventory().getInvnId());
		
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.get("http://localhost:8080/CS6359_Project/login.jsp");
	    WebElement username = driver.findElement(By.name("username"));
	    WebElement password = driver.findElement(By.name("password"));
	    WebElement button = driver.findElement(By.xpath("/html/body/form/input[3]"));         

	    username.sendKeys(testUser.getUsername());
	    password.sendKeys(testUser.getPassword());
	    button.click();
	    Thread.sleep(1000);
	    Assert.assertEquals("Home", driver.getTitle());
	    
	    WebElement catLink = driver.findElement(By.xpath("html/body/div/a[2]"));
		catLink.click();
		Thread.sleep(1000);
		Assert.assertEquals("Category",driver.getTitle());
		
		
		WebElement paintLink = driver.findElement(By.xpath("html/body/div[2]/a[1]"));
		paintLink.click();
		Thread.sleep(1000);
		Assert.assertEquals("Paintings",driver.getTitle());
		Thread.sleep(3000);
		
		WebElement viewButton = driver.findElement(By.name("ViewDetails"));
		viewButton.click();
		Thread.sleep(1000);
		Assert.assertEquals("Product Details",driver.getTitle());
		
		WebElement addCartButton = driver.findElement(By.name("AddToCart"));
		addCartButton.click();
		Thread.sleep(1000);
		Assert.assertEquals("CartPage",driver.getTitle());
		
		WebElement checkoutButton = driver.findElement(By.name("Checkout"));
		checkoutButton.click();
		Thread.sleep(1000);
		
		
			
		WebElement logout = driver.findElement(By.xpath("html/body/div/a[8]"));
		logout.click();
		Thread.sleep(3000);
		Assert.assertEquals("Login",driver.getTitle());
		
	}

	@After 
	public void closePage(){
		driver.quit();
	}

}
