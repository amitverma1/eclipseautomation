package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageElements.CreateNcrPage;
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
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iframe-encts")));
		LoginPage.driver_login.switchTo().frame(LoginPage.driver_login.findElement(By.id("iframe-encts")));
		System.out.println("Switched to iframe-encts");
		
		
		nrb_obj = new CreateNrb();
		
		//createNcr_obj.create_new_NCR();
		
		
	}
	
	@Test(priority = 1, alwaysRun = true)
	public void create_new_ncr() throws InterruptedException {
		
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("create")));
		LoginPage.driver_login.findElement(By.className("create")).click();
		
	}
	
	
	
	@Test(priority = 1, alwaysRun = true)
	public void switch_tab_nrb() {
		nrb_obj.switch_nrb_tab();
		Assert.assertEquals("List NRBs", nrb_obj.NRB_tab_label.getText());
		
	}
	
	@Test(priority = 2, alwaysRun = true)
	public void createNRB_withBlankFields() {
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
	public void createNRB_MajorInternal() {
		Assert.assertEquals("Internal NRB Details", nrb_obj.nrb_label.getText());
		nrb_obj.enter_desc("Major Internal");
		nrb_obj.enter_date("25-04-2014");
		nrb_obj.select_init_class("Major");
		nrb_obj.click_create();
		
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ncb-details-fieldset']/div[@class='legend']")));
		Assert.assertEquals("List NRBs", nrb_obj.NRB_tab_label.getText());
	}
	
	@Test(priority = 7, alwaysRun = true)
	public void createNRB_CustomerNrb() {
		Assert.assertEquals("Customer NRB Details", nrb_obj.nrb_label.getText());
		nrb_obj.enter_desc("Customer NRB");
		nrb_obj.enter_date("25-04-2014");
		nrb_obj.click_create();
		
		nrb_obj.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ncb-details-fieldset']/div[@class='legend']")));
		Assert.assertEquals("List NRBs", nrb_obj.NRB_tab_label.getText());
	}
	
  
}
