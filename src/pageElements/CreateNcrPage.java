package pageElements;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class CreateNcrPage {
	
	public WebElement NCRMenu;
	public WebElement createNewNcr;
	public WebElement title;
	public WebElement reference;
	public WebElement description;
	public WebElement revision;
	public WebElement selectPT;
	public WebElement newTextArea;
	public WebElement create_button;
	
	public WebElement error_message_title;
	public WebElement error_message_reference;
	public WebElement error_message_description;
	public WebElement error_message_revision;
	public WebElement error_message_PTitems;
	public WebElement error_message_newTextArea;
	
	
	
	public void create_new_NCR() {
		
		NCRMenu = LoginPage.driver_login.findElement(By.xpath("//ul[@class='sf-menu']/li[1]/a"));
		createNewNcr = LoginPage.driver_login.findElement(By.xpath("//ul[@class='sf-menu']/li[1]/ul/li[4]/a"));
		NCRMenu.click();
		createNewNcr.click();
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
	
	public void select_PTItem(String... PT_Item_texts){
		
		selectPT = LoginPage.driver_login.findElement(By.xpath("//td[@id='selectedPtItemList']/a"));
		selectPT.click();
		
	//	LoginPage.driver_login.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Actions multiplePTItems = new Actions(LoginPage.driver_login);
		
		for (String text : PT_Item_texts){
			WebElement PT_item = LoginPage.driver_login.findElement(By.xpath("//div[@id='tree']//a[contains(text(),'" + text + "')]"));
			multiplePTItems.keyDown(Keys.CONTROL)
				.click(PT_item)
				.keyUp(Keys.CONTROL);
			Action selectMultiple = multiplePTItems.build();
			selectMultiple.perform();
		}
		
		LoginPage.driver_login.findElement(By.id("choose-pt-item-button")).click();
	}
	
	public void enter_newTextArea(String newTextAreaNCR){
		newTextArea = LoginPage.driver_login.findElement(By.id("ncrDetailsForm_ncr_metadata_metadata_1383"));
		newTextArea.clear();
		newTextArea.sendKeys(newTextAreaNCR);
	}
	
	public void click_create() {
		create_button = LoginPage.driver_login.findElement(By.id("ncrDetailsForm_form_button_create"));
		create_button.click();
		
	}
	
	public String get_error_message_title() {
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
	
	public String get_error_message_PTitems() {
		String err_PTitems = LoginPage.driver_login.findElement(By.xpath("//div[@id='entityTypeErrorDiv']")).getText();
		return err_PTitems;
		
	}
	
	public String get_error_message_newTextArea() {
		String err_newTextArea = LoginPage.driver_login.findElement(By.xpath("//div[@id='wwerr_ncrDetailsForm_ncr_metadata_metadata_1383']/div")).getText();
		return err_newTextArea;
		
	}
	
	

}
