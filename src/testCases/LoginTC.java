
package testCases;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;




import pageElements.LoginPage;

public class LoginTC {

	public LoginPage log_obj;
	
	@BeforeClass
	@Parameters({"browser"})
	public void beforeClass(@Optional("ie") String browser) throws IOException{
		//LoginPage.driver_login = new FirefoxDriver();
		
		
		if (browser.equalsIgnoreCase("ie")) {
			// For IE
			System.setProperty("webdriver.ie.driver", ".//src/drivers/IEDriverServer.exe");
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer(); 
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	        LoginPage.driver_login = new InternetExplorerDriver(ieCapabilities);
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
		log_obj = new LoginPage();
		
	}
	
	@Test(priority = 1, alwaysRun = true)
	public void loginWithBlankCredentials() throws IOException
	{
		log_obj.signIn();
		Assert.assertEquals("The entered Username-password combination is incorrect.", log_obj.getErrorMsg());
		
	}
	
	@Test(priority = 2, alwaysRun = true)
	public void loginWithBlankUsername()
	{
		
		log_obj.enter_pswrd("hell0");
		log_obj.signIn();
		Assert.assertEquals("The entered Username-password combination is incorrect.", log_obj.getErrorMsg());
		
	}
	
	@Test(priority = 3, alwaysRun = true)
	public void loginWithBlankPassowrd()
	{
		
		log_obj.enter_pswrd("");
		log_obj.enter_usernm("jbertran");
		log_obj.signIn();
		Assert.assertEquals("The entered Username-password combination is incorrect.", log_obj.getErrorMsg());
	}
	
	@Test(priority = 4, alwaysRun = true)
	public void loginWithWrongCredentials()
	{
		
		log_obj.enter_usernm("xyz");
		log_obj.enter_pswrd("abc01");
		log_obj.signIn();
		Assert.assertEquals("The entered Username-password combination is incorrect.", log_obj.getErrorMsg());
	}
	
	@Test(priority = 5, alwaysRun = true)
	public void loginWithCorrectCredentials()
	{
		log_obj.enter_usernm("selenium");
		log_obj.enter_pswrd("hell0");
		log_obj.signIn();
		Assert.assertEquals("Auto Eclipse", LoginPage.driver_login.findElement(By.xpath("//a[@id='changePasswordLink']")).getText());
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void catchExceptions(ITestResult result){
	    Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	    String methodName = result.getName();
	    String className;
	    Class<?> enclosingClass = getClass().getEnclosingClass();
        if (enclosingClass != null) {
        	className = enclosingClass.getName();
        } else {
        	className = getClass().getName();
        }
	    if(!result.isSuccess()){
	        File scrFile = ((TakesScreenshot)LoginPage.driver_login).getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(scrFile, new File(".\\screenshot\\"+className+ "_" +methodName+"_"+formater.format(calendar.getTime())+".png"));
	            
	            System.out.println("Screenshot captured.");
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	}
	
	@AfterClass
	public void afterclass() {
		LoginPage.driver_login.quit();
	}

}
