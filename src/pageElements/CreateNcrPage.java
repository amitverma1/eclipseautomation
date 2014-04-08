package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateNcrPage {
	
	public static WebDriver driver_create_ncr;
	
	public WebElement createNewNcr;
	public WebElement title;
	public WebElement reference;
	public WebElement description;
	public WebElement revision;
	public WebElement selectPT;
	public WebElement create;
	
	public WebElement error_message_title;
	public WebElement error_message_reference;
	public WebElement error_message_description;
	public WebElement error_message_revision;
	public WebElement error_message_PTitems;
	
	
	public CreateNcrPage(){
		
		createNewNcr = driver_create_ncr.
		titleNCR_field = driver_create_ncr.findElement(By.id("ncrDetailsForm_ncr_titlencrDetailsForm_ncr_title"));
		referenceNCR_field = driver_create_ncr.findElement(By.id("ncrDetailsForm_ncr_reference"));
		descriptionNCR_field = driver_create_ncr.findElement(By.id("ncrDescriptionOnDetailsPage"));
		revisionNCR_field = driver_create_ncr.findElement(By.id("ncrDetailsForm_ncr_revision"));
	//	selectPT_Link = driver_create_ncr.findElement(By.name("ncr.ptItems"));
		selectPT_Link = driver_create_ncr.findElement(By.linkText("Select"));
		createNCR_button = driver_create_ncr.findElement(By.id("ncrDetailsForm_form_button_create"));
		
		error_message_title = driver_create_ncr.findElement(By.xpath("//*[@id='wwerr_ncrDetailsForm_ncr_title']/div"));
		error_message_reference = driver_create_ncr.findElement(By.xpath("//*[@id='wwerr_ncrDetailsForm_ncr_title']/div"));
		error_message_description = driver_create_ncr.findElement(By.xpath("//*[@id='wwerr_ncrDescriptionOnDetailsPage']/div"));
		error_message_revision = driver_create_ncr.findElement(By.xpath("//*[@id='wwerr_ncrDetailsForm_ncr_revision']/div"));
		error_message_PTitems = driver_create_ncr.findElement(By.xpath("//*[@id='entityTypeErrorDiv']"));
		
		
		
	}
	
	
	public void enter_title(String titleNCR){
		titleNCR_field.clear();
		titleNCR_field.sendKeys(titleNCR);
	}
	
	public void enter_reference(String referenceNCR){
		referenceNCR_field.clear();
		referenceNCR_field.sendKeys(referenceNCR);
	}
	
	public void enter_decription(String decriptionNCR){
		descriptionNCR_field.clear();
		descriptionNCR_field.sendKeys(decriptionNCR);
	}
	
	public void enter_revision(String revisionNCR){
		revisionNCR_field.clear();
		revisionNCR_field.sendKeys(revisionNCR);
	}
	
	/*public void enter_title(String titleNCR){
		titleNCR_field.clear();
		titleNCR_field.sendKeys(titleNCR);
	}
	*/
	
	
	

}
