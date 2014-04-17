package utilityMethods;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import pageElements.LoginPage;
import pageElements.WelcomeFrame;

public class Login_Eclipse {
	
	public LoginPage log_obj;
	public WelcomeFrame wframe_obj;
	
	
	
	public Login_Eclipse(String browser) throws IOException {
		
		//LoginPage.driver_login = new FirefoxDriver();
		
		
		if (browser.equalsIgnoreCase("ie")) {
			// For IE
			System.setProperty("webdriver.ie.driver", ".//src/drivers/IEDriverServer.exe");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
	        LoginPage.driver_login = new InternetExplorerDriver(caps);
	        Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 2");
	        LoginPage.driver_login.manage().deleteAllCookies();
			}
			
			else if (browser.equalsIgnoreCase("chrome")) {
			
			// For Chrome
			System.setProperty("webdriver.chrome.driver", ".//src/drivers/chromedriver.exe");
			LoginPage.driver_login = new ChromeDriver();
			
			}
			
			else if (browser.equalsIgnoreCase("firefox")) {
			
			// For Firefox
				LoginPage.driver_login = new FirefoxDriver();
				
			}
			
			else {
				
				// For Firefox
				LoginPage.driver_login = new FirefoxDriver();
			}
		
		
		
		LoginPage.driver_login.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		LoginPage.driver_login.manage().window().maximize();
		
		log_obj = new LoginPage();
		wframe_obj = new WelcomeFrame();
		
		
	}
	
	/*public Login_Eclipse() {
		LoginPage.driver_login = new FirefoxDriver();
		
		LoginPage.driver_login.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
		LoginPage.driver_login.manage().window().maximize();
		
		log_obj = new LoginPage();
		wframe_obj = new WelcomeFrame();
	}*/
	
	public void login() {
		
		log_obj.enter_usernm("selenium");
		log_obj.enter_pswrd("hell0");
		log_obj.signIn();
		Assert.assertEquals("Auto Eclipse", LoginPage.driver_login.findElement(By.xpath("//a[@id='changePasswordLink']")).getText());
		wframe_obj.switch_project();
		wframe_obj.switch_encts();
	}

	public void logout() {
		wframe_obj.click_logout();
		Assert.assertEquals("Please sign in. Enter your username and password.", LoginPage.driver_login.findElement(By.xpath("//div[@class='formContainer centreText']/p")).getText());
	}

	
}
