package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageElements.LoginPage;
import pageElements.WelcomeFrame;
import utilityMethods.Login_Eclipse;

public class LogoutTC {
	
	WelcomeFrame frame_obj;
	Login_Eclipse login_obj;
	
	@BeforeClass
	public void beforeClass(){
	
		login_obj = new Login_Eclipse();
		login_obj.login();
		
		LoginPage.driver_login.switchTo().defaultContent();
		frame_obj = new WelcomeFrame();
	}
	
  @Test
  public void logout() {
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
