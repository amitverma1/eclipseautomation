package testCases;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageElements.CreateNcrPage;
import pageElements.LoginPage;
import utilityMethods.login_eclipse;

public class CreateNCR_TC {
	
	public CreateNcrPage createNCR_obj;
	public login_eclipse login_obj;
	
	@BeforeClass
	public void beforeclass() {
		
		login_obj = new login_eclipse();
		login_obj.login();
		LoginPage.driver_login.switchTo().frame(LoginPage.driver_login.findElement(By.id("iframe-encts")));
		
		createNCR_obj = new CreateNcrPage();
	
	}
	
	@Test
	public void create_ncr() {
		
		createNCR_obj.create_new_NCR();
		createNCR_obj.enter_title("Testing");
		createNCR_obj.enter_reference("test_ref_"+ (int) (Math.random() * 99999));
		createNCR_obj.enter_description("Testing Create NCR");
		createNCR_obj.enter_revision("1.0");
		createNCR_obj.select_PTItem("DMU Software","Coaxial","Module");
		createNCR_obj.enter_newTextArea("Testing testing testing...");
		createNCR_obj.click_create();
		
	}

}
