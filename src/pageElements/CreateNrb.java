package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNrb {

	public WebElement NRB_tab;
	public WebElement NRB_tab_label;
	public WebElement create_NRB_button;
	public WebElement nrb_label;
	public WebElement nrb_desc;
	public WebElement nrb_init_class;
	public WebElement nrb_date;
	public WebElement nrb_create_modal;
	
	
	public WebElement nrb_desc_errMsg;
	public WebElement nrb_init_class_errMsg;
	public WebElement nrb_date_errMsg;
	
	public WebDriverWait wait = new WebDriverWait(LoginPage.driver_login, 100);
	
	public void switch_nrb_tab() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='ncr-tab3' and span[contains(text(),'NRBs')]]")));
		NRB_tab = LoginPage.driver_login.findElement(By.xpath("//a[@id='ncr-tab3' and span[contains(text(),'NRBs')]]"));
		NRB_tab.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ncb-details-fieldset']/div[@class='legend']")));
		NRB_tab_label = LoginPage.driver_login.findElement(By.xpath("//div[@id='ncb-details-fieldset']/div[@class='legend']"));
	}
	
	public void click_create_nrb() throws InterruptedException {
		//LoginPage.driver_login.switchTo().activeElement();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-nrb-btn")));
		Thread.sleep(5000);
		create_NRB_button = LoginPage.driver_login.findElement(By.id("create-nrb-btn"));
		
		create_NRB_button.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='nrbCreateContainer']/div[@class='legend']")));
		nrb_label = LoginPage.driver_login.findElement(By.xpath("//div[@id='nrbCreateContainer']/div[@class='legend']"));
	}
	
	public void enter_desc(String desc) {
		LoginPage.driver_login.switchTo().activeElement();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nrbDescriptionEditTextarea")));
		nrb_desc = LoginPage.driver_login.findElement(By.id("nrbDescriptionEditTextarea"));
		nrb_desc.clear();
		nrb_desc.sendKeys(desc);
	}
	
	public void select_init_class(String init_class) {
		LoginPage.driver_login.switchTo().activeElement();
		if (LoginPage.driver_login.findElements(By.id("nrbDetailsForm_nrb_initiatorType")).size() > 0){
		nrb_init_class = LoginPage.driver_login.findElement(By.id("nrbDetailsForm_nrb_initiatorType"));
		}
		else {
			nrb_init_class = LoginPage.driver_login.findElement(By.id("nrb_initiatorType"));
		}
		Select dropDown = new Select(nrb_init_class);
		if(init_class.equalsIgnoreCase("major")) {
			dropDown.selectByValue("2");
		}
		else if(init_class.equalsIgnoreCase("minor")) {
			dropDown.selectByValue("1");
		}
		else {
			dropDown.selectByValue("");
		}
	}
	
	public void enter_date(String date) {
		LoginPage.driver_login.switchTo().activeElement();
		nrb_date = LoginPage.driver_login.findElement(By.id("nrbDatePicker"));
		nrb_date.clear();
		nrb_date.sendKeys(date);
	}
	
	public void click_create() {
		LoginPage.driver_login.switchTo().activeElement();
		nrb_create_modal = LoginPage.driver_login.findElement(By.id("create-nrb"));
		nrb_create_modal.click();
	}
	
	public String get_errMsg_desc() {
		LoginPage.driver_login.switchTo().activeElement();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wwerr_nrbDescriptionEditTextarea")));
		nrb_desc_errMsg = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_nrbDescriptionEditTextarea']/div"));
		String errMsg = nrb_desc_errMsg.getText();
		return errMsg;
	}
	
	public String get_errMsg_initClass() {
		LoginPage.driver_login.switchTo().activeElement();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wwerr_nrb_initiatorType")));
		nrb_init_class_errMsg = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_nrb_initiatorType']/div"));
		String errMsg = nrb_init_class_errMsg.getText();
		return errMsg;
	}
	
	public String get_errMsg_date() {
		LoginPage.driver_login.switchTo().activeElement();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wwerr_nrbDatePicker")));
		nrb_date_errMsg = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_nrbDatePicker']/div"));
		String errMsg = nrb_date_errMsg.getText();
		return errMsg;
	}
	
}
