package testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageElements.LoginPage;
import pageElements.WelcomeFrame;
import utilityMethods.Login_Eclipse;

public class Change_Password_TC {
	
	WelcomeFrame frame_obj;
	Login_Eclipse login_obj;
	
	
	@BeforeClass
	@Parameters({"browser"})
	public void beforeClass(String browser) throws IOException{
	
		login_obj = new Login_Eclipse(browser);
		login_obj.login();
		
		LoginPage.driver_login.switchTo().defaultContent();
		frame_obj = new WelcomeFrame();
	}
	
	@Test(priority=1, alwaysRun=true)
	public void saveWithoutPassword(){
		
		frame_obj.click_username_link();
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		Assert.assertEquals("Change Password", frame_obj.get_modalWindow_label());
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		frame_obj.click_save_password();
		
	//=============Assertions ===========//
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		Assert.assertEquals(frame_obj.get_cur_pwd_err(), "Current Password is required");
		Assert.assertEquals(frame_obj.get_new_pwd_err(), "New Password is required");
		Assert.assertEquals(frame_obj.get_confirm_pwd_err(), "Confirm Password is required");
		
	}
	
	@Test(priority=2, alwaysRun=true)
	public void saveWithWrongCurrentPassword(){
		frame_obj.set_current_password("hell1");
		frame_obj.set_new_password("hell2");
		frame_obj.set_confirm_password("hell2");
		frame_obj.click_save_password();
		
	//=============Assertions ===========//
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		Assert.assertEquals(frame_obj.get_pwd_err(), "Old password value is incorrect. Please check capslock");
		
	}
	
	@Test(priority=3, alwaysRun=true)
	public void saveWithBlankCurrentPassword(){
		frame_obj.set_current_password("");
		frame_obj.set_new_password("hell2");
		frame_obj.set_confirm_password("hell2");
		frame_obj.click_save_password();
		
	//=============Assertions ===========//
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		Assert.assertEquals(frame_obj.get_cur_pwd_err(), "Current Password is required");
		
	}
	
	@Test(priority=4, alwaysRun=true)
	public void saveWithWhiteSpacesAllFields(){
		frame_obj.set_current_password("   ");
		frame_obj.set_new_password("   ");
		frame_obj.set_confirm_password("   ");
		frame_obj.click_save_password();
		
	//=============Assertions ===========//
		// *** Implicit Wait is not working here. So, implementing explicit wait. ***
		//LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		WebDriverWait wait_err_msg = new WebDriverWait(LoginPage.driver_login, 60);
		wait_err_msg.until(ExpectedConditions.visibilityOfElementLocated(By.id("wwerr_changePasswordForm_currentPassword")));
		Assert.assertEquals(frame_obj.get_cur_pwd_err(), "Current Password is required");
		Assert.assertEquals(frame_obj.get_new_pwd_err(), "New Password is required");
		Assert.assertEquals(frame_obj.get_confirm_pwd_err(), "Confirm Password is required");
		
	}
	
	@Test(priority=5, alwaysRun=true)
	public void saveWithMismatchNewConfirmPswrd(){
		frame_obj.set_current_password("hell0");
		frame_obj.set_new_password("hell1");
		frame_obj.set_confirm_password("hell2");
		frame_obj.click_save_password();
		
	//=============Assertions ===========//
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		Assert.assertEquals(frame_obj.get_pwd_err(), "Confirm password value does not match New password value");
	}
	
	@Test(priority=6, alwaysRun=true)
	public void saveWithRandomSymbols(){
		frame_obj.set_current_password("hell0");
		frame_obj.set_new_password("!@#$%");
		frame_obj.set_confirm_password("!@#$%");
		frame_obj.click_save_password();
		
		
	//=============Assertions ===========//
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		frame_obj.handle_accept_alert();
	}
	
	
	@Test(priority=7, alwaysRun=true)
	public void saveWithNumerics(){
		frame_obj.click_username_link();
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		frame_obj.set_current_password("!@#$%");
		frame_obj.set_new_password("12345");
		frame_obj.set_confirm_password("12345");
		frame_obj.click_save_password();
		
		
	//=============Assertions ===========//
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		frame_obj.handle_accept_alert();
	}
	
	@Test(priority=8, alwaysRun=true)
	public void saveWithValidCredentials(){
		frame_obj.click_username_link();
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		frame_obj.set_current_password("12345");
		frame_obj.set_new_password("hell0");
		frame_obj.set_confirm_password("hell0");
		frame_obj.click_save_password();
		
	//=============Assertions ===========//
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		frame_obj.handle_accept_alert();
	}
	
	@AfterClass
	public void afterclass() {
		LoginPage.driver_login.quit();
	}

}
