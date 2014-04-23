package testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageElements.CreateNcrPage;
import pageElements.LoginPage;
import utilityMethods.Login_Eclipse;

public class CreateNCR_TC {
	
	public CreateNcrPage createNCR_obj;
	public Login_Eclipse login_obj;
	
	@BeforeClass
	@Parameters({"browser"})
	public void beforeClass(@Optional("firefox") String browser) throws IOException{
		
		login_obj = new Login_Eclipse(browser);
		login_obj.login();
		Assert.assertEquals("Auto Eclipse", LoginPage.driver_login.findElement(By.xpath("//a[@id='changePasswordLink']")).getText());
		
		LoginPage.driver_login.switchTo().frame(LoginPage.driver_login.findElement(By.id("iframe-encts")));
		//System.out.println("Switched to iframe-encts");
		
		createNCR_obj = new CreateNcrPage();
	
	}
	
	@Test(priority = 1, alwaysRun = true)
	public void Create_NCR_withBlankFields() throws InterruptedException {
		createNCR_obj.create_new_NCR();
		//LoginPage.driver_login.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(LoginPage.driver_login, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Create NCR')]")));
		Assert.assertEquals("Create NCR", createNCR_obj.get_pageTitle());
		createNCR_obj.click_create();
		Assert.assertEquals("Title is required", createNCR_obj.get_error_message_title());
		Assert.assertEquals("Reference is required", createNCR_obj.get_error_message_reference());
		Assert.assertEquals("Description is required", createNCR_obj.get_error_message_description());
		Assert.assertEquals("PT Items is required", createNCR_obj.get_error_message_PTitems());
		Assert.assertEquals("Revision is required", createNCR_obj.get_error_message_revision());
		Assert.assertEquals("JM2 TA1 is required.", createNCR_obj.get_error_message_newTextArea());
	}
	
	
	@Test(priority = 2, alwaysRun = true)
	public void Create_NCR_withWhiteSpaces() {
		createNCR_obj.enter_title("   ");
		createNCR_obj.enter_reference("   ");
		createNCR_obj.enter_description("   ");
		createNCR_obj.enter_revision("   ");
		createNCR_obj.enter_newTextArea("   ");
		createNCR_obj.click_create();
		Assert.assertEquals("Title is required", createNCR_obj.get_error_message_title());
		Assert.assertEquals("Reference is required", createNCR_obj.get_error_message_reference());
		Assert.assertEquals("Description is required", createNCR_obj.get_error_message_description());
		Assert.assertEquals("PT Items is required", createNCR_obj.get_error_message_PTitems());
		Assert.assertEquals("Revision is required", createNCR_obj.get_error_message_revision());
		Assert.assertEquals("JM2 TA1 is required.", createNCR_obj.get_error_message_newTextArea());
		
	}
	
	@Test(priority = 3, alwaysRun = true)
	public void Create_NCR_withSymbols() {
		createNCR_obj.enter_title("!@#$%^&*()_+");
		createNCR_obj.enter_reference("!@#$%^&*()_+"+ (int) (Math.random() * 99999));
		createNCR_obj.enter_description("!@#$%^&*()_+");
		createNCR_obj.enter_revision("!@#$%^&*()_+");
		createNCR_obj.select_PTItem("Part-1");
		createNCR_obj.enter_newTextArea("!@#$%^&*()_+");
		createNCR_obj.click_create();
		LoginPage.driver_login.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertEquals("Edit NCR", createNCR_obj.get_pageTitle());
		
	}
	
	@Test(priority = 4, alwaysRun = true)
	public void Create_NCR_withRepeatedReference() throws InterruptedException {
		createNCR_obj.create_new_NCR();
		createNCR_obj.enter_title("Testing");
		createNCR_obj.enter_reference("Test");
		createNCR_obj.enter_description("Testing Create NCR");
		createNCR_obj.enter_revision("1.0");
		createNCR_obj.select_PTItem("Part-1","Part-2","P-1a");
		createNCR_obj.enter_newTextArea("Testing testing testing...");
		createNCR_obj.click_create();
		Assert.assertEquals("NCR Test already exists.", createNCR_obj.get_error_message_reference());
		
	}
	
	@Test(priority = 5, alwaysRun = true)
	public void Create_NCR_withValidData() {
		createNCR_obj.enter_title("Testing");
		createNCR_obj.enter_reference("test_ref_"+ (int) (Math.random() * 99999));
		createNCR_obj.enter_description("Testing Create NCR");
		createNCR_obj.enter_revision("1.0");
		createNCR_obj.enter_newTextArea("Testing testing testing...");
		createNCR_obj.click_create();
		LoginPage.driver_login.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertEquals("Edit NCR", createNCR_obj.get_pageTitle());
	}
	
	

}
