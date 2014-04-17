package pageElements;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WelcomeFrame {
	
// ============ Top Frame Elements ======================= //
	public WebElement project_dropdown;
	public WebElement logout;
	public WebElement username_link;
	public WebElement encts_tab;
	public WebElement aim_tab;
	
	public WebElement jail_tab;		
	public WebElement pam_tab;
	//public WebElement cal_tab;
	
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
	//public WebElement policy_help_text;
	
	
	
	public String get_modalWindow_label() {
		change_password_modal_label = LoginPage.driver_login.findElement(By.className("legend"));
		String label = change_password_modal_label.getText();
		return label;
	}
	
	
// Switching Project to another project through ID //
	public void switch_project(){
		project_dropdown = LoginPage.driver_login.findElement(By.xpath("//select[@id='paramSwitcherId']"));
	
		Select select = new Select(project_dropdown);
		select.selectByValue("1020");
		
	}
	
	
	public void click_username_link(){
		LoginPage.driver_login.switchTo().activeElement();
		// *** Implicit Wait is not working here. So, implementing explicit wait. ***
		WebDriverWait wait_err_msg = new WebDriverWait(LoginPage.driver_login, 50);
		wait_err_msg.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='changePasswordLink']")));
		username_link = LoginPage.driver_login.findElement(By.xpath("//a[@id='changePasswordLink']"));
		username_link.click();
		
	}

	
	public void click_logout(){
		
		logout = LoginPage.driver_login.findElement(By.xpath("//li[@id='logoutLink']/a[2]"));
		logout.click();
		
	}
	
// Switching to Another Project //
	
	public void switch_encts(){
		
		encts_tab = LoginPage.driver_login.findElement(By.xpath("//li[@id='tab-encts']/a"));
		encts_tab.click();
		
	}
	
	public void switch_aim(){
		
		aim_tab = LoginPage.driver_login.findElement(By.xpath("//li[@id='tab-aim']/a"));
		aim_tab.click();
		
	}
	
	public void switch_pam(){
		
		pam_tab = LoginPage.driver_login.findElement(By.xpath("//li[@id='tab-pam']/a"));
		pam_tab.click();
		
	}
	
	public void switch_jail(){
		
		jail_tab = LoginPage.driver_login.findElement(By.xpath("//li[@id='tab-jail']/a"));
		jail_tab.click();
		
	}
	

//  ============ Change Password Modal Window Methods ====== //
	public void set_current_password(String current_password){
		LoginPage.driver_login.switchTo().activeElement();
		// *** Implicit Wait is not working here. So, implementing explicit wait. ***
		WebDriverWait wait_err_msg = new WebDriverWait(LoginPage.driver_login, 50);
		wait_err_msg.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='changePasswordForm_currentPassword']")));
		current_password_modal = LoginPage.driver_login.findElement(By.xpath("//input[@id='changePasswordForm_currentPassword']"));
		current_password_modal.clear();
		current_password_modal.sendKeys(current_password);
	}
	
	
    public void set_new_password(String new_password){
    	LoginPage.driver_login.switchTo().activeElement();
		new_password_modal = LoginPage.driver_login.findElement(By.xpath("//input[@id='changePasswordForm_newPassword']"));
		new_password_modal.clear();
		new_password_modal.sendKeys(new_password);
	}
	
    public void set_confirm_password(String new_password){
    	LoginPage.driver_login.switchTo().activeElement();
		confirm_password_modal = LoginPage.driver_login.findElement(By.xpath("//input[@id='changePasswordForm_confirmPassword']"));
		confirm_password_modal.clear();
		confirm_password_modal.sendKeys(new_password);
	}
	
    public void click_save_password(){
    	LoginPage.driver_login.switchTo().activeElement();
    	change_password_save_button = LoginPage.driver_login.findElement(By.xpath("//input[@id='updatePasswordBtn']"));
  		change_password_save_button.click();
  	}
	
	public String get_cur_pwd_err(){
		LoginPage.driver_login.switchTo().activeElement();
		WebDriverWait wait = new WebDriverWait(LoginPage.driver_login, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='wwerr_changePasswordForm_currentPassword']/div")));
		current_password_err = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_changePasswordForm_currentPassword']/div"));
		String cur_err = current_password_err.getText();
		
		return cur_err;
		
	}
	
	public String get_new_pwd_err(){
		LoginPage.driver_login.switchTo().activeElement();
		new_password_err = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_changePasswordForm_newPassword']/div"));
		String new_err = new_password_err.getText();
		
		return new_err;
		
	}
		
	
	public String get_confirm_pwd_err(){
		LoginPage.driver_login.switchTo().activeElement();
		confirm_password_err = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_changePasswordForm_confirmPassword']/div"));
		String confirm_err = confirm_password_err.getText();
		
		return confirm_err;
		
	}
	

	public String get_pwd_err(){
		LoginPage.driver_login.switchTo().activeElement();
		wrong_old_password_err = LoginPage.driver_login.findElement(By.xpath("//ul[@id='changePasswordForm_null']/li/span"));
		String old_err = wrong_old_password_err.getText();
		
		return old_err;
		
	}
	
	public void handle_accept_alert() {
		WebDriverWait wait = new WebDriverWait(LoginPage.driver_login, 5);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = LoginPage.driver_login.switchTo().alert();
			String AlertText = alert.getText();
			alert.accept();
			Assert.assertEquals("Password has been changed successfully.",AlertText);
			} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	
	
	// Will be implemented later
	/*public String get_policy_help_text(){
		
		policy_help_text = LoginPage.driver_login.findElement(By.xpath("//ul[@id='changePasswordForm_null']/li/span"));
		String policy_help = policy_help_text.getText();
		
		return policy_help;
		
	}*/
		

}
