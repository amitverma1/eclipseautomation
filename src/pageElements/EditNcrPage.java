package pageElements;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditNcrPage {
	
	public WebElement pageTitle;
	public WebElement title;
	public WebElement reference;
	public WebElement description;
	public WebElement revision;
	public WebElement selectPT;
	public WebElement newTextArea;
	public WebElement save_button;
	
	public WebElement error_message_title;
	public WebElement error_message_reference;
	public WebElement error_message_description;
	public WebElement error_message_revision;
	public WebElement error_message_newTextArea;
	
	public WebDriverWait wait = new WebDriverWait(LoginPage.driver_login, 100);
	
	
	public String get_pageTitle() {
		String pageTitle = LoginPage.driver_login.findElement(By.xpath("//div[@id='newPageTitle']/h1")).getText();
		return pageTitle;
	}
	
	public void enter_title(String titleNCR){
		title = LoginPage.driver_login.findElement(By.id("ncrDetailsForm_ncr_title"));
		title.clear();
		title.sendKeys(titleNCR);
	}
	
	public void enter_reference(String referenceNCR){
		reference = LoginPage.driver_login.findElement(By.id("ncrDetailsForm_ncr_reference"));
		reference.clear();
		reference.sendKeys(referenceNCR);
	}
	
	public void enter_description(String descriptionNCR){
		description = LoginPage.driver_login.findElement(By.id("ncrDescriptionOnDetailsPage"));
		description.clear();
		description.sendKeys(descriptionNCR);
	}
	
	public void enter_revision(String revisionNCR){
		revision = LoginPage.driver_login.findElement(By.id("ncrDetailsForm_ncr_revision"));
		revision.clear();
		revision.sendKeys(revisionNCR);
	}
	
	public void select_PTItem(String... PT_Item_texts) throws AWTException, InterruptedException{
		
		LoginPage.driver_login.findElement(By.xpath("//td[@id='selectedPtItemList']/a")).click();
		LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		LoginPage.driver_login.findElement(By.xpath("//div[@id='tree']//a[contains(text(),'Part-1')]")).click();
		Thread.sleep(500);
		LoginPage.driver_login.findElement(By.xpath("//div[@id='tree']//a[contains(text(),'Part-2')]")).click();
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		LoginPage.driver_login.findElement(By.id("choose-pt-item-button")).click();
	}
	
	public void enter_newTextArea(String newTextAreaNCR){
		newTextArea = LoginPage.driver_login.findElement(By.id("ncrDetailsForm_ncr_metadata_metadata_1385"));
		newTextArea.clear();
		newTextArea.sendKeys(newTextAreaNCR);
	}
	
	
	public void click_save_update() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Save']")));
		save_button = LoginPage.driver_login.findElement(By.xpath("//input[@value='Save']"));
		save_button.sendKeys(Keys.RETURN);
	}
	
	public String get_error_message_title() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='wwerr_ncrDetailsForm_ncr_title']/div")));
		String err_title = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_ncrDetailsForm_ncr_title']/div")).getText();
		return err_title;
		
	}
	
	public String get_error_message_reference() {
		String err_reference = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_ncrDetailsForm_ncr_reference']/div")).getText();
		return err_reference;
		
	}
	
	public String get_error_message_description() {
		String err_description = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_ncrDescriptionOnDetailsPage']/div")).getText();
		return err_description;
		
	}
	
	public String get_error_message_revision() {
		String err_revision = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_ncrDetailsForm_ncr_revision']/div")).getText();
		return err_revision;
		
	}
	
	
	public String get_error_message_newTextArea() {
		String err_newTextArea = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_ncrDetailsForm_ncr_metadata_metadata_1385']/div")).getText();
		return err_newTextArea;
		
	}

}
