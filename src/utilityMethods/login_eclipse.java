package utilityMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import pageElements.LoginPage;

public class login_eclipse {
	
	public WebDriver driver;
	public LoginPage log_obj;
	
	public login_eclipse() {
		
		driver = new FirefoxDriver();
		driver.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		log_obj = new LoginPage();
		
	}
	
	public void login() {
		
		log_obj.enter_usernm("jbertran");
		log_obj.enter_pswrd("hell0");
		log_obj.signIn();
		Assert.assertEquals("Jerome Bertrand", LoginPage.driver_login.findElement(By.xpath("//a[@id='changePasswordLink']")).getText());
	}

}
