package testCases;

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
		LoginPage.driver_login = new FirefoxDriver();
		LoginPage.driver_login.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		log_obj = new LoginPage();
		
	}
	
	@Test(priority = 1, alwaysRun = true)
	public void loginWithBlankCredentials()
	{
		//LoginPage.driver_login.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		log_obj.signIn();
		Assert.assertEquals("The entered Username-password combination is incorrect.", log_obj.getErrorMsg());
	}
	
	@Test(priority = 2, alwaysRun = true)
	public void loginWithBlankUsername()
	{
		
		//LoginPage.driver_login.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		//LoginPage.driver_login.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		log_obj.enter_pswrd("hell0");
		log_obj.signIn();
		Assert.assertEquals("The entered Username-password combination is incorrect.", log_obj.getErrorMsg());
	}
	
	@Test(priority = 3, alwaysRun = true)
	public void loginWithBlankPassowrd()
	{
		
		//LoginPage.driver_login.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		//LoginPage.driver_login.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		log_obj.enter_pswrd("");
		log_obj.enter_usernm("jbertran");
		log_obj.signIn();
		Assert.assertEquals("The entered Username-password combination is incorrect.", log_obj.getErrorMsg());
	}
	
	@Test(priority = 4, alwaysRun = true)
	public void loginWithWrongCredentials()
	{
		
		//LoginPage.driver_login.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		//LoginPage.driver_login.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		log_obj.enter_usernm("xyz");
		log_obj.enter_pswrd("abc01");
		log_obj.signIn();
		Assert.assertEquals("The entered Username-password combination is incorrect.", log_obj.getErrorMsg());
	}
	
	@Test(priority = 5, alwaysRun = true)
	public void loginWithCorrectCredentials()
	{
		
		//LoginPage.driver_login.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		//LoginPage.driver_login.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		log_obj.enter_usernm("jbertran");
		log_obj.enter_pswrd("hell0");
		log_obj.signIn();
		Assert.assertEquals("Jerome Bertrand", LoginPage.driver_login.findElement(By.xpath("//a[@id='changePasswordLink']")).getText());
	}
	

}
