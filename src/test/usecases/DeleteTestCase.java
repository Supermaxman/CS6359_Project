package test.usecases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DeleteTestCase {
	
	WebDriver driver;

	@Test
	public void login() throws InterruptedException{ 
		
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.get("http://localhost:8080/CS6359_Project/login.jsp");
	    WebElement username = driver.findElement(By.name("username"));
	    WebElement password = driver.findElement(By.name("password"));
	    WebElement button = driver.findElement(By.xpath("/html/body/form/input[3]"));         

	    username.sendKeys("123");
	    password.sendKeys("123");
	    button.click();
	    Thread.sleep(1000);
	    Assert.assertEquals("Home", driver.getTitle());
	    
	    WebElement invLink = driver.findElement(By.partialLinkText("Inventory"));
		invLink.click();
		Thread.sleep(1000);
		Assert.assertEquals("Inventory",driver.getTitle());
		
		WebElement editLink = driver.findElement(By.name("EditDetails"));
		editLink.click();
		Thread.sleep(1000);
		Assert.assertEquals("Edit Product Details",driver.getTitle());
		
		
		WebElement deleteButton = driver.findElement(By.name("removeproduct1"));
		deleteButton.click();
		
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
