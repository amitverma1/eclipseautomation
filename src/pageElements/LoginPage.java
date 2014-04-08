package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage {

	public static WebDriver driver_login;
	
	public WebElement usrnm_field;
	public WebElement pswrd_field;
	public WebElement signIn_button;
	public WebElement error_msg;
	
	
	public void enter_usernm(String uname) {
		usrnm_field = driver_login.findElement(By.className("usernameField"));
		usrnm_field.clear();
		usrnm_field.sendKeys(uname);
	}
	
	public void enter_pswrd(String Pwrd) {
		pswrd_field = driver_login.findElement(By.className("pswrdField"));
		pswrd_field.clear();
		pswrd_field.sendKeys(Pwrd);
	}
	
	public void signIn() {
		signIn_button = driver_login.findElement(By.className("primarySubmitButton"));
		signIn_button.click();
	}
	
	public String getErrorMsg(){
		
		error_msg = driver_login.findElement(By.xpath("//div[@class='message error']/p"));
		String errMsg = error_msg.getText();
		return errMsg;
		
	}
}
