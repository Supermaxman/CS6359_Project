package test.usecases;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import db.services.impl.CraftPersistenceServiceImpl;
import db.services.impl.PaintingPersistenceServiceImpl;
import db.services.impl.SculpturePersistenceServiceImpl;
import db.services.impl.UserPersistenceServiceImpl;
import domain.product.Craft;
import domain.product.Painting;
import domain.product.Sculpture;
import domain.user.User;
import test.utils.TestUtils;


public class SearchProductTest {
	
	private WebDriver driver;
	private User testUser;
	
	@Before
	public void login() throws Exception {		
		testUser = TestUtils.generateUser();
		UserPersistenceServiceImpl.getInstance().create(testUser);
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
	    driver.get("http://localhost:8080/CS6359_Project/login.jsp");
	    driver.findElement(By.name("username")).sendKeys(testUser.getUsername());
	    driver.findElement(By.name("password")).sendKeys(testUser.getPassword());
	    driver.findElement(By.name("submit")).click();   
	    Assert.assertEquals("Home", driver.getTitle());
	}

	@Test
	public void searchPainting() throws Exception {
		Painting painting = TestUtils.generatePainting();
		PaintingPersistenceServiceImpl.getInstance().create(painting, testUser.getInventory().getInvnId());
		driver.findElement(By.name("searchCriteria")).sendKeys(painting.getName());
		driver.findElement(By.name("searchSubmit")).click();
	    Assert.assertEquals("Search Results", driver.getTitle());
	}
	
	@Test
	public void searchSculpture() throws Exception {
		Sculpture sculpt = TestUtils.generateSculpture();
		SculpturePersistenceServiceImpl.getInstance().create(sculpt, testUser.getInventory().getInvnId());
		driver.findElement(By.name("searchCriteria")).sendKeys(sculpt.getName());
		driver.findElement(By.name("searchSubmit")).click();
	    Assert.assertEquals("Search Results", driver.getTitle());
	}
	
	@Test
	public void searchCraft() throws Exception {
		Craft craft = TestUtils.generateCraft();
		CraftPersistenceServiceImpl.getInstance().create(craft, testUser.getInventory().getInvnId());
		driver.findElement(By.name("searchCriteria")).sendKeys(craft.getName());
		driver.findElement(By.name("searchSubmit")).click();
	    Assert.assertEquals("Search Results", driver.getTitle());
	}

	@After 
	public void closePage(){
		driver.quit();
	}
}
