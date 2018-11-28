package test.usecases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import db.services.impl.CartPersistenceServiceImpl;
import db.services.impl.CraftPersistenceServiceImpl;
import db.services.impl.PaintingPersistenceServiceImpl;
import db.services.impl.SculpturePersistenceServiceImpl;
import db.services.impl.UserPersistenceServiceImpl;
import domain.product.Craft;
import domain.product.Painting;
import domain.product.Sculpture;
import domain.user.User;
import test.utils.TestUtils;

public class RemoveProductFromCartTest {

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

	@After 
	public void closePage(){
		driver.quit();
	}

	@Test
	public void removePaintingFromCart() throws Exception {
		Painting painting = TestUtils.generatePainting();
		PaintingPersistenceServiceImpl.getInstance().create(painting, testUser.getInventory().getInvnId());
		testUser.getCart().addProduct(painting);
		CartPersistenceServiceImpl.getInstance().update(testUser.getCart());
	    driver.findElement(By.name("menucart")).click();
		driver.findElement(By.name("Remove")).click();
		Assert.assertEquals("Cart", driver.getTitle());		
	}
	
	@Test
	public void removeSculptureFromCart() throws Exception {
		Sculpture sculpt = TestUtils.generateSculpture();
		SculpturePersistenceServiceImpl.getInstance().create(sculpt, testUser.getInventory().getInvnId());
		testUser.getCart().addProduct(sculpt);
		CartPersistenceServiceImpl.getInstance().update(testUser.getCart());
	    driver.findElement(By.name("menucart")).click();
		driver.findElement(By.name("Remove")).click();
		Assert.assertEquals("Cart", driver.getTitle());		
	}
	
	@Test
	public void removeCraftFromCart() throws Exception {
		Craft craft = TestUtils.generateCraft();
		CraftPersistenceServiceImpl.getInstance().create(craft, testUser.getInventory().getInvnId());
		testUser.getCart().addProduct(craft);
		CartPersistenceServiceImpl.getInstance().update(testUser.getCart());
	    driver.findElement(By.name("menucart")).click();
		driver.findElement(By.name("Remove")).click();
		Assert.assertEquals("Cart", driver.getTitle());		
	}

}
