package test.usecases;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.WebElement;

/*import com.thoughtworks.selenium.SeleneseTestCase;

public class LoginTestCase extends SeleneseTestCase {
    public void setUp() throws Exception {  
        login();
    } 
    
}
*/

public class LoginTestCase 
{
	WebDriver driver;

@Test
public void login() throws InterruptedException{ 
	
	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("http://login.jsp");
    WebElement username = driver.findElement(By.name("username"));
    WebElement password = driver.findElement(By.name("password"));
    WebElement button = driver.findElement(By.xpath("/html/body/form/input[3]"));         

    username.sendKeys("sushrut");
    password.sendKeys("patnaik");
    button.click();
    Assert.assertEquals("what is our title ", driver.getTitle());
}

@Test
public void openInventory() throws InterruptedException{
	
	WebElement link = driver.findElement(By.id(""));
	link.click();
	Thread.sleep(5000);
	Assert.assertEquals("Inventory",driver.getTitle());

}

@Test
public void addproduct() throws InterruptedException{
	
	
}
@After 
public void closePage(){
driver.quit();
}
}