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
	
	public LoginPage()	{
		
		usrnm_field = driver_login.findElement(By.className("usernameField"));
		pswrd_field = driver_login.findElement(By.className("pswrdField"));
		signIn_button = driver_login.findElement(By.className("primarySubmitButton"));
		error_msg = driver_login.findElement(By.xpath("//div[@class='message error']/p"));
	}
	
	public void enter_usernm(String uname) {
		usrnm_field.clear();
		usrnm_field.sendKeys(uname);
	}
	
	public void enter_pswrd(String Pwrd) {
		pswrd_field.clear();
		pswrd_field.sendKeys(Pwrd);
	}
	
	public void signIn() {
		signIn_button.click();
	}
}
