package test.usecases;

import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;

import domain.user.User;
import test.utils.TestUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegisterTestCase 
{
	WebDriver driver;
	
	@Test
	public void register() throws Exception { 
		User testUser = TestUtils.generateUser();
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.get("http://localhost:8080/CS6359_Project/register.jsp");
	    driver.findElement(By.name("username")).sendKeys(testUser.getUsername());
	    driver.findElement(By.name("name")).sendKeys(testUser.getName());
	    driver.findElement(By.name("description")).sendKeys(testUser.getDescription());
	    driver.findElement(By.name("password")).sendKeys(testUser.getPassword());
	    driver.findElement(By.name("retry-password")).sendKeys(testUser.getPassword());
	    driver.findElement(By.name("address")).sendKeys(testUser.getAddress());
	    driver.findElement(By.name("number")).sendKeys(testUser.getCreditCard().getNumber());	   
	    driver.findElement(By.name("cvv")).sendKeys(testUser.getCreditCard().getCvv());
	    driver.findElement(By.name("submit")).click();
	    
	    Assert.assertEquals("Login", driver.getTitle());
	    driver.findElement(By.name("username")).sendKeys(testUser.getUsername());
	    driver.findElement(By.name("password")).sendKeys(testUser.getPassword());
	    driver.findElement(By.name("submit")).click();
	    Assert.assertEquals("Home", driver.getTitle());
		
	}
	
	@After 
	public void closePage(){
		driver.quit();
	}
}