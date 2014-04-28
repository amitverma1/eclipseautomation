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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageElements.EditNcrPage;
import pageElements.LoginPage;
import utilityMethods.Login_Eclipse;

public class EditNcr_TC {

	public Login_Eclipse login_obj;
	public EditNcrPage editNcr_obj;
	
	@BeforeClass
	@Parameters({"browser"})
	public void beforeClass(@Optional("firefox") String browser) throws IOException, InterruptedException{
		
		login_obj = new Login_Eclipse(browser);
		login_obj.login();
		LoginPage.driver_login.switchTo().frame(LoginPage.driver_login.findElement(By.id("iframe-encts")));
		System.out.println("Switched to iframe-encts");
		
		
		editNcr_obj = new EditNcrPage();
		
	}
	
	@Test(priority = 1, alwaysRun = true)
	public void create_new_ncr() throws InterruptedException, AWTException {
		
		editNcr_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("create")));
		LoginPage.driver_login.findElement(By.className("create")).click();
		editNcr_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ncrDetailsForm_ncr_title")));
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
		editNcr_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ncrDetailsForm_ncr_metadata_metadata_1385")));
		LoginPage.driver_login.findElement(By.id("ncrDetailsForm_ncr_metadata_metadata_1385")).sendKeys("Testing testing testing...");
		LoginPage.driver_login.findElement(By.id("ncrDetailsForm_form_button_create")).click();
		editNcr_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='legend' and contains(text(),'NCR Details')]")));
	}
	
	@Test(priority = 2, alwaysRun = true)
	public void Edit_NCR_withBlankFields() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(LoginPage.driver_login, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Edit NCR')]")));
		Assert.assertEquals("Edit NCR", editNcr_obj.get_pageTitle());
		editNcr_obj.enter_title("");
		editNcr_obj.enter_reference("");
		editNcr_obj.enter_description("");
		editNcr_obj.enter_revision("");
		editNcr_obj.enter_newTextArea("");
		editNcr_obj.click_save_update();
		LoginPage.driver_login.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertEquals("Title is required", editNcr_obj.get_error_message_title());
		Assert.assertEquals("Reference is required", editNcr_obj.get_error_message_reference());
		Assert.assertEquals("Description is required", editNcr_obj.get_error_message_description());
		Assert.assertEquals("Revision is required", editNcr_obj.get_error_message_revision());
		Assert.assertEquals("JM2 TA1 is required.", editNcr_obj.get_error_message_newTextArea());
		
	}
	
	@Test(priority = 3, alwaysRun = true)
	public void Edit_NCR_withWhiteSpaces() {
		editNcr_obj.enter_title("   ");
		editNcr_obj.enter_reference("   ");
		editNcr_obj.enter_description("   ");
		editNcr_obj.enter_revision("   ");
		editNcr_obj.enter_newTextArea("   ");
		editNcr_obj.click_save_update();
		LoginPage.driver_login.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertEquals("Title is required", editNcr_obj.get_error_message_title());
		Assert.assertEquals("Reference is required", editNcr_obj.get_error_message_reference());
		Assert.assertEquals("Description is required", editNcr_obj.get_error_message_description());
		Assert.assertEquals("Revision is required", editNcr_obj.get_error_message_revision());
		Assert.assertEquals("JM2 TA1 is required.", editNcr_obj.get_error_message_newTextArea());
		
	}
	
	@Test(priority = 4, alwaysRun = true)
	public void Edit_NCR_withRandomSymbols() {
		editNcr_obj.enter_title("!@#$%^&*()_+");
		editNcr_obj.enter_reference("!@#$%^&*()_+"+ (int) (Math.random() * 99999));
		editNcr_obj.enter_description("!@#$%^&*()_+");
		editNcr_obj.enter_revision("!@#$%^&*()_+");
		editNcr_obj.enter_newTextArea("!@#$%^&*()_+");
		editNcr_obj.click_save_update();
		WebDriverWait wait1 = new WebDriverWait(LoginPage.driver_login, 60);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='updateNCRSuccess']/ul/li/span")));
		Assert.assertEquals("Saved Successfully", LoginPage.driver_login.findElement(By.xpath("//div[@id='updateNCRSuccess']/ul/li/span")).getText());
		
	}
	
	@Test(priority = 5, alwaysRun = true)
	public void Edit_NCR_withValidData() {
		editNcr_obj.enter_title("Edited Testing");
		editNcr_obj.enter_reference("edited test_ref_"+ (int) (Math.random() * 99999));
		editNcr_obj.enter_description("Edited Testing Create NCR");
		editNcr_obj.enter_revision("Edited 1.0");
		editNcr_obj.enter_newTextArea("Edited Testing testing testing...");
		editNcr_obj.click_save_update();
		WebDriverWait wait1 = new WebDriverWait(LoginPage.driver_login, 60);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='updateNCRSuccess']/ul/li/span")));
		Assert.assertEquals("Saved Successfully", LoginPage.driver_login.findElement(By.xpath("//div[@id='updateNCRSuccess']/ul/li/span")).getText());
		
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
