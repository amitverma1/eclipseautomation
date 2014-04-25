package testCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageElements.CreateNrb;
import pageElements.LoginPage;
import utilityMethods.Login_Eclipse;

public class CreateNrb_TC {
	
	public Login_Eclipse login_obj;
	public CreateNrb nrb_obj;
	
	@BeforeClass
	@Parameters({"browser"})
	public void beforeClass(@Optional("firefox") String browser) throws IOException, InterruptedException{
		
		login_obj = new Login_Eclipse(browser);
		login_obj.login();
		LoginPage.driver_login.switchTo().frame(LoginPage.driver_login.findElement(By.id("iframe-encts")));
		System.out.println("Switched to iframe-encts");
		
		
		nrb_obj = new CreateNrb();
		
		//createNcr_obj.create_new_NCR();
		
		
	}
	
	@Test(priority = 1, alwaysRun = true)
	public void create_new_ncr() throws InterruptedException, AWTException {
		
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("create")));
		LoginPage.driver_login.findElement(By.className("create")).click();
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ncrDetailsForm_ncr_title")));
		LoginPage.driver_login.findElement(By.id("ncrDetailsForm_ncr_title")).sendKeys("Testing");
		LoginPage.driver_login.findElement(By.id("ncrDetailsForm_ncr_reference")).sendKeys("test_ref_"+ (int) (Math.random() * 99999));
		LoginPage.driver_login.findElement(By.id("ncrDescriptionOnDetailsPage")).sendKeys("Testing Create NCR");
		LoginPage.driver_login.findElement(By.id("ncrDetailsForm_ncr_revision")).sendKeys("1.0");
		LoginPage.driver_login.findElement(By.xpath("//td[@id='selectedPtItemList']/a")).click();
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		LoginPage.driver_login.findElement(By.xpath("//div[@id='tree']//a[contains(text(),'Part-1')]")).click();
		Thread.sleep(500);
		LoginPage.driver_login.findElement(By.xpath("//div[@id='tree']//a[contains(text(),'Part-2')]")).click();
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		LoginPage.driver_login.findElement(By.id("choose-pt-item-button")).click();
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ncrDetailsForm_ncr_metadata_metadata_1385")));
		LoginPage.driver_login.findElement(By.id("ncrDetailsForm_ncr_metadata_metadata_1385")).sendKeys("Testing testing testing...");
		LoginPage.driver_login.findElement(By.id("ncrDetailsForm_form_button_create")).click();
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='legend' and contains(text(),'NCR Details')]")));
	}
	
	
	
	@Test(priority = 1, alwaysRun = true)
	public void switch_tab_nrb() {
		nrb_obj.switch_nrb_tab();
		Assert.assertEquals("List NRBs", nrb_obj.NRB_tab_label.getText());
		
	}
	
	@Test(priority = 2, alwaysRun = true)
	public void createNRB_withBlankFields() throws InterruptedException {
		nrb_obj.click_create_nrb();
		Assert.assertEquals("Internal NRB Details", nrb_obj.nrb_label.getText());
		nrb_obj.enter_desc("");
		nrb_obj.enter_date("");
		nrb_obj.click_create();
		
		Assert.assertEquals("Description is required", nrb_obj.get_errMsg_desc());
		Assert.assertEquals("Initiator is required", nrb_obj.get_errMsg_initClass());
		Assert.assertEquals("NRB date is required", nrb_obj.get_errMsg_date());
	}
	
	@Test(priority = 3, alwaysRun = true)
	public void createNRB_withWhiteSpaces() {
		Assert.assertEquals("Internal NRB Details", nrb_obj.nrb_label.getText());
		nrb_obj.enter_desc("   ");
		nrb_obj.enter_date("   ");
		nrb_obj.click_create();
		
		Assert.assertEquals("Description is required", nrb_obj.get_errMsg_desc());
		Assert.assertEquals("Initiator is required", nrb_obj.get_errMsg_initClass());
		Assert.assertEquals("NRB date is required", nrb_obj.get_errMsg_date());
	}
	
	@Test(priority = 4, alwaysRun = true)
	public void createNRB_withRandomSymbols() {
		Assert.assertEquals("Internal NRB Details", nrb_obj.nrb_label.getText());
		nrb_obj.enter_desc("!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?");
		nrb_obj.enter_date("!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?");
		nrb_obj.click_create();
		
		Assert.assertEquals("Initiator is required", nrb_obj.get_errMsg_initClass());
		Assert.assertEquals("NRB Date format is invalid", nrb_obj.get_errMsg_date());
	}
	
	@Test(priority = 5, alwaysRun = true)
	public void createNRB_MinorInternal() {
		Assert.assertEquals("Internal NRB Details", nrb_obj.nrb_label.getText());
		nrb_obj.enter_desc("Minor Internal");
		nrb_obj.enter_date("25-04-2014");
		nrb_obj.select_init_class("Minor");
		nrb_obj.click_create();
		
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ncb-details-fieldset']/div[@class='legend']")));
		Assert.assertEquals("List NRBs", nrb_obj.NRB_tab_label.getText());
	}
	
	@Test(priority = 6, alwaysRun = true)
	public void createNRB_MajorInternal() throws InterruptedException {
		nrb_obj.click_create_nrb();
		Assert.assertEquals("Internal NRB Details", nrb_obj.nrb_label.getText());
		nrb_obj.enter_desc("Major Internal");
		nrb_obj.enter_date("25-04-2014");
		nrb_obj.select_init_class("Major");
		nrb_obj.click_create();
		
		LoginPage.driver_login.switchTo().activeElement();
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ncb-details-fieldset']/div[@class='legend']")));
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ncb-details-fieldset']/div[@class='legend']")));
		nrb_obj.NRB_tab_label = LoginPage.driver_login.findElement(By.xpath("//div[@id='ncb-details-fieldset']/div[@class='legend']"));
		Assert.assertEquals("List NRBs", nrb_obj.NRB_tab_label.getText());
	}
	
	@Test(priority = 7, alwaysRun = true)
	public void createNRB_CustomerNrb() throws InterruptedException {
		nrb_obj.click_create_nrb();
		Assert.assertEquals("Customer NRB Details", nrb_obj.nrb_label.getText());
		nrb_obj.enter_desc("Customer NRB");
		nrb_obj.enter_date("25-04-2014");
		nrb_obj.click_create();
		
		LoginPage.driver_login.switchTo().activeElement();
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ncb-details-fieldset']/div[@class='legend']")));
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ncb-details-fieldset']/div[@class='legend']")));
		nrb_obj.NRB_tab_label = LoginPage.driver_login.findElement(By.xpath("//div[@id='ncb-details-fieldset']/div[@class='legend']"));
		Assert.assertEquals("List NRBs", nrb_obj.NRB_tab_label.getText());
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
