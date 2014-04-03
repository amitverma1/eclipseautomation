package pageElements;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPage {

	public static WebDriver driver;
	
	@BeforeClass
	public void beforeclass() {
		driver = new FirefoxDriver();
		
	}
	
	@Test(priority = 1, alwaysRun = true)
	public void loginWithBlankCredentials()
	{
		driver.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		driver.findElement(By.className("primarySubmitButton")).click();
		Assert.assertEquals("The entered Username-password combination is incorrect.", driver.findElement(By.xpath("//div[@class='message error']/p")).getText());
	}
	
	@Test(priority = 2, alwaysRun = true)
	public void loginWithBlankUsername()
	{
		
		driver.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.className("pswrdField")).clear();
		driver.findElement(By.className("pswrdField")).sendKeys("hell0");
		driver.findElement(By.className("primarySubmitButton")).click();
		Assert.assertEquals("The entered Username-password combination is incorrect.", driver.findElement(By.xpath("//div[@class='message error']/p")).getText());
	}
	
	@Test(priority = 3, alwaysRun = true)
	public void loginWithBlankPassowrd()
	{
		
		driver.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.className("pswrdField")).clear();
		driver.findElement(By.className("usernameField")).clear();
		driver.findElement(By.className("usernameField")).sendKeys("jbertran");
		driver.findElement(By.className("primarySubmitButton")).click();
		Assert.assertEquals("The entered Username-password combination is incorrect.", driver.findElement(By.xpath("//div[@class='message error']/p")).getText());
	}
	
	@Test(priority = 4, alwaysRun = true)
	public void loginWithWrongCredentials()
	{
		
		driver.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.className("usernameField")).clear();
		driver.findElement(By.className("usernameField")).sendKeys("xyz");
		driver.findElement(By.className("pswrdField")).clear();
		driver.findElement(By.className("pswrdField")).sendKeys("abc01");
		driver.findElement(By.className("primarySubmitButton")).click();
		Assert.assertEquals("The entered Username-password combination is incorrect.", driver.findElement(By.xpath("//div[@class='message error']/p")).getText());
	}
	
	@Test(priority = 5, alwaysRun = true)
	public void loginWithCorrectCredentials()
	{
		
		driver.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.className("usernameField")).clear();
		driver.findElement(By.className("usernameField")).sendKeys("jbertran");
		driver.findElement(By.className("pswrdField")).clear();
		driver.findElement(By.className("pswrdField")).sendKeys("hell0");
		driver.findElement(By.className("primarySubmitButton")).click();
		Assert.assertEquals("Jerome Bertrand", driver.findElement(By.xpath("//a[@id='changePasswordLink']")).getText());
	}

}
