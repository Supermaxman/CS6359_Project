package test.usecases;
import org.junit.*;
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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class EditProductInInventoryTest {
	
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
	public void editPainting() throws Exception {
		Painting painting = TestUtils.generatePainting();
		PaintingPersistenceServiceImpl.getInstance().create(painting, testUser.getInventory().getInvnId());
	    driver.findElement(By.name("menuinventory")).click();
		driver.findElement(By.name("EditDetails")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys(painting.getName());
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys(painting.getDescription());
		driver.findElement(By.name("price")).clear();
		driver.findElement(By.name("price")).sendKeys(Double.toString(painting.getPrice()));
		driver.findElement(By.name("canvasType")).clear();
		driver.findElement(By.name("canvasType")).sendKeys(painting.getCanvasType());
		driver.findElement(By.name("paintType")).clear();
		driver.findElement(By.name("paintType")).sendKeys(painting.getPaintType());
		driver.findElement(By.name("length")).clear();
		driver.findElement(By.name("length")).sendKeys(Double.toString(painting.getLength()));
		driver.findElement(By.name("width")).clear();
		driver.findElement(By.name("width")).sendKeys(Double.toString(painting.getWidth()));
		driver.findElement(By.name("SaveDetails")).click();
	    Assert.assertEquals("Inventory", driver.getTitle());		
	}
	
	@Test
	public void editSculpture() throws Exception {
		Sculpture sculpt = TestUtils.generateSculpture();
		SculpturePersistenceServiceImpl.getInstance().create(sculpt, testUser.getInventory().getInvnId());
	    driver.findElement(By.name("menuinventory")).click();
		driver.findElement(By.name("EditDetails")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys(sculpt.getName());
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys(sculpt.getDescription());
		driver.findElement(By.name("price")).clear();
		driver.findElement(By.name("price")).sendKeys(Double.toString(sculpt.getPrice()));
		driver.findElement(By.name("length")).clear();
		driver.findElement(By.name("length")).sendKeys(Double.toString(sculpt.getLength()));
		driver.findElement(By.name("width")).clear();
		driver.findElement(By.name("width")).sendKeys(Double.toString(sculpt.getWidth()));
		driver.findElement(By.name("height")).clear();
		driver.findElement(By.name("height")).sendKeys(Double.toString(sculpt.getHeight()));
		driver.findElement(By.name("material")).clear();
		driver.findElement(By.name("material")).sendKeys(sculpt.getMaterial());
		driver.findElement(By.name("weight")).clear();
		driver.findElement(By.name("weight")).sendKeys(Double.toString(sculpt.getWeight()));
		driver.findElement(By.name("SaveDetails")).click();
	    Assert.assertEquals("Inventory", driver.getTitle());		
	}
	
	@Test
	public void editCraft() throws Exception {
		Craft craft = TestUtils.generateCraft();
		CraftPersistenceServiceImpl.getInstance().create(craft, testUser.getInventory().getInvnId());
	    driver.findElement(By.name("menuinventory")).click();
		driver.findElement(By.name("EditDetails")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys(craft.getName());
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys(craft.getDescription());
		driver.findElement(By.name("price")).clear();
		driver.findElement(By.name("price")).sendKeys(Double.toString(craft.getPrice()));
		driver.findElement(By.name("length")).clear();
		driver.findElement(By.name("length")).sendKeys(Double.toString(craft.getLength()));
		driver.findElement(By.name("width")).clear();
		driver.findElement(By.name("width")).sendKeys(Double.toString(craft.getWidth()));
		driver.findElement(By.name("height")).clear();
		driver.findElement(By.name("height")).sendKeys(Double.toString(craft.getHeight()));
		driver.findElement(By.name("usage")).clear();
		driver.findElement(By.name("usage")).sendKeys(craft.getUsage());
		driver.findElement(By.name("SaveDetails")).click();
	    Assert.assertEquals("Inventory", driver.getTitle());
	}
	
	@After 
	public void closePage(){
		driver.quit();
	}
}