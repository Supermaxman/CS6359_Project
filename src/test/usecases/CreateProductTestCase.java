package test.usecases;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;

import db.services.UserPersistenceService;
import db.services.impl.UserPersistenceServiceImpl;
import domain.user.User;
import test.utils.TestUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.WebElement;


public class CreateProductTestCase 
{
	WebDriver driver;
	
	@Test
	public void createProduct() throws Exception { 
		User testUser = TestUtils.generateUser();
		UserPersistenceService userService = new UserPersistenceServiceImpl();
		userService.create(testUser);
		
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
	    
	    WebElement invLink = driver.findElement(By.partialLinkText("Inventory"));
		invLink.click();
		Thread.sleep(1000);
		Assert.assertEquals("Inventory",driver.getTitle());
		
		WebElement paintLink = driver.findElement(By.name("Painting"));
		paintLink.click();
		Thread.sleep(1000);
		Assert.assertEquals("New Product",driver.getTitle());
		
		
		WebElement paintName = driver.findElement(By.xpath("html/body/form[1]/input[1]"));
		WebElement paintDesc = driver.findElement(By.xpath("html/body/form[1]/input[2]"));
		WebElement paintPrice = driver.findElement(By.xpath("html/body/form[1]/input[3]"));
		WebElement paintCanvas = driver.findElement(By.xpath("html/body/form[1]/input[4]"));
		WebElement paintPaint = driver.findElement(By.xpath("html/body/form[1]/input[5]"));
		WebElement paintLength = driver.findElement(By.xpath("html/body/form[1]/input[6]"));
		WebElement paintWidth = driver.findElement(By.xpath("html/body/form[1]/input[7]"));
		WebElement paintImage = driver.findElement(By.xpath("html/body/form[1]/input[8]"));
		WebElement createButton = driver.findElement(By.xpath("/html/body/form[1]/input[9]")); 
		paintName.sendKeys("Starry Nights");
		paintDesc.sendKeys("Masterpiece by Vincent Van Gogh");
		paintPrice.sendKeys("150000");
		paintCanvas.sendKeys("Cloth");
		paintPaint.sendKeys("Acryllic");
		paintLength.sendKeys("15");
		paintWidth.sendKeys("10");
		paintImage.sendKeys("C:/A.jpg");
		createButton.click();
		
		Thread.sleep(3000);
		Assert.assertEquals("Inventory",driver.getTitle());
		
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