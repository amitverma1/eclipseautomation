package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.junit.Before;
import org.junit.Test;

public class CreateNCRPage {

	public static WebDriver driver;
	
	@Before
	public void beforeclass() {
		driver = new FirefoxDriver();
		driver.get("http://ndi-pc-410:8080/josso/signon/login.do?josso_back_to=/ilayout/i-layout");
	}
	
	@Test
	public void loginWithBlankCredentials()
	{
		driver.findElement(By.className("primarySubmitButton")).click();
	}

}
