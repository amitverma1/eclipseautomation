package testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageElements.LoginPage;
import pageElements.WelcomeFrame;
import utilityMethods.Login_Eclipse;

public class LogoutTC {
	
	WelcomeFrame frame_obj;
	Login_Eclipse login_obj;
	
	@BeforeClass
	@Parameters({"browser"})
	public void beforeClass(@Optional("ie") String browser) throws IOException{
	
		login_obj = new Login_Eclipse(browser);
		login_obj.login();
		
		//LoginPage.driver_login.switchTo().defaultContent();
		frame_obj = new WelcomeFrame();
	}
	
  @Test
  public void logout() throws InterruptedException {
	Thread.sleep(5000);
	  frame_obj.click_logout();
	  
	//=============Assertions ===========//
	  LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
	  Assert.assertEquals("Please sign in. Enter your username and password.", LoginPage.driver_login.findElement(By.xpath("//div[@class='formContainer centreText']/p")).getText());
	  
  }
  
  @AfterClass
	public void afterclass() {
		LoginPage.driver_login.quit();
	}
  
}
