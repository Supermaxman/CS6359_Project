package test.usecases;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class ViewPagesTestCase {
	WebDriver driver;

	@Test
	public void viewPage() throws InterruptedException{ 
		
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.get("http://localhost:8080/CS6359_Project/login.jsp");
	    WebElement username = driver.findElement(By.name("username"));
	    WebElement password = driver.findElement(By.name("password"));
	    WebElement button = driver.findElement(By.xpath("/html/body/form/input[3]"));         

	    username.sendKeys("sushrut");
	    password.sendKeys("patnaik");
	    button.click();
	    Thread.sleep(1000);
	    Assert.assertEquals("Home", driver.getTitle());
	    
	    WebElement transactionLink = driver.findElement(By.xpath("html/body/div/a[5]"));
	    transactionLink.click();
		Thread.sleep(1000);
		Assert.assertEquals("Transactions",driver.getTitle());
		
		WebElement faqLink = driver.findElement(By.xpath("html/body/div/a[7]"));
		faqLink.click();
		Thread.sleep(3000);
		Assert.assertEquals("FAQ",driver.getTitle());
			
		WebElement aboutLink = driver.findElement(By.xpath("html/body/div/a[6]"));
		aboutLink.click();
		Thread.sleep(3000);
		Assert.assertEquals("About",driver.getTitle());
			
		
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
