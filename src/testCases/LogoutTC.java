package testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageElements.LoginPage;
import pageElements.WelcomeFrame;
import utilityMethods.Login_Eclipse;

public class LogoutTC {
	
	WelcomeFrame frame_obj;
	Login_Eclipse login_obj;
	
	@BeforeClass
	@Parameters({"browser"})
	public void beforeClass(@Optional("ie") String browser) throws IOException{
	
		login_obj = new Login_Eclipse(browser);
		login_obj.login();
		
		//LoginPage.driver_login.switchTo().defaultContent();
		frame_obj = new WelcomeFrame();
	}
	
  @Test
  public void logout() throws InterruptedException {
	Thread.sleep(5000);
	  frame_obj.click_logout();
	  
	//=============Assertions ===========//
	  LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
	  Assert.assertEquals("Please sign in. Enter your username and password.", LoginPage.driver_login.findElement(By.xpath("//div[@class='formContainer centreText']/p")).getText());
	  
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
