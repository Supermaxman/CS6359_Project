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


public class BrowseCategoryItemsTest {
	
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
		Painting painting = TestUtils.generatePainting();
		Sculpture sculpt = TestUtils.generateSculpture();
		Craft craft = TestUtils.generateCraft();
		PaintingPersistenceServiceImpl.getInstance().create(painting, testUser.getInventory().getInvnId());
		SculpturePersistenceServiceImpl.getInstance().create(sculpt, testUser.getInventory().getInvnId());
		CraftPersistenceServiceImpl.getInstance().create(craft, testUser.getInventory().getInvnId());
	    driver.findElement(By.name("menucategory")).click();
	}
	
	@Test
	public void browsePaintings() throws Exception { 
		driver.findElement(By.name("paintings")).click();
	    Assert.assertEquals("Paintings", driver.getTitle());
	}
	
	@Test
	public void browseSculptures() throws Exception { 
		driver.findElement(By.name("sculptures")).click();
	    Assert.assertEquals("Sculptures", driver.getTitle());
	}
	
	@Test
	public void browseCrafts() throws Exception { 
		driver.findElement(By.name("crafts")).click();
	    Assert.assertEquals("Crafts", driver.getTitle());
	}

	@After 
	public void closePage(){
		driver.quit();
	}
}
