package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageElements.LoginPage;

public class LoginTC {

	public LoginPage log_obj;
	
	@BeforeClass
	public void beforeclass() {
		LoginPage.driver = new FirefoxDriver();
		
	}
	
	@Test(priority = 1, alwaysRun = true)
	public void loginWithBlankCredentials()
	{
		LoginPage.driver.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		log_obj.signIn_button.click();
		Assert.assertEquals("The entered Username-password combination is incorrect.", log_obj.error_msg.getText());
	}
	
	@Test(priority = 2, alwaysRun = true)
	public void loginWithBlankUsername()
	{
		
		LoginPage.driver.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		LoginPage.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		log_obj.pswrd_field.clear();
		log_obj.pswrd_field.sendKeys("hell0");
		log_obj.signIn_button.click();
		Assert.assertEquals("The entered Username-password combination is incorrect.", log_obj.error_msg.getText());
	}
	
	@Test(priority = 3, alwaysRun = true)
	public void loginWithBlankPassowrd()
	{
		
		LoginPage.driver.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		LoginPage.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		log_obj.pswrd_field.clear();
		log_obj.usrnm_field.clear();
		log_obj.usrnm_field.sendKeys("jbertran");
		log_obj.signIn_button.click();
		Assert.assertEquals("The entered Username-password combination is incorrect.", log_obj.error_msg.getText());
	}
	
	@Test(priority = 4, alwaysRun = true)
	public void loginWithWrongCredentials()
	{
		
		LoginPage.driver.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		LoginPage.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		log_obj.usrnm_field.clear();
		log_obj.usrnm_field.sendKeys("xyz");
		log_obj.pswrd_field.clear();
		log_obj.pswrd_field.sendKeys("abc01");
		log_obj.signIn_button.click();
		Assert.assertEquals("The entered Username-password combination is incorrect.", log_obj.error_msg.getText());
	}
	
	@Test(priority = 5, alwaysRun = true)
	public void loginWithCorrectCredentials()
	{
		
		LoginPage.driver.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		LoginPage.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		log_obj.usrnm_field.clear();
		log_obj.usrnm_field.sendKeys("jbertran");
		log_obj.pswrd_field.clear();
		log_obj.pswrd_field.sendKeys("hell0");
		log_obj.signIn_button.click();
		Assert.assertEquals("Jerome Bertrand", LoginPage.driver.findElement(By.xpath("//a[@id='changePasswordLink']")).getText());
	}

}
