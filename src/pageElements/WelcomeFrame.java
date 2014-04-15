package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WelcomeFrame {
	
// ============ Top Frame Elements ======================= //
	public WebElement project_dropdown;
	public WebElement logout;
	public WebElement username_link;
	public WebElement encts_tab;
	public WebElement aim_tab;
	
	public WebElement jail_tab;		
	public WebElement pam_tab;
	public WebElement cal_tab;
	
// ======================== Change Password Modal window Attributes=================Username Link====================  //
	
	public WebElement change_password_modal_label;
	public WebElement change_password_save_button;
	public WebElement current_password_modal;
	public WebElement new_password_modal;
	public WebElement confirm_password_modal;
	
	public WebElement current_password_err;
	public WebElement new_password_err;
	public WebElement confirm_password_err;
	public WebElement wrong_old_password_err;
	public WebElement new_password_mismatch_err;
	public WebElement policy_help_text;
	
	
	
// Switching Project to another project through ID //
	public void switch_project(){
		LoginPage.driver_login.switchTo().defaultContent();
		project_dropdown = LoginPage.driver_login.findElement(By.xpath("//[@id='paramSwitcherId']"));
	
		Select select = new Select(project_dropdown);
		select.selectByValue("1020");
		LoginPage.driver_login.switchTo().frame(LoginPage.driver_login.findElement(By.id("iframe-encts")));
	
	}
	
	
	public void click_username_link(){
		username_link = LoginPage.driver_login.findElement(By.xpath("//a[@id='changePasswordLink']"));
		username_link.click();
		
	}

	
	public void click_logout(){
		
		logout = LoginPage.driver_login.findElement(By.linkText("Logout"));
		logout.click();
		
	}
	
// Switching to Another Project //
	
	public void switch_encts(){
		
		encts_tab = LoginPage.driver_login.findElement(By.xpath("//[@href='#tabContent-encts']/span"));
		encts_tab.click();
		
	}
	
	public void switch_aim(){
		
		aim_tab = LoginPage.driver_login.findElement(By.xpath("//[@href='#tabContent-aim']/span"));
		aim_tab.click();
		
	}
	
	public void switch_pam(){
		
		pam_tab = LoginPage.driver_login.findElement(By.xpath("//[@href='#tabContent-pam']/span"));
		pam_tab.click();
		
	}
	

//  ============ Change Password Modal Window Methods ====== //
	public void set_current_password(String current_password){
		//LoginPage.driver_login.switchTo().activeElement();
		current_password_modal = LoginPage.driver_login.findElement(By.xpath("//input[@id='changePasswordForm_currentPassword']"));
		current_password_modal.clear();
		current_password_modal.sendKeys(current_password);
		
	}
	
	
    public void set_new_password(String new_password){
		
		new_password_modal = LoginPage.driver_login.findElement(By.xpath("//input[@id='changePasswordForm_newPassword']"));
		new_password_modal.clear();
		new_password_modal.sendKeys(new_password);
		
	}
	
    public void set_confirm_password(String new_password){
		
		confirm_password_modal = LoginPage.driver_login.findElement(By.xpath("//input[@id='changePasswordForm_confirmPassword']"));
		confirm_password_modal.clear();
		confirm_password_modal.sendKeys(new_password);
		
	}
	
    public void click_save_password(){
    	//LoginPage.driver_login.switchTo().activeElement();
  		change_password_save_button = LoginPage.driver_login.findElement(By.xpath("//input[@id='updatePasswordBtn']"));
  		change_password_save_button.click();
  		
  	}
	
	public String get_cur_pwd_err(){
		//LoginPage.driver_login.switchTo().activeElement();
		current_password_err = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_changePasswordForm_currentPassword']/div"));
		String cur_err = current_password_err.getText();
		
		return cur_err;
		
	}
	
	public String get_new_pwd_err(){
		//LoginPage.driver_login.switchTo().activeElement();
		new_password_err = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_changePasswordForm_newPassword']/div"));
		String new_err = new_password_err.getText();
		
		return new_err;
		
	}
		
	
	public String get_confirm_pwd_err(){
		//LoginPage.driver_login.switchTo().activeElement();
		confirm_password_err = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_changePasswordForm_confirmPassword']/div"));
		String confirm_err = confirm_password_err.getText();
		
		return confirm_err;
		
	}
	

	public String get_pwd_err(){
		//LoginPage.driver_login.switchTo().activeElement();
		wrong_old_password_err = LoginPage.driver_login.findElement(By.xpath("//ul[@id='changePasswordForm_null']/li/span"));
		String old_err = wrong_old_password_err.getText();
		
		return old_err;
		
	}
	
/*============================= Can be merged to "get_old_pwd_err" method =================== 
	public String get_new_pwd_mismatch_err(){
		
		new_password_mismatch_err = LoginPage.driver_login.findElement(By.xpath("//ul[@id='changePasswordForm_null']/li/span"));
		String new_mismatch_err = new_password_mismatch_err.getText();
		
		return new_mismatch_err;
		
	}*/
		
	
	
	public String get_policy_help_text(){
		
		policy_help_text = LoginPage.driver_login.findElement(By.xpath("//ul[@id='changePasswordForm_null']/li/span"));
		String policy_help = policy_help_text.getText();
		
		return policy_help;
		
	}
		

}
