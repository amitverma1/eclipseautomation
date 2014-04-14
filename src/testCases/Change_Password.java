package testCases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageElements.LoginPage;
import pageElements.WelcomeFrame;
import utilityMethods.Login_Eclipse;

public class Change_Password {
	
	WelcomeFrame frame_obj;
	Login_Eclipse login_obj;
	
	
	@BeforeClass
	public void beforeClass(){
	
		login_obj = new Login_Eclipse();
		login_obj.login();
		
		LoginPage.driver_login.switchTo().defaultContent();
		frame_obj = new WelcomeFrame();
	}
	
	@Test(priority=1, alwaysRun=true)
	public void saveWithoutPassword(){
		frame_obj.click_username_link();
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		frame_obj.click_save_password();
		
	//=============Assertions ===========//
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		Assert.assertEquals(frame_obj.get_cur_pwd_err(), "Current Password is required");
		Assert.assertEquals(frame_obj.get_new_pwd_err(), "New Password is required");
		Assert.assertEquals(frame_obj.get_confirm_pwd_err(), "Confirm Password is required");
		
	}
	
	
	
	

}
